package com.emergency.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

    public static final ObjectMapper jsonMapper = new ObjectMapper();

    public static String toJson(Object value) throws IOException {
        String json = null;
        try {
            if (value != null) {
                json = jsonMapper.writeValueAsString(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (json == null) {
            json = "{\"error\",\"nodata\"}";
        }
        return json;
    }


    public static <T> T jsonToBean(String json, Class<T> clazz) throws IOException {
        try {
            return jsonMapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonToMap(String json, TypeReference typeReference) throws IOException {
        try {
            return jsonMapper.readValue(json, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String,String> jsonToStringMap(String json) throws IOException {
        TypeReference typeReference = new TypeReference<HashMap<String,String>>(){};
        return jsonToMap(json,typeReference);
    }
    public static <T> T objFromString(String jsonStr, Class<T> cls)
            throws JsonMappingException, JsonParseException, IOException
    {
        org.codehaus.jackson.map.ObjectMapper om = new org.codehaus.jackson.map.ObjectMapper();
        return om.readValue(jsonStr, cls);
    }

    public static <T> T objFromString(String jsonStr, org.codehaus.jackson.type.TypeReference<T> typeReference)
            throws  JsonMappingException, JsonParseException, IOException
    {
        org.codehaus.jackson.map.ObjectMapper om = new org.codehaus.jackson.map.ObjectMapper();
        return om.readValue(jsonStr, typeReference);
    }

    public static String toString(Object obj)
            throws JsonMappingException, JsonGenerationException, IOException
    {
        org.codehaus.jackson.map.ObjectMapper om = new org.codehaus.jackson.map.ObjectMapper();
        return om.writeValueAsString(obj);
    }
}
