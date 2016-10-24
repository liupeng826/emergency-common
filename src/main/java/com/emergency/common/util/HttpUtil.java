package com.emergency.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
    public static <T> T sendRequest(String serviceUrl, TypeReference<T> typeRef, Map<String, Object> paramMap)
            throws IOException {
        String paramString = JsonUtil.toString(paramMap);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("body", paramString));
        System.out.println(paramString);

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(serviceUrl);
        httpost.setEntity(new UrlEncodedFormEntity(params, Charset.forName("UTF8")));

        HttpResponse response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();
        String jsonResponse = EntityUtils.toString(entity);

        System.out.println(jsonResponse);

        return JsonUtil.objFromString(jsonResponse, typeRef);
    }

    public static boolean sendJson(String url, String params, Map<String, Object> map) throws Exception {
        StringEntity stringEntity = new StringEntity(params.toString(), Charset.forName("UTF8"));
        stringEntity.setContentType("application/json");

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        httpost.setEntity(stringEntity);
        try {
            HttpResponse response = httpClient.execute(httpost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                map.put(WebUtils.Result.REASON, "HTTP failed: " + response.getStatusLine());
                return false;
            }

            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));
            return true;

        } finally {
            httpost.releaseConnection();
        }
    }
    
    public static String send(String url,  Map<String, Object> header,List<BasicNameValuePair> nvps, Map<String, Object> map) throws Exception {
        UrlEncodedFormEntity entityParam =new UrlEncodedFormEntity(nvps,Charset.forName("UTF8"));
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        if(header != null){
            Iterator<String> iter = header.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                Object value = header.get(key);
                httpost.addHeader(key, value.toString());
            }
        }
        httpost.setEntity(entityParam);
        try {
            HttpResponse response = httpClient.execute(httpost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                map.put(WebUtils.Result.REASON, "HTTP failed: " + response.getStatusLine());
                return null;
            }
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } finally {
            httpost.releaseConnection();
        }
    }

    public static String sendJson(String url, Map<String, Object> header, String params, Map<String, Object> map)
            throws Exception {
        StringEntity stringEntity = new StringEntity(params.toString(), Charset.forName("UTF8"));
        stringEntity.setContentType("application/json");

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        Iterator<String> iter = header.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            Object value = header.get(key);
            httpost.addHeader(key, value.toString());
        }
        httpost.setEntity(stringEntity);
        try {
            HttpResponse response = httpClient.execute(httpost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                map.put(WebUtils.Result.REASON, "HTTP failed: " + response.getStatusLine());
                return null;
            }

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);

        } finally {
            httpost.releaseConnection();
        }
    }

    public static String sendGetJson(String url,Map<String, Object> header, Map<String, Object> map) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httGet = new HttpGet(url);
        Iterator<String> iter = header.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            Object value = header.get(key);
            httGet.addHeader(key, value.toString());
        }
        try {
            HttpResponse response = httpClient.execute(httGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                map.put(WebUtils.Result.REASON, "HTTP failed: " + response.getStatusLine());
                return null;
            }

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } finally {
            httGet.releaseConnection();
        }
    }

    public static String delete(String url, Map<String, Object> header, Map<String, Object> map) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpDelete httpDelete = new HttpDelete(url);
        Iterator<String> iter = header.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            Object value = header.get(key);
            httpDelete.addHeader(key, value.toString());
        }
        try {
            HttpResponse response = httpClient.execute(httpDelete);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                map.put(WebUtils.Result.REASON, "HTTP failed: " + response.getStatusLine());
                return null;
            }

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);

        } finally {
            httpDelete.releaseConnection();
        }
    }
    public static String sendPutJson(String url, Map<String, Object> header, String params, Map<String, Object> map)
            throws Exception {
        StringEntity stringEntity = new StringEntity(params.toString(), Charset.forName("UTF8"));
        stringEntity.setContentType("application/json");

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPut httpost = new HttpPut(url);
        Iterator<String> iter = header.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            Object value = header.get(key);
            httpost.addHeader(key, value.toString());
        }
        httpost.setEntity(stringEntity);
        try {
            HttpResponse response = httpClient.execute(httpost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                map.put(WebUtils.Result.REASON, "HTTP failed: " + response.getStatusLine());
                return null;
            }

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);

        } finally {
            httpost.releaseConnection();
        }
    }




}
