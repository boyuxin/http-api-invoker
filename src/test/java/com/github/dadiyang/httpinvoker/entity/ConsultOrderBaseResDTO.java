package com.github.dadiyang.httpinvoker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 问诊单基础响应类
 *
 * @Author xuess
 * @Date 2021/12/4 11:13
 * @Version 1.0
 */
@Setter
@Getter
@ToString
public class ConsultOrderBaseResDTO implements Serializable {

    private static final long serialVersionUID = 8729506181135666451L;

    /**
     * 删除标识
     */
    private String usableFlag;

    /**
     * 问诊单号
     */
    private String diagnoseId;

    /**
     * 患者id
     */
    private String patientId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 患者年龄
     */
    private String patientAge;

    /**
     * 患者性别
     */
    private String patientGender;

    /**
     * 所属人id：用户号userId / 群号 groupId
     */
    private String ownerId;

    /**
     * 聊天类型(单人：SINGLE，群聊：GROUP)
     */
    private String chatType;

    /**
     * 医生号
     */
    private String doctorId;

    /**
     * 实际医生号
     */
    private String realDoctorId;

    /**
     * 医生主体 id
     */
    private String doctorDomainId;

    /**
     * 医生角色
     */
    private String doctorRole;

    /**
     * 主诉
     */
    private String userDescribe;

    /**
     * 问诊渠道 (快速问诊/家医问诊/名医问诊)
     */
    private String businessType;

    /**
     * 问诊方式 (图文/电话/视频)
     */
    private String consultMode;

    /**
     * 订单类型 (问诊单/咨询单)
     */
    private String orderType;

    /**
     * 问诊单类型 (直接问诊/转诊transfer)
     */
    private String consultType;

    /**
     * 接诊时间 医生接诊时间
     */
    private Date consultStartTime;

    /**
     * 结束时间 问诊单结束时间
     */
    private Date consultEndTime;

    /**
     * 预期结束时间
     */
    private Date planEndTime;

    /**
     * 订单来源类型(小程序/APP（ios ，android）/微信/H5）
     */
    private String appType;

    /**
     * 问诊状态 待接诊/接诊中/超时关闭/手动取消关闭/问诊结束
     */
    private String consultStatus;

    /**
     * 支付订单号 关联订单信息
     */
    private String payOrderId;

    /**
     * 主体编码 多租户 多主体
     */
    private String masterCode;

    /**
     * 主体下业务渠道
     */
    private String consultSource;

    /**
     * 业务入口
     */
    private String entranceSource;

    /**
     * 初始医生id
     */
    private String originDoctorId;

    /**
     * 初始问诊id
     */
    private String originDiagnoseId;

    /**
     * 咨询摘要
     */
    private String roundup;

    /**
     * 总结建议
     */
    private String summary;


    /**
     * 预约状态
     */
    private String subscribeStatus;

    /**
     * 预约时间
     */
    private Date subscribeBeginTime;

    /**
     * 预约结束时间
     */
    private Date subscribeEndTime;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 数据来源
     */
    private String platformSource;

    /**
     * 咨询订单id（老平台）
     */
    private Long consultOrderId;

    /**
     * 聊天列表id（老平台）
     */
    private Long catalogId;

    /**
     * 开单埋点:活动id
     */
    private String globalActivityId;

    /**
     * 开单埋点:渠道id
     */
    private String globalChannelId;

    /**
     * 转诊类型
     */
    private String transferType;

    /**
     * 问诊支付类型：B2B类型
     */
    private String orderPayType;

    /**
     * 机器人id
     */
    private String robotId;

    /**
     * 额外参数值
     */
    private String externalValue;

    /**
     * 额外参
     */
    private String external;

    /**
     * 全局编码
     */
    private String globalUserCode;

    /**
     * 一体化P
     */
    private String patId;

}
