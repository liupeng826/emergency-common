package com.emergency.common.dto;

import java.util.HashMap;

/**
 * Created by lr on 15/11/17.
 */
public class SmsRequestDTO {

    private String mobile;

    private HashMap<String,String> paramMap;

    private Long templateId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public HashMap<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(HashMap<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
}
