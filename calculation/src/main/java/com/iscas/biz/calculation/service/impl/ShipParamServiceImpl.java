package com.iscas.biz.calculation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.iscas.base.biz.service.fileserver.FileServerService;
import com.iscas.biz.calculation.entity.db.Project;
import com.iscas.biz.calculation.entity.db.ShipParam;
import com.iscas.biz.calculation.enums.CalculationSpecification;
import com.iscas.biz.calculation.enums.CheckType;
import com.iscas.biz.calculation.enums.NavigationArea;
import com.iscas.biz.calculation.enums.ShipType;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.ShipParamMapper;
import com.iscas.biz.calculation.service.ShipParamService;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.common.tools.core.date.DateSafeUtils;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.ComboboxData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 根据项目Id查询船舶参数
     *
     * @param projectId
     * @return
     */
    @Override
    @Transactional
    public ShipParam listByProjectId(Integer projectId) {
        QueryWrapper<ShipParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        List<ShipParam> shipParams = shipParamMapper.selectList(queryWrapper);

        if (CollectionUtils.isEmpty(shipParams)) {
            return null;
        }
        return shipParams.get(0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(Map<String, Object> shipParam) throws ValidDataException {
        checkParam(shipParam);
        QueryWrapper<ShipParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", shipParam.get("project_id"));
        shipParamMapper.delete(queryWrapper);
        ImmutableMap<String, Object> forceItem = ImmutableMap.of("create_time", DateSafeUtils.format(new Date()));
        tableDefinitionService.saveData(TABLE_IDENTITY, shipParam, true, ShipParam.class, forceItem);
        return 1;
    }

    @Override
    public int saveWithFile(ShipParam shipParam) {
//        MultipartFile paramFile = shipParam.getParamFile();
//        if (paramFile == null) {
//            throw new RuntimeException("配置文件不可为空");
//        }
//        try {
//            Map<String, String> upload = fileServerService.upload(new MultipartFile[]{paramFile});
//            shipParam.setParamFilePath(upload.get(paramFile.getOriginalFilename()));
//        } catch (IOException e) {
//            throw new RuntimeException("配置文件保存失败", e);
//        }
//
//        //解析文件
//        try {
//            String string = IOUtils.toString(paramFile.getInputStream(), StandardCharsets.UTF_8);
//            System.out.println(string);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return 0;
    }

    private void checkParam(Map<String, Object> shipParam) {
        if (null == shipParam) {
            return;
        }
        Integer projectId = (Integer) shipParam.get("project_id");
        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException(String.format("项目:[%s]不存在!!!", String.valueOf(projectId)));
        }

        //校验工况
        if (CalculationSpecification.COMMON_SPECIFICATION.equals(project.getCalculationSpecification())) {
            shipParam.put("displacement", null);
            shipParam.put("portrait_gravity", null);
            shipParam.put("cruising_displacement", null);
            shipParam.put("cruising_portrait_gravity", null);
        } else {
            Double extremeDisplacement = Double.parseDouble(shipParam.get("displacement").toString());
            Double extremePortraitGravity = Double.parseDouble(shipParam.get("portrait_gravity").toString());
            Double cruisingDisplacement = Double.parseDouble(shipParam.get("cruising_displacement").toString());
            Double cruisingPortraitGravity = Double.parseDouble(shipParam.get("cruising_portrait_gravity").toString());
            CheckType currentType = CheckType.getByValueStr((String) shipParam.get("current_type"));
            //校验为空
            if (null == cruisingDisplacement || null == cruisingPortraitGravity || null == extremeDisplacement || null == extremePortraitGravity || null == currentType) {
                throw new RuntimeException("当前校核准则校验工况必填");
            }
        }
    }

    @Override
    public int updateById(Map<String, Object> data) throws ValidDataException {
        if (null == data.get("param_id")) {
            throw new RuntimeException("更新时id不可为空");
        }
        checkParam(data);
        Integer projectId = (Integer) data.get("project_id");
        QueryWrapper<ShipParam> queryWrapper = Wrappers.emptyWrapper();
        queryWrapper.eq("project_id", projectId);
        List<ShipParam> shipParams = shipParamMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(shipParams)) {
            throw new RuntimeException("当前项目船舶参数不存在");
        }
        ImmutableMap<String, Object> forceItem = ImmutableMap.of("update_time", DateSafeUtils.format(new Date()));
        tableDefinitionService.saveData(TABLE_IDENTITY, data, false, ShipParam.class, forceItem);
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

    @Override
    public List<ComboboxData> listNavigationAreaCombobox() {
        List<ComboboxData> result = Lists.newArrayList();
        NavigationArea[] navigationAreas = NavigationArea.values();
        for (NavigationArea navigationArea : navigationAreas) {
            ComboboxData comboboxData = new ComboboxData();
            comboboxData.setLabel(navigationArea.getDescCH());
            comboboxData.setValue(navigationArea.getValue());
            result.add(comboboxData);
        }
        return result;

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void addCheckTypeCondition(QueryWrapper queryWrapper, Integer projectId) {
        Project project = projectMapper.selectById(projectId);
        //通用规范中是不区分工况的
        if (null == project || CalculationSpecification.COMMON_SPECIFICATION.equals(project.getCalculationSpecification())) {
            return;
        }
        ShipParam shipParam = this.listByProjectId(projectId);
        if (null == shipParam || null == shipParam.getCurrentType()) {
            throw new RuntimeException("船舶参数异常");
        }
        queryWrapper.eq("check_type", shipParam.getCurrentType().getValue());
    }

    @Override
    public void addCheckTypeCondition(UpdateWrapper queryWrapper, Integer projectId) {
        Project project = projectMapper.selectById(projectId);
        //通用规范中是不区分工况的
        if (null == project || CalculationSpecification.COMMON_SPECIFICATION.equals(project.getCalculationSpecification())) {
            return;
        }
        ShipParam shipParam = this.listByProjectId(projectId);
        if (null == shipParam || null == shipParam.getCurrentType()) {
            throw new RuntimeException("船舶参数异常");
        }
        queryWrapper.eq("check_type", shipParam.getCurrentType().getValue());
    }
}
