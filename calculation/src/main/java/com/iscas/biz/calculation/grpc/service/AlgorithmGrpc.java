package com.iscas.biz.calculation.grpc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.iscas.biz.calculation.entity.db.*;
import com.iscas.biz.calculation.entity.dto.CalSectionDTO;
import com.iscas.biz.calculation.entity.dto.WeightDTO;
import com.iscas.biz.calculation.grpc.*;
import com.iscas.biz.calculation.grpc.Gravity;
import com.iscas.biz.calculation.grpc.SubGravity;
import com.iscas.biz.calculation.grpc.WeightDistribution;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.ShipParamMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/11 16:27
 */
@Service
public class AlgorithmGrpc {


    private final GrpcHolder grpcHolder;

    private final ProjectMapper projectMapper;

    private final ShipParamMapper shipParamMapper;

    private volatile static Integer currentProjectId;

    private volatile static Boolean shipParam = false;

    private volatile static Boolean buoyancy = false;

    private volatile static Boolean weight = false;
    private volatile static Boolean section = false;

    private volatile static boolean dist = false;

    public AlgorithmGrpc(GrpcHolder grpcHolder, ProjectMapper projectMapper, ShipParamMapper shipParamMapper) {
        this.grpcHolder = grpcHolder;
        this.projectMapper = projectMapper;
        this.shipParamMapper = shipParamMapper;
    }

    public ShipParamResponse callShipParam(Project project, ShipParam shipParam) {
        if (null == shipParam || null == project) {
            return ShipParamResponse.newBuilder()
                    .setCode(1)
                    .setMessage("船舶参数为空!")
                    .build();
        }

        ShipParamRequest shipParamRequest = ShipParamRequest.newBuilder()
                .setLWl(shipParam.getWaterlineLength())
                .setWidth(shipParam.getMouldedBreadth())
                .setDepth(shipParam.getMouldedDepth())
                .setDraught(shipParam.getDesignedDraft())
                .setArea(Integer.parseInt(shipParam.getNavigationArea().getValue()))
                .setCheckType(Integer.parseInt(shipParam.getCheckType().getValue()))
                .setDisplacement(shipParam.getDisplacement())
                .setPortraitGravity(shipParam.getPortraitGravity())
                .setPrinciple(Integer.parseInt(project.getCalculationSpecification().getValue()))
                .setType(shipParam.getShipType().getValue())
                .build();

        ShipParamResponse response = grpcHolder.calculationBlockingStub().shipParam(shipParamRequest);
        AlgorithmGrpc.currentProjectId = project.getProjectId();
        AlgorithmGrpc.shipParam = true;
        AlgorithmGrpc.buoyancy = false;
        AlgorithmGrpc.weight = false;

        return response;
    }


    public BuoyancyResponse callBuoyancy(ShipParam shipParam, BuoyancyParam buoyancyParam) {
        if (!Objects.equals(shipParam.getProjectId(), AlgorithmGrpc.currentProjectId)) {
            ShipParamResponse shipParamResponse = callShipParam(projectMapper.selectById(shipParam.getProjectId()), shipParam);
            if (0 != shipParamResponse.getCode()) {
                throw new RuntimeException("船舶参数配置失败" + shipParamResponse.getMessage());
            }
        }
        BuoyancyRequest buoyancyRequest = BuoyancyRequest.newBuilder()
                .setBuoyancycurveFilePath(buoyancyParam.getBuoyancyCurveFilePath())
                .setBrojeanFilePath(buoyancyParam.getBonjungCurveFilePath())
                .addPrecision(buoyancyParam.getPrecisionDisplacement())
                .addPrecision(buoyancyParam.getPrecisionGravity())
                .setTarget(Integer.parseInt(shipParam.getCheckType().getValue()))
                .setTm(buoyancyParam.getDraftMean())
                .setTa(buoyancyParam.getDraftAft())
                .setTf(buoyancyParam.getDraftForward()).build();
        BuoyancyResponse buoyancyResponse = grpcHolder.calculationBlockingStub().buoyancy(buoyancyRequest);
        AlgorithmGrpc.buoyancy = true;
        return buoyancyResponse;
    }

