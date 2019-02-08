package com.jeramtough.jtlog.facade;

import com.jeramtough.jtlog.annotation.LogConfiguration;
import com.jeramtough.jtlog.jtlogger.Logger;
import com.jeramtough.jtlog.jtlogger.LoggerManager;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.util.MyStringUtil;

/**
 * 日志工具类，使用一个全局的JtLogger对象实例，可用静态方法直接调用
 * Created on 2018-08-21 18:15
 * by @author JeramTough
 */
public class L {

    private static Logger logger;

    static {
        logger = LoggerManager.getLogger(L.class);

        StringBuilder text = new StringBuilder();
        for (int ii = 0; ii < 3; ii++) {
            if (ii != 1) {
                for (int i = 0;
                     i < logger.getLogContext().getLogConfig().getMaxLengthOfRow() * 2;
                     ++i) {
                    text.append("-");
                }
            }
            else {
                text.append(MyStringUtil.getLogo());
            }
        }
        p(text.toString());
    }


    public static void arrive() {
        logger.arrive();
    }

    public static <T> void p(T message) {
        logger.p(message);
    }

    public static <T> void info(T message) {
        logger.info(message);
    }

    public static <T> void info(String tag, T message) {
        logger.info(tag, message);
    }

    public static <T> void warn(T message) {
        logger.warn(message);
    }

    public static <T> void warn(String tag, T message) {
        logger.warn(tag, message);
    }

    public static <T> void error(T message) {
        logger.error(message);
    }

    public static <T> void error(String tag, T message) {
        logger.error(tag, message);
    }

    public static <T> void debug(T message) {
        logger.debug(message);
    }

    public static <T> void debug(String tag, T message) {
        logger.debug(tag, message);
    }

    public static <T> void debugs(T... messages) {
        logger.debugs(messages);
    }

    public static <T> void verbose(T message) {
        logger.verbose(message);
    }

    public static <T> void verbose(String tag, T message) {
        logger.verbose(tag, message);
    }

    public static LogContext getLogContext() {
        return logger.getLogContext();
    }


}
