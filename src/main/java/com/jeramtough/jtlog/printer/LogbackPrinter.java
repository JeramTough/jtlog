package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.bean.LogContext;
import com.jeramtough.jtlog.bean.LogInformation;

import java.lang.reflect.InvocationTargetException;

/**
 * Created on 2018-08-21 18:21
 * by @author JeramTough
 */
public class LogbackPrinter extends BasePrinter {

    public static final String LOGBACK_FACTORY_PACKAGE_NAME = "org.slf4j.LoggerFactory";

    private Object loggerObject;

    public LogbackPrinter(LogContext logContext) {
        super(logContext);
        try {
            loggerObject = Class.forName("org.slf4j.LoggerFactory").getMethod(
                   "getLogger",String.class).invoke(null, logContext.getContextName());
        } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void verbose(LogInformation logInformation, String stylizedText) {
        try {
            loggerObject.getClass().getMethod("trace",String.class).invoke(loggerObject,stylizedText);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void arrive(LogInformation logInformation, String stylizedText) {
        try {
            loggerObject.getClass().getMethod("debug",String.class).invoke(loggerObject,
                    stylizedText);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void debug(LogInformation logInformation, String stylizedText) {
        try {
            loggerObject.getClass().getMethod("debug",String.class).invoke(loggerObject,
                    stylizedText);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void info(LogInformation logInformation, String stylizedText) {
        try {
            loggerObject.getClass().getMethod("info",String.class).invoke(loggerObject,
                    stylizedText);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void warn(LogInformation logInformation, String stylizedText) {
        try {
            loggerObject.getClass().getMethod("warn",String.class).invoke(loggerObject,
                    stylizedText);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error(LogInformation logInformation, String stylizedText) {
        try {
            loggerObject.getClass().getMethod("error",String.class).invoke(loggerObject,
                    stylizedText);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void println(LogInformation logInformation, String stylizedText) {
        System.out.println(stylizedText);
    }

}
