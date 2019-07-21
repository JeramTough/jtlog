package com.jeramtough.jtlog.annotation;

import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.header.LogHeader;
import com.jeramtough.jtlog.lang.DefaultBoolean;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.recorder.LogRecorder;

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
     *
     * @return contextName
     */
    String contextName() default "default";

    /**
     * 决定是否允许输出日志
     * <p>
     * 返回值DefaultBoolean.DEFAULT表示使用全局默认值，返回值DefaultBoolean.FALSE表示false,
     * 返回值DefaultBoolean.TRUE 表示true
     * 关于全局默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     * </p>
     */
    DefaultBoolean isEnabled() default DefaultBoolean.DEFAULT;

    /**
     * 决定是否使用JtLogger框架的Api进行日志输出，
     * false的话会自适应使用Logback或者Log4j2的Api进行输出
     * 的api实现输出
     * <p>
     * 返回值DefaultBoolean.DEFAULT表示使用全局默认值，返回值DefaultBoolean.FALSE表示false,
     * 返回值DefaultBoolean.TRUE 表示true
     * 关于全局默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     * </p>
     */
    DefaultBoolean isUsedJtloggerApi() default DefaultBoolean.DEFAULT;


    /**
     * 决定日志框架输出内容时，每一行字数的最大长度，超过这个长度就会换行,设置
     * 为0时则是不换行
     * <p>
     * 返回值小于0使用全局默认值，返回值0表示不换行, 返回值大于0 表示超过这个值就会换行
     * 关于全局默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     * </p>
     */
    int maxLengthOfRow() default -1;

    /**
     * 决定最低可见日志等级，默认为DEBUG等级，日志等级优先级就参考
     * {@link LogLevel}
     * <p>
     * 返回LogLevel.DEFAULT使用全局默认值
     * 关于全局默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     * </p>
     */
    LogLevel minVisibleLevel() default LogLevel.DEFAULT;


    /**
     * 决定要输出的日志信息头及顺序，有则输出，没有则不输出，
     * 日志信息头见{@link LogLevel}
     * <p>
     * 返回{ LogHeader.DEFAULT }使用全局默认值
     * 关于全局默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     * </p>
     */
    LogHeader[] logHeaders() default {LogHeader.DEFAULT};

    /**
     * 每条新日志间的空行数, 0则两条日志间无空行
     * <p>
     * 返回值小于0使用全局默认值，返回值0表示两条日志间无空行, 返回值大于0 表示两条日志间有n条空行
     * 关于全局默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     * </p>
     */
    int wrapCount() default -1;

    /**
     * Trace信息的回溯下标偏移量
     * <p>
     * 返回值0表示Trace输出信息没有偏移, 返回值大于或则小于0表示Trace输出信息有偏移
     * 关于全局默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     * </p>
     */
    int stackTraceOffset() default 0;

    /**
     * 添加额外的日志过滤器，过滤器类必须有个无参公共的构造函数，过滤器详细见{@link com.jeramtough.jtlog.filter.LogFilter}
     */
    Class<? extends LogFilter>[] logFilters() default {};


    /**
     * 添加额外的日志记录器，记录器类必须有个无参公共的构造函数，过滤器详细见{@link com.jeramtough.jtlog.recorder.LogRecorder}
     */
    Class<? extends LogRecorder>[] logRecorders() default {};

    /**
     * 决定日志时间信息头的输出格式
     * <p>
     * 返回"default"使用全局默认值,返回时间格式则使用该格式
     * 关于全局默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     * </p>
     */
    String dateFormat() default "default";
}
