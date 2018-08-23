package com.jeramtough.jtlog.jtlogger;

import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.log.LogConfig;
import com.jeramtough.jtlog.log.LogContext;

import java.util.HashMap;

/**
 * Created on 2018-08-21 17:11
 * by @author JeramTough
 */
public final class JtLoggerManager {

    private static HashMap<String, JtLogger> jtLoggerHashMap;

    static {
        jtLoggerHashMap = new HashMap<>();
    }

    private JtLoggerManager() {
    }

    public static JtLogger getJtLogger(Class c) {
        String contextName = parseContextNameFromAnnotation(c);
        JtLogger jtLogger;
        if (jtLoggerHashMap.containsKey(contextName)) {
            jtLogger = jtLoggerHashMap.get(contextName);
        } else {
            LogContext logContext = new LogContext();
            LogConfig logConfig = parseLogConfigFromAnnotation(c);
            logContext.setLogConfig(logConfig);
            logContext.setContextName(contextName);
            jtLogger = new JtLoggerImpl(logContext);
            jtLoggerHashMap.put(contextName, jtLogger);
        }
        return jtLogger;
    }

    //*************************
    private static LogConfig parseLogConfigFromAnnotation(Class c) {
        LogConfig logConfig = new LogConfig();

        JtLoggerConfig jtLoggerConfig = (JtLoggerConfig) c.getDeclaredAnnotation(JtLoggerConfig.class);

        if (jtLoggerConfig != null) {
            logConfig.setEnabled(jtLoggerConfig.isEnabled());
            logConfig.setUsedJtloggerApi(jtLoggerConfig.isUsedJtloggerApi());
            logConfig.setMaxLengthOfRow(jtLoggerConfig.maxLengthOfRow());
            logConfig.setMinVisibleLevel(jtLoggerConfig.minVisibleLevel());
        }
        return logConfig;
    }

    private static String parseContextNameFromAnnotation(Class c) {
        JtLoggerConfig jtLoggerConfig = (JtLoggerConfig) c.getDeclaredAnnotation(JtLoggerConfig.class);
        if (jtLoggerConfig != null && !jtLoggerConfig.contextName().equals("")) {
            return jtLoggerConfig.contextName();
        } else {
            return c.getSimpleName();
        }
    }

}
