package com.jeramtough.jtlog.log.config;

import com.jeramtough.jtlog.level.LogLevel;

/**
 * Created on 2018-09-25 01:05
 * by @author JeramTough
 */
public interface LogConfigDefaultValues {

    /**
     * 加载默认值：
     * 日志内容输出，每行最大长度，超过这个长度就会换行
     */
    int loadMaxLengthOfRow();

    /**
     * 加载默认值：
     * 是否允许输出日志
     */
    boolean loadIsEnabled();

    /**
     * 加载默认值：
     * 是否使用JtLogger框架的Api,当为Android框架时强制使用Logcat的Api实现输出，
     * false的话会自适应使用Logback或者Log4j2
     * 的api实现输出
     */
    boolean loadIsUsedJtloggerApi();


    /**
     * 加载默认值：
     * 最低可见日志等级，默认为DEBUG等级，日志等级优先级就参考
     * {@link LogLevel}
     */
    LogLevel loadMinVisibleLevel();

    /**
     * 加载默认值：
     * 决定输出位置caller的标记
     * {caller}=(L.java:71)
     */
    int loadCallerPlus();
}
