package com.jeramtough.jtlog.annotation;

import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.filter.TagLogFilter;
import com.jeramtough.jtlog.level.LogLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

/**
 * Created on 2018-08-22 09:16
 * by @author JeramTough
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JtLoggerConfig {
    boolean isEnabled() default true;

    boolean isUsedJtloggerApi() default false;

    int maxLengthOfRow() default 130;

    LogLevel minVisibleLevel() default LogLevel.DEBUG;

    String contextName() default "";
}
