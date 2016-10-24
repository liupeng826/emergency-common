package com.emergency.common.constants;

/**
 * Created by sunxiaoyan on 15/12/29.
 */
public class KeyValueObject {
    private String key;

    private String value;

    private String tips;

    public KeyValueObject(String key, String value, String tips) {
        this.key = key;
        this.value = value;
        this.tips = tips;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
