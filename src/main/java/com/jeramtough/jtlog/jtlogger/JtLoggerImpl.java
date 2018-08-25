package com.jeramtough.jtlog.jtlogger;

import com.jeramtough.jtlog.printer.PrinterFactory;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.log.LogInformation;

/**
 * Created on 2018-08-21 14:51
 * by @author JeramTough
 */
public class JtLoggerImpl implements JtLogger {

    private LogContext logContext;

    JtLoggerImpl(LogContext logContext) {
        this.logContext = logContext;
    }

    @Override
    public synchronized void arrive() {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(LogLevel.ARRIVE)
                .setLogContext(logContext).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public synchronized <T> void p(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(LogLevel.PRINTLN)
                .setLogContext(logContext).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public synchronized <T> void info(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(LogLevel.INFO)
                .setMessage(message).setLogContext(logContext).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public synchronized <T> void info(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(LogLevel.INFO)
                .setMessage(message).setLogContext(logContext).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public synchronized <T> void warn(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(LogLevel.WARN)
                .setLogContext(logContext).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public synchronized <T> void warn(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(LogLevel.WARN)
                .setMessage(message).setLogContext(logContext).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public synchronized <T> void error(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(LogLevel.ERROR)
                .setLogContext(logContext).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public synchronized <T> void error(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(LogLevel.ERROR)
                .setMessage(message).setLogContext(logContext).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public synchronized <T> void debug(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(LogLevel.DEBUG)
                .setLogContext(logContext).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public synchronized <T> void debug(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(LogLevel.DEBUG)
                .setMessage(message).setLogContext(logContext).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public synchronized <T> void debugs(T... messages) {
        String message = "";
        for (int i = 0; i < messages.length; i++) {
            String m = messages[i] == null ? "[null]" : messages[i].toString();
            if (m != null) {
                if (i == 0) {
                    message = message + m;
                } else {
                    message = message + " ，" + m;
                }
            }
        }

        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(LogLevel.DEBUG)
                .setLogContext(logContext).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public synchronized <T> void verbose(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(LogLevel.VERBOSE)
                .setLogContext(logContext).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public synchronized <T> void verbose(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(LogLevel.VERBOSE)
                .setMessage(message).setLogContext(logContext).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    @Override
    public synchronized LogContext getLogContext() {
        return logContext;
    }

}
