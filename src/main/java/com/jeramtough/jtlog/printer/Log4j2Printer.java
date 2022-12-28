package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.bean.LogInformation;

import java.lang.reflect.InvocationTargetException;

/**
 * Created on 2018-08-21 19:27
 * by @author JeramTough
 */
public class Log4j2Printer extends BasePrinter {

    public static final String LOG4J2_LOG_MANAGER_PACKAGE_NAME = "org.apache.logging.log4j" +
            ".LogManager";

    public Log4j2Printer() {

    }

    @Override
    public void verbose(LogInformation logInformation, String stylizedText) {
        try {
            getLoggerObject(logInformation.getLogContext()).getClass().getMethod("trace",
                    String.class).invoke(getLoggerObject(logInformation.getLogContext()),
                    stylizedText);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void arrive(LogInformation logInformation, String stylizedText) {
        try {
            getLoggerObject(logInformation.getLogContext()).getClass().getMethod("error",
                    String.class).invoke(getLoggerObject(logInformation.getLogContext()),
                    stylizedText);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void debug(LogInformation logInformation, String stylizedText) {
        try {
            getLoggerObject(logInformation.getLogContext()).getClass().getMethod("debug",
                    String.class).invoke(getLoggerObject(logInformation.getLogContext()),
                    stylizedText);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void info(LogInformation logInformation, String stylizedText) {
        try {
            getLoggerObject(logInformation.getLogContext()).getClass().getMethod("info",
                    String.class).invoke(getLoggerObject(logInformation.getLogContext()),
                    stylizedText);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void warn(LogInformation logInformation, String stylizedText) {
        try {
            getLoggerObject(logInformation.getLogContext()).getClass().getMethod("warn",
                    String.class).invoke(getLoggerObject(logInformation.getLogContext()),
                    stylizedText);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error(LogInformation logInformation, String stylizedText) {
        try {
            getLoggerObject(logInformation.getLogContext()).getClass().getMethod("error",
                    String.class).invoke(getLoggerObject(logInformation.getLogContext()),
                    stylizedText);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void println(LogInformation logInformation, String stylizedText) {
        System.out.println(stylizedText);
    }

    //*********

    private Object getLoggerObject(LogContext logContext) {
        try {
            Object loggerObject = Class.forName(LOG4J2_LOG_MANAGER_PACKAGE_NAME).getMethod(
                    "getLogger", String.class).invoke(null, logContext.getContextName());
            return loggerObject;
        }
        catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException |
               InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalStateException("获取日志对象失败");
        }
    }

}
