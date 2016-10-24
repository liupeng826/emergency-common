package com.emergency.common.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionQueryResponse extends Response {
    // app id
    private String appId;
    // 支付用户ID
    private String userId;
    // 支付渠道
    private Long channelId;
    // 手续费
    private BigDecimal charge;

    private BigDecimal amount;

    private BigDecimal applicationVol;

    private String applicationNo;

    private String transferOutOrderNo;

    private Timestamp transactionTime;

    private Date transactionDate;

    private Date confirmedDate;

    private BigDecimal transferOutConfirmedVol;

    private BigDecimal confirmedAmount;

    private BigDecimal transferInConfirmedVol;

    private String fundTransactionNo;

    private Integer status;

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

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getApplicationVol() {
        return applicationVol;
    }

    public void setApplicationVol(BigDecimal applicationVol) {
        this.applicationVol = applicationVol;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getTransferOutOrderNo() {
        return transferOutOrderNo;
    }

    public void setTransferOutOrderNo(String transferOutOrderNo) {
        this.transferOutOrderNo = transferOutOrderNo;
    }

    public Timestamp getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Timestamp transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public BigDecimal getTransferOutConfirmedVol() {
        return transferOutConfirmedVol;
    }

    public void setTransferOutConfirmedVol(BigDecimal transferOutConfirmedVol) {
        this.transferOutConfirmedVol = transferOutConfirmedVol;
    }

    public BigDecimal getConfirmedAmount() {
        return confirmedAmount;
    }

    public void setConfirmedAmount(BigDecimal confirmedAmount) {
        this.confirmedAmount = confirmedAmount;
    }

    public BigDecimal getTransferInConfirmedVol() {
        return transferInConfirmedVol;
    }

    public void setTransferInConfirmedVol(BigDecimal transferInConfirmedVol) {
        this.transferInConfirmedVol = transferInConfirmedVol;
    }

    public String getFundTransactionNo() {
        return fundTransactionNo;
    }

    public void setFundTransactionNo(String fundTransactionNo) {
        this.fundTransactionNo = fundTransactionNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
