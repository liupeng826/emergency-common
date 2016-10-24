package com.emergency.common.pay;

import com.emergency.common.util.StringUtil;

import java.util.*;

/**
 * 签名加密类
 */
public class PaySignature {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;



    /**
     * 对参数列表根据Key正序排序并转化为URL请求的参数格式
     * @param sortedParams 需要排序的参数
     * @return
     */
    public static String getSignContent(Map<String, Object> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            if (key.equals(PayConstants.SIGN)) continue;
            String value = String.valueOf(sortedParams.get(key));
            if (StringUtil.areNotEmpty(key, value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }


}
