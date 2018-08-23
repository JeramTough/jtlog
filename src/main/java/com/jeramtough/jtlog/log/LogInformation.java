package com.jeramtough.jtlog.log;

import com.jeramtough.jtlog.level.LogLevel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志信息类，包含输出内容，时间，日志等级，标签等信息
 * <p>
 * Created by 11718
 * on 2017  October 14 Saturday 17:38.
 */

public class LogInformation {

    private final static int CALLER_COUNT = 4;

    private String message;
    private StackTraceElement stackTraceElement;
    private String tag;
    private LogLevel logLevel;
    private LogContext logContext;


    private LogInformation() {
        stackTraceElement = ((new Exception()).getStackTrace())[CALLER_COUNT];
    }

    public String getTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
        String time = format.format(date);
        return time;
    }

    public String getThread() {
        String thread = Thread.currentThread().getName();
        return thread;
    }

    public String getMessage() {
        return message;
    }

    public String getClassName() {
        String className = stackTraceElement.getClassName();
        return className;
    }

    public String getMethodName() {
        String methodName = stackTraceElement.getMethodName();
        return methodName;
    }

    public String getLine() {
        String line = stackTraceElement.getLineNumber() + "";
        return line;
    }

    public String getFileName() {
        return stackTraceElement.getFileName();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    /*public LogContext getLogContext() {
        return logContext;
    }*/

    public void setLogContext(LogContext logContext) {
        this.logContext = logContext;
    }

    //{{{{{{{{{}}}}}}}}}}}}}}}}}
    public static class Builder {
        private LogInformation logInformation;

        public Builder() {
            logInformation = new LogInformation();
        }

        public Builder setJtLogLevel(LogLevel logLevel) {
            logInformation.setLogLevel(logLevel);
            return this;
        }

        public Builder setTag(String tag) {
            logInformation.setTag(tag);
            return this;
        }

        public Builder setMessage(String message) {
            logInformation.setMessage(message);
            return this;
        }

        public Builder setLogContext(LogContext logContext) {
            logInformation.setLogContext(logContext);
            return this;
        }

        public LogInformation build() {
            return logInformation;
        }
    }
}
