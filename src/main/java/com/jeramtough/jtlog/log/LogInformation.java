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

    private Object message;
    private String messageString;
    private StackTraceElement stackTraceElement;
    private String tag;
    private String time;
    private String threadName;
    private String className;
    private String methodName;
    private String line;
    private LogLevel logLevel;
    private LogContext logContext;


    private LogInformation() {
    }

    void setStackTraceElement(StackTraceElement stackTraceElement) {
        this.stackTraceElement = stackTraceElement;
    }

    public String getTime() {
        return time;
    }

    public String getThreadName() {
        return threadName;
    }

    public String getMessage() {
        return messageString;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getLine() {
        return line;
    }

    public String getFileName() {
        return stackTraceElement.getFileName();
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    void setTag(String tag) {
        this.tag = tag;
    }

    void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public LogContext getLogContext() {
        return logContext;
    }

    void setLogContext(LogContext logContext) {
        this.logContext = logContext;
    }


    private void processingInformation() {
        if (stackTraceElement == null) {
            stackTraceElement =
                    ((new Exception()).getStackTrace())[CALLER_COUNT + logContext.getLogConfig().getCallerPlus()];
        }

        if (message == null) {
            messageString = "[null]";
        }
        else {
            messageString = message.toString();
        }

        //processing time
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
        time = format.format(date);

        threadName = Thread.currentThread().getName();

        className = stackTraceElement.getClassName();

        methodName = stackTraceElement.getMethodName();

        line = stackTraceElement.getLineNumber() + "";

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

        public Builder setMessage(Object message) {
            logInformation.setMessage(message);
            return this;
        }

        public Builder setLogContext(LogContext logContext) {
            logInformation.setLogContext(logContext);
            return this;
        }

        public Builder setStackTraceElement(StackTraceElement stackTraceElement) {
            logInformation.setStackTraceElement(stackTraceElement);
            return this;
        }

        public LogInformation build() {
            logInformation.processingInformation();
            return logInformation;
        }
    }
}
