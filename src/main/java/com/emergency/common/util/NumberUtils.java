package com.emergency.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Jiliang Yu
 */
public class NumberUtils {

    public static String longAmountToString(Long longAmount){
        NumberFormat numberFormat = new DecimalFormat("0.00");
        return numberFormat.format(longAmount / 100);
    }

    public static Long stringAmountToLong(String strAmount) {
        return Long.valueOf(strAmount.replace(".", ""));
    }


    public static boolean checkMinStep(BigDecimal bigDecimalNumber,BigDecimal baseNumber) {
        int pointIndex = baseNumber.toString().indexOf(".");
        if (pointIndex != -1) {
            int powIndex = baseNumber.toString().length() - pointIndex - 1;
            BigDecimal ratio = new BigDecimal(Math.pow(10, powIndex));
            if (bigDecimalNumber.multiply(ratio).remainder(baseNumber.multiply(ratio)).compareTo(BigDecimal.ZERO) == 0) {
                return true;
            }
        } else {
            if (bigDecimalNumber.remainder(baseNumber).equals(BigDecimal.ZERO)) {
                return true;
            }
        }
        return false;
    }
}
