package com.emergency.common.pay;


/**
 *
 */
public interface PayApiClient {

    /**
     * @param <T>
     * @param request
     * @return
     * @throws PayApiException
     */
    public <T extends Response> T execute(Request<T> request) throws PayApiException;


}
