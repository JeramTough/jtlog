package com.jeramtough.jtlog.jtlogger;

import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.handler.ComponentHandler;
import com.jeramtough.jtlog.handler.DefaultComponentHandler;
import com.jeramtough.jtlog.log.LogConfig;
import com.jeramtough.jtlog.log.LogContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * 负责生成和管理JtLogger实例
 * <p>
 * Created on 2018-08-21 17:11
 * by @author JeramTough
 */
public final class JtLoggerManager {

    private static HashMap<String, JtLogger> jtLoggerHashMap;
    private static HashMap<Class, ComponentHandler> componentHandlerHashMap;

    static {
        jtLoggerHashMap = new HashMap<>();
        componentHandlerHashMap = new HashMap<>();
    }

    private JtLoggerManager() {
    }

    public static JtLogger getJtLogger(Class contextClass) {
        String contextName = parseContextNameFromAnnotation(contextClass);
        JtLogger jtLogger;
        if (jtLoggerHashMap.containsKey(contextName)) {
            jtLogger = jtLoggerHashMap.get(contextName);
        }
        else {
            LogConfig logConfig = parseLogConfigFromAnnotation(contextClass);
            LogContext logContext = new LogContext(contextName, logConfig);
            jtLogger = new JtLoggerImpl(logContext);
            jtLoggerHashMap.put(contextName, jtLogger);
        }
        return jtLogger;
    }

    public static JtLogger getJtLogger(String contextName) {
        JtLogger jtLogger;
        if (jtLoggerHashMap.containsKey(contextName)) {
            jtLogger = jtLoggerHashMap.get(contextName);
        }
        else {
            LogConfig logConfig = new LogConfig();
            LogContext logContext = new LogContext(contextName, logConfig);
            jtLogger = new JtLoggerImpl(logContext);
            jtLoggerHashMap.put(contextName, jtLogger);
        }
        return jtLogger;
    }

    public static JtLogger getJtLogger(LogContext logContext) {
        JtLogger jtLogger;
        if (jtLoggerHashMap.containsKey(logContext.getContextName())) {
            jtLogger = jtLoggerHashMap.get(logContext.getContextName());
            jtLogger.getLogContext().setLogConfig(logContext.getLogConfig());
        }
        else {
            jtLogger = new JtLoggerImpl(logContext);
            jtLoggerHashMap.put(logContext.getContextName(), jtLogger);
        }
        return jtLogger;
    }

    //*************************
    private static LogConfig parseLogConfigFromAnnotation(Class contextClass) {
        LogConfig logConfig = new LogConfig();

        JtLoggerConfig jtLoggerConfig = (JtLoggerConfig) contextClass.getAnnotation(JtLoggerConfig.class);

        if (jtLoggerConfig != null) {
            logConfig.setEnabled(jtLoggerConfig.isEnabled());
            logConfig.setUsedJtloggerApi(jtLoggerConfig.isUsedJtloggerApi());
            logConfig.setMaxLengthOfRow(jtLoggerConfig.maxLengthOfRow());
            logConfig.setMinVisibleLevel(jtLoggerConfig.minVisibleLevel());
            logConfig.setCallerPlus(jtLoggerConfig.callerPlus());

            //得到componentHandler对象实例.
            ComponentHandler componentHandler;
            if (componentHandlerHashMap.containsKey(contextClass)) {
                componentHandler = componentHandlerHashMap.get(contextClass);
            }
            else {
                componentHandler =
                        parseComponentHandlerByComponentHandlerClass(jtLoggerConfig.recorderHandleClass());
                componentHandlerHashMap.put(contextClass, componentHandler);
            }

            //设置LogRecorder和LogFilter
            componentHandler.handleLogFilters(logConfig.getLogFilters());
            componentHandler.handleLogRecorders(logConfig.getLogRecorders());
        }
        return logConfig;
    }

    private static String parseContextNameFromAnnotation(Class c) {
        JtLoggerConfig jtLoggerConfig = (JtLoggerConfig) c.getAnnotation(JtLoggerConfig.class);
        if (jtLoggerConfig != null && !jtLoggerConfig.contextName().equals("")) {
            return jtLoggerConfig.contextName();
        }
        else {
            return c.getSimpleName();
        }
    }

    private static ComponentHandler parseComponentHandlerByComponentHandlerClass(Class<?
            extends ComponentHandler> c) {
        ComponentHandler componentHandler = null;
        try {
            Constructor constructor = c.getConstructor();
            componentHandler = (ComponentHandler) constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (componentHandler == null) {
                componentHandler = new DefaultComponentHandler();
            }
        }
        return componentHandler;
    }
}
