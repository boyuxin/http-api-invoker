package com.github.dadiyang.httpinvoker.interfaces;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * @author boyuxin
 * @description
 * @date 2022/12/29 16:03
 */
public class My111Test {
    public static void main(String[] args) {
//        BigDecimal bigDecimalQuick = new BigDecimal("10");
//        BigDecimal bigDecimalFamous = new BigDecimal("1");
//
//        BigDecimal multiply = bigDecimalFamous.add(bigDecimalQuick).multiply(new BigDecimal("0"));
//        System.out.println(multiply.doubleValue() > bigDecimalFamous.doubleValue());
//        System.out.println(multiply.toString());
//
//        float v = bigDecimalFamous.divide(bigDecimalFamous.add(bigDecimalQuick), RoundingMode.HALF_UP).floatValue();
//        System.out.println(v);
//
//        HashMap<String, String> stringStringHashMap = new HashMap<>();
//        String s = stringStringHashMap.get("222");
//        System.out.println(s);


//        String s = "pages/webView/index?url=https%3A%2F%2Fh5.test.fosun-creative.com%2Ffe-medicine-mall-c%2Fpages%2Fdetail%2Fdetail%3FskuId%3D%s%26supplierId%3D%s%26globalActivityId%3D%s%26globalChannelId%3D%s%26doctorId%3D%s%26sendType%3DmustApplet";
//        String pushAppletUrl = String.format(s, "33333", "44444",
//                "55555", "66666", "77777");
//        System.out.println(pushAppletUrl);


        for (int i = 1; i < 3592244; i+=0) {
            int j = i + 10000;
            String s = "update t_consult_doctor_patient set updated_by = 'doctorPatientSynchro' where  updated_by =  'CHAN_YI_DATA_CLEAN' and created_by = 'doctorPatientSynchro' and id between "+i+" and " +j +";";
            System.out.println(s);
            i = j;
        }

    }
}
