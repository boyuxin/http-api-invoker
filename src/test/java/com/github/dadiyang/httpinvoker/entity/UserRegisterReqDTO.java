package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;

/**
 * @author yanglvsen
 * @version Id: UserRegisterReqDTO.java, v 0.1 2021/12/3 15:13 yanglvsen Exp $$
 */
@Data
public class UserRegisterReqDTO {

    private static final long serialVersionUID = -4376170946741516029L;

    private String reqSystem;
    private String traceId;
    private String userNo;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 头像地址
     * 没有则使用默认头像地址
     */
    private String avatarImgUrl;


    /**
     * 主体编码
     */
    private String masterCode;

    /**
     * 主体下业务渠道
     */
    private String consultSource;

    /**
     * 账号类型（内部 外部）
     */
    private String accountType;

}
