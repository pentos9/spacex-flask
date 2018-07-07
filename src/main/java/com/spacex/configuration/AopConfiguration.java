package com.spacex.configuration;

import com.spacex.interceptor.PerformanceMonitorInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Configuration
@EnableAspectJAutoProxy
@Component
public class AopConfiguration {

    private Logger logger = LoggerFactory.getLogger(AopConfiguration.class);

    @Around("com.spacex.configuration.CommonJoinPointConfig.executeTime()")
    public Object around(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            return result;
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
