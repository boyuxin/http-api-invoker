package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yanglvsen
 * @version Id: QueryConsultSettingsResDTO.java, v 0.1 2022/4/27 10:32 yanglvsen Exp $$
 */
@Data
public class QueryConsultSettingsResDTO implements Serializable {

    private static final long serialVersionUID = -8663986241474791380L;

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
     * 问诊方式 (图文/视频/电子处方随访)
     */
    private String consultMode;

    /**
     * 配置开关
     */
    private String settingsSwitch;

    /**
     * 单次服务定价
     */
    private Long servicePrice;

    /**
     * 商品Id
     */
    private String skuId;

    /**
     * 店铺id
     */
    private String storeNo;

    /**
     * 权益id
     */
    private String assetsOrderId;

    /**
     * 产品编号
     */
    private String productCode;

    /**
     * 图标地址
     */
    private String iconUrl;

    /**
     * 排序
     */
    private int sort;

    /**
     * 最近可约时间: 开始时间(yyyy-MM-dd HH:mm:ss)
     */
    private String latestSubscribeBeginTime;

    /**
     * 最近可约时间: 结束时间yyyy-MM-dd HH:mm:ss)
     */
    private String latestSubscribeEndTime;

    /**
     * 接单时间段
     */
    private List<ConsultTimeConfigResDTO> consultTimeConfigResDTOList;

    /**
     * 退单超时时长
     */
    private String chargebackTime;

    /**
     * 自动回复话术
     */
    private String automaticReplyScript;

    /**
     * 服务时长
     */
    private String serviceDuration;

}
