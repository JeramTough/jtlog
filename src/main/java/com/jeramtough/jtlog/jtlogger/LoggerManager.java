package com.jeramtough.jtlog.jtlogger;

import com.jeramtough.jtlog.annotation.LogConfiguration;
import com.jeramtough.jtlog.config.SimpleLogConfigDefaultValues;
import com.jeramtough.jtlog.config.LogConfig;
import com.jeramtough.jtlog.config.LogConfigDefaultValues;
import com.jeramtough.jtlog.config.LogConfigFactory;
import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.filter.EnableLogFilter;
import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.filter.MinLevelLogFilter;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 负责生成和管理JtLogger实例
 * <p>
 * Created on 2019-02-08 13:45
 * by @author JeramTough
 */
public final class LoggerManager {

    private static HashMap<String, Logger> jtLoggerHashMap;

    private static LogConfigDefaultValues logConfigDefaultValues;

    static {
        jtLoggerHashMap = new HashMap<>();
        logConfigDefaultValues = new SimpleLogConfigDefaultValues();
    }

    public static Logger getLogger(String contextName) {
        Logger logger;
        if (jtLoggerHashMap.containsKey(contextName)) {
            logger = jtLoggerHashMap.get(contextName);
        }
        else {
            LogConfig logConfig = LogConfigFactory.getDefaultValueLogConfig(
                    logConfigDefaultValues);
            LogContext logContext = new LogContext(contextName, logConfig);
            logger = generatingLogger(logContext);
        }
        return logger;
    }

    public static Logger getLogger(LogContext logContext) {
        Logger logger;
        if (jtLoggerHashMap.containsKey(logContext.getContextName())) {
            logger = jtLoggerHashMap.get(logContext.getContextName());
        }
        else {
            logger = generatingLogger(logContext);
        }
        return logger;
    }

    public static Logger getLogger(Class contextClass) {
        String contextName = parseContextNameFromAnnotation(contextClass);

        Logger logger;
        if (jtLoggerHashMap.containsKey(contextName)) {
            logger = jtLoggerHashMap.get(contextName);
        }
        else {
            LogConfig logConfig = LogConfigFactory.getLogConfigByAnnotation(
                    logConfigDefaultValues, contextClass);
            LogContext logContext = new LogContext(contextName, logConfig);

            LogFilter[] additionalLogFilters =
                    parseAdditionalLogFiltersFromAnnotation(contextClass);
            logContext.getLogFilters().addAll(Arrays.asList(additionalLogFilters));

            logger = generatingLogger(logContext);
        }
        return logger;
    }

    public static LogConfigDefaultValues getLogConfigDefaultValues() {
        return logConfigDefaultValues;
    }

    public static void setLogConfigDefaultValues(
            LogConfigDefaultValues logConfigDefaultValues) {
        LoggerManager.logConfigDefaultValues = logConfigDefaultValues;
    }


    //**********************************

    private static Logger generatingLogger(LogContext logContext) {
        Logger logger = new JtLogger(logContext);

        //add some default LogFillters
        addCertainLogFilters(logContext);

        jtLoggerHashMap.put(logContext.getContextName(), logger);
        return logger;
    }

    private static String parseContextNameFromAnnotation(Class c) {
        LogConfiguration logConfiguration = (LogConfiguration) c.getAnnotation(
                LogConfiguration.class);
        if (logConfiguration != null && !"default".equals(logConfiguration.contextName())) {
            return logConfiguration.contextName();
        }
        else {
            return c.getSimpleName();
        }
    }

    private static LogFilter[] parseAdditionalLogFiltersFromAnnotation(Class c) {
        LogConfiguration logConfiguration = (LogConfiguration) c.getAnnotation(
                LogConfiguration.class);
        if (logConfiguration != null) {
            LogFilter[] logFilters = new LogFilter[logConfiguration.logFilter().length];
            for (int i = 0; i < logConfiguration.logFilter().length; i++) {
                Class<? extends LogFilter> filterClass = logConfiguration.logFilter()[i];
                try {
                    LogFilter logFilter = filterClass.newInstance();
                    logFilters[i] = logFilter;

                }
                catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                    return new LogFilter[]{};
                }
            }
            return logFilters;
        }
        return new LogFilter[]{};
    }

    private static void addCertainLogFilters(LogContext logContext) {
        EnableLogFilter enableLogFilter = new EnableLogFilter();
        MinLevelLogFilter minLevelLogFilter = new MinLevelLogFilter();

        logContext.getLogFilters().add(enableLogFilter);
        logContext.getLogFilters().add(minLevelLogFilter);
    }

}
