package com.emergency.common.exception;

import java.io.Serializable;

/**
 * 基金错误代码以及错误描述
 * @author yujl
 * @date 23/3/15
 */
public class ErrorCode implements Serializable {

    private String errorCode;

    private String errorMsg;

    public ErrorCode(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public ErrorCode appendMsg(String msg) {
        this.errorMsg += ": "+msg;
        return this;
    }

}
