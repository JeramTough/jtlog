package com.jeramtough.jtlog.annotation;

import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.header.LogHeader;
import com.jeramtough.jtlog.lang.DefaultBoolean;
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
     * 返回值DefaultBoolean.DEFAULT表示使用默认值，返回值DefaultBoolean.FALSE表示false,
     * 返回值DefaultBoolean.TRUE 表示true
     * 关于默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     */
    DefaultBoolean isEnabled() default DefaultBoolean.DEFAULT;

    /**
     * 决定是否使用JtLogger框架的Api进行日志输出，
     * false的话会自适应使用Logback或者Log4j2的Api进行输出
     * 的api实现输出
     * <p>
     * <p>
     * 返回值DefaultBoolean.DEFAULT表示使用默认值，返回值DefaultBoolean.FALSE表示false,
     * 返回值DefaultBoolean.TRUE 表示true
     * 关于默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     */
    DefaultBoolean isUsedJtloggerApi() default DefaultBoolean.DEFAULT;


    /**
     * 决定日志框架输出内容时，每一行的最大长度，超过这个长度就会换行,设置
     * 为0时则是不换行
     * <p>
     * <p>
     * 返回值小于0使用默认值，返回值0表示不换行, 返回值大于0 表示超过这个值就会换行
     * 关于默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     */
    int maxLengthOfRow() default -1;

    /**
     * 决定最低可见日志等级，默认为DEBUG等级，日志等级优先级就参考
     * {@link LogLevel}
     * <p>
     * <p>
     * 返回LogLevel.DEFAULT使用默认值
     * 关于默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     */
    LogLevel minVisibleLevel() default LogLevel.DEFAULT;


    /**
     * 决定要输出的日志信息头及顺序，有则输出，没有则不输出，
     * 日志信息头见{@link LogLevel}
     * <p>
     * <p>
     * 返回{ LogHeader.DEFAULT }使用默认值
     * 关于默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     */
    LogHeader[] logHeaders() default {LogHeader.DEFAULT};

    /**
     * 每条新日志间的空行数, 0则两条日志间无空行
     * <p>
     * 返回值小于0使用默认值，返回值0表示两条日志间无空行, 返回值大于0 表示两条日志间有n条空行
     * 关于默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     */
    int wrapCount() default -1;

    /**
     * 添加额外的日志过滤器，过滤器类必须有个无参公共的构造函数，过滤器详细见{@link LogFilter}
     */
    Class<? extends LogFilter>[] logFilter() default {};

    /**
     * 决定日志时间信息头的输出格式
     * <p>
     * <p>
     * 返回"default"使用默认值,返回时间格式则使用该格式
     * 关于默认值见日志配置默认值接口{@link com.jeramtough.jtlog.config.LogConfigDefaultValues}
     */
    String dataFormat() default "default";
}
