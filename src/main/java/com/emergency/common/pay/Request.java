package com.emergency.common.pay;


import org.hibernate.validator.constraints.NotEmpty;


public abstract class Request<T extends Response> extends PayObject {

    public abstract Class<T> getResponseClass();

    private String version = PayConstants.VERSION;

    @NotEmpty(message = "APP ID不能为空")
    private String appId;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
