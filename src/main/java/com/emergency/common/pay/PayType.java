package com.emergency.common.pay;


public enum PayType {

    PAY(1), // 支付
    REFUND(2); // 回款

    private int index;

    private PayType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
