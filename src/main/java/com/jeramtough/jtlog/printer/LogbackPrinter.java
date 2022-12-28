package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.bean.LogInformation;

import java.lang.reflect.InvocationTargetException;

/**
 * Created on 2018-08-21 18:21
 * by @author JeramTough
 */
public class LogbackPrinter extends BasePrinter {

    public static final String LOGBACK_FACTORY_PACKAGE_NAME = "org.slf4j.LoggerFactory";


    public LogbackPrinter() {
    }

    @Override
    public void verbose(LogInformation logInformation, String stylizedText) {
        Object loggerObject = getLoggerObject(logInformation.getLogContext());
        try {
            loggerObject.getClass().getMethod("trace", String.class).invoke(loggerObject,
                    stylizedText);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void arrive(LogInformation logInformation, String stylizedText) {
        Object loggerObject = getLoggerObject(logInformation.getLogContext());
        try {
            loggerObject.getClass().getMethod("debug", String.class).invoke(loggerObject,
                    stylizedText);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void debug(LogInformation logInformation, String stylizedText) {
        Object loggerObject = getLoggerObject(logInformation.getLogContext());
        try {
            loggerObject.getClass().getMethod("debug", String.class).invoke(loggerObject,
                    stylizedText);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void info(LogInformation logInformation, String stylizedText) {
        Object loggerObject = getLoggerObject(logInformation.getLogContext());
        try {
            loggerObject.getClass().getMethod("info", String.class).invoke(loggerObject,
                    stylizedText);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void warn(LogInformation logInformation, String stylizedText) {
        Object loggerObject = getLoggerObject(logInformation.getLogContext());
        try {
            loggerObject.getClass().getMethod("warn", String.class).invoke(loggerObject,
                    stylizedText);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error(LogInformation logInformation, String stylizedText) {
        Object loggerObject = getLoggerObject(logInformation.getLogContext());
        try {
            loggerObject.getClass().getMethod("error", String.class).invoke(loggerObject,
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
            Object loggerObject = Class.forName(LOGBACK_FACTORY_PACKAGE_NAME).getMethod(
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
