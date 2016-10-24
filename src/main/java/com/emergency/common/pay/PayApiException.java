package com.emergency.common.pay;


/**
 *
 */
public class PayApiException extends Exception {


    private static final long serialVersionUID = 619665168809888631L;

    private String            errCode;
    private String            errMsg;

    public PayApiException() {
        super();
    }

    public PayApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public PayApiException(String message) {
        super(message);
    }

    public PayApiException(Throwable cause) {
        super(cause);
    }

    public PayApiException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

}