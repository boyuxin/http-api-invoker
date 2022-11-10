package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Describe:
 * @Author: wangch
 * @Date: 2022/6/16 10:23
 */
@Data
public class CloseConsultOrder implements Serializable {

    /**
     * 医生id
     */
    private String diagnoseId;
    private String roundup = "暂无特别，可详见咨询记录";
    private String summary= "无特别建议";
    private List<Dats> sumIds;

    public CloseConsultOrder() {
        Dats dats = new Dats();
        ArrayList<Dats> objects = new ArrayList<>();
        objects.add(dats);
        this.sumIds = objects;
    }
}
@Data
class Dats{
    private int sumId = 145962;
    private String sumName = "咨询";

}
