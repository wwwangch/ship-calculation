package com.iscas.biz.calculation.service;

import com.iscas.biz.calculation.entity.db.sigma.*;
import com.iscas.biz.calculation.entity.dto.sigma.Sigma1DTO;

import java.io.IOException;
import java.util.List;

public interface StrengthService {
    List<Sigma1> getSigma1(Integer projectId, Integer sectionId);

    List<Sigma2> getSigma2(Integer projectId, Integer sectionId);

    List<Sigma3> getSigma3(Integer projectId, Integer sectionId);

    List<Sigma4> getSigma4(Integer projectId, Integer sectionId);

    List<ShearingStress> getShearingStress(Integer projectId, Integer sectionId);


    List<Sigma1> calSigma1(Integer projectId, Integer sectionId) throws IllegalAccessException;


    List<Sigma2> calSigma2(Integer projectId, Integer sectionId) throws IllegalAccessException;

    List<Sigma3> calSigma3(Integer projectId, Integer sectionId) throws IllegalAccessException;

    List<Sigma4> calSigma4(Integer projectId, Integer sectionId) throws IllegalAccessException;

    List<ShearingStress> calShearingStress(Integer projectId, Integer sectionId) throws IllegalAccessException;
    void sigma1Export(Integer projectId, Integer sectionId)throws IOException;

    void sigma2Export(Integer projectId, Integer sectionId)throws IOException;

    void sigma3Export(Integer projectId, Integer sectionId)throws IOException;

    void sigma4Export(Integer projectId, Integer sectionId)throws IOException;

    void shearingStressExport(Integer projectId, Integer sectionId)throws IOException;
}
