package com.iscas.biz.calculation.grpc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.iscas.biz.calculation.entity.db.TProfile;
import com.iscas.biz.calculation.entity.db.*;
import com.iscas.biz.calculation.entity.db.sigma.*;
import com.iscas.biz.calculation.entity.dto.*;
import com.iscas.biz.calculation.entity.dto.sigma.Sigma1DTO;
import com.iscas.biz.calculation.enums.CalculationSpecification;
import com.iscas.biz.calculation.enums.CheckType;
import com.iscas.biz.calculation.grpc.Gravity;
import com.iscas.biz.calculation.grpc.SubGravity;
import com.iscas.biz.calculation.grpc.WeightDistribution;
import com.iscas.biz.calculation.grpc.*;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.ShipParamMapper;
import com.spire.ms.System.Collections.ArrayList;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private volatile static Boolean girderStrength = false;

    private volatile static boolean dist = false;

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

        ShipParamRequest.Builder builder = ShipParamRequest.newBuilder()
                .setLWl(shipParam.getWaterlineLength())
                .setWidth(shipParam.getMouldedBreadth())
                .setDepth(shipParam.getMouldedDepth())
                .setDraught(shipParam.getDesignedDraft())
                .setArea(Integer.parseInt(shipParam.getNavigationArea().getValue()))
                .setPrinciple(Integer.parseInt(project.getCalculationSpecification().getValue()))
                .setType(shipParam.getShipType().getValue())
                .setVmax(shipParam.getVmax())
                .setSpeed(shipParam.getSpeed());
        //通规不需要工况
        if (!CalculationSpecification.COMMON_SPECIFICATION.equals(project.getCalculationSpecification())) {
            builder.setDisplacement(shipParam.getDisplacement())
                    .setPortraitGravity(shipParam.getPortraitGravity())
                    .setCheckType(Integer.parseInt(shipParam.getCurrentType().getValue()));
        }

        ShipParamRequest shipParamRequest = builder.build();
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
                .setTarget(Integer.parseInt(Optional.ofNullable(shipParam.getCurrentType()).orElse(CheckType.EXTREME).getValue()))
                .setTm(buoyancyParam.getDraftMean())
                .setTa(buoyancyParam.getDraftAft())
                .setTf(buoyancyParam.getDraftForward()).build();
        BuoyancyResponse buoyancyResponse = grpcHolder.calculationBlockingStub().buoyancy(buoyancyRequest);
        AlgorithmGrpc.buoyancy = true;
        return buoyancyResponse;
    }

    public Weight callWeight(ShipParam shipParamTmp, WeightDTO weightDTO) {

        Project project = projectMapper.selectById(shipParamTmp.getProjectId());
        ShipParamResponse tmp = callShipParam(project, shipParamTmp);
        if (0 != tmp.getCode()) {
            throw new RuntimeException("船舶参数配置失败:" + tmp.getMessage());
        }


/*        Integer projectId = weightDTO.getProjectId();
        if (!Objects.equals(projectId, AlgorithmGrpc.currentProjectId)) {
            QueryWrapper<ShipParam> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("project_id", projectId);
            ShipParam shipParam = shipParamMapper.selectOne(queryWrapper);
            ShipParamResponse shipParamResponse = callShipParam(projectMapper.selectById(projectId), shipParam);
            if (0 != shipParamResponse.getCode()) {
                throw new RuntimeException("船舶参数配置失败" + shipParamResponse.getMessage());
            }
        }*/
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
        weight.setProjectId(shipParamTmp.getProjectId());

        //非通规设置工况类型
        if (CalculationSpecification.COMMON_SPECIFICATION.equals(project.getCalculationSpecification())) {
            weight.setCheckType(null);
        } else {
            weight.setCheckType(shipParamTmp.getCurrentType());
        }
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
        if (calSectionDTO.getProfileFilePathOld() == null || calSectionDTO.getRibNumber() == null) {
            throw new RuntimeException("剖面计算参数错误");
        }
        SectionRequest sectionRequest = SectionRequest.newBuilder()
                .setProfileFilePath(calSectionDTO.getProfileFilePathOld())
                .setRibNumber(calSectionDTO.getRibNumber())
                .setExecuteAutoCadPath("")
//                .addAllBulbFlats(calSectionDTO.getBulbFlats())
//                .addAllTProfiles(calSectionDTO.getTProfiles())
//                .setIsHalfProfile(calSectionDTO.isHalfProfile())
                .build();
        SectionResponse sectionResponse = grpcHolder.calculationBlockingStub().calSection(sectionRequest);
        if (sectionResponse == null) {
            throw new RuntimeException("剖面计算失败:");
        }
        CalSection calSection = new CalSection();
        calSection.setProjectId(projectId);
        calSection.setProfileFilePathOld(calSectionDTO.getProfileFilePathOld());
        calSection.setProfileFileName(calSectionDTO.getProfileFileName());
        calSection.setRibNumber(calSectionDTO.getRibNumber());
//        calSection.setBulbFlats(calSectionDTO.getBulbFlats());
//        calSection.setTProfiles(calSectionDTO.getTProfiles());
        calSection.setHalfProfile(calSectionDTO.isHalfProfile());
        calSection.setFirstMoment0(sectionResponse.getFirstMoment0());
        calSection.setInteria0(sectionResponse.getInteria0());
//        calSection.setZaxisH(sectionResponse.getZaxisH());
//        calSection.setFirstMomH(sectionResponse.getFirstMomH());
//        calSection.setInteriaH(sectionResponse.getInteriaH());
//        calSection.setZaxisS(sectionResponse.getZaxisS());
//        calSection.setFirstMomS(sectionResponse.getFirstMomS());
//        calSection.setInteriaS(sectionResponse.getInteriaS());
        calSection.setZaxis0(sectionResponse.getZaxis0());
        calSection.setArea(sectionResponse.getArea());
//        calSection.setModuleUpper(sectionResponse.getModuleUpper());
        calSection.setModuleLower(sectionResponse.getModuleLower());
        calSection.setProfileFilePath(sectionResponse.getProfileFilePath());
        AlgorithmGrpc.section = true;
        return calSection;
    }

    /**
     * 附加丫头计算
     *
     * @param calAdditionDTO
     * @return
     */
    public CalAddition calAddition(CalAdditionDTO calAdditionDTO) {
        Integer projectId = calAdditionDTO.getProjectId();

        AdditionalForceHeadRequest headRequest = AdditionalForceHeadRequest.newBuilder()
                .setUpbuildstyle(calAdditionDTO.getUpBuiltForm())
                .setFreeboard(calAdditionDTO.getFreeboard())
                .setShouloupos(calAdditionDTO.getRibNumber())
                .setChuanzhongPos(calAdditionDTO.getMidRibNumber())
                .setLeiguJianju(calAdditionDTO.getRibSpacing())
                .setForefreeboard(Optional.ofNullable(calAdditionDTO.getForeFreeBoard()).orElse(0d))
                .setAftfreeboard(Optional.ofNullable(calAdditionDTO.getAfterFreeBoard()).orElse(0d))
                .setBridgeForePos(Optional.ofNullable(calAdditionDTO.getBridgeForePos()).orElse(0d))
                .setBridgeForeHeight(Optional.ofNullable(calAdditionDTO.getBridgeForeHeight()).orElse(0d))
                .setBridgeAftPos(Optional.ofNullable(calAdditionDTO.getBridgeAftPos()).orElse(0d))
                .setBridgeAftHeight(Optional.ofNullable(calAdditionDTO.getBridgeAftHeight()).orElse(0d))
                .setDraugthnoraml(calAdditionDTO.getDraugthnoraml())
                .setIsCollision(calAdditionDTO.getCollisionBulkhead())
                .setDynamicyatou(calAdditionDTO.getShuidongyali())
                .setLeiweihao(calAdditionDTO.getLeiweihao())
                .setAirguanyatou(calAdditionDTO.getAirguanyatou())
                .addAllDeckName(Optional.ofNullable(calAdditionDTO.getDeckName()).orElse(Lists.newArrayList()))
                .addAllDeckHeight(Optional.ofNullable(calAdditionDTO.getDeckHeight()).orElse(Lists.newArrayList()).stream().map(number -> Double.parseDouble(number.toString())).collect(Collectors.toList()))
                .addAllBoolLiquidTank(calAdditionDTO.getLiquidTanks())
                .build();
        AdditionalForceHeadResponse headResponse = grpcHolder.calculationBlockingStub().calAdditionalForceHead(headRequest);
        if (headResponse == null) {
            throw new RuntimeException("附加压头计算失败");
        }

        CalAddition calAddition = new CalAddition();
        calAddition.setProjectId(projectId);
        calAddition.setLeiweihaos(Lists.newArrayList(headResponse.getLeiweihaoList()));
        calAddition.setAddyatous(Lists.newArrayList(headResponse.getAddyatouhList()));
        calAddition.setStrDecks(Lists.newArrayList(headResponse.getStrdeckList()));
        calAddition.setDeckYatous(Lists.newArrayList(headResponse.getDeckyatouList()));
//        AlgorithmGrpc.section = true;
        return calAddition;
    }

    public Material material(MaterialDTO materialDTO) {
        Integer projectId = materialDTO.getProjectId();
        SupportingMaterialStrengthRequest strengthRequest = SupportingMaterialStrengthRequest.newBuilder()
                .addAllGuicaiType(materialDTO.getGuicaiType())
                .addAllDaibanHouUpper(materialDTO.getStripPlateThicknessUpper())
                .addAllDaibanHouLower(materialDTO.getStripPlateThicknessLower())
                .addAllDaibanKuan(materialDTO.getStripPlateWidth())
                .setZongguKuaju(materialDTO.getZongguKuaju())
                .addAllGuicaiTypeUpper(materialDTO.getGuicaiTypeUppers())
                .addAllGuicaiTypeLower(materialDTO.getGuicaiTypeLowers())
                .addAllFuQiangCaiYieldLimit(materialDTO.getFuQiangCaiYieldLimits())
                .build();
        SupportingMaterialStrengthResponse strengthResponse = grpcHolder.calculationBlockingStub().calSupportingMaterialStrength(strengthRequest);
        if (strengthResponse == null) {
            throw new RuntimeException("扶强材计算失败");
        }
        Material material = new Material();
        material.setProjectId(projectId);
        material.setLowerLoad(Lists.newArrayList(strengthResponse.getLowerLoadList()));
        material.setUpperLoad(Lists.newArrayList(strengthResponse.getUpperLoadList()));
        material.setZiyouZhongwan(Lists.newArrayList(strengthResponse.getZiyouZhongwanList()));
        material.setZiyouShangwan(Lists.newArrayList(strengthResponse.getZiyouShangwanList()));
        material.setZiyouXiawan(Lists.newArrayList(strengthResponse.getZiyouXiawanList()));
        material.setZiyouShangjian(Lists.newArrayList(strengthResponse.getZiyouShangjianList()));
        material.setZiyouXiajian(Lists.newArrayList(strengthResponse.getZiyouXiajianList()));
        material.setGangxingShangwan(Lists.newArrayList(strengthResponse.getGangxingShangwanList()));
        material.setGangxingXiawan(Lists.newArrayList(strengthResponse.getGangxingXiawanList()));
        material.setGangxingShangjian(Lists.newArrayList(strengthResponse.getGangxingShangjianList()));
        material.setGangxingXiajian(Lists.newArrayList(strengthResponse.getGangxingXiajianList()));
        material.setYingliZhongying(Lists.newArrayList(strengthResponse.getYingliZhongyingList()));
        material.setYingliShangying(Lists.newArrayList(strengthResponse.getYingliShangyingList()));
        material.setYingliXiaying(Lists.newArrayList(strengthResponse.getYingliXiayingList()));
        material.setYingliXuying(Lists.newArrayList(strengthResponse.getYingliXuyingList()));
        material.setYingliShangjian(Lists.newArrayList(strengthResponse.getYingliShangjianList()));
        material.setYingliXiajian(Lists.newArrayList(strengthResponse.getYingliXiajianList()));
        material.setYingliXujian(Lists.newArrayList(strengthResponse.getYingliXujianList()));
        material.setMMaxEl(Lists.newArrayList(strengthResponse.getMMaxElList()));
        material.setNMaxEl(Lists.newArrayList(strengthResponse.getNMaxElList()));
        material.setStressMaxEl(Lists.newArrayList(strengthResponse.getStressMaxElList()));
        material.setShearMaxEl(Lists.newArrayList(strengthResponse.getShearMaxElList()));
        AlgorithmGrpc.section = true;

        return material;
    }

