package com.jeramtough.jtlog.bean;

import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.tag.Tag;

import javax.xml.crypto.Data;
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
    private String messageStr;
    private StackTraceElement stackTraceElement;
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

    void setStackTraceElement(StackTraceElement stackTraceElement) {
        this.stackTraceElement = stackTraceElement;
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

    void setMessage(Object message) {
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

    void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    private void processingInformation() {
        if (stackTraceElement == null) {
            stackTraceElement =
                    ((new Exception()).getStackTrace())[CALLER_COUNT];
        }

        if (message == null) {
            messageStr = "[null]";
        }
        else {
            messageStr = message.toString();
        }

        date = new Date();

        threadName = Thread.currentThread().getName();
        className = stackTraceElement.getClassName();
        methodName = stackTraceElement.getMethodName();
        fileName = stackTraceElement.getFileName();

        line = stackTraceElement.getLineNumber() + "";

        //processing trace
        trace = "at " + className + "." +
                methodName + "(" +
                fileName + ":" +
                line + ")";
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

        public Builder setTag(Tag tag) {
            logInformation.setTag(tag);
            return this;
        }

        public Builder setMessage(Object message) {
            logInformation.setMessage(message);
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
