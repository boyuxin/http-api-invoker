package com.github.dadiyang.httpinvoker.entity.convert;

import com.github.dadiyang.httpinvoker.entity.*;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ConsultSettingsConvert {

    public static SubmitConsultSettingsReqDTO getSubmitConsultSettingsReqDTO(String traceId, QueryConsultSettingsResDTO resDTO , List<CleanDoctorData> cleanDoctorData) {
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
        reqDTO.setAutomaticReplyScript(resDTO.getAutomaticReplyScript());
        reqDTO.setServiceDuration(resDTO.getServiceDuration());
        reqDTO.setSupplierId(1296267);

        if ( cleanDoctorData == null || CollectionUtils.isEmpty(cleanDoctorData)){
            ArrayList<AssetsPackagePropertiesReqDTO> assetsPackagePropertiesReqDTOS = new ArrayList<>();
            //图文问诊
            AssetsPackagePropertiesReqDTO assetsPackagePropertiesReqDTO = new AssetsPackagePropertiesReqDTO();
            assetsPackagePropertiesReqDTO.setAssetsPackageType("IMAGE_TEXT");
            assetsPackagePropertiesReqDTO.setAssetsPackageCode("620221005515");
            assetsPackagePropertiesReqDTOS.add(assetsPackagePropertiesReqDTO);
            //视频问诊
            AssetsPackagePropertiesReqDTO assetsPackagePropertiesReqDTO2 = new AssetsPackagePropertiesReqDTO();
            assetsPackagePropertiesReqDTO2.setAssetsPackageType("VIDEO");
            assetsPackagePropertiesReqDTO2.setAssetsPackageCode("620221005514");
            assetsPackagePropertiesReqDTOS.add(assetsPackagePropertiesReqDTO2);

            //TODO 电话问诊
            AssetsPackagePropertiesReqDTO assetsPackagePropertiesReqDTO3 = new AssetsPackagePropertiesReqDTO();
            assetsPackagePropertiesReqDTO3.setAssetsPackageType("TELEPHONE");
            assetsPackagePropertiesReqDTO3.setAssetsPackageCode("620231006593");
            assetsPackagePropertiesReqDTOS.add(assetsPackagePropertiesReqDTO3);
            reqDTO.setAssetsPackagePropertiesClean(assetsPackagePropertiesReqDTOS);
        }else {
            ArrayList<AssetsPackagePropertiesReqDTO> assetsPackagePropertiesReqDTOS = new ArrayList<>();
            for (CleanDoctorData cleanDoctor :cleanDoctorData ) {
                AssetsPackagePropertiesReqDTO assetsPackagePropertiesReqDTO = new AssetsPackagePropertiesReqDTO();
                assetsPackagePropertiesReqDTO.setAssetsPackageType(cleanDoctor.getConsultMode());
                assetsPackagePropertiesReqDTO.setAssetsPackageCode(cleanDoctor.getAssetProductCode());
                assetsPackagePropertiesReqDTOS.add(assetsPackagePropertiesReqDTO);
            }
            reqDTO.setAssetsPackagePropertiesClean(assetsPackagePropertiesReqDTOS);
        }

        return reqDTO;
    }

    public static void main(String[] args) {
        System.out.println("1");

        try {
            System.out.println("2");
            SubmitConsultSettingsReqDTO submitConsultSettingsReqDTO = new SubmitConsultSettingsReqDTO();
            submitConsultSettingsReqDTO = null;
            submitConsultSettingsReqDTO.getSettingsSwitch();
        }catch (Exception e){
            System.out.println("3");
        }finally {
            System.out.println("4");

        }
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
