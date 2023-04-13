package com.iscas.biz.calculation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.iscas.base.biz.service.fileserver.FileServerService;
import com.iscas.biz.calculation.constant.RegularConstants;
import com.iscas.biz.calculation.entity.db.Project;
import com.iscas.biz.calculation.entity.db.ShipParam;
import com.iscas.biz.calculation.enums.CalculationSpecification;
import com.iscas.biz.calculation.enums.ShipType;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.ShipParamMapper;
import com.iscas.biz.calculation.service.ShipParamService;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.common.tools.core.date.DateSafeUtils;
import com.iscas.common.web.tools.json.JsonUtils;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.ComboboxData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/11 17:53
 */
@Service
@Slf4j
public class ShipParamServiceImpl implements ShipParamService {
    private final ShipParamMapper shipParamMapper;

    private final ProjectMapper projectMapper;

    private final FileServerService fileServerService;
    private final TableDefinitionService tableDefinitionService;

    private final static String TABLE_IDENTITY = "ship_param";


    public ShipParamServiceImpl(ShipParamMapper shipParamMapper, ProjectMapper projectMapper, FileServerService fileServerService, TableDefinitionService tableDefinitionService) {
        this.shipParamMapper = shipParamMapper;
        this.projectMapper = projectMapper;
        this.fileServerService = fileServerService;
        this.tableDefinitionService = tableDefinitionService;
    }

    @Override
    public int save(ShipParam shipParam) throws ValidDataException {
        checkParam(shipParam);
        TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {
        };
        Map<String, Object> data = JsonUtils.fromJson(JsonUtils.toJson(shipParam), typeReference);
        ImmutableMap<String, Object> forceItem = ImmutableMap.of("create_time", DateSafeUtils.format(new Date()));
        tableDefinitionService.saveData(TABLE_IDENTITY, data, false, null, null);
        return 1;
    }

    @Override
    public int saveWithFile(ShipParam shipParam) {
        MultipartFile paramFile = shipParam.getParamFile();
        if (paramFile == null) {
            throw new RuntimeException("配置文件不可为空");
        }
        try {
            Map<String, String> upload = fileServerService.upload(new MultipartFile[]{paramFile});
            shipParam.setParamFilePath(upload.get(paramFile.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException("配置文件保存失败", e);
        }

        //解析文件
        try {
            String string = IOUtils.toString(paramFile.getInputStream(), StandardCharsets.UTF_8);
            System.out.println(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    private void checkParam(ShipParam shipParam) {
        if (null == shipParam) {
            return;
        }
        Integer projectId = shipParam.getProjectId();
        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException(String.format("项目:[%s]不存在!!!", String.valueOf(projectId)));
        }
        if (CalculationSpecification.COMMON_SPECIFICATION.equals(project.getCalculationSpecification())) {
            shipParam.setCruisingDisplacement(null);
            shipParam.setCruisingPortraitGravity(null);
            shipParam.setExtremeDisplacement(null);
            shipParam.setExtremePortraitGravity(null);
        } else {
            Double cruisingDisplacement = shipParam.getCruisingDisplacement();
            String cruisingPortraitGravity = shipParam.getCruisingPortraitGravity();
            Double extremeDisplacement = shipParam.getExtremeDisplacement();
            String extremePortraitGravity = shipParam.getExtremePortraitGravity();
            //校验为空
            if (null == cruisingDisplacement || null == cruisingPortraitGravity || null == extremeDisplacement || null == extremePortraitGravity) {
                throw new RuntimeException("当前校核准则校验工况必填");
            }
            //校验位置是否合规
            if (!cruisingPortraitGravity.matches(RegularConstants.PORTRAIT_GRAVITY) ||
                    !extremePortraitGravity.matches(RegularConstants.PORTRAIT_GRAVITY)) {
                throw new RuntimeException("校验工况重心纵向位置格式异常");
            }
        }
    }

    @Override
    public int updateById(ShipParam shipParam) throws ValidDataException {
        if (null == shipParam.getParamId()) {
            throw new RuntimeException("更新时id不可为空");
        }
        checkParam(shipParam);
        Integer projectId = shipParam.getProjectId();
        QueryWrapper<ShipParam> queryWrapper = Wrappers.emptyWrapper();
        queryWrapper.eq("project_id", projectId);
        List<ShipParam> shipParams = shipParamMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(shipParams)) {
            throw new RuntimeException("当前项目船舶参数已经存在");
        }

        TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {
        };
        Map<String, Object> data = JsonUtils.fromJson(JsonUtils.toJson(shipParam), typeReference);
        ImmutableMap<String, Object> forceItem = ImmutableMap.of("update_time", DateSafeUtils.format(new Date()));
        tableDefinitionService.saveData(TABLE_IDENTITY, data, false, null, null);
        return 1;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        shipParamMapper.deleteBatchIds(ids);
        return true;
    }

    @Override
    public List<ComboboxData> listShipTypeCombobox() {
        List<ComboboxData> result = Lists.newArrayList();
        for (ShipType shipType : ShipType.values()) {
            ComboboxData comboboxData = new ComboboxData();
            comboboxData.setLabel(shipType.getValue());
            comboboxData.setValue(shipType.getValue());
            result.add(comboboxData);
        }
        return result;
    }
}
