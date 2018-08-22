package com.jeramtough.jtlog.jtlogger;

import com.jeramtough.jtlog.log.LogContext;

/**
 * 这个是客户端使用JtLog日志框架的核心接口
 * <p>
 * Created on 2018-08-21 14:34
 * by @author JeramTough
 */
public interface JtLogger {

    /**
     * 程序执行到标记锚点输出，格式为:<>br</>
     */
    void arrive();

    /**
     * 普通的输出，不带任何格式,相当于<br/>
     * System.out.println();
     *
     * @param message 输出内容
     */
    <T1> void p(T1 message);

    /**
     * 输出Info级别的日志
     *
     * @param message 输出内容
     */
    <T> void info(T message);


    /**
     * 输出warn级别的日志
     *
     * @param tag     标签
     * @param message 输出内容
     */
    <T> void info(String tag, T message);


    /**
     * @param message 输出内容
     */
    <T> void warn(T message);

    /**
     * 输出warn级别的日志
     *
     * @param tag     标签
     * @param message 输出内容
     */
    <T> void warn(String tag, T message);

    /**
     * 输出error级别的日志
     *
     * @param message 输出内容
     */
    <T> void error(T message);

    /**
     * 输出error级别的日志
     *
     * @param tag     标签
     * @param message 输出内容
     */
    <T> void error(String tag, T message);

    /**
     * 输出debug级别的日志
     *
     * @param message 输出内容
     */
    <T> void debug(T message);

    /**
     * 输出debug级别的日志
     *
     * @param tag     标签
     * @param message 输出内容
     */
    <T> void debug(String tag, T message);

    /**
     * 输出verbose级别的日志
     *
     * @param message 输出内容
     */
    <T> void verbose(T message);

    /**
     * 输出verbose级别的日志
     *
     * @param tag     标签
     * @param message 输出内容
     */
    <T> void verbose(String tag, T message);

    LogContext getLogContext();
}
