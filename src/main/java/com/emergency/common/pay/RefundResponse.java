package com.emergency.common.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RefundResponse extends Response {
    // app id
    private String appId;
    // 回款用户ID
    private String userId;
    // 支付渠道
    private Long channelId;
    // 申请份额
    private BigDecimal vol;
    // 外部订单序列号
    private String transferOutOrderNo;
    // 手续费
    private BigDecimal charge;
    // 回款序列号
    private String refundApplicationNo;
    // 回款时间
    private Date refundTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }

    public String getTransferOutOrderNo() {
        return transferOutOrderNo;
    }

    public void setTransferOutOrderNo(String transferOutOrderNo) {
        this.transferOutOrderNo = transferOutOrderNo;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public String getRefundApplicationNo() {
        return refundApplicationNo;
    }

    public void setRefundApplicationNo(String refundApplicationNo) {
        this.refundApplicationNo = refundApplicationNo;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }
}
