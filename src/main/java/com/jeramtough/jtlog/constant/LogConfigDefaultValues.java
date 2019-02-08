package com.jeramtough.jtlog.constant;

import com.jeramtough.jtlog.level.LogLevel;

/**
 * Created on 2018-09-25 01:15
 * by @author JeramTough
 */
public class LogConfigDefaultValues {

    /**
     * 日志内容输出，每行最大长度，超过这个长度就会换行
     */
    public static final int MAX_LENGTH_OF_ROM = 0;

    /**
     * 是否允许输出日志
     */
    public static final boolean IS_ENABLED = true;


    /**
     * 是否允许输出日志Trace信息.
     */
    public static boolean IS_PRINTED_TRACE = true;


    /**
     * 是否使用JtLogger框架的Api,当为Android框架时强制使用Logcat的Api实现输出，
     * false的话会自适应使用Logback或者Log4j2
     * 的api实现输出
     */
    public static final boolean IS_USED_JTLOGGER_API = true;

    /**
     * 最低可见日志等级，默认为DEBUG等级，日志等级优先级就参考
     * {@link LogLevel}
     */
    public static LogLevel MIN_VISIBLE_LEVEL = LogLevel.DEBUG;


    /**
     * 决定输出位置caller的标记
     * {caller}=(L.java:71)
     */
    public static int CALLER_PLUS = 0;

}
