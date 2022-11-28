package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;

/**
 * @author yanglvsen
 * @version Id: QueryMultiConsultSettingsReqDTO.java, v 0.1 2022/4/27 10:26 yanglvsen Exp $$
 */
@Data
public class QueryMultiConsultSettingsReqDTO extends BaseReqDTO {

    private static final long serialVersionUID = 178991503217484599L;

    /**
     * 医生id
     */
    private String doctorId;

    /**
     * 主体编码
     */
    private String masterCode = "FOSUN_HEALTH";

    /**
     * 是否返回组装包含默认值的数据
     * YES：是
     * NO：否
     */
    private String includeDefaultData = "YES";

}
