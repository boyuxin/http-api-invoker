package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;

/**
 * @author yanglvsen
 * @version Id: AssetsPackageProperties.java, v 0.1 2022/4/29 11:52 yanglvsen Exp $$
 */
@Data
public class AssetsPackagePropertiesReqDTO {

    /**
     * 权益包类型
     */
    private String assetsPackageType;

    /**
     * 权益包id
     */
    private String assetsPackageId;

    /**
     * 产品编码
     */
    private String assetsPackageCode;

}
