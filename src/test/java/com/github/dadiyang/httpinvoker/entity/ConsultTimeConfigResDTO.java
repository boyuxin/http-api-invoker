package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yanglvsen
 * @version Id: ConsultTimeConfigResDTO.java, v 0.1 2022/4/27 10:33 yanglvsen Exp $$
 */
@Data
public class ConsultTimeConfigResDTO implements Serializable {

    private static final long serialVersionUID = -6392154065269915940L;

    /**
     * 咨询配置编码
     */
    private String consultSettingsNo;

    /**
     * 接诊时间配置编码
     */
    private String consultTimeConfigNo;

    /**
     * 接诊时间设置类型(全部时间/每周/自定义)
     */
    private String configTimeType;

    /**
     * 每周几
     */
    private String dayOfWeek;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 删除标识
     * 可用：USABLE
     * 删除：UN_USABLE
     */
    private String usableFlag;

    /**
     * 启用:ENABLE
     * 禁用:DISABLE
     */
    private String status;

}
