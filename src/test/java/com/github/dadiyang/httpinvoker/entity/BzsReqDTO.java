package com.github.dadiyang.httpinvoker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 邦指数
 *
 * @author micro
 */
@Setter
@Getter
@ToString(callSuper = true)
public class BzsReqDTO extends BaseReqDTO {

    private static final long serialVersionUID = -3285772952924087608L;

    /**
     * 问诊单号
     */
    private String diagnoseId;

}
