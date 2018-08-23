package com.jeramtough.jtlog.facade;

import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.jtlogger.JtLogger;
import com.jeramtough.jtlog.jtlogger.JtLoggerManager;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.util.MyStringUtil;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * Created on 2018-08-21 18:15
 * by @author JeramTough
 */
@JtLoggerConfig(isUsedJtloggerApi = false,
        minVisibleLevel = LogLevel.VERBOSE)
public class L {

    private static JtLogger jtLogger;

    static {
        jtLogger = JtLoggerManager.getJtLogger(L.class);

        StringBuilder text = new StringBuilder();
        for (int ii = 0; ii < 3; ii++) {
            if (ii != 1) {
                for (int i = 0; i < jtLogger.getLogContext().getLogConfig().getMaxLengthOfRow() * 2; ++i) {
                    text.append("-");
                }
            } else {
                text.append(MyStringUtil.getLogo());
            }
        }
        p(text.toString());
    }


    public static void arrive() {
        jtLogger.arrive();
    }


    public static <T1> void p(T1 message) {
        jtLogger.p(message);
    }

    public static <T> void info(T message) {
        jtLogger.info(message);
    }

    public static <T> void info(String tag, T message) {
        jtLogger.info(tag, message);
    }

    public static <T> void warn(T message) {
        jtLogger.warn(message);
    }

    public static <T> void warn(String tag, T message) {
        jtLogger.warn(tag, message);
    }

    public static <T> void error(T message) {
        jtLogger.error(message);
    }

    public static <T> void error(String tag, T message) {
        jtLogger.error(tag, message);
    }

    public static <T> void debug(T message) {
        jtLogger.debug(message);
    }

    public static <T> void debug(String tag, T message) {
        jtLogger.debug(tag, message);
    }

    public static <T> void verbose(T message) {
        jtLogger.verbose(message);
    }

    public static <T> void verbose(String tag, T message) {
        jtLogger.verbose(tag, message);
    }

    public static LogContext getLogContext() {
        return jtLogger.getLogContext();
    }
}
