package com.github.dadiyang.httpinvoker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@ToString(callSuper = true)
public class HospitalDoctorDownInfoInputDTO implements Serializable {

    /**
     * 上游院内医生工号
     */
    private String upHisDoctorNo;

    /**
     * 上游院内医生id
     */
    private String upHisDoctorId;

    /**
     * 上游院内医院id
     */
    private String upHisHospitalSoid;

    /**
     * 平台医生id
     */
    private String downDoctorId;
    
    /**
     * 医院
     */
    private String downHospital;

    /**
     * 下游院内医生工号
     */
    private String downHisDoctorNo;

    /**
     * 下游院内医生id
     */
    private String downHisDoctorId;

    /**
     * 下游院内医院id
     */
    private String downHisHospitalSoid;

    /**
     * 双医问诊开关  ON-打开  OFF-关闭
     */
    private String status;

}