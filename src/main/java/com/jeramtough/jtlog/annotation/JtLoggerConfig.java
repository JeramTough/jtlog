package com.jeramtough.jtlog.annotation;

import com.jeramtough.jtlog.level.LogLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志框架的设置注释类
 * <p>
 * Created on 2018-08-22 09:16
 * by @author JeramTough
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JtLoggerConfig {

    /**
     * 是否允许输出日志
     */
    boolean isEnabled() default true;

    /**
     * 是否使用JtLogger框架的Api,当为Android框架时强制使用Logcat的Api实现输出，<br/>
     * false的话会自适应使用Logback或者Log4j2<br/>
     * 的api实现输出
     */
    boolean isUsedJtloggerApi() default true;


    /**
     * 日志内容输出，每行最大长度，超过这个长度就会换行
     */
    int maxLengthOfRow() default 130;

    /**
     * 最低可见日志等级，默认为DEBUG等级，日志等级优先级就参考<br/>
     * {@link LogLevel}
     */
    LogLevel minVisibleLevel() default LogLevel.DEBUG;

    /**
     * 日志环境名，日志框架根据环境名区分不同的日志环境
     */
    String contextName() default "";

    int callerPlus() default 0;
}
