package com.iscas.biz.calculation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.property.ColumnWidthProperty;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.calculation.entity.db.Moment;
import com.iscas.biz.calculation.entity.db.Project;
import com.iscas.biz.calculation.entity.db.Section;
import com.iscas.biz.calculation.entity.db.sigma.*;
import com.iscas.biz.calculation.entity.dto.sigma.Sigma1DTO;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.*;
import com.iscas.biz.calculation.service.StrengthService;
import com.iscas.biz.calculation.util.ExcelWidthStyleStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author yichuan@iscas.ac.cn
 * @Date 2023/6/1 9:08
 */
@Service
public class StrengthServiceImpl implements StrengthService {
    private final Sigma1Mapper sigma1Mapper;

    private final Sigma2Mapper sigma2Mapper;

    private final Sigma3Mapper sigma3Mapper;

    private final Sigma4Mapper sigma4Mapper;

    private final ShearingStressMapper shearingStressMapper;

    private final AlgorithmGrpc algorithmGrpc;

    private final SectionMapper sectionMapper;

    private final MomentMapper momentMapper;

    public StrengthServiceImpl(Sigma1Mapper sigma1Mapper, Sigma2Mapper sigma2Mapper, Sigma3Mapper sigma3Mapper, Sigma4Mapper sigma4Mapper, ShearingStressMapper shearingStressMapper, AlgorithmGrpc algorithmGrpc, SectionMapper sectionMapper, MomentMapper momentMapper) {
        this.sigma1Mapper = sigma1Mapper;
        this.sigma2Mapper = sigma2Mapper;
        this.sigma3Mapper = sigma3Mapper;
        this.sigma4Mapper = sigma4Mapper;
        this.shearingStressMapper = shearingStressMapper;
        this.algorithmGrpc = algorithmGrpc;
        this.sectionMapper = sectionMapper;
        this.momentMapper = momentMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Sigma1> getSigma1(Integer projectId, Integer sectionId) {
        QueryWrapper<Sigma1> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<Sigma1> sigma1List = sigma1Mapper.selectList(queryWrapper);
        return sigma1List;
    }

    @Override
    public List<Sigma2> getSigma2(Integer projectId, Integer sectionId) {
        QueryWrapper<Sigma2> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<Sigma2> sigma2List = sigma2Mapper.selectList(queryWrapper);
        return sigma2List;
    }

    @Override
    public List<Sigma3> getSigma3(Integer projectId, Integer sectionId) {
        QueryWrapper<Sigma3> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<Sigma3> sigma3List = sigma3Mapper.selectList(queryWrapper);
        return sigma3List;
    }

    @Override
    public List<Sigma4> getSigma4(Integer projectId, Integer sectionId) {
        QueryWrapper<Sigma4> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<Sigma4> sigma4List = sigma4Mapper.selectList(queryWrapper);
        return sigma4List;
    }

    @Override
    public List<ShearingStress> getShearingStress(Integer projectId, Integer sectionId) {
        QueryWrapper<ShearingStress> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<ShearingStress> shearingStressList = shearingStressMapper.selectList(queryWrapper);
        return shearingStressList;
    }

    @Override
    public List<Sigma1> calSigma1(Integer projectId, Integer sectionId) throws IllegalAccessException {
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        if (null == sectionId) {
            throw new RuntimeException("参数[sectionId]不可为空");
        }
        QueryWrapper<Section> sectionQueryWrapper = new QueryWrapper();
        sectionQueryWrapper.eq("project_id", projectId);
        sectionQueryWrapper.eq("section_id", sectionId);
        Section section = sectionMapper.selectOne(sectionQueryWrapper);
        Sigma1DTO sigma1DTO = new Sigma1DTO();
        QueryWrapper<Moment> momentQueryWrapper = new QueryWrapper<>();
        momentQueryWrapper.eq("project_id", projectId);
        momentQueryWrapper.eq("section_id", sectionId);
        Moment moment = momentMapper.selectOne(momentQueryWrapper);
        //复制属性
        copyFields(section, sigma1DTO);
        copyFields(moment, sigma1DTO);
        List<Sigma1> calSigma1List = algorithmGrpc.calSigma1(sigma1DTO);
        //清空该项目该剖面历史数据
        UpdateWrapper<Sigma1> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_id", projectId);
        updateWrapper.eq("section_id", sectionId);
        sigma1Mapper.delete(updateWrapper);
        //插入新数据
        for (int i = 0; i < calSigma1List.size(); i++) {
            sigma1Mapper.insert(calSigma1List.get(i));
        }
        return calSigma1List;
    }


    @Override
    public List<Sigma2> calSigma2(Integer projectId, Integer sectionId) throws IllegalAccessException {
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        if (null == sectionId) {
            throw new RuntimeException("参数[sectionId]不可为空");
        }
        List<Sigma2> calSigma2List = algorithmGrpc.calSigma2(projectId, sectionId);
        //清空该项目该剖面历史数据
        UpdateWrapper<Sigma2> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_id", projectId);
        updateWrapper.eq("section_id", sectionId);
        sigma2Mapper.delete(updateWrapper);
        //插入新数据
        for (int i = 0; i < calSigma2List.size(); i++) {
            sigma2Mapper.insert(calSigma2List.get(i));
        }
        return calSigma2List;
    }

    @Override
    public List<Sigma3> calSigma3(Integer projectId, Integer sectionId) throws IllegalAccessException {
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        if (null == sectionId) {
            throw new RuntimeException("参数[sectionId]不可为空");
        }
        List<Sigma3> calSigma3List = algorithmGrpc.calSigma3(projectId, sectionId);
        //清空该项目该剖面历史数据
        UpdateWrapper<Sigma3> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_id", projectId);
        updateWrapper.eq("section_id", sectionId);
        sigma3Mapper.delete(updateWrapper);
        //插入新数据
        for (int i = 0; i < calSigma3List.size(); i++) {
            sigma3Mapper.insert(calSigma3List.get(i));
        }
        return calSigma3List;
    }

    @Override
    public List<Sigma4> calSigma4(Integer projectId, Integer sectionId) throws IllegalAccessException {
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        if (null == sectionId) {
            throw new RuntimeException("参数[sectionId]不可为空");
        }
        List<Sigma4> calSigma4List = algorithmGrpc.calSigma4(projectId, sectionId);
        //清空该项目该剖面历史数据
        UpdateWrapper<Sigma4> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_id", projectId);
        updateWrapper.eq("section_id", sectionId);
        sigma4Mapper.delete(updateWrapper);
        //插入新数据
        for (int i = 0; i < calSigma4List.size(); i++) {
            sigma4Mapper.insert(calSigma4List.get(i));
        }
        return calSigma4List;
    }

    @Override
    public List<ShearingStress> calShearingStress(Integer projectId, Integer sectionId) throws IllegalAccessException {
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        if (null == sectionId) {
            throw new RuntimeException("参数[sectionId]不可为空");
        }
        List<ShearingStress> calShearingStressList = algorithmGrpc.calShearingStress(projectId, sectionId);
        //清空该项目该剖面历史数据
        UpdateWrapper<ShearingStress> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_id", projectId);
        updateWrapper.eq("section_id", sectionId);
        shearingStressMapper.delete(updateWrapper);
        //插入新数据
        for (int i = 0; i < calShearingStressList.size(); i++) {
            shearingStressMapper.insert(calShearingStressList.get(i));
        }
        return calShearingStressList;
    }

    public static <T, U> void copyFields(T sourceObj, U destObj) throws IllegalAccessException {
        Field[] sourceFields = sourceObj.getClass().getDeclaredFields();
        Field[] destFields = destObj.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            String sourceFieldName = sourceField.getName();
            Object sourceFieldValue = sourceField.get(sourceObj);

            for (Field destField : destFields) {
                destField.setAccessible(true);
                String destFieldName = destField.getName();
                Object destFieldValue = destField.get(destObj);

                if (sourceFieldName.equals(destFieldName) &&
                        sourceFieldValue != null &&
                        sourceFieldValue.getClass().equals(destField.getType())) {
                    destField.set(destObj, sourceFieldValue);
                }
            }
        }
    }


