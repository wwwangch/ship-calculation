package com.iscas.biz.calculation.grpc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.iscas.biz.calculation.entity.db.*;
import com.iscas.biz.calculation.entity.dto.SlamLoadDTO;
import com.iscas.biz.calculation.entity.dto.StaticLoadDTO;
import com.iscas.biz.calculation.entity.dto.WaveLoadDTO;
import com.iscas.biz.calculation.entity.dto.WeightDTO;
import com.iscas.biz.calculation.grpc.Gravity;
import com.iscas.biz.calculation.grpc.SubGravity;
import com.iscas.biz.calculation.grpc.WeightDistribution;
import com.iscas.biz.calculation.grpc.*;
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

    private volatile static Boolean staticLoad = false;

    private volatile static Boolean waveLoad = false;

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
                throw new RuntimeException("船舶参数配置失败:" + shipParamResponse.getMessage());
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

    public StaticLoad calStaticLoad(StaticLoadDTO project) {
//        if (!(buoyancy && weight)) {
//            throw new RuntimeException("前置计算:浮力分布或重量分布尚未计算!");
//        }
        StaticLoadResponse staticLoadResponse = grpcHolder.calculationBlockingStub().calStaticLoad(StaticLoadRequest.newBuilder().build());
        StaticLoad staticLoad = new StaticLoad();
        staticLoad.setProjectId(project.getProjectId());
        staticLoad.setNvec(Lists.newArrayList(staticLoadResponse.getNvecList()));
        staticLoad.setMvec(Lists.newArrayList(staticLoadResponse.getMvecList()));
        staticLoad.setNvecM(Lists.newArrayList(staticLoadResponse.getNvecMList()));
        staticLoad.setMvecM(Lists.newArrayList(staticLoadResponse.getMvecMList()));
        AlgorithmGrpc.staticLoad = true;
        return staticLoad;
    }

    public WaveLoad calWaveLoad(WaveLoadDTO waveLoadDTO) {
//        if (!AlgorithmGrpc.staticLoad) {
//            throw new RuntimeException("前置计算尚未完成!");
//        }
        WaveLoadResponse waveLoadResponse = grpcHolder.calculationBlockingStub().calWaveLoad(WaveLoadRequest.newBuilder()
                .setHeight(waveLoadDTO.getWaveHeight())
                .build());
        WaveLoad dbWaveLoad = new WaveLoad();
        dbWaveLoad.setProjectId(waveLoadDTO.getProjectId());
        dbWaveLoad.setWaveHeight(waveLoadDTO.getWaveHeight());
        dbWaveLoad.setMbb(Lists.newArrayList(waveLoadResponse.getMbbList()));
        dbWaveLoad.setNwvecH(Lists.newArrayList(waveLoadResponse.getNwvecHList()));
        dbWaveLoad.setMwvecH(Lists.newArrayList(waveLoadResponse.getMwvecHList()));
        dbWaveLoad.setNwvecMH(Lists.newArrayList(waveLoadResponse.getNwvecMHList()));
        dbWaveLoad.setMwvecMH(Lists.newArrayList(waveLoadResponse.getMwvecMHList()));
        dbWaveLoad.setBdeltaS(Lists.newArrayList(waveLoadResponse.getBdeltaSList()));
        dbWaveLoad.setNwvecS(Lists.newArrayList(waveLoadResponse.getNwvecSList()));
        dbWaveLoad.setMwvecS(Lists.newArrayList(waveLoadResponse.getMwvecSList()));
        dbWaveLoad.setNwvecMS(Lists.newArrayList(waveLoadResponse.getNwvecMSList()));
        dbWaveLoad.setMwvecMS(Lists.newArrayList(waveLoadResponse.getMwvecMSList()));
        AlgorithmGrpc.waveLoad = true;
        return dbWaveLoad;
    }

    public SlamLoad calSlamLoad(SlamLoadDTO slamLoadDTO) {
        //        if (!AlgorithmGrpc.waveLoad) {
//            throw new RuntimeException("前置计算尚未完成!");
//        }
        SlamLoadResponse slamLoadResponse = grpcHolder.calculationBlockingStub().calSlamLoad(SlamLoadRequest.newBuilder()
                .setSpeed(slamLoadDTO.getSpeed())
                .build());
        SlamLoad dbSlamLoad = new SlamLoad();
        dbSlamLoad.setProjectId(slamLoadDTO.getProjectId());
        dbSlamLoad.setSpeed(slamLoadDTO.getSpeed());
        dbSlamLoad.setNwb(Lists.newArrayList(slamLoadResponse.getNwbList()));
        dbSlamLoad.setPwbm(Lists.newArrayList(slamLoadResponse.getPwbmList()));
        AlgorithmGrpc.waveLoad = true;
        return dbSlamLoad;
    }

}
