package com.jeramtough.jtlog.jtlogger;

import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.log.LogConfig;
import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.logproxy.EnabledJtLoggerProxy;
import com.jeramtough.jtlog.logproxy.LevelLoggerProxy;

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
        String contextName = c.getSimpleName();
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

//            jtLogger = loadLogProxy(jtLogger);
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
            logConfig.setVisibleLevel(jtLoggerConfig.visibleLevel());
        }
        return logConfig;
    }

    private static JtLogger loadLogProxy(JtLogger jtLogger) {
        EnabledJtLoggerProxy enabledJtLoggerProxy = new EnabledJtLoggerProxy();
        LevelLoggerProxy levelLoggerProxy = new LevelLoggerProxy();

        jtLogger = enabledJtLoggerProxy.doProxy(jtLogger);
        jtLogger = levelLoggerProxy.doProxy(jtLogger);
        return jtLogger;
    }

}
