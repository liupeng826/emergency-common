package com.emergency.common.exception;

/**
 * 通过错误信息
 * @author yujl
 * @date 3/23/15
 */
public class CommonErrors {
    public final static ErrorCode SUCCESS = new ErrorCode("00000", "成功");
    public final static ErrorCode DB_EXCEPTION = new ErrorCode("99998", "数据操作异常");

    public final static ErrorCode REQUEST_PARAMS_ERROR = new ErrorCode("99996", "请求参数错误");
}
