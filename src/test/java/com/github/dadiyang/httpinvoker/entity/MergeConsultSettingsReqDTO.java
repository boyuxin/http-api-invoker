package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Describe:
 * @Author: wangch
 * @Date: 2022/6/16 10:23
 */
@Data
public class MergeConsultSettingsReqDTO implements Serializable {

    /**
     * 医生id
     */
    private Set<String> doctorIds;
}
