package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author yanglvsen
 * @version Id: SubmitConsultSettingsReqDTO.java, v 0.1 2022/4/27 9:55 yanglvsen Exp $$
 */
@Data
@ToString(callSuper = true)
public class SubmitConsultSettingsExcelData extends BaseReqDTO {

    private static final long serialVersionUID = -636754184165168841L;

    /**
     * 医生id
     */
    private String doctorId;

    /**
     * 配置开关 "开通  ON", "关闭 OFF"
     */
    private String settingsSwitch;

    /**
     * 问诊方式 (图文/视频/电子处方随访)  "IMAGE_TEXT", "TELEPHONE", "VIDEO", "ELECTRONIC_PRESCRIPTION"
     * 图文 IMAGE_TEXT
     * 视频 VIDEO
     */
    private String consultMode;

    /**
     * 单次服务定价 元
     */
    private String servicePrice;

    public String getServicePrice() {
        if (servicePrice == null) {
            return "10000000";
        }
        BigDecimal num1 = new BigDecimal(servicePrice);
        BigDecimal num2 = new BigDecimal("100");
        BigDecimal multiply = num2.multiply(num1);
        return multiply.toString();
    }

    public String getSettingsSwitch() {
        if ("开通".equals(this.settingsSwitch)) {
            return "ON";
        } else if ("关闭".equals(this.settingsSwitch)) {
            return "OFF";
        }else {
            return null;
        }
    }

    public String getConsultMode() {
        if ("图文".equals(this.consultMode)) {
            return "IMAGE_TEXT";
        } else if ("视频".equals(this.consultMode)) {
            return "VIDEO";
        }else {
            return null;
        }
    }
}
