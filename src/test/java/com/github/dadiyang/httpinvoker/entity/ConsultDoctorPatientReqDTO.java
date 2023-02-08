package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: wcy
 * @CreateDate: 2022/6/21 18:02
 */
@Data
public class ConsultDoctorPatientReqDTO implements Serializable {

    private static final long serialVersionUID = 6269212260926466634L;

    /**
     * 医生id
     */
    private String doctorId;


    /**
     * 用户id
     */
    private String ownerId;



    /**
     * 患者性别
     */
    private String patientGender;

    /**
     * 患者年龄
     */
    private String patientAge;


    /**
     * 患者姓名
     */
    private String patientName;


    /**
     * 患者id
     */
    private String patientId;






    /**
     * 聊天类型
     */
    private String chatType;



    /**
     * 实际接诊医生id
     */
    private String realDoctorId;

    /**
     * 医生主体
     */
    private String doctorDomainId;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 问诊方式
     */
    private String consultMode;

    /**
     * 主体编号
     */
    private String masterCode;

    /**
     * 渠道码
     */
    private String consultSource;

    /**
     * 业务入口
     */
    private String entranceSource;






    /**
     * 患者手机
     */
    private String patientTel;

    /**
     * 最新一次咨询时间
     */
    private Date lastConsultTime;

    /**
     * 客户标签
     */
    private String customerTag;

    /**
     * 诊断标签
     */
    private String illTag;

    /**
     * 诊断标签名称
     */
    private String illTagName;

    /**
     * 消息id
     */
    private String msgId;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息来源渠道:融云/企微宝/企微
     */
    private String msgSource;

    /**
     * 消息时间戳
     */
    private Long msgTimestamp;

    /**
     * 消息发送方
     */
    private String senderId;

    /**
     * 消息接收方
     */
    private String receiverId;

    /**
     * 消息展示内容
     */
    private String msgShowContent;

    /**
     * 消息未读标识
     */
    private String readFlag;

    /**
     * 用户侧最新消息时间戳
     */
    private Long userLatestMsgTime;

    /**
     * 医生侧消息未读标识
     */
    private String doctorReadFlag;

    /**
     * 医生侧最新消息时间戳
     */
    private Long doctorLatestMsgTime;

    /**
     * 平台标识
     * 老平台：OLD_PLATFORM
     * 新平台：NEW_PLATFORM
     */
    private String platformSource;

    /**
     * 来源平台
     */
    private String clientPlatform;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 来源类型  1-门诊  2-线上
     */
    private Integer sourceType;

    /**
     * 企微主体编号
     */
    private String corpId;

    /**
     * 是否绑定医生
     */
    private String bandDoctor;
}
