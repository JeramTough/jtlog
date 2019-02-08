package com.jeramtough.jtlog.context;

import com.jeramtough.jtlog.config.LogConfig;
import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.jtlogger.JtLogger;
import com.jeramtough.jtlog.recorder.LogRecorder;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个是日志环境类,
 * 每一个{@link JtLogger}类都有个日志环境类，包含{@link LogConfig},contextName
 * Created on 2018-08-21 14:45
 * by @author JeramTough
 */
public final class LogContext {

    private String contextName;

    /**
     * 日志过滤器集合
     */
    private List<LogFilter> logFilters;

    /**
     * 日志记录器集合
     */
    private List<LogRecorder> logRecorders;

    /**
     * 日志配置类
     */
    private LogConfig logConfig;


    public LogContext(String contextName, LogConfig logConfig) {
        this.contextName = contextName;
        this.logConfig = logConfig;
        logFilters = new ArrayList<>();
        logRecorders = new ArrayList<>();
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }


    public LogConfig getLogConfig() {
        return logConfig;
    }

    public void setLogConfig(LogConfig logConfig) {
        this.logConfig = logConfig;
    }

    public List<LogFilter> getLogFilters() {
        return logFilters;
    }

    public List<LogRecorder> getLogRecorders() {
        return logRecorders;
    }
}
