package com.jeramtough.jtlog.annotation;

import com.jeramtough.jtlog.config.JtLogConfigDefaultValues;
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
public @interface LogConfiguration {

    /**
     * 决定日志环境名，{context}=的标识，日志框架根据环境名区分不同的日志环境
     * 默认为类名
     */
    String contextName() default "default";

    /**
     * 决定是否允许输出日志
     * <p>
     * <p>
     * 返回值小于0表示使用默认值，返回值0表示false, 返回值大于0 表示true
     * 默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     */
    int isEnabled() default -1;

    /**
     * 决定是否使用JtLogger框架的Api进行日志输出，
     * false的话会自适应使用Logback或者Log4j2的Api进行输出
     * 的api实现输出
     * <p>
     * <p>
     * 返回值小于0表示使用默认值，返回值0表示false, 返回值大于0 表示true
     * 默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     */
    int isUsedJtloggerApi() default -1;


    /**
     * 决定日志框架输出内容时，每一行的最大长度，超过这个长度就会换行,设置
     * 为0时则是不换行
     * <p>
     * <p>
     * 返回值小于0使用默认值，返回值0表示不换行, 返回值大于0 表示超过这个值就会换行
     * 默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     */
    int maxLengthOfRow() default -1;

    /**
     * 决定最低可见日志等级，默认为DEBUG等级，日志等级优先级就参考
     * {@link LogLevel}
     * <p>
     * <p>
     * 返回LogLevel.DEFAULT使用默认值
     * 默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     */
    LogLevel minVisibleLevel() default LogLevel.DEFAULT;


    /* *//**
     * 见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     * <p>
     * <p>
     * 决定输出位置caller的标记
     * {caller}=(L.java:71)
     *//*
    int callerPlus() default JtLogConfigDefaultValues.CALLER_PLUS;*/

    /**
     * 决定日志头格式，其语法规则见{@link com.jeramtough.jtlog.util.LogHeaderFormatUtil}
     * <p>
     * 返回"defalut"使用默认值
     * 见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     */
    String logHeaderFormat() default "default";


}
