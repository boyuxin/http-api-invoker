package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yanglvsen
 * @version Id: UserRegisterResDTO.java, v 0.1 2021/12/3 15:17 yanglvsen Exp $$
 */
@Data
public class UserRegisterResDTO implements Serializable {

    private static final long serialVersionUID = -2735390822927162193L;

    /**
     * token
     */
    private String token;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 主体编码
     */
    private String masterCode;

    /**
     * 主体下业务渠道
     */
    private String consultSource;

}
