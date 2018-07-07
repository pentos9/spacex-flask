package com.spacex.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;


public class PerformanceMonitorInterceptor extends AbstractMonitoringInterceptor {

    private Logger logger = LoggerFactory.getLogger(PerformanceMonitorInterceptor.class);

    @Override
    protected Object invokeUnderTrace(MethodInvocation methodInvocation, Log log) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            Object result = methodInvocation.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            long end = System.currentTimeMillis();
            long time = end - start;
            logger.info(String.format("executeTime->%s", time));
            if (time > 100) {
                logger.warn(String.format("[Warning] executeTime is greater than %s time!", time));
            }
        }
        return null;
    }
}
