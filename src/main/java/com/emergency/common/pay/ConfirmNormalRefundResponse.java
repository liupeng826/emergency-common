package com.emergency.common.pay;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by lr on 15/8/5.
 */
public class ConfirmNormalRefundResponse extends Response {
    private int status;

    private Date confirmedDate;

    private Timestamp transactionTime;

    private Date transactionDate;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
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
}