//    public GirderStrength calGirderStrength(GirderStrengthDTO girderStrengthDTO) {
//        Integer projectId = girderStrengthDTO.getProjectId();
//        if (!Objects.equals(projectId, AlgorithmGrpc.currentProjectId)) {
//            QueryWrapper<ShipParam> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("project_id", projectId);
//            ShipParam shipParam = shipParamMapper.selectOne(queryWrapper);
//            ShipParamResponse shipParamResponse = callShipParam(projectMapper.selectById(projectId), shipParam);
//            if (0 != shipParamResponse.getCode()) {
//                throw new RuntimeException("船舶参数配置失败" + shipParamResponse.getMessage());
//            }
//        }
//        GirderStrengthRequest girderStrengthRequest = GirderStrengthRequest.newBuilder()
//                .setKuaChang(girderStrengthDTO.getKuaChang())
//                .setGirderDistance(girderStrengthDTO.getGirderDistance())
//                .build();
//        GirderStrengthResponse girderStrengthResponse = grpcHolder.calculationBlockingStub().calGirderStrength(girderStrengthRequest);
//        if (girderStrengthResponse == null) {
//            throw new RuntimeException("板件弯矩应力计算失败:");
//        }
//        GirderStrength girderStrength = new GirderStrength();
//        girderStrength.setProjectId(projectId);
//        girderStrength.setKuaChang(girderStrengthDTO.getKuaChang());
//        girderStrength.setGirderDistance(girderStrengthDTO.getGirderDistance());
//
//        girderStrength.setSigma1SH(girderStrengthResponse.getSigma1SH());
//        girderStrength.setSigma1MidH(girderStrengthResponse.getSigma1MidH());
//        girderStrength.setSigma1SS(girderStrengthResponse.getSigma1SS());
//        girderStrength.setSigma1MidS(girderStrengthResponse.getSigma1MidS());
//
//        girderStrength.setStress2SH(Lists.newArrayList(girderStrengthResponse.getStress2SHList()));
//        girderStrength.setStress2MidH(Lists.newArrayList(girderStrengthResponse.getStress2MidHList()));
//        girderStrength.setStress3UpH(Lists.newArrayList(girderStrengthResponse.getStress3UpHList()));
//        girderStrength.setStress3DownH(Lists.newArrayList(girderStrengthResponse.getStress3DownHList()));
//        girderStrength.setStress4UpH(Lists.newArrayList(girderStrengthResponse.getStress4UpHList()));
//        girderStrength.setStress4downH(Lists.newArrayList(girderStrengthResponse.getStress4DownHList()));
//        girderStrength.setStress2SS(Lists.newArrayList(girderStrengthResponse.getStress2SSList()));
//        girderStrength.setStress2MidS(Lists.newArrayList(girderStrengthResponse.getStress2MidSList()));
//        girderStrength.setStress3UpS(Lists.newArrayList(girderStrengthResponse.getStress3UpSList()));
//        girderStrength.setStress3DownS(Lists.newArrayList(girderStrengthResponse.getStress3DownSList()));
//        girderStrength.setStress4UpS(Lists.newArrayList(girderStrengthResponse.getStress4UpSList()));
//        girderStrength.setStress4downS(Lists.newArrayList(girderStrengthResponse.getStress4DownSList()));
//        AlgorithmGrpc.girderStrength = true;
//        return girderStrength;
//    }

    public Dist calDist(Integer projectId, Integer sectionId) {

        if (!Objects.equals(projectId, AlgorithmGrpc.currentProjectId)) {
            QueryWrapper<ShipParam> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("project_id", projectId);
            ShipParam shipParam = shipParamMapper.selectOne(queryWrapper);
            ShipParamResponse shipParamResponse = callShipParam(projectMapper.selectById(projectId), shipParam);
            if (0 != shipParamResponse.getCode()) {
                throw new RuntimeException("船舶参数配置失败" + shipParamResponse.getMessage());
            }
        }
        DistRequest distRequest = DistRequest.newBuilder().build();
        DistResponse distResponse = grpcHolder.calculationBlockingStub().calDist(distRequest);
        if (distResponse == null) {
            throw new RuntimeException("应力分布计算失败:");
        }
        Dist dist = new Dist();
        dist.setProjectId(projectId);
        dist.setSectionId(sectionId);
        dist.setExtremeH(distResponse.getExtremeH());
        dist.setExtremeS(distResponse.getExtremeS());
        dist.setOverloadH1(distResponse.getOverloadH1());
        dist.setOverloadH2(distResponse.getOverloadH2());
        dist.setOverloadS1(distResponse.getOverloadS1());
        dist.setOverloadS2(distResponse.getOverloadS2());
        //todo 后期如果有了图片返回，将图片复制到指定的文件目录下，此处存储文件名，调用公共的picture/preview进行返回预览
        dist.setStressdisPath(distResponse.getStressdisPath());
        AlgorithmGrpc.dist = true;
        return dist;
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
//                .setSpeed(slamLoadDTO.getSpeed())
                .build());
        SlamLoad dbSlamLoad = new SlamLoad();
        dbSlamLoad.setProjectId(slamLoadDTO.getProjectId());
        dbSlamLoad.setSpeed(slamLoadDTO.getSpeed());
        dbSlamLoad.setNwb(Lists.newArrayList(slamLoadResponse.getNwbList()));
        dbSlamLoad.setPwbm(Lists.newArrayList(slamLoadResponse.getPwbmList()));
        AlgorithmGrpc.waveLoad = true;
        return dbSlamLoad;
    }

    public List<Sigma1> calSigma1(Sigma1DTO sigma1DTO) {
        List old = sigma1DTO.getFrGuige();
        List newFr = new java.util.ArrayList<>();
        for (int i = 0; i < old.size(); i++) {
            List tmp = (List) old.get(i);
            newFr.add(tmp.get(1));
        }
        Sigma1Response sigma1Response = grpcHolder.calculationBlockingStub().calSigma1(Sigma1Request.newBuilder()
                .addAllKuaChang(Lists.newArrayList(sigma1DTO.getKuaChang()))
                .setGirderDistance(sigma1DTO.getGirderDistance())
                .setFrDistance(sigma1DTO.getFrDistance())
                .setTrusswidth(sigma1DTO.getTrusswidth())
                .addAllFrGuige(newFr)
                .addAllPlateThick(Lists.newArrayList(sigma1DTO.getPlateThick()))
                .setDeviceWeight(sigma1DTO.getDeviceWeight())
                .addAllGirderWidth(Lists.newArrayList(sigma1DTO.getGirderWidth()))
                .setMaterialYieldLimit(sigma1DTO.getMaterialYieldLimit())
                .setIsCustomLoad(sigma1DTO.getIsCustomLoad())
                .setMidArchWaveMoment(sigma1DTO.getMidArchWaveMoment())
                .setMidArchImpactMoment(sigma1DTO.getMidArchImpactMoment())
                .setMidArchShear(sigma1DTO.getMidArchShear())
                .setMidArchDraught(sigma1DTO.getMidArchDraught())
                .setMidVerticalWaveMoment(sigma1DTO.getMidVerticalWaveMoment())
                .setMidVerticalImpactMoment(sigma1DTO.getMidVerticalImpactMoment())
                .setMidVerticalShear(sigma1DTO.getMidVerticalShear())
                .setMidVerticalDraught(sigma1DTO.getMidVerticalDraught())
                .addAllXiancethick(Lists.newArrayList(sigma1DTO.getXiancethick()))
                .setNumGirder(sigma1DTO.getNumGirders())
                .build());

        List<Sigma1> sigma1List = new ArrayList();
        for (int i = 0; i < sigma1Response.getSigma1List().size(); i++) {
            Sigma1 sigma1 = new Sigma1();
            sigma1.setProjectId(sigma1DTO.getProjectId());
            sigma1.setSectionId(sigma1DTO.getSectionId());
            sigma1.setSigma1Down(sigma1Response.getSigma1(i).getSigma1Down());
            sigma1.setSigma1HUp(sigma1Response.getSigma1(i).getSigma1HUp());
            sigma1.setSigma1SUp(sigma1Response.getSigma1(i).getSigma1SUp());
            sigma1.setSigma1SDown(sigma1Response.getSigma1(i).getSigma1SDown());
            sigma1.setAllowStress(sigma1Response.getSigma1(i).getAllowStress());
            sigma1List.add(sigma1);
        }
        return sigma1List;
    }

    public List<Sigma2> calSigma2(Integer projectId, Integer sectionId) {
        Sigma2Response sigma2Response = grpcHolder.calculationBlockingStub().calSigma2(Sigma2Request.newBuilder().build());
        List<Sigma2> sigma2List = Lists.newArrayList();
        for (int i = 0; i < sigma2Response.getSigma2List().size(); i++) {
            Sigma2 sigma2 = new Sigma2();
            sigma2.setProjectId(projectId);
            sigma2.setSectionId(sectionId);
            sigma2.setZhonggongZhizuoShang(sigma2Response.getSigma2(i).getZhonggongZhizuoShang());
            sigma2.setZhonggongZhizuoXia(sigma2Response.getSigma2(i).getZhonggongZhizuoXia());

            sigma2.setZhonggongKuazhongShang(sigma2Response.getSigma2(i).getZhonggongKuazhongShang());
            sigma2.setZhonggongKuazhongXia(sigma2Response.getSigma2(i).getZhonggongKuazhongXia());


            sigma2.setZhongchuiZhizuoShang(sigma2Response.getSigma2(i).getZhongchuiZhizuoShang());
            sigma2.setZhongchuiZhizuoXia(sigma2Response.getSigma2(i).getZhongchuiZhizuoXia());

            sigma2.setZhongchuiKuazhongShang(sigma2Response.getSigma2(i).getZhongchuiKuazhongShang());
            sigma2.setZhongchuiKuazhongXia(sigma2Response.getSigma2(i).getZhongchuiKuazhongXia());

//            sigma2.setAllowStress(sigma2Response.getSigma2(i).getAllowStress());
            sigma2.setCombineAllowStress(sigma2Response.getSigma2(i).getCombineAllowStress());
            sigma2.setCombineZhonggongZhizuoShang(sigma2Response.getSigma2(i).getCombineZhonggongZhizuoShang());
            sigma2.setCombineZhonggongZhizuoXia(sigma2Response.getSigma2(i).getCombineZhonggongZhizuoXia());
            sigma2.setCombineZhonggongKuazhongShang(sigma2Response.getSigma2(i).getCombineZhonggongKuazhongShang());
            sigma2.setCombineZhonggongKuazhongXia(sigma2Response.getSigma2(i).getCombineZhonggongKuazhongXia());
            sigma2.setCombineZhongchuiZhizuoShang(sigma2Response.getSigma2(i).getCombineZhongchuiZhizuoShang());
            sigma2.setCombineZhongchuiZhizuoXia(sigma2Response.getSigma2(i).getCombineZhongchuiZhizuoXia());
            sigma2.setCombineZhongchuiKuazhongShang(sigma2Response.getSigma2(i).getCombineZhongchuiKuazhongShang());
            sigma2.setCombineZhongchuiKuazhongXia(sigma2Response.getSigma2(i).getCombineZhongchuiKuazhongXia());

            sigma2List.add(sigma2);
        }
        return sigma2List;
    }

    public List<Sigma3> calSigma3(Integer projectId, Integer sectionId) {
        Sigma3Response sigma3Response = grpcHolder.calculationBlockingStub().calSigma3(Sigma3Request.newBuilder().build());
        List<Sigma3> sigma3List = Lists.newArrayList();
        for (int i = 0; i < sigma3Response.getSigma3List().size(); i++) {
            Sigma3 sigma3 = new Sigma3();
            sigma3.setProjectId(projectId);
            sigma3.setSectionId(sectionId);
            sigma3.setZhonggongZhizuoShang(sigma3Response.getSigma3(i).getZhonggongZhizuoShang());
            sigma3.setZhonggongZhizuoXia(sigma3Response.getSigma3(i).getZhonggongZhizuoXia());

            sigma3.setZhonggongKuazhongShang(sigma3Response.getSigma3(i).getZhonggongKuazhongShang());
            sigma3.setZhonggongKuazhongXia(sigma3Response.getSigma3(i).getZhonggongKuazhongXia());


            sigma3.setZhongchuiZhizuoShang(sigma3Response.getSigma3(i).getZhongchuiZhizuoShang());
            sigma3.setZhongchuiZhizuoXia(sigma3Response.getSigma3(i).getZhongchuiZhizuoXia());

            sigma3.setZhongchuiKuazhongShang(sigma3Response.getSigma3(i).getZhongchuiKuazhongShang());
            sigma3.setZhongchuiKuazhongXia(sigma3Response.getSigma3(i).getZhongchuiKuazhongXia());

//            sigma3.setAllowStress(sigma3Response.getSigma3(i).getAllowStress());
            sigma3.setCombineAllowStress(sigma3Response.getSigma3(i).getCombineAllowStress());
            sigma3.setCombineZhonggongZhizuoShang(sigma3Response.getSigma3(i).getCombineZhonggongZhizuoShang());
            sigma3.setCombineZhonggongZhizuoXia(sigma3Response.getSigma3(i).getCombineZhonggongZhizuoXia());
            sigma3.setCombineZhonggongKuazhongShang(sigma3Response.getSigma3(i).getCombineZhonggongKuazhongShang());
            sigma3.setCombineZhonggongKuazhongXia(sigma3Response.getSigma3(i).getCombineZhonggongKuazhongXia());
            sigma3.setCombineZhongchuiZhizuoShang(sigma3Response.getSigma3(i).getCombineZhongchuiZhizuoShang());
            sigma3.setCombineZhongchuiZhizuoXia(sigma3Response.getSigma3(i).getCombineZhongchuiZhizuoXia());
            sigma3.setCombineZhongchuiKuazhongShang(sigma3Response.getSigma3(i).getCombineZhongchuiKuazhongShang());
            sigma3.setCombineZhongchuiKuazhongXia(sigma3Response.getSigma3(i).getCombineZhongchuiKuazhongXia());

            sigma3List.add(sigma3);
        }
        return sigma3List;
    }

    public List<Sigma4> calSigma4(Integer projectId, Integer sectionId) {
        Sigma4Response sigma4Response = grpcHolder.calculationBlockingStub().calSigma4(Sigma4Request.newBuilder().build());
        List<Sigma4> sigma4List = Lists.newArrayList();
        for (int i = 0; i < sigma4Response.getSigma4List().size(); i++) {
            Sigma4 sigma4 = new Sigma4();
            sigma4.setProjectId(projectId);
            sigma4.setSectionId(sectionId);

            sigma4.setZhonggongZhizuo(sigma4Response.getSigma4(i).getZhonggongZhizuo());
            sigma4.setZhonggongKuazhong(sigma4Response.getSigma4(i).getZhonggongKuazhong());

            sigma4.setZhongchuiZhizuo(sigma4Response.getSigma4(i).getZhongchuiZhizuo());
            sigma4.setZhongchuiKuazhong(sigma4Response.getSigma4(i).getZhongchuiKuazhong());

            sigma4.setCombineAllowStress(sigma4Response.getSigma4(i).getCombineAllowStress());
            sigma4.setCombineZhonggongZhizuo(sigma4Response.getSigma4(i).getCombineZhonggongZhizuo());
            sigma4.setCombineZhonggongKuazhong(sigma4Response.getSigma4(i).getCombineZhonggongKuazhong());
            sigma4.setCombineZhongchuiZhizuo(sigma4Response.getSigma4(i).getCombineZhongchuiZhizuo());
            sigma4.setCombineZhongchuiKuazhong(sigma4Response.getSigma4(i).getCombineZhongchuiKuazhong());

            sigma4List.add(sigma4);
        }
        return sigma4List;
    }

    public List<ShearingStress> calShearingStress(Integer projectId, Integer sectionId) {
        ShearingStressResponse shearingStressResponse = grpcHolder.calculationBlockingStub().calShearingStress(ShearingStressRequest.newBuilder().build());
        List<ShearingStress> shearingStressList = Lists.newArrayList();
        for (int i = 0; i < shearingStressResponse.getShearingStressList().size(); i++) {
            ShearingStress shearingStress = new ShearingStress();
            shearingStress.setProjectId(projectId);
            shearingStress.setSectionId(sectionId);
            shearingStress.setZhongchuiMax(shearingStressResponse.getShearingStress(i).getZhongchuiMax());
            shearingStress.setZhonggongMax(shearingStressResponse.getShearingStress(i).getZhonggongMax());
            shearingStress.setShearingStress(shearingStressResponse.getShearingStress(i).getShearingStress());
            shearingStressList.add(shearingStress);
        }
        return shearingStressList;
    }

    /**
     * 舱壁板材校核
     *
     * @param bulkheadId           舱壁id
     * @param shipParam            船舶参数
     * @param bulkheadCompartments 区间数据
     * @return
     */
    public BulkheadCheckResult calBulkheadCheck(Integer bulkheadId, ShipParam shipParam, List<BulkheadCompartment> bulkheadCompartments) {
        List<Double> banWidth = Lists.newArrayList();
        List<Double> banThick = Lists.newArrayList();
        List<Double> cangbiBancailiao = Lists.newArrayList();
        for (BulkheadCompartment bulkheadCompartment : bulkheadCompartments) {
            banWidth.add(bulkheadCompartment.getPlateWidth());
            banThick.add(bulkheadCompartment.getPlateThickness());
            cangbiBancailiao.add(Double.parseDouble(bulkheadCompartment.getMaterial()));
        }
        CompartmentBulkheadSheetResponse compartmentBulkheadSheetResponse = grpcHolder.calculationBlockingStub().calCompartmentBulkheadSheet(CompartmentBulkheadSheetRequest.newBuilder()
                .addAllBanWidth(banWidth)
                .addAllBanThick(banThick)
                .addAllCangbiBancailiao(cangbiBancailiao)
                .build());
        BulkheadCheckResult bulkheadCheckResult = new BulkheadCheckResult();
        bulkheadCheckResult.setBulkheadId(bulkheadId);
        bulkheadCheckResult.setProjectId(shipParam.getProjectId());
        bulkheadCheckResult.setStrdeckdistrict(Lists.newArrayList(compartmentBulkheadSheetResponse.getStrdeckdistrictList()));
        bulkheadCheckResult.setDisload(Lists.newArrayList(compartmentBulkheadSheetResponse.getDisloadList()));
        bulkheadCheckResult.setLgvList(Lists.newArrayList(compartmentBulkheadSheetResponse.getLgvListList()));
        bulkheadCheckResult.setUList(Lists.newArrayList(compartmentBulkheadSheetResponse.getUListList()));
        bulkheadCheckResult.setChi1List(Lists.newArrayList(compartmentBulkheadSheetResponse.getChi1ListList()));
        bulkheadCheckResult.setChi2List(Lists.newArrayList(compartmentBulkheadSheetResponse.getChi2ListList()));
        bulkheadCheckResult.setStressXlList(Lists.newArrayList(compartmentBulkheadSheetResponse.getStressXlListList()));
        bulkheadCheckResult.setStressKuozhong(Lists.newArrayList(compartmentBulkheadSheetResponse.getStressKuozhongList()));
        bulkheadCheckResult.setStressZhizuo(Lists.newArrayList(compartmentBulkheadSheetResponse.getStressZhizuoList()));
        bulkheadCheckResult.setShearAllow(Lists.newArrayList(compartmentBulkheadSheetResponse.getShearAllowList()));
        return bulkheadCheckResult;
    }

    /**
     * 计算T型材信息
     *
     * @param tProfiles
     * @return
     */
    public List<TProfile> calTProfileProperty(List<TProfile> tProfiles) {
        List<TProfile> result = Lists.newArrayList();
        List<String> list = tProfiles.stream().map(TProfile::getModel).toList();
        TProfileResponse tProfileResponse = grpcHolder.calculationBlockingStub().calTProfileProperty(TProfileRequest
                .newBuilder()
                .addAllTProfilesTypeList(list)
                .build());
        if (tProfileResponse.getCode() == 1) {
            throw new RuntimeException("T型材信息计算异常");
        }
        List<com.iscas.biz.calculation.grpc.TProfile> tProfilesList = tProfileResponse.getTProfilesList();
        if (CollectionUtils.isEmpty(tProfilesList)) {
            return result;
        }
        result.addAll(tProfilesList.stream().map(tProfile -> {
            TProfile dbTProfile = new TProfile();
            dbTProfile.setModel(tProfile.getModel());
            dbTProfile.setKeelHeight(tProfile.getKeelHeight());
            dbTProfile.setKeelThickness(tProfile.getKeelThickness());
            dbTProfile.setDeckWidth(tProfile.getDeckWidth());
            dbTProfile.setDeckThickness(tProfile.getDeckThickness());
            dbTProfile.setSectionalArea(tProfile.getSectionalArea());
            dbTProfile.setMomentOfInertia(tProfile.getMomentOfInertia());
            dbTProfile.setCentroidPosition(tProfile.getCentroidPosition());
            return dbTProfile;
        }).toList());
        return result;
    }

}
