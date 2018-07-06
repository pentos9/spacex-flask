package com.spacex.configuration;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {
    @Pointcut(
            "execution(public String com.spacex.controller.monitor.MonitorController.monitor(..))"
    )
    public void monitor() {
    }

    @Pointcut(
            "@annotation(com.spacex.annotation.ExecuteTimeAnnotation))"
    )
    public void executeTime() {
    }
}
