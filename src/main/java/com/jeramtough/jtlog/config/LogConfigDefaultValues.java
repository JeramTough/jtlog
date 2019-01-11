package com.jeramtough.jtlog.config;

import com.jeramtough.jtlog.level.LogLevel;

/**
 * Created on 2018-09-25 01:05
 * by @author JeramTough
 */
public interface LogConfigDefaultValues {

    /**
     * 决定日志内容输出，每行最大长度，超过这个长度就会换行
     *
     * @return 加载默认值
     */
    int decidedMaxLengthOfRow();

    /**
     * 决定是否允许输出日志
     *
     * @return 加载默认值
     */
    boolean decidedIsEnabled();

    /**
     * 决定是否允许输出日志Trace信息.
     *
     * @return 加载默认值
     */
    boolean decidedIsPrintedTrace();

    /**
     * 决定是否使用JtLogger框架的Api,当为Android框架时强制使用Logcat的Api实现输出，
     * false的话会自适应使用Logback或者Log4j2
     * 的api实现输出
     *
     * @return 加载默认值
     */
    boolean decidedIsUsedJtloggerApi();


    /**
     * 决定最低可见日志等级，默认为DEBUG等级，日志等级优先级就参考
     * {@link LogLevel}
     *
     * @return 加载默认值
     */
    LogLevel decidedMinVisibleLevel();

    /**
     * 决定决定输出位置caller的标记
     * {caller}=(L.java:71)
     *
     * @return 加载默认值
     */
    int decidedCallerPlus();
}
