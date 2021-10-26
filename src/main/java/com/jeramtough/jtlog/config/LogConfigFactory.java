package com.jeramtough.jtlog.config;

/**
 * Created on 2019-08-01 14:39
 * by @author JeramTough
 */
public interface LogConfigFactory {

    LogConfig getDefaultValueLogConfig(String contextName);

    LogConfig getLogConfigByAnnotation(String contextName,
                                       Class<?> classWithLogConfigerAnnotation);
}
