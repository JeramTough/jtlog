package com.jeramtough.jtlog.jtlogger;

import com.jeramtough.jtlog.printer.PrinterFactory;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.bean.LogInformation;

/**
 * Created on 2018-08-21 14:51
 * by @author JeramTough
 */
public class JtLogger implements Logger {

    private LogContext logContext;

    JtLogger(LogContext logContext) {
        this.logContext = logContext;
    }

    @Override
    public void arrive() {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.ARRIVE).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void p(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.PRINTLN).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void info(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.INFO).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void infoT(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.INFO).setMessage(message).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void info(T message, Object... placeholders) {
        String formatMessage = String.format(message.toString(), placeholders);
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.INFO).setMessage(formatMessage).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void infoT(String tag, T message, Object... placeholders) {
        String formatMessage = String.format(message.toString(), placeholders);
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.INFO).setMessage(formatMessage).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void warn(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.WARN).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void warnT(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.WARN).setMessage(message).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void warn(T message, Object... placeholders) {
        String formatMessage = String.format(message.toString(), placeholders);
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.WARN).setMessage(formatMessage).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void warnT(String tag, T message, Object... placeholders) {
        String formatMessage = String.format(message.toString(), placeholders);
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.WARN).setMessage(formatMessage).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void error(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.ERROR).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void errorT(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.ERROR).setMessage(message).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void error(T message, Object... placeholders) {
        String formatMessage = String.format(message.toString(), placeholders);
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.ERROR).setMessage(formatMessage).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void errorT(String tag, T message, Object... placeholders) {
        String formatMessage = String.format(message.toString(), placeholders);
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.ERROR).setMessage(formatMessage).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void debug(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.DEBUG).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void debugT(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.DEBUG).setMessage(message).setTag(
                tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void debug(T message, Object... placeholders) {
        String formatMessage = String.format(message.toString(), placeholders);
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.DEBUG).setMessage(formatMessage).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void debugT(String tag, T message, Object... placeholders) {
        String formatMessage = String.format(message.toString(), placeholders);
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.DEBUG).setMessage(formatMessage).setTag(
                tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void debugs(T... messages) {
        String message = "";
        for (int i = 0; i < messages.length; i++) {
            String m = messages[i] == null ? "[null]" : messages[i].toString();
            if (m != null) {
                if (i == 0) {
                    message = message + m;
                }
                else {
                    message = message + " ，" + m;
                }
            }
        }

        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.DEBUG).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void verbose(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.VERBOSE).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void verboseT(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.VERBOSE).setMessage(message).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void verbose(T message, Object... placeholders) {
        String formatMessage = String.format(message.toString(), placeholders);
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.VERBOSE).setMessage(formatMessage).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public <T> void verboseT(String tag, T message, Object... placeholders) {
        String formatMessage = String.format(message.toString(), placeholders);
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.VERBOSE).setMessage(formatMessage).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public LogContext getLogContext() {
        return logContext;
    }

}
