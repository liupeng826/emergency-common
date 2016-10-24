package com.emergency.common.pay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PayRequest extends Request<PayResponse>{
    // 支付用户ID
    @NotEmpty(message = "支付用户ID不能为空")
    private String userId;
    // 支付渠道
    @NotNull(message = "支付渠道不能为空")
    private Long channelId;
    // 金额
    @NotNull(message = "支付金额不能为空")
    private BigDecimal amount;
    // 外部订单序列号
    @NotEmpty(message = "外部订单号不能为空")
    private String transferOutOrderNo;
    // 是否强制支付
    @NotNull
    private Boolean isForce;

    @NotNull
    public Integer getType() {
        return type;
    }


    private Integer type;

    @Override
    @JsonIgnore
    public Class<PayResponse> getResponseClass() {
        return PayResponse.class;
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

    public Boolean getIsForce() {
        return isForce;
    }

    public void setIsForce(Boolean isForce) {
        this.isForce = isForce;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
