package com.jeramtough.jtlog.handler;

import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.recorder.LogRecorder;

import java.util.ArrayList;

/**
 * 添加配置组件（比如：日志记录器{@link LogRecorder},日志过滤器{@link com.jeramtough.jtlog.filter.LogFilter}）的接口
 * <p>
 * Created on 2018-09-13 11:50
 * by @author JeramTough
 */
public interface ComponentHandler {

    /**
     * 使用logFilters集合添加自定义日志过滤器
     *
     * @param logFilters 日志过滤器集合
     */
    void handleLogFilters(ArrayList<LogFilter> logFilters);

    /**
     * 使用logRecorders集合添加自定义日志记录器
     *
     * @param logRecorders 日志记录器集合
     */
    void handleLogRecorders(ArrayList<LogRecorder> logRecorders);
}