    @Override
    public void sigma1Export(Integer projectId, Integer sectionId) throws IOException {
        QueryWrapper<Sigma1> sigma1QueryWrapper = new QueryWrapper<>();
        sigma1QueryWrapper.eq("project_id", projectId);
        sigma1QueryWrapper.eq("section_id", sectionId);
        List<Sigma1> sigma1List = sigma1Mapper.selectList(sigma1QueryWrapper);
        if (null == sigma1List || sigma1List.size() == 0) {
            throw new RuntimeException("当前数据不存在!");
        }
        List<List<String>> headList = new ArrayList<>();
        if (sigma1List.size() > 0) {
            List<String> head0 = new ArrayList<>();
            head0.add("");
            headList.add(head0);
            List<String> head1 = new ArrayList<>();
            head1.add("中拱");
            headList.add(head1);
            List<String> head2 = new ArrayList<>();
            head2.add("中垂");
            headList.add(head2);
        }

        List<Sigma1Export> sigma1ExportList = new ArrayList<>();
        if (sigma1List.size() > 0) {
            Sigma1Export xuyong = new Sigma1Export();
            xuyong.setName("许用应力");
            xuyong.setZhonggong(sigma1List.get(0).getAllowStress());
            xuyong.setZhongchui(sigma1List.get(0).getAllowStress());
            sigma1ExportList.add(xuyong);
        }

        for (int i = 0; i < sigma1List.size(); i++) {
            int number = i + 1;
            Sigma1Export sigma1Export1 = new Sigma1Export();
            sigma1Export1.setName("龙骨" + number + "上纤维（σ1）");
            sigma1Export1.setZhonggong(sigma1List.get(i).getSigma1HUp());
            sigma1Export1.setZhongchui(sigma1List.get(i).getSigma1SUp());
            Sigma1Export sigma1Export2 = new Sigma1Export();
            sigma1Export2.setName("龙骨" + number + "下纤维（σ1）");
            sigma1Export2.setZhonggong(sigma1List.get(i).getSigma1Down());
            sigma1Export2.setZhongchui(sigma1List.get(i).getSigma1SDown());
            sigma1ExportList.add(sigma1Export1);
            sigma1ExportList.add(sigma1Export2);
        }
        EasyExcel.write(SpringUtils.getResponse().getOutputStream(), Sigma1Export.class)
                .head(headList).sheet("0").registerWriteHandler(new ExcelWidthStyleStrategy()).doWrite(sigma1ExportList);
    }

