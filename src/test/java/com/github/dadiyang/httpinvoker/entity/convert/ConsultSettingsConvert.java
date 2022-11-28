package com.github.dadiyang.httpinvoker.entity.convert;

import com.github.dadiyang.httpinvoker.entity.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author boyuxin
 * @description
 * @date 2022/11/28 14:49
 */
public class ConsultSettingsConvert {

    public static SubmitConsultSettingsReqDTO getSubmitConsultSettingsReqDTO(String traceId, QueryConsultSettingsResDTO resDTO) {
        SubmitConsultSettingsReqDTO reqDTO = new SubmitConsultSettingsReqDTO();
        reqDTO.setDoctorId(resDTO.getDoctorId());
        reqDTO.setServicePrice(resDTO.getServicePrice());
        reqDTO.setConsultSettingsNo(resDTO.getConsultSettingsNo());
        reqDTO.setDoctorId(resDTO.getDoctorId());
        reqDTO.setMasterCode("FOSUN_HEALTH");
        reqDTO.setConsultMode(resDTO.getConsultMode());
        reqDTO.setSettingsSwitch(resDTO.getSettingsSwitch());
        reqDTO.setServicePrice(StringUtils.isEmpty(resDTO.getServicePrice()) ? 20000 : resDTO.getServicePrice());
        reqDTO.setConsultTimeConfigReqDTOList(boListConvert2ConsultTimeConfigResDTOList(resDTO.getConsultTimeConfigResDTOList()));
        reqDTO.setChargebackTime(resDTO.getChargebackTime());
        reqDTO.setReqSystem("API-清洗医生数据");
        reqDTO.setTraceId(traceId);
        reqDTO.setCleanFlag("CLEAN");
        reqDTO.setChargebackTime(StringUtils.isEmpty(resDTO.getChargebackTime()) ? "48" : resDTO.getChargebackTime());

        ArrayList<AssetsPackagePropertiesReqDTO> assetsPackagePropertiesReqDTOS = new ArrayList<>();
        //图文问诊
        AssetsPackagePropertiesReqDTO assetsPackagePropertiesReqDTO = new AssetsPackagePropertiesReqDTO();
        assetsPackagePropertiesReqDTO.setAssetsPackageType("IMAGE_TEXT");
        assetsPackagePropertiesReqDTO.setAssetsPackageCode("A0020015");
        assetsPackagePropertiesReqDTOS.add(assetsPackagePropertiesReqDTO);
        //视频问诊
        AssetsPackagePropertiesReqDTO assetsPackagePropertiesReqDTO2 = new AssetsPackagePropertiesReqDTO();
        assetsPackagePropertiesReqDTO2.setAssetsPackageType("VIDEO");
        assetsPackagePropertiesReqDTO2.setAssetsPackageCode("A0040014");
        assetsPackagePropertiesReqDTOS.add(assetsPackagePropertiesReqDTO2);

        reqDTO.setAssetsPackagePropertiesClean(assetsPackagePropertiesReqDTOS);
        return reqDTO;
    }


    public static List<ConsultTimeConfigReqDTO> boListConvert2ConsultTimeConfigResDTOList(List<ConsultTimeConfigResDTO> consultTimeConfigBOList) {
        if (CollectionUtils.isEmpty(consultTimeConfigBOList)) {
            return Collections.emptyList();
        }
        return consultTimeConfigBOList.stream().map(ConsultSettingsConvert::boConvert2ConsultTimeConfigResDTO).collect(Collectors.toList());
    }

    public static ConsultTimeConfigReqDTO boConvert2ConsultTimeConfigResDTO(ConsultTimeConfigResDTO consultTimeConfigBO) {
        if (null == consultTimeConfigBO) {
            return null;
        }
        ConsultTimeConfigReqDTO consultTimeConfigResDTO = new ConsultTimeConfigReqDTO();
        consultTimeConfigResDTO.setConsultTimeConfigNo(consultTimeConfigBO.getConsultTimeConfigNo());
        consultTimeConfigResDTO.setConfigTimeType(consultTimeConfigBO.getConfigTimeType());
        consultTimeConfigResDTO.setDayOfWeek(consultTimeConfigBO.getDayOfWeek());
        consultTimeConfigResDTO.setStartTime(consultTimeConfigBO.getStartTime());
        consultTimeConfigResDTO.setEndTime(consultTimeConfigBO.getEndTime());
        consultTimeConfigResDTO.setStatus(consultTimeConfigBO.getStatus());
        return consultTimeConfigResDTO;
    }
}
