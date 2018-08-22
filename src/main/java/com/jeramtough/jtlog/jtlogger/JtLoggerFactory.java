package com.jeramtough.jtlog.jtlogger;

import com.jeramtough.jtlog.log.LogConfig;
import com.jeramtough.jtlog.log.LogContext;

import java.util.HashMap;

/**
 * Created on 2018-08-21 17:11
 * by @author JeramTough
 */
public final class JtLoggerFactory {

    private static HashMap<String, JtLogger> jtLoggerHashMap;

    static {
        jtLoggerHashMap = new HashMap<>();
    }

    private JtLoggerFactory() {
    }

    public static JtLogger getJtLogger(Class c) {
        String contextName = c.getSimpleName();
        JtLogger jtLogger;
        if (jtLoggerHashMap.containsKey(contextName)) {
            jtLogger = jtLoggerHashMap.get(contextName);
        } else {
            LogContext logContext = new LogContext();
            LogConfig logConfig = new LogConfig();
            logContext.setLogConfig(logConfig);
            logContext.setContextName(contextName);

            jtLogger = new JtLoggerImpl(logContext);
            jtLoggerHashMap.put(contextName, jtLogger);
        }
        return jtLogger;
    }

}