    private static class Sigma1Export {
        @ExcelProperty("")
        private String name;
        @ExcelProperty("中拱")
        private Double zhonggong;
        @ExcelProperty("中垂")
        private Double zhongchui;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getZhonggong() {
            return zhonggong;
        }

        public void setZhonggong(Double zhonggong) {
            this.zhonggong = zhonggong;
        }

        public Double getZhongchui() {
            return zhongchui;
        }

        public void setZhongchui(Double zhongchui) {
            this.zhongchui = zhongchui;
        }
    }


    @Override
    public void sigma2Export(Integer projectId, Integer sectionId) throws IOException {
        QueryWrapper<Sigma2> sigma2QueryWrapper = new QueryWrapper<>();
        sigma2QueryWrapper.eq("project_id", projectId);
        sigma2QueryWrapper.eq("section_id", sectionId);
        List<Sigma2> sigma2List = sigma2Mapper.selectList(sigma2QueryWrapper);
        if (null == sigma2List || sigma2List.size() == 0) {
            throw new RuntimeException("当前数据不存在!");
        }

        List<TableHeader> sigma2ExportList = new ArrayList<>();
        if (sigma2List.size() > 0) {
            TableHeader xuyong = new TableHeader();
            xuyong.setStatus("许用应力");
            xuyong.setZhonggongZhizuo(sigma2List.get(0).getAllowStress());
            xuyong.setZhonggongKuazhong(sigma2List.get(0).getAllowStress());
            xuyong.setZhongchuiZhizuo(sigma2List.get(0).getAllowStress());
            xuyong.setZhongchuiKuazhong(sigma2List.get(0).getAllowStress());
            TableHeader hecheng = new TableHeader();
            hecheng.setStatus("合成许用应力");
            hecheng.setZhonggongZhizuo(sigma2List.get(0).getAllowStress());
            hecheng.setZhonggongKuazhong(sigma2List.get(0).getAllowStress());
            hecheng.setZhongchuiZhizuo(sigma2List.get(0).getAllowStress());
            hecheng.setZhongchuiKuazhong(sigma2List.get(0).getAllowStress());

            sigma2ExportList.add(xuyong);
            sigma2ExportList.add(hecheng);
        }

        for (int i = 0; i < sigma2List.size(); i++) {
            int number = i + 1;
            TableHeader sigma2Export = new TableHeader();
            sigma2Export.setStatus("龙骨" + number + "上纤维（σ2）");
            sigma2Export.setZhonggongZhizuo(sigma2List.get(i).getZhonggongZhizuoShang());
            sigma2Export.setZhongchuiKuazhong(sigma2List.get(i).getZhongchuiKuazhongShang());
            sigma2Export.setZhongchuiZhizuo(sigma2List.get(i).getZhongchuiZhizuoShang());
            sigma2Export.setZhongchuiKuazhong(sigma2List.get(i).getZhongchuiKuazhongShang());
            TableHeader sigma2Export2 = new TableHeader();
            sigma2Export2.setStatus("龙骨" + number + "下纤维（σ2）");
            sigma2Export2.setZhonggongZhizuo(sigma2List.get(i).getZhonggongZhizuoXia());
            sigma2Export2.setZhongchuiKuazhong(sigma2List.get(i).getZhongchuiKuazhongXia());
            sigma2Export2.setZhongchuiZhizuo(sigma2List.get(i).getZhongchuiZhizuoXia());
            sigma2Export2.setZhongchuiKuazhong(sigma2List.get(i).getZhongchuiKuazhongXia());
            TableHeader sigma2Export3 = new TableHeader();
            sigma2Export3.setStatus("龙骨" + number + "上纤维合成应力（σ1+σ2）");
            sigma2Export3.setZhonggongZhizuo(sigma2List.get(i).getCombineZhonggongZhizuoShang());
            sigma2Export3.setZhongchuiKuazhong(sigma2List.get(i).getCombineZhonggongKuazhongShang());
            sigma2Export3.setZhongchuiZhizuo(sigma2List.get(i).getCombineZhongchuiZhizuoShang());
            sigma2Export3.setZhongchuiKuazhong(sigma2List.get(i).getCombineZhongchuiKuazhongShang());
            TableHeader sigma2Export4 = new TableHeader();
            sigma2Export4.setStatus("龙骨" + number + "下纤维合成应力（σ1+σ2）");
            sigma2Export4.setZhonggongZhizuo(sigma2List.get(i).getCombineZhonggongZhizuoXia());
            sigma2Export4.setZhongchuiKuazhong(sigma2List.get(i).getCombineZhonggongKuazhongXia());
            sigma2Export4.setZhongchuiZhizuo(sigma2List.get(i).getCombineZhongchuiZhizuoXia());
            sigma2Export4.setZhongchuiKuazhong(sigma2List.get(i).getCombineZhongchuiKuazhongXia());
            sigma2ExportList.add(sigma2Export);
            sigma2ExportList.add(sigma2Export2);
            sigma2ExportList.add(sigma2Export3);
            sigma2ExportList.add(sigma2Export4);
        }
        EasyExcel.write(SpringUtils.getResponse().getOutputStream(),TableHeader.class).sheet("0").registerWriteHandler(new ExcelWidthStyleStrategy()).doWrite(sigma2ExportList);
    }

