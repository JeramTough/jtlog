package com.jeramtough.jtlog.jtlogger;

import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.filter.EnableLogFilter;
import com.jeramtough.jtlog.filter.MinLevelLogFilter;
import com.jeramtough.jtlog.handler.ComponentHandler;
import com.jeramtough.jtlog.handler.DefaultComponentHandler;
import com.jeramtough.jtlog.bean.LogContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * 负责生成和管理JtLogger实例
 * <p>
 * Created on 2018-08-21 17:11
 * by @author JeramTough
 */
public final class LoggerManager {

    private static HashMap<String, Logger> jtLoggerHashMap;
    private static HashMap<Class, ComponentHandler> componentHandlerHashMap;

    static {
        jtLoggerHashMap = new HashMap<>();
        componentHandlerHashMap = new HashMap<>();
    }

    private LoggerManager() {
    }

    public static Logger getJtLogger(Class contextClass) {
        String contextName = parseContextNameFromAnnotation(contextClass);
        Logger logger;
        if (jtLoggerHashMap.containsKey(contextName)) {
            logger = jtLoggerHashMap.get(contextName);
        }
        else {
            LogConfig logConfig = parseLogConfigFromAnnotation(contextClass);

            //添加一些默认实现的日志过滤器
            addSomeLogFillters(logConfig);

            LogContext logContext = new LogContext(contextName, logConfig);

            logger = new JtLogger(logContext);
            jtLoggerHashMap.put(contextName, logger);
        }
        return logger;
    }

    public static Logger getJtLogger(String contextName) {
        Logger logger;
        if (jtLoggerHashMap.containsKey(contextName)) {
            logger = jtLoggerHashMap.get(contextName);
        }
        else {
            LogConfig logConfig = new LogConfig();

            //添加一些默认实现的日志过滤器
            addSomeLogFillters(logConfig);

            LogContext logContext = new LogContext(contextName, logConfig);
            logger = new JtLogger(logContext);
            jtLoggerHashMap.put(contextName, logger);
        }
        return logger;
    }

    public static Logger getJtLogger(LogContext logContext) {
        Logger logger;
        if (jtLoggerHashMap.containsKey(logContext.getContextName())) {
            logger = jtLoggerHashMap.get(logContext.getContextName());
            logger.getLogContext().setLogConfig(logContext.getLogConfig());
        }
        else {

            //添加一些默认实现的日志过滤器
            addSomeLogFillters(logContext.getLogConfig());

            logger = new JtLogger(logContext);
            jtLoggerHashMap.put(logContext.getContextName(), logger);
        }
        return logger;
    }

    //*************************
    private static LogConfig parseLogConfigFromAnnotation(Class contextClass) {

        LogConfig logConfig = new LogConfig();

        JtLoggerConfig jtLoggerConfig = (JtLoggerConfig) contextClass.getAnnotation(
                JtLoggerConfig.class);

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
                        parseComponentHandlerByComponentHandlerClass(
                                jtLoggerConfig.componentHandleClass());
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
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        finally {
            if (componentHandler == null) {
                componentHandler = new DefaultComponentHandler();
            }
        }
        return componentHandler;
    }

    private static void addSomeLogFillters(LogConfig logConfig) {
        EnableLogFilter enableLogFilter = new EnableLogFilter();
        MinLevelLogFilter minLevelLogFilter = new MinLevelLogFilter();

        logConfig.addLogFilter(enableLogFilter);
        logConfig.addLogFilter(minLevelLogFilter);
    }
}
