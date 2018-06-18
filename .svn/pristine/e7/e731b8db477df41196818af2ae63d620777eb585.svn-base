package org.nwnu.pub.util;

import java.math.BigDecimal;

/**
 * Created by 冯晓 on 2017/8/14.
 */
public class BigDecimalUtil {

    //让该类无法在外部实例化
    private BigDecimalUtil(){}

    public static BigDecimal add(BigDecimal v1, BigDecimal v2){
        return v1.add(v2);
    }

    public static BigDecimal sub(float v1, float v2){
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal mul(float v1, int v2){
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal div(float v1, float v2){
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP); //四舍五入，保留两位小数
    }

}