    @Override
    public void sigma3Export(Integer projectId, Integer sectionId) throws IOException {
        QueryWrapper<Sigma3> sigma3QueryWrapper = new QueryWrapper<>();
        sigma3QueryWrapper.eq("project_id", projectId);
        sigma3QueryWrapper.eq("section_id", sectionId);
        List<Sigma3> sigma3List = sigma3Mapper.selectList(sigma3QueryWrapper);
        if (null == sigma3List || sigma3List.size() == 0) {
            throw new RuntimeException("当前数据不存在!");
        }

        List<TableHeader> sigma3ExportList = new ArrayList<>();
        if (sigma3List.size() > 0) {
            TableHeader xuyong = new TableHeader();
            xuyong.setStatus("许用应力");
            xuyong.setZhonggongZhizuo(sigma3List.get(0).getAllowStress());
            xuyong.setZhonggongKuazhong(sigma3List.get(0).getAllowStress());
            xuyong.setZhongchuiZhizuo(sigma3List.get(0).getAllowStress());
            xuyong.setZhongchuiKuazhong(sigma3List.get(0).getAllowStress());
            TableHeader hecheng = new TableHeader();
            hecheng.setStatus("合成许用应力");
            hecheng.setZhonggongZhizuo(sigma3List.get(0).getAllowStress());
            hecheng.setZhonggongKuazhong(sigma3List.get(0).getAllowStress());
            hecheng.setZhongchuiZhizuo(sigma3List.get(0).getAllowStress());
            hecheng.setZhongchuiKuazhong(sigma3List.get(0).getAllowStress());

            sigma3ExportList.add(xuyong);
            sigma3ExportList.add(hecheng);
        }

        for (int i = 0; i < sigma3List.size(); i++) {
            int number = i + 1;
            TableHeader sigma3Export = new TableHeader();
            sigma3Export.setStatus("龙骨" + number + "上纤维（σ3）");
            sigma3Export.setZhonggongZhizuo(sigma3List.get(i).getZhonggongZhizuoShang());
            sigma3Export.setZhongchuiKuazhong(sigma3List.get(i).getZhongchuiKuazhongShang());
            sigma3Export.setZhongchuiZhizuo(sigma3List.get(i).getZhongchuiZhizuoShang());
            sigma3Export.setZhongchuiKuazhong(sigma3List.get(i).getZhongchuiKuazhongShang());
            TableHeader sigma3Export2 = new TableHeader();
            sigma3Export2.setStatus("龙骨" + number + "下纤维（σ3）");
            sigma3Export2.setZhonggongZhizuo(sigma3List.get(i).getZhonggongZhizuoXia());
            sigma3Export2.setZhongchuiKuazhong(sigma3List.get(i).getZhongchuiKuazhongXia());
            sigma3Export2.setZhongchuiZhizuo(sigma3List.get(i).getZhongchuiZhizuoXia());
            sigma3Export2.setZhongchuiKuazhong(sigma3List.get(i).getZhongchuiKuazhongXia());
            TableHeader sigma3Export3 = new TableHeader();
            sigma3Export3.setStatus("龙骨" + number + "上纤维合成应力（σ1+σ2+σ3）");
            sigma3Export3.setZhonggongZhizuo(sigma3List.get(i).getCombineZhonggongZhizuoShang());
            sigma3Export3.setZhongchuiKuazhong(sigma3List.get(i).getCombineZhonggongKuazhongShang());
            sigma3Export3.setZhongchuiZhizuo(sigma3List.get(i).getCombineZhongchuiZhizuoShang());
            sigma3Export3.setZhongchuiKuazhong(sigma3List.get(i).getCombineZhongchuiKuazhongShang());
            TableHeader sigma3Export4 = new TableHeader();
            sigma3Export4.setStatus("龙骨" + number + "下纤维合成应力（σ1+σ2+σ3）");
            sigma3Export4.setZhonggongZhizuo(sigma3List.get(i).getCombineZhonggongZhizuoXia());
            sigma3Export4.setZhongchuiKuazhong(sigma3List.get(i).getCombineZhonggongKuazhongXia());
            sigma3Export4.setZhongchuiZhizuo(sigma3List.get(i).getCombineZhongchuiZhizuoXia());
            sigma3Export4.setZhongchuiKuazhong(sigma3List.get(i).getCombineZhongchuiKuazhongXia());
            sigma3ExportList.add(sigma3Export);
            sigma3ExportList.add(sigma3Export2);
            sigma3ExportList.add(sigma3Export3);
            sigma3ExportList.add(sigma3Export4);
        }
        EasyExcel.write(SpringUtils.getResponse().getOutputStream(),TableHeader.class).sheet("0").registerWriteHandler(new ExcelWidthStyleStrategy()).doWrite(sigma3ExportList);
    }

