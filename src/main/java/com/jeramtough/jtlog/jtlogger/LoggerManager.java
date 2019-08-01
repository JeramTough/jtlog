package com.jeramtough.jtlog.jtlogger;

import com.jeramtough.jtlog.annotation.LogConfiguration;
import com.jeramtough.jtlog.config.*;
import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.filter.EnableLogFilter;
import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.filter.MinLevelLogFilter;
import com.jeramtough.jtlog.recorder.LogRecorder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 负责生成和管理JtLogger实例
 * <p>
 * Created on 2019-02-08 13:45
 * by @author JeramTough
 */
public final class LoggerManager {

    private static Map<String, Logger> loggerMap;

    private static LogConfigDefaultValues logConfigDefaultValues;
    private static LogConfigFactory logConfigFactory;

    static {
        logConfigDefaultValues = new SimpleLogConfigDefaultValues();
        initResources();
    }

    static void initResources(){
        loggerMap = new HashMap<>();
        logConfigFactory = getLogConfigFactory(logConfigDefaultValues);
    }

    public static Logger getLogger(String contextName) {
        Logger logger;
        if (loggerMap.containsKey(contextName)) {
            logger = loggerMap.get(contextName);
        }
        else {
            LogConfig logConfig = logConfigFactory.getDefaultValueLogConfig(contextName);
            LogContext logContext = new LogContext(contextName, logConfig);
            logger = generatingLogger(logContext);
        }
        return logger;
    }

    /**
     * @deprecated 不好使也不常用，已经不推荐使用了
     */
    @Deprecated
    public static Logger getLogger(LogContext logContext) {
        Logger logger;
        if (loggerMap.containsKey(logContext.getContextName())) {
            logger = loggerMap.get(logContext.getContextName());
        }
        else {
            logger = generatingLogger(logContext);
        }
        return logger;
    }

    public static Logger getLogger(Class contextClass) {
        String contextName = parseContextNameFromAnnotation(contextClass);

        Logger logger;
        if (loggerMap.containsKey(contextName)) {
            logger = loggerMap.get(contextName);
        }
        else {
            LogConfig logConfig = logConfigFactory.getLogConfigByAnnotation
                    (contextName, contextClass);
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
        initResources();
    }


    //**********************************

    private static LogConfigFactory getLogConfigFactory(
            LogConfigDefaultValues logConfigDefaultValues) {

        if (logConfigDefaultValues.decideCoverConfigFile() == null) {
            return new DefalutLogConfigFactory(logConfigDefaultValues);
        }
        else {
            return new CoverLogConfigFactory(logConfigDefaultValues);
        }
    }

    private static Logger generatingLogger(LogContext logContext) {
        Logger logger = new JtLogger(logContext);

        //add some default LogFilters
        addCertainLogFilters(logContext);

        loggerMap.put(logContext.getContextName(), logger);
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

        //添加默认的两个过滤器
        EnableLogFilter enableLogFilter = new EnableLogFilter();
        MinLevelLogFilter minLevelLogFilter = new MinLevelLogFilter();
        logContext.getLogFilters().add(enableLogFilter);
        logContext.getLogFilters().add(minLevelLogFilter);

        //添加用户自定义的全局过滤器和记录器
        logConfigDefaultValues.additionGlobalLogFilters(logContext.getLogFilters());
        logConfigDefaultValues.additionGlobalLogRecorders(logContext.getLogRecorders());
    }

}
