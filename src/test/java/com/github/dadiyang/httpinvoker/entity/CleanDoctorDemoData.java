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
public class CleanDoctorDemoData {

    private String callId;

    private String docId;
}
