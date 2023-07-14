package com.github.dadiyang.httpinvoker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author xuess
 * @Date 2021/12/4 12:33
 * @Version 1.0
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ModifyConsultOrderReqDTO extends BaseReqDTO {

    private static final long serialVersionUID = 8775654391426209622L;

    /**
     * 问诊单号
     */
    private String diagnoseId;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 患者id
     */
    private String patientId;

    /**
     * 实际医生号
     */
    private String realDoctorId;

    /**
     * 接诊时间 医生接诊时间
     */
    private Date consultStartTime;

    /**
     * 问诊状态 待接诊/接诊中/超时关闭/手动取消关闭/问诊结束/拒诊
     */
    private String consultStatus;

    /**
     * 支付订单号 关联订单信息
     */
    private String payOrderId;

    /**
     * 主体下业务渠道
     */
    private String consultSource;

    /**
     * 取消问诊原因/拒绝接诊原因
     */
    private String reason;

    /**
     * 原问诊单状态
     */
    private String originConsultStatus;
}
