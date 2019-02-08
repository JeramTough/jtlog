package com.jeramtough.jtlog.facade;

import com.jeramtough.jtlog.annotation.LogConfiguration;
import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.jtlogger.Logger;
import com.jeramtough.jtlog.jtlogger.LoggerManager;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.printer.PrinterFactory;
import com.jeramtough.jtlog.util.MyStringUtil;

/**
 * 日志工具类，使用一个全局的JtLogger对象实例，可用静态方法直接调用
 * Created on 2018-08-21 18:15
 * by @author JeramTough
 */
public class L {

    private static LogContext logContext;

    static {
        Logger logger = LoggerManager.getLogger(L.class);
        logContext = logger.getLogContext();

        StringBuilder text = new StringBuilder();
        for (int ii = 0; ii < 3; ii++) {
            if (ii != 1) {
                for (int i = 0;
                     i < logger.getLogContext().getLogConfig().getMaxLengthOfRow() * 2;
                     ++i) {
                    text.append("-");
                }
            }
            else {
                text.append(MyStringUtil.getLogo());
            }
        }
        p(text.toString());
    }


    public static void arrive() {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.ARRIVE).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    public static <T> void p(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.PRINTLN).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    public static <T> void info(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.INFO).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    public static <T> void info(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.INFO).setMessage(message).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    public static <T> void warn(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.WARN).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    public static <T> void warn(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.WARN).setMessage(message).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    public static <T> void error(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.ERROR).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    public static <T> void error(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.ERROR).setMessage(message).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    public static <T> void debug(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.DEBUG).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    public static <T> void debug(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.DEBUG).setMessage(message).setTag(
                tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    public static <T> void debugs(T... messages) {
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

    public static <T> void verbose(T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.VERBOSE).setMessage(message).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    public static <T> void verbose(String tag, T message) {
        LogInformation logInformation = new LogInformation.Builder().setJtLogLevel(
                LogLevel.VERBOSE).setMessage(message).setTag(tag).build();
        PrinterFactory.getPrinter(logContext).print(logInformation);
    }

    public static LogContext getLogContext() {
        return logContext;
    }


}
