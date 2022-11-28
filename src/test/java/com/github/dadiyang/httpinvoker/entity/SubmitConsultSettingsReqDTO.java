package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author yanglvsen
 * @version Id: SubmitConsultSettingsReqDTO.java, v 0.1 2022/4/27 9:55 yanglvsen Exp $$
 */
@Data
@ToString(callSuper = true)
public class SubmitConsultSettingsReqDTO extends BaseReqDTO {

    private static final long serialVersionUID = -636754184165168841L;

    /**
     * 咨询配置编码
     */
    private String consultSettingsNo;

    /**
     * 医生id
     */
    private String doctorId;

    /**
     * 主体编码
     */
    private String masterCode;

    /**
     * 问诊方式 (图文/视频/电子处方随访)  "IMAGE_TEXT", "TELEPHONE", "VIDEO", "ELECTRONIC_PRESCRIPTION"
     */
    private String consultMode;

    /**
     * 配置开关 "ON", "OFF"
     */
    private String settingsSwitch;

    /**
     * 单次服务定价
     */
    private Long servicePrice;

    /**
     * 退单超时时长(单位：小时)
     */
    private String chargebackTime = "48";

    /**
     * 接单时间段
     */
    private List<ConsultTimeConfigReqDTO> consultTimeConfigReqDTOList;


    /**
     * 清洗标识
     */
    private String cleanFlag;

    List<AssetsPackagePropertiesReqDTO> assetsPackagePropertiesClean;

}