    @Override
    public void sigma4Export(Integer projectId, Integer sectionId) throws IOException {
        QueryWrapper<Sigma4> sigma4QueryWrapper = new QueryWrapper<>();
        sigma4QueryWrapper.eq("project_id", projectId);
        sigma4QueryWrapper.eq("section_id", sectionId);
        List<Sigma4> sigma4List = sigma4Mapper.selectList(sigma4QueryWrapper);
        if (null == sigma4List || sigma4List.size() == 0) {
            throw new RuntimeException("当前数据不存在!");
        }

        List<TableHeader> sigma4ExportList = new ArrayList<>();
        if (sigma4List.size() > 0) {
            TableHeader hecheng = new TableHeader();
            hecheng.setStatus("合成许用应力");
            hecheng.setZhonggongZhizuo(sigma4List.get(0).getAllowStress());
            hecheng.setZhonggongKuazhong(sigma4List.get(0).getAllowStress());
            hecheng.setZhongchuiZhizuo(sigma4List.get(0).getAllowStress());
            hecheng.setZhongchuiKuazhong(sigma4List.get(0).getAllowStress());
            sigma4ExportList.add(hecheng);
        }

        for (int i = 0; i < sigma4List.size(); i++) {
            int number = i + 1;
            TableHeader sigma4Export = new TableHeader();
            sigma4Export.setStatus("龙骨" + number + "（σ4）");
            sigma4Export.setZhonggongZhizuo(sigma4List.get(i).getZhonggongZhizuo());
            sigma4Export.setZhongchuiKuazhong(sigma4List.get(i).getZhongchuiKuazhong());
            sigma4Export.setZhongchuiZhizuo(sigma4List.get(i).getZhongchuiZhizuo());
            sigma4Export.setZhongchuiKuazhong(sigma4List.get(i).getZhongchuiKuazhong());
            TableHeader sigma4Export2 = new TableHeader();
            sigma4Export2.setStatus("龙骨" + number + "四种应力合成（σ1+σ2+σ3+σ4）");
            sigma4Export2.setZhonggongZhizuo(sigma4List.get(i).getCombineZhonggongZhizuo());
            sigma4Export2.setZhongchuiKuazhong(sigma4List.get(i).getCombineZhongchuiKuazhong());
            sigma4Export2.setZhongchuiZhizuo(sigma4List.get(i).getCombineZhongchuiZhizuo());
            sigma4Export2.setZhongchuiKuazhong(sigma4List.get(i).getCombineZhongchuiKuazhong());
            sigma4ExportList.add(sigma4Export);
            sigma4ExportList.add(sigma4Export2);
        }
        EasyExcel.write(SpringUtils.getResponse().getOutputStream(),TableHeader.class).sheet("0").registerWriteHandler(new ExcelWidthStyleStrategy()).doWrite(sigma4ExportList);
    }