    public Weight callWeight(WeightDTO weightDTO) {
        Integer projectId = weightDTO.getProjectId();
        if (!Objects.equals(projectId, AlgorithmGrpc.currentProjectId)) {
            QueryWrapper<ShipParam> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("project_id", projectId);
            ShipParam shipParam = shipParamMapper.selectOne(queryWrapper);
            ShipParamResponse shipParamResponse = callShipParam(projectMapper.selectById(projectId), shipParam);
            if (0 != shipParamResponse.getCode()) {
                throw new RuntimeException("船舶参数配置失败" + shipParamResponse.getMessage());
            }
        }
        WeightRequest weightRequest = WeightRequest.newBuilder()
                .setLoadingFilePath(weightDTO.getLoadingFilePath())
                .build();
        WeightResponse weightResponse = grpcHolder.calculationBlockingStub().calWeightDistribute(weightRequest);
        if (0 != weightResponse.getCode()) {
            throw new RuntimeException("重量分布计算失败:" + weightResponse.getMessage());
        }
        Weight weight = new Weight();
        weight.setLoadingFileName(weightDTO.getLoadingFileName());
        weight.setLoadingFilePath(weightDTO.getLoadingFilePath());
        weight.setProjectId(projectId);
        List<WeightDistribution> weightDistributionsList = weightResponse.getWeightDistributionsList();
        if (CollectionUtils.isNotEmpty(weightDistributionsList)) {
            List<com.iscas.biz.calculation.entity.db.WeightDistribution> dbList = Lists.newArrayList();
            for (WeightDistribution weightDistribution : weightDistributionsList) {
                com.iscas.biz.calculation.entity.db.WeightDistribution db = new com.iscas.biz.calculation.entity.db.WeightDistribution();
                db.setName(weightDistribution.getName().toStringUtf8());
                db.setWeightItems(Lists.newArrayList(weightDistribution.getWeightItemsList()));
                dbList.add(db);
            }
            weight.setWeightDistributions(dbList);
        }

        weight.setAllDirs(Lists.newArrayList(weightResponse.getAllDisList()));


        Gravity allRst = weightResponse.getAllRst();
        if (null != allRst) {
            com.iscas.biz.calculation.entity.db.Gravity db = new com.iscas.biz.calculation.entity.db.Gravity();
            db.setDisplacement(allRst.getDisplacement());
            db.setXg(allRst.getXg());
            weight.setAllRst(db);
        }

        List<SubGravity> subGravitiesList = weightResponse.getSubGravitiesList();
        if (CollectionUtils.isNotEmpty(subGravitiesList)) {
            List<com.iscas.biz.calculation.entity.db.SubGravity> dbList = Lists.newArrayList();
            for (SubGravity subGravity : subGravitiesList) {
                com.iscas.biz.calculation.entity.db.SubGravity db = new com.iscas.biz.calculation.entity.db.SubGravity();
                db.setName(subGravity.getName().toStringUtf8());
                db.setXg(subGravity.getXg());
                db.setDisplacement(subGravity.getDisplacement());
                dbList.add(db);
            }
            weight.setSubGravities(dbList);
        }
        AlgorithmGrpc.weight = true;
        return weight;
    }

    public CalSection calSection(CalSectionDTO calSectionDTO) {
        Integer projectId = calSectionDTO.getProjectId();
        if (!Objects.equals(projectId, AlgorithmGrpc.currentProjectId)) {
            QueryWrapper<ShipParam> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("project_id", projectId);
            ShipParam shipParam = shipParamMapper.selectOne(queryWrapper);
            ShipParamResponse shipParamResponse = callShipParam(projectMapper.selectById(projectId), shipParam);
            if (0 != shipParamResponse.getCode()) {
                throw new RuntimeException("船舶参数配置失败" + shipParamResponse.getMessage());
            }
        }
        SectionRequest sectionRequest = SectionRequest.newBuilder()
                .setProfileFilePath(calSectionDTO.getProfileFilePath())
                .build();
        SectionResponse sectionResponse = grpcHolder.calculationBlockingStub().calSection(sectionRequest);
        if (sectionResponse == null) {
            throw new RuntimeException("剖面计算计算失败:");
        }
        CalSection calSection = new CalSection();
        calSection.setProjectId(projectId);
        calSection.setProfileFilePath(calSectionDTO.getProfileFilePath());
        calSection.setProfileFileName(calSectionDTO.getProfileFileName());
        calSection.setFirstMoment0(sectionResponse.getFirstMoment0());
        calSection.setInteria0(sectionResponse.getInteria0());
        calSection.setZaxisH(sectionResponse.getZaxisH());
        calSection.setFirstMomH(sectionResponse.getFirstMomH());
        calSection.setInteriaH(sectionResponse.getInteriaH());
        calSection.setZaxisS(sectionResponse.getZaxisS());
        calSection.setFirstMomS(sectionResponse.getFirstMomS());
        calSection.setInteriaS(sectionResponse.getInteriaS());
        AlgorithmGrpc.section = true;
        return calSection;
    }

    public Dist calDist(Integer projectId) {

        if (!Objects.equals(projectId, AlgorithmGrpc.currentProjectId)) {
            QueryWrapper<ShipParam> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("project_id", projectId);
            ShipParam shipParam = shipParamMapper.selectOne(queryWrapper);
            ShipParamResponse shipParamResponse = callShipParam(projectMapper.selectById(projectId), shipParam);
            if (0 != shipParamResponse.getCode()) {
                throw new RuntimeException("船舶参数配置失败" + shipParamResponse.getMessage());
            }
        }
        DistRequest distRequest = DistRequest.newBuilder()                .build();
        DistResponse distResponse = grpcHolder.calculationBlockingStub().calDist(distRequest);
        if (distResponse == null) {
            throw new RuntimeException("应力分布计算失败:");
        }
        Dist dist = new Dist();
        dist.setProjectId(projectId);
        dist.setExtremeH(distResponse.getExtremeH());
        dist.setExtremeS(distResponse.getExtremeS());
        AlgorithmGrpc.dist = true;
        return dist;
    }
}
