package com.emergency.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * 异常拦截器,通过aop方式拦截
 *  例:
 *  <bean id="exceptionAspect" class="ExceptionAspect">
 *     <property name="exceptionCallback" value="fundExceptionCallback"/>
 *     <property name="exceptionList">
 *         <list>
 *             <value>com.hn.fund.middle.FundConnectTimeoutException</value>
 *             <value>com.hn.fund.middle.FundSocketTimeoutException</value>
 *         </list>
 *     </property>
 *  </bean>
 *
 *
 *   <aop:config>
 *       <aop:aspect id="fundApiProxyAspect" ref="exceptionAspect" >
 *           <aop:pointcut id="fundApiProxyAspect" expression="execution(* com.hnczb.gzb.proxy.*(..))" />
 *           <aop:around pointcut-ref="fundApiProxyAspect" method="around" />
 *       </aop:aspect>
 *   </aop:config>
 *
 * @author yujl
 * @date 2016-01-11
 */
public class ExceptionAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionAspect.class);

    private List<ExceptionCallback> exceptionCallbackList;

    public Object around(ProceedingJoinPoint call) throws Throwable {
        try {
            return call.proceed();
        } catch (Throwable e) {
            if (exceptionCallbackList != null) {
                for (ExceptionCallback exceptionCallback : exceptionCallbackList) {
                    LOGGER.info("callback: "+exceptionCallback.getClass().getName());
                    exceptionCallback.callback(call, e);
                }
            }
            throw e;
        }
    }

    public List<ExceptionCallback> getExceptionCallbackList() {
        return exceptionCallbackList;
    }

    public void setExceptionCallbackList(List<ExceptionCallback> exceptionCallbackList) {
        this.exceptionCallbackList = exceptionCallbackList;
    }
}
