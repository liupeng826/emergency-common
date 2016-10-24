package com.emergency.common.pay;

/**
 * 响应解释器接口。响应格式可以是JSON, XML等等。
 */
public interface PayParser<T extends Response> {

    /**
     * 把响应字符串解释成相应的领域对象。
     *
     * @param rsp 响应字符串
     * @return 领域对象
     */
    public T parse(String rsp) throws PayApiException;

    /**
     * 获取响应类类型。
     */
    public Class<T> getResponseClass() throws PayApiException;

}
