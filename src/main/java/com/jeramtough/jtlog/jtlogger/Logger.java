package com.jeramtough.jtlog.jtlogger;

import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.tag.Tag;

/**
 * 这个是客户端使用JtLog日志框架的核心接口
 * <p>
 * Created on 2018-08-21 14:34
 * by @author JeramTough
 */
public interface Logger {

    /**
     * 程序执行到标记锚点输出，格式为:
     */
    void arrive();

    /**
     * 普通的输出，不带任何格式,类似于
     * System.out.println();
     *
     * @param message 输出内容
     * @param <T>     任何数据类型
     */
    <T> void p(T message);

    /**
     * 普通的错误输出，不带任何格式,类似于
     * System.err.println();
     *
     * @param message 输出内容
     * @param <T>     任何数据类型
     */
    <T> void e(T message);

    /**
     * 输出Info级别的日志
     *
     * @param message 输出内容
     * @param <T>     任何数据类型
     */
    <T> void info(T message);


    /**
     * 输出带tag标签的info级别的日志
     *
     * @param tag     标签
     * @param message 输出内容
     * @param <T>     任何数据类型
     */
    <T> void info(Tag tag, T message);

    /**
     * 输出info级别的日志
     *
     * @param message      输出内容
     * @param placeholders %s、%d等占位符
     * @param <T>          任何数据类型
     */
    <T> void info(T message, Object... placeholders);

    /**
     * 输出带tag标签的info级别的日志
     *
     * @param tag          标签
     * @param message      输出内容
     * @param placeholders %s、%d等占位符
     * @param <T>          任何数据类型
     */
    <T> void info(Tag tag, T message, Object... placeholders);

    /**
     * 输出warn级别的日志
     *
     * @param message 输出内容
     * @param <T>     任何数据类型
     */
    <T> void warn(T message);

    /**
     * 输出带tag标签的warn级别的日志
     *
     * @param tag     标签
     * @param message 输出内容
     * @param <T>     任何数据类型
     */
    <T> void warn(Tag tag, T message);

    /**
     * 输出warn级别的日志
     *
     * @param message      输出内容
     * @param placeholders %s、%d等占位符
     * @param <T>          任何数据类型
     */
    <T> void warn(T message, Object... placeholders);

    /**
     * 输出带tag标签的warn级别的日志
     *
     * @param tag          标签
     * @param message      输出内容
     * @param placeholders %s、%d等占位符
     * @param <T>          任何数据类型
     */
    <T> void warn(Tag tag, T message, Object... placeholders);

    /**
     * 输出error级别的日志
     *
     * @param message 输出内容
     * @param <T>     任何数据类型
     */
    <T> void error(T message);

    /**
     * 输出带tag标签的error级别的日志
     *
     * @param tag     标签
     * @param message 输出内容
     * @param <T>     任何数据类型
     */
    <T> void error(Tag tag, T message);

    /**
     * 输出error级别的日志
     *
     * @param message      输出内容
     * @param placeholders %s、%d等占位符
     * @param <T>          任何数据类型
     */
    <T> void error(T message, Object... placeholders);

    /**
     * 输出带tag标签的error级别的日志
     *
     * @param tag          标签
     * @param message      输出内容
     * @param placeholders %s、%d等占位符
     * @param <T>          任何数据类型
     */
    <T> void error(Tag tag, T message, Object... placeholders);


    /**
     * 输出error级别的日志
     *
     * @param e 异常类
     */
    void error(Exception e);

    /**
     * 输出error级别的日志
     *
     * @param e            异常类
     * @param message      输出内容
     * @param placeholders %s、%d等占位符
     * @param <T>          任何数据类型
     */
    <T> void error(Exception e, T message, Object... placeholders);

    /**
     * 输出带tag标签的error级别的日志
     *
     * @param tag          标签
     * @param e            异常类
     * @param message      输出内容
     * @param placeholders %s、%d等占位符
     * @param <T>          任何数据类型
     */
    <T> void error(Tag tag, Exception e, T message, Object... placeholders);


    /**
     * 输出debug级别的日志
     *
     * @param message 输出内容
     * @param <T>     任何数据类型
     */
    <T> void debug(T message);

    /**
     * 输出带tag标签的debug级别的日志
     *
     * @param tag     标签
     * @param message 输出内容
     * @param <T>     任何数据类型
     */
    <T> void debug(Tag tag, T message);

    /**
     * 输出debug级别的日志
     *
     * @param message      输出内容
     * @param placeholders %s、%d等占位符
     * @param <T>          任何数据类型
     */
    <T> void debug(T message, Object... placeholders);

    /**
     * 输出带tag标签的debug级别的日志
     *
     * @param tag          标签
     * @param message      输出内容
     * @param placeholders %s、%d等占位符
     * @param <T>          任何数据类型
     */
    <T> void debug(Tag tag, T message, Object... placeholders);


    /**
     * 输出debug级别的日志输出，可输出多条debug消息，
     * 以“，”分割
     *
     * @param messages 输出内容
     * @param <T>      任何数据类型
     */
    <T> void debugs(T... messages);

    /**
     * 输出verbose级别的日志
     *
     * @param message 输出内容
     * @param <T>     任何数据类型
     */
    <T> void verbose(T message);

    /**
     * 输出带tag标签的verbose级别的日志
     *
     * @param tag     标签
     * @param message 输出内容
     * @param <T>     任何数据类型
     */
    <T> void verbose(Tag tag, T message);

    /**
     * 输出verbose级别的日志
     *
     * @param message      输出内容
     * @param placeholders %s、%d等占位符
     * @param <T>          任何数据类型
     */
    <T> void verbose(T message, Object... placeholders);

    /**
     * 输出带tag标签的verbose级别的日志
     *
     * @param tag          标签
     * @param message      输出内容
     * @param placeholders %s、%d等占位符
     * @param <T>          任何数据类型
     */
    <T> void verbose(Tag tag, T message, Object... placeholders);

    /**
     * 得到日志环境类
     *
     * @return 日志环境类 {@link LogContext}
     */
    LogContext getLogContext();
}
