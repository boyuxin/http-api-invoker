package com.github.dadiyang.httpinvoker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author xuess
 * @Date 2021/12/3 14:18
 * @Version 1.0
 */
@Setter
@Getter
@ToString(callSuper = true)
public class ConsultOrderResDTO extends ConsultOrderBaseResDTO {

    private static final long serialVersionUID = -5422506268611613496L;

    /**
     * 融云id
     */
    private String rongCloudUID;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 开单来源 患者/医生
     */
    private String openOrderSource;

    /**
     * 医生融云id
     */
    private String doctorRongCloudUID;

    /**
     * 医生姓名
     */
    private String doctorName;

    /**
     * 医生职称id
     */
    private String doctorPositionalId;

    /**
     * 医生职称名称
     */
    private String doctorProfessionalName;

    /**
     * 医生头像
     */
    private String doctorAvatar;

    /**
     * 科室名称
     */
    private String departmentName;
    
    /**
     * 跳转im地址
     */
    private String imUrl;


    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorMsg;
}
