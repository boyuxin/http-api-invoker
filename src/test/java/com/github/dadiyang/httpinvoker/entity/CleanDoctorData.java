package com.github.dadiyang.httpinvoker.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
public class CleanDoctorData {
    private String docId;
    private String consultMode;
    private String servicePrice;
    private String settingsSwitch;
    private String chargebackTime;
    private String automaticReplyScript;
    private String serviceDuration;
    private String skuId;
    private String assetProductCode;

    private String remark;
}
