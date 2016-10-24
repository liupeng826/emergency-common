package com.emergency.common.pay;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RefundRequest extends Request<PayResponse>{
    // 支付用户ID
    @NotEmpty(message = "回款用户ID不能为空")
    private String userId;
    // 支付渠道
    @NotNull(message = "回款渠道不能为空")
    private Long channelId;
    // 金额
    @NotNull(message = "回款份额不能为空")
    private BigDecimal vol;
    // 外部订单序列号
    @NotEmpty(message = "外部订单号不能为空")
    private String transferOutOrderNo;

    @NotNull(message = "类型不能为空")
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

    public int getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
