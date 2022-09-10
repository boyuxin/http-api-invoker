package com.github.dadiyang.httpinvoker.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
public class DemoData {
    private String callId;
    private String diagnoseId;
    private String docId;

    private String success;
    private String result;
    private String errorCode;
    private String errorMsg;
    private String exception;
//    private String doubleData;

    public static void main(String[] args) {

        //update t_consult_order set updated_at = '2022-08-26 00:00:00' where id between 1190119 and 1200119 and doctor_id in ( '261742' , '261740');
        int index = 1400000;
        for (int i = 1400000; i < 1551436; i++) {
            i=10000+index;
            System.out.println("update t_consult_order set updated_at = '2022-08-26 00:00:00' where id between "+index+" and "+i+" and doctor_id in ( '261742' , '261740');");
            index = i;
        }
    }

}
