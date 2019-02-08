package com.jeramtough.jtlog.annotation;

import com.jeramtough.jtlog.constant.LogConfigDefaultValues;
import com.jeramtough.jtlog.handler.ComponentHandler;
import com.jeramtough.jtlog.handler.DefaultComponentHandler;
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
     *
     * @return return true if enabled
     */
    boolean isEnabled() default LogConfigDefaultValues.IS_ENABLED;

    /**
     * 是否使用JtLogger框架的Api进行日志输出，
     * false的话会自适应使用Logback或者Log4j2的Api进行输出
     * 的api实现输出
     *
     * @return 默认true
     */
    boolean isUsedJtloggerApi() default LogConfigDefaultValues.IS_USED_JTLOGGER_API;


    /**
     * 日志框架输出内容时，每一行的最大长度，超过这个长度就会换行,设置
     * 为0时则是不换行
     *
     * @return 默认0
     */
    int maxLengthOfRow() default LogConfigDefaultValues.MAX_LENGTH_OF_ROM;

    /**
     * 最低可见日志等级，默认为DEBUG等级，日志等级优先级就参考
     * {@link LogLevel}
     *
     * @return 默认为DEBUG等级
     */
    LogLevel minVisibleLevel() default LogLevel.DEBUG;

    /**
     * 日志环境名，{context}=的标识，日志框架根据环境名区分不同的日志环境
     *
     * @return 默认""
     */
    String contextName() default "";

    /**
     * 决定输出位置caller的标记
     * {caller}=(L.java:71)
     *
     * @return 默认0
     */
    int callerPlus() default LogConfigDefaultValues.CALLER_PLUS;

    /**
     * 添加日志系统附加组件的把持类
     *
     * @return 默认返回 {@link DefaultComponentHandler}
     */
    Class<? extends ComponentHandler> componentHandleClass() default DefaultComponentHandler.class;

}
