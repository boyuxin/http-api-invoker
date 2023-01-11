package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author xuess
 * @Date 2022/7/31 12:36
 * @Version 1.0
 */
@Data
public class RefundReqDTO implements Serializable {

    private static final long serialVersionUID = 3558259093042149152L;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    private LocalDateTime updatedTime;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 订单类型 1.咨询订单
     */
    private String orderType;

    /**
     * 订单id
     */
    private String orderNo;

    /**
     * 支付金额
     */
    private BigDecimal payAmount;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 支付订单号 支付时唯一标识
     */
    private String payNo;

    /**
     * 退款成功时间
     */
    private LocalDateTime payTime;

    /**
     * 退款订单号 退款唯一标识
     */
    private String refundNo;

    /**
     * 退款成功时间
     */
    private LocalDateTime refundTime;

    /**
     * 状态 0-待支付 1-支付成功，2-支付失败 3-待退款 4-退款中 5-退款成功 6-退款失败
     */
    private String status;

    /**
     * 订单号
     */
    private String payOrderId;

    /**
     * 问诊单号
     */
    private String diagnoseId;

}
