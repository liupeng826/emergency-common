package com.emergency.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtherUtil {

    public static boolean verifyPhone(String phone) {
        String regExp = "^[1][0-9]{10}$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(phone);

        return matcher.find();
    }

    public static boolean verifyName(String realName) {
        String regExp = "^(([\\u4E00-\\u9FA5]|\\u00b7){2,10})$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(realName);

        return matcher.find();
    }

    public static String addStar(String originString, int start, int length) {
        String firstString=originString.substring(0,start);
        String lastString=originString.substring(start+length,originString.length());
        String replaceString="";
        for (int i = 0; i < length; i++) {
            replaceString=replaceString+"*";
        }

        return firstString+replaceString+lastString;
    }
}
