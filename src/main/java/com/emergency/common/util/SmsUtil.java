package com.emergency.common.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SmsUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(SmsUtil.class);

    private String smsUrl = "http://10.211.4.110:8302/sms-service/api/smsnotice";

    private String smsNewUrl = "http://10.211.4.111:8302/sms-service/api/sms";


    private int connectTimeout = 3000;

    private int socketTimeout = 3000;

    @Value("${sms.url}")
    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    @Value("${sms.connectTimeout}")
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    @Value("${sms.socketTimeout}")
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }


    public void sendSms(String mobile, String sms) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(smsUrl + "?tel=" + mobile + "&content=" + URLEncoder.encode(sms, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout).build();
        httpGet.setConfig(requestConfig);
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

            String response = EntityUtils.toString(httpResponse.getEntity(), Charset.forName("utf-8"));

            LOGGER.debug("发送短信: {} 给手机: {}, 返回值: {}", new Object[]{mobile, response});
        } catch (IOException e) {
            LOGGER.error("发送短信给手机: " + mobile + " 失败", e);
        }
    }

    public void sendSms(String mobile, Long templateId, HashMap<String, String> hashMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(smsNewUrl);
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            nameValuePairs.add(new BasicNameValuePair("mobile", mobile));
            nameValuePairs.add(new BasicNameValuePair("templateId", templateId.toString()));
            hashMap.put("mobile", mobile);
            hashMap.put("templateId", templateId.toString());
            String jsonString = "";
            try {
                jsonString = JsonUtil.toJson(hashMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringEntity se = new StringEntity(jsonString,"utf-8");
            se.setContentType("application/json");
            se.setContentEncoding("UTF-8");
            httpPost.setEntity(se);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

            String response = EntityUtils.toString(httpResponse.getEntity(), Charset.forName("utf-8"));
            LOGGER.debug("发送短信: {} 给手机: {}, 返回值: {}", new Object[]{mobile, response});
        } catch (IOException e) {
            LOGGER.error("发送短信给手机: " + mobile + " 失败", e);
        }
    }

    @Value("${sms.newUrl}")
    public void setSmsNewUrl(String smsNewUrl) {
        this.smsNewUrl = smsNewUrl;
    }
}
