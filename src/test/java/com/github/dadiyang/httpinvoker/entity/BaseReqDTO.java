package com.github.dadiyang.httpinvoker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author xuess
 * @Date 2021/12/4 11:06
 * @Version 1.0
 */
@Setter
@Getter
@ToString
public class BaseReqDTO implements Serializable {

    private static final long serialVersionUID = 8428000061421344080L;

    /**
     * 请求系统
     */
    private String reqSystem;

    /**
     * 日志号
     */
    private String traceId;

}
