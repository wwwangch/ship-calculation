package com.iscas.biz.calculation.service;

import com.iscas.biz.calculation.entity.db.SlamLoad;
import com.iscas.biz.calculation.entity.db.StaticLoad;
import com.iscas.biz.calculation.entity.db.WaveLoad;
import com.iscas.biz.calculation.entity.dto.SlamLoadDTO;
import com.iscas.biz.calculation.entity.dto.StaticLoadDTO;
import com.iscas.biz.calculation.entity.dto.WaveLoadDTO;
import com.iscas.templet.common.ResponseEntity;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/27 23:16
 */
public interface LoadService {
    StaticLoad getStaticData(Integer projectId);

    WaveLoad getWaveData(Integer projectId);

    SlamLoad getSlamData(Integer projectId);

    StaticLoad calStaticLoad(StaticLoadDTO staticLoadDTO);

    WaveLoad calWaveLoad(WaveLoadDTO waveLoadDTO);

    SlamLoad calSlamLoad(SlamLoadDTO slamLoadDTO);
}
