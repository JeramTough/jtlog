package com.jeramtough.jtlog.bean;

import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.jtlogger.LoggerManager;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.tag.Tag;

import java.util.Date;

/**
 * 日志信息类，包含输出内容，时间，日志等级，标签等信息
 * <p>
 * Created by 11718
 * on 2017  October 14 Saturday 17:38.
 *
 * @author JeramTough
 */

public class LogInformation {


    private Object message;
    private String messageStr;

    private Tag tag;
    private Date date;
    private String threadName;
    private String className;
    private String fileName;
    private String methodName;
    private String line;

    private LogLevel logLevel;
    private String trace;


    private LogInformation() {
    }

    public Date getDate() {
        return date;
    }

    public String getThreadName() {
        return threadName;
    }

    public String getMessageStr() {
        return messageStr;
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
        return fileName;
    }

    public String getTrace() {
        return trace;
    }

    private void setMessage(Object message) {
        this.message = message;
    }

    public Tag getTag() {
        return tag;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    void setTag(Tag tag) {
        this.tag = tag;
    }

    private void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }


    //{{{{{{{{{}}}}}}}}}}}}}}}}}


    public static class Builder {
        private LogInformation logInformation;
        private final static int STACK_TRACE_COUNT = 3;
        private int stackTraceOffset;

        public Builder() {
        }

        public Builder(LogContext logContext) {
            logInformation = new LogInformation();
            stackTraceOffset = logContext.getLogConfig().getStackTraceOffset();
        }

        public Builder setJtLogLevel(LogLevel logLevel) {
            logInformation.setLogLevel(logLevel);
            return this;
        }

        public Builder setTag(Tag tag) {
            logInformation.setTag(tag);
            return this;
        }

        public Builder setMessage(Object message) {
            logInformation.setMessage(message);
            return this;
        }

        public LogInformation build() {
            processingInformation(logInformation);
            return logInformation;
        }

        private void processingInformation(LogInformation logInformation) {
            StackTraceElement stackTraceElement =
                    ((new Exception()).getStackTrace())[STACK_TRACE_COUNT + stackTraceOffset];

            if (logInformation.message == null) {
                logInformation.messageStr = "[null]";
            }
            else {
                logInformation.messageStr = logInformation.message.toString();
            }

            logInformation.date = new Date();

            logInformation.threadName = Thread.currentThread().getName();
            logInformation.className = stackTraceElement.getClassName();
            logInformation.methodName = stackTraceElement.getMethodName();
            logInformation.fileName = stackTraceElement.getFileName();

            logInformation.line = stackTraceElement.getLineNumber() + "";

            //processing trace
            logInformation.trace = "at " + logInformation.className + "." +
                    logInformation.methodName + "(" +
                    logInformation.fileName + ":" +
                    logInformation.line + ")";
        }

    }
}