    @Override
    public void shearingStressExport(Integer projectId, Integer sectionId) throws IOException {
        QueryWrapper<ShearingStress> shearingStressQueryWrapper = new QueryWrapper<>();
        shearingStressQueryWrapper.eq("project_id", projectId);
        shearingStressQueryWrapper.eq("section_id", sectionId);
        List<ShearingStress> shearingStressList = shearingStressMapper.selectList(shearingStressQueryWrapper);
        if (null == shearingStressList || shearingStressList.size() == 0) {
            throw new RuntimeException("当前数据不存在!");
        }
        List<List<String>> headList = new ArrayList<>();
        if (shearingStressList.size() > 0) {
            List<String> head0 = new ArrayList<>();
            head0.add("");
            headList.add(head0);
            List<String> head1 = new ArrayList<>();
            head1.add("中拱");
            headList.add(head1);
            List<String> head2 = new ArrayList<>();
            head2.add("中垂");
            headList.add(head2);
        }
        Sigma1Export shearingStressExport = new Sigma1Export();
        shearingStressExport.setName("最大剪应力τ");
        shearingStressExport.setZhonggong(shearingStressList.get(0).getZhonggongMax());
        shearingStressExport.setZhongchui(shearingStressList.get(0).getZhongchuiMax());
        Sigma1Export shearingStressExport2 = new Sigma1Export();
        shearingStressExport2.setName("许用剪应力[τ]\t");
        shearingStressExport2.setZhonggong(shearingStressList.get(0).getShearingStress());
        shearingStressExport2.setZhongchui(shearingStressList.get(0).getShearingStress());
        List<Sigma1Export> shearingStressExportList = new ArrayList<>();
        shearingStressExportList.add(shearingStressExport);
        shearingStressExportList.add(shearingStressExport2);
        EasyExcel.write(SpringUtils.getResponse().getOutputStream(), Sigma1Export.class)
                .head(headList).sheet("0").registerWriteHandler(new ExcelWidthStyleStrategy()).doWrite(shearingStressExportList);


    }

