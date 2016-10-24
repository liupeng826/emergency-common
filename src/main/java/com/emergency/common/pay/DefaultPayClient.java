package com.emergency.common.pay;


import com.emergency.common.util.JsonUtil;
import com.emergency.common.util.RsaUtils;
import com.emergency.common.util.WebUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * DefaultPayClient
 */
public class DefaultPayClient implements PayApiClient {

    private String serverUrl;
    private String privateKey;
    private String payPublicKey;
    private int connectTimeout = 3000;
    private int readTimeout = 15000;

    private static Map<Class, String> urlMap = new HashMap<Class, String>();

    static {
        urlMap.put(PayResponse.class, "pay");
        urlMap.put(UserChannelChargeResponse.class, "channel/charge");
        urlMap.put(TransactionQueryResponse.class, "transaction");
    }


    @Override
    public <T extends Response> T execute(Request<T> request) throws PayApiException {

        String jsonStr = null;
        Map<String, Object> requestMap = null;
        try{
            jsonStr = JsonUtil.toJson(request);
            requestMap = JsonUtil.jsonToBean(jsonStr, Map.class);
        }catch (IOException e){
            throw new PayApiException("请求支付接口数据转换失败", e);
        }
        requestMap.put(PayConstants.SIGN_TYPE, PayConstants.SIGN_TYPE_RSA);

        byte[] signContent = PaySignature.getSignContent(requestMap).getBytes(Charset.forName(PayConstants.CHARSET_UTF8));

        String sign = null;
        try {
            sign = RsaUtils.sign(signContent, this.privateKey);
        } catch (Exception e) {
            throw new PayApiException("请求支付接口报文签名失败", e);
        }
        requestMap.put(PayConstants.SIGN, sign);

        String res = null;
        try {
            res = WebUtils.doPost(serverUrl+"/"+urlMap.get(request.getResponseClass()), requestMap, connectTimeout, readTimeout);
        } catch (IOException e) {
            throw new PayApiException("请求支付接口异常", e);
        }

        T responseMap = null;
        try{
            responseMap = JsonUtil.jsonToBean(res, request.getResponseClass());
        }catch (IOException e){
            throw new PayApiException("请求支付接口结果转换对象失败", e);
        }
        return responseMap;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPayPublicKey() {
        return payPublicKey;
    }

    public void setPayPublicKey(String payPublicKey) {
        this.payPublicKey = payPublicKey;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public static Map<Class, String> getUrlMap() {
        return urlMap;
    }

    public static void setUrlMap(Map<Class, String> urlMap) {
        DefaultPayClient.urlMap = urlMap;
    }
}
