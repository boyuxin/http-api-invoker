package com.github.dadiyang.httpinvoker.entity;

import lombok.Data;

/**
 * @author boyuxin
 * @description
 * @date 2024/1/4 15:43
 */
@Data
public class DataT {
    private String id;
    private String name;
    private boolean click;
    private String data;




    public DataT(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
