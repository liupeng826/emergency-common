package com.emergency.common.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionQueryRequest extends Request<TransactionQueryResponse> {
    // 支付用户ID
    @NotEmpty(message = "支付用户ID不能为空")
    private String userId;

    // 支付类型
    private Integer payType = PayType.PAY.getIndex();

    // 支付订单号
    @NotEmpty(message = "支付流水单号不能为空")
    private String payApplicationNo;

    @Override
    public Class<TransactionQueryResponse> getResponseClass() {
        return TransactionQueryResponse.class;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayApplicationNo() {
        return payApplicationNo;
    }

    public void setPayApplicationNo(String payApplicationNo) {
        this.payApplicationNo = payApplicationNo;
    }
}
