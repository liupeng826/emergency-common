package com.emergency.common.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserChannelChargeRequest  extends Request<UserChannelChargeResponse> {
    // 支付用户ID
    private String userId;
    // 支付渠道
    private Long channelId;


    @Override
    public Class<UserChannelChargeResponse> getResponseClass() {
        return UserChannelChargeResponse.class;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

}
