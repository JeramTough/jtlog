package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.bean.LogContext;
import com.jeramtough.jtlog.bean.LogInformation;

import java.lang.reflect.InvocationTargetException;

/**
 * Created on 2018-08-21 19:27
 * by @author JeramTough
 */
public class Log4j2Printer extends BasePrinter {

    public static final String LOG4J2_LOG_MANAGER_PACKAGE_NAME = "org.apache.logging.log4j" +
            ".LogManager";
    private Object loggerObject;

    public Log4j2Printer(LogContext logContext) {
        super(logContext);

        try {
            loggerObject = Class.forName("org.slf4j.LoggerFactory").getMethod(
                    "getLogger", String.class).invoke(null, logContext.getContextName());
        } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void verbose(LogInformation logInformation, String stylizedText) {
        try {
            loggerObject.getClass().getMethod("trace", String.class).invoke(loggerObject, stylizedText);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void arrive(LogInformation logInformation, String stylizedText) {
        try {
            loggerObject.getClass().getMethod("debug", String.class).invoke(loggerObject,
                    stylizedText);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void debug(LogInformation logInformation, String stylizedText) {
        try {
            loggerObject.getClass().getMethod("debug", String.class).invoke(loggerObject,
                    stylizedText);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void info(LogInformation logInformation, String stylizedText) {
        try {
            loggerObject.getClass().getMethod("info", String.class).invoke(loggerObject,
                    stylizedText);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void warn(LogInformation logInformation, String stylizedText) {
        try {
            loggerObject.getClass().getMethod("warn", String.class).invoke(loggerObject,
                    stylizedText);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error(LogInformation logInformation, String stylizedText) {
        try {
            loggerObject.getClass().getMethod("error", String.class).invoke(loggerObject,
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
