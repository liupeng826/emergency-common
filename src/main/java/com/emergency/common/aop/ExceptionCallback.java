package com.emergency.common.aop;


import org.aspectj.lang.JoinPoint;

public interface ExceptionCallback {

    /**
     * 异常拦截器拦截到异常后,回调此方法
     * @param joinPoint
     * @param e
     */
    void callback(JoinPoint joinPoint, Throwable e);
}
