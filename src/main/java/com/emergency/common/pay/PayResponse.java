package com.emergency.common.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PayResponse extends Response {
    // app id
    private String appId;
    // 支付用户ID
    private String userId;
    // 支付渠道
    private Long channelId;
    // 金额
    private BigDecimal amount;
    // 外部订单序列号
    private String transferOutOrderNo;
    // 手续费
    private BigDecimal charge;
    // 支付序列号
    private String payApplicationNo;
    // 支付时间
    private Date payTime;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public String getPayApplicationNo() {
        return payApplicationNo;
    }

    public void setPayApplicationNo(String payApplicationNo) {
        this.payApplicationNo = payApplicationNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

}
