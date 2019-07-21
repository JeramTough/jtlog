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
import com.jeramtough.jtlog.recorder.LogRecorder;

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
            LogRecorder[] additionalLogRecorders =
                    parseAdditionalLogRecodersFromAnnotation(contextClass);
            logContext.getLogFilters().addAll(Arrays.asList(additionalLogFilters));
            logContext.getLogRecorders().addAll(Arrays.asList(additionalLogRecorders));
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

        jtLoggerHashMap.clear();
    }


    //**********************************

    private static Logger generatingLogger(LogContext logContext) {
        Logger logger = new JtLogger(logContext);

        //add some default LogFilters
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
            LogFilter[] logFilters = new LogFilter[logConfiguration.logFilters().length];
            for (int i = 0; i < logConfiguration.logFilters().length; i++) {
                Class<? extends LogFilter> filterClass = logConfiguration.logFilters()[i];
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

    private static LogRecorder[] parseAdditionalLogRecodersFromAnnotation(Class c) {
        LogConfiguration logConfiguration = (LogConfiguration) c.getAnnotation(
                LogConfiguration.class);
        if (logConfiguration != null) {
            LogRecorder[] logRecorders = new LogRecorder[logConfiguration.logRecorders().length];
            for (int i = 0; i < logConfiguration.logRecorders().length; i++) {
                Class<? extends LogRecorder> recorderClass = logConfiguration.logRecorders()[i];
                try {
                    LogRecorder logRecorder = recorderClass.newInstance();
                    logRecorders[i] = logRecorder;

                }
                catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                    return new LogRecorder[]{};
                }
            }
            return logRecorders;
        }
        return new LogRecorder[]{};
    }

    private static void addCertainLogFilters(LogContext logContext) {
        EnableLogFilter enableLogFilter = new EnableLogFilter();
        MinLevelLogFilter minLevelLogFilter = new MinLevelLogFilter();

        logContext.getLogFilters().add(enableLogFilter);
        logContext.getLogFilters().add(minLevelLogFilter);
    }

}
