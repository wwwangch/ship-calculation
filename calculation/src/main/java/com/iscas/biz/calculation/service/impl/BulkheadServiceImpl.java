package com.iscas.biz.calculation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.calculation.entity.db.Bulkhead;
import com.iscas.biz.calculation.entity.db.BulkheadCompartment;
import com.iscas.biz.calculation.mapper.BulkheadCompartmentMapper;
import com.iscas.biz.calculation.mapper.BulkheadMapper;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.service.BulkheadService;
import com.iscas.common.tools.office.excel.ExcelUtils;
import com.iscas.common.web.tools.file.FileDownloadUtils;
import com.iscas.datasong.connector.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    @Transactional(rollbackFor = Exception.class)
    public void saveCompartment(Object bulkheadFilePath) {
        if (null == bulkheadFilePath) {
            return;
        }
        String filePath = bulkheadFilePath.toString();
        // 判断上传的文件是否为excel
        if (!(filePath.endsWith(".xls") || filePath.endsWith("xlsx"))) {
            throw new RuntimeException("文件格式异常");
        }

        File file = new File(filePath);
        if (!file.exists())
            return;

        // 根据文件路径查找保存的横舱壁数据
        QueryWrapper<Bulkhead> bulkheadQueryWrapper = new QueryWrapper<>();
        bulkheadQueryWrapper.lambda().eq(Bulkhead::getBulkheadFilePath, filePath);
        List<Bulkhead> bulkheadList = this.list(bulkheadQueryWrapper);
        if (CollectionUtils.isEmpty(bulkheadList))
            return;

        Bulkhead bulkhead = bulkheadList.get(0);
        QueryWrapper<BulkheadCompartment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bulkhead_id", bulkhead.getBulkheadId());
        compartmentMapper.delete(queryWrapper);

        Map<String, List> resultMap = new HashMap<>();
        try {
            ExcelUtils.readExcelToListMap(filePath, resultMap);
            Thread.sleep(1000);
        } catch (ExcelUtils.ExcelHandlerException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        int index = 1;
        int num = 0;
        List<String> names = Lists.newArrayList();
        List<Number> heights = Lists.newArrayList();
        for (Map.Entry<String, List> entry : resultMap.entrySet()) {
            //valueList 每个元素代表一行数据
            List<Map<String, Object>> valueList = entry.getValue();
            Integer k1 = null;
            Double v1 = null;
            //map代表一行数据  key为头
            for (Map<String, Object> map : valueList) {
                if (map.size() != 2)
                    continue;
                List<Map.Entry<String, Object>> entryList = map.entrySet().stream().toList();
                if (k1 == null || v1 == null) {
                    // 获取第二个键值对
                    k1 = Double.valueOf(entryList.get(0).getValue().toString()).intValue();
                    v1 = Double.parseDouble(entryList.get(1).getValue().toString());
                } else {
                    Integer k2 = Double.valueOf(entryList.get(0).getValue().toString()).intValue();
                    Double v2 = Double.parseDouble(entryList.get(1).getValue().toString());
                    BulkheadCompartment bulkheadCompartment = new BulkheadCompartment();
                    bulkheadCompartment.setName("甲板" + index++);
                    bulkheadCompartment.setProjectId(bulkhead.getProjectId());
                    bulkheadCompartment.setBulkheadId(bulkhead.getBulkheadId());
                    bulkheadCompartment.setCompartment(k1 + "-" + k2);
                    bulkheadCompartment.setHeightAbove((v1 - v2));
                    compartmentMapper.insert(bulkheadCompartment);
                    num++;
                    k1 = k2;
                    v1 = v2;
                }
                names.add(String.valueOf(k1));
                heights.add(v1);
            }
        }

        bulkhead.setDeckNum(Optional.ofNullable(bulkhead.getDeckNum()).orElse(0) + num);
        bulkhead.setDeckNames(names);
        bulkhead.setDeckHeights(heights);
        this.updateById(bulkhead);
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

    /**
     * 下载模板
     */
    @Override
    public void downloadTemplate() throws IOException {
        FileDownloadUtils.downFile(SpringUtils.getRequest(), SpringUtils.getResponse(), ResourceUtils.getFile("classpath:bulkhead/templates/bulkhead.xlsx"), "舱壁高度模板.xlsx");
    }
}
