package com.jeramtough.jtlog.log;

import com.jeramtough.jtlog.jtlogger.JtLoggerImpl;

/**
 * 这个是日志环境类,<br/>
 * 每一个{@link JtLoggerImpl}类都有个日志环境类，包含{@link LogConfig},contextName
 * Created on 2018-08-21 14:45
 * by @author JeramTough
 */
public final class LogContext {

    private String contextName;
    private LogConfig logConfig;

    public LogContext() {
        logConfig = new LogConfig();
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
}
