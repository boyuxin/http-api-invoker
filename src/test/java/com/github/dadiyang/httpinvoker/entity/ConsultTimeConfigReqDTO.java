package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yanglvsen
 * @version Id: ConsultTimeConfigReqDTO.java, v 0.1 2022/4/27 10:00 yanglvsen Exp $$
 */
@Data
@ToString(callSuper = true)
public class ConsultTimeConfigReqDTO implements Serializable {

    private static final long serialVersionUID = -2648803283485468202L;

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
     * 启用:ENABLE
     * 禁用:DISABLE
     */
    private String status;


}