    /**
     * sigma2,sigma3,sigma4,shearingStress通用表头
     */
    private static class TableHeader {
        @ExcelProperty(value = {"校核状态", "校核位置（应力单位:MPa）"})
        private String status;
        @ExcelProperty(value = {"中拱", "支座处"})
        private Double zhonggongZhizuo;
        @ExcelProperty(value = {"中拱", "跨中处"})
        private Double zhonggongKuazhong;
        @ExcelProperty(value = {"中垂", "支座处"})

        private Double zhongchuiZhizuo;
        @ExcelProperty(value = {"中垂", "跨中处"})
        private Double zhongchuiKuazhong;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Double getZhonggongZhizuo() {
            return zhonggongZhizuo;
        }

        public void setZhonggongZhizuo(Double zhonggongZhizuo) {
            this.zhonggongZhizuo = zhonggongZhizuo;
        }

        public Double getZhonggongKuazhong() {
            return zhonggongKuazhong;
        }

        public void setZhonggongKuazhong(Double zhonggongKuazhong) {
            this.zhonggongKuazhong = zhonggongKuazhong;
        }

        public Double getZhongchuiZhizuo() {
            return zhongchuiZhizuo;
        }

        public void setZhongchuiZhizuo(Double zhongchuiZhizuo) {
            this.zhongchuiZhizuo = zhongchuiZhizuo;
        }

        public Double getZhongchuiKuazhong() {
            return zhongchuiKuazhong;
        }

        public void setZhongchuiKuazhong(Double zhongchuiKuazhong) {
            this.zhongchuiKuazhong = zhongchuiKuazhong;
        }
    }
}
