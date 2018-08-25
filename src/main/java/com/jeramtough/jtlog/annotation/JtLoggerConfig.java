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
     * 是否使用JtLogger框架的Api进行日志输出，<br/>
     * false的话会自适应使用Logback或者Log4j2的Api进行输出<br/>
     * 的api实现输出
     */
    boolean isUsedJtloggerApi() default true;


    /**
     * 日志框架输出内容时，每一行的最大长度，超过这个长度就会换行
     */
    int maxLengthOfRow() default 130;

    /**
     * 最低可见日志等级，默认为DEBUG等级，日志等级优先级就参考<br/>
     * {@link LogLevel}
     */
    LogLevel minVisibleLevel() default LogLevel.DEBUG;

    /**
     * 日志环境名，{context}=的标识，日志框架根据环境名区分不同的日志环境
     */
    String contextName() default "";

    /**
     * 决定输出位置caller的标记<br/>
     * {caller}=(L.java:71)
     */
    int callerPlus() default 0;
}
