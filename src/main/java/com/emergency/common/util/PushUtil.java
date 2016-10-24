package com.emergency.common.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PushUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(PushUtil.class);

    private static String pushUrl = "https://www.hnrmb.com/api/internal/services/notification";

    private static int socketTimeout = 60000;
    private static int connectTimeout = 3000;
    private static int contentLength = 2000;

    public static void push(List<String> userIds, String message) throws IOException {
        HttpClient httpClient = HttpClients.custom().build();

        HttpPost httpPost = new HttpPost(pushUrl);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("User-Agent", "fund-web/2.0.6");

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userIds", userIds);
        long time = System.currentTimeMillis();
        String notification = message;

        LOGGER.debug("{} - push {} to {}", time, notification, JsonUtil.toJson(userIds));

        if(notification.length() > contentLength){
            paramMap.put("notification", notification.substring(0, contentLength));
        } else {
            paramMap.put("notification", notification);
        }
        paramMap.put("host", "System");
        paramMap.put("marker", "notification");

        httpPost.setEntity(new StringEntity(JsonUtil.toJson(paramMap), ContentType.APPLICATION_JSON));

        try {
            HttpResponse response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity());
            LOGGER.debug("{} - push result:{}", time, result);
        } catch (IOException e) {
            LOGGER.error(String.format("%s - push failed.", time), e);
        }
    }
}
