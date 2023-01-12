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
        BigDecimal bigDecimalQuick = new BigDecimal("10");
        BigDecimal bigDecimalFamous = new BigDecimal("1");

        BigDecimal multiply = bigDecimalFamous.add(bigDecimalQuick).multiply(new BigDecimal("0"));
        System.out.println(multiply.doubleValue() > bigDecimalFamous.doubleValue());
        System.out.println(multiply.toString());

        float v = bigDecimalFamous.divide(bigDecimalFamous.add(bigDecimalQuick), RoundingMode.HALF_UP).floatValue();
        System.out.println(v);

        HashMap<String, String> stringStringHashMap = new HashMap<>();
        String s = stringStringHashMap.get("222");
        System.out.println(s);

    }
}
