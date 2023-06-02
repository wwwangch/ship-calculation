package com.iscas.biz.calculation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.biz.calculation.entity.db.Bulkhead;
import com.iscas.biz.calculation.entity.db.BulkheadCompartment;
import com.iscas.biz.calculation.mapper.BulkheadCompartmentMapper;
import com.iscas.biz.calculation.mapper.BulkheadMapper;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.service.BulkheadService;
import com.iscas.common.tools.office.excel.ExcelUtils;
import com.iscas.datasong.connector.util.CollectionUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 14:09
 */
@Service
public class BulkheadServiceImpl extends ServiceImpl<BulkheadMapper, Bulkhead> implements BulkheadService {

    private final ProjectMapper projectMapper;
    private final BulkheadCompartmentMapper compartmentMapper;

    public BulkheadServiceImpl(ProjectMapper projectMapper, BulkheadCompartmentMapper compartmentMapper) {
        this.projectMapper = projectMapper;
        this.compartmentMapper = compartmentMapper;
    }

    @Override
    public Integer update(Bulkhead bulkhead) {
        Integer projectId = bulkhead.getProjectId();
        if (null == projectMapper.selectById(projectId)) {
            throw new RuntimeException(String.format("项目:[%s]不存在", projectId));
        }
        return this.updateById(bulkhead) ? 1 : 0;
    }

    @Override
    public void saveCompartment(Object bulkheadFilePath) {
        if (bulkheadFilePath != null) {
            String filePath = bulkheadFilePath.toString();
            // 判断上传的文件是否为excel
            if (filePath.endsWith(".xls") || filePath.endsWith(".XLS") || filePath.endsWith(".xlsx") || filePath.endsWith(".XLSX")) {
                File file = new File(filePath);
                if (file.exists()) {
                    // 根据文件路径查找保存的横舱壁数据
                    QueryWrapper<Bulkhead> bulkheadQueryWrapper = new QueryWrapper<>();
                    bulkheadQueryWrapper.lambda().eq(Bulkhead::getBulkheadFilePath, filePath);
                    List<Bulkhead> bulkheadList = this.list(bulkheadQueryWrapper);
                    if (CollectionUtils.isNotEmpty(bulkheadList)) {
                        Bulkhead bulkhead = bulkheadList.get(0);
                        Map<String, List> resultMap = new HashMap<>();
                        try {
                            ExcelUtils.readExcelToListMap(filePath, resultMap);
                        } catch (ExcelUtils.ExcelHandlerException e) {
                            throw new RuntimeException(e);
                        }
                        for (Map.Entry<String, List> entry : resultMap.entrySet()) {
                            List<Map<String, Object>> valueList = entry.getValue();
                            Integer k1 = null;
                            Double v1 = null;
                            for (Map<String, Object> map : valueList) {
                                if (map.size() == 2) {
                                    List<Map.Entry<String, Object>> entryList = map.entrySet().stream().collect(Collectors.toList());
                                    if (k1 == null || v1 == null) {
                                        // 获取第二个键值对
                                        k1 = Double.valueOf(entryList.get(0).getValue().toString()).intValue();
                                        v1 = Double.parseDouble(entryList.get(1).getValue().toString());
                                    } else {
                                        Integer k2 = Double.valueOf(entryList.get(0).getValue().toString()).intValue();
                                        Double v2 = Double.parseDouble(entryList.get(1).getValue().toString());
                                        BulkheadCompartment bulkheadCompartment = new BulkheadCompartment();
                                        bulkheadCompartment.setProjectId(bulkhead.getProjectId());
                                        bulkheadCompartment.setBulkheadId(bulkhead.getBulkheadId());
                                        bulkheadCompartment.setCompartment(k1 + "-" + k2);
                                        bulkheadCompartment.setHeightAbove("" + (v1 - v2));
                                        compartmentMapper.insert(bulkheadCompartment);
                                        k1 = k2;
                                        v1 = v2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Boolean deleteByIds(List<Integer> ids) {
        try {
            if (CollectionUtils.isNotEmpty(ids)) {
                QueryWrapper<BulkheadCompartment> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().in(BulkheadCompartment::getBulkheadId, ids);
                compartmentMapper.delete(queryWrapper);
                return this.removeByIds(ids);
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("删除剖面数据时异常", e);
        }
    }
}
