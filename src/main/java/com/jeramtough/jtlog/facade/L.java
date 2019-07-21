package com.jeramtough.jtlog.facade;

import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.jtlogger.Logger;
import com.jeramtough.jtlog.jtlogger.LoggerManager;
import com.jeramtough.jtlog.tag.Tag;
import com.jeramtough.jtlog.util.MyStringUtil;

/**
 * 日志工具类，使用一个全局的JtLogger对象实例，可用静态方法直接调用
 * Created on 2018-08-21 18:15
 * by @author JeramTough
 */
public class L {

    static {
        StringBuilder text = new StringBuilder();
        //三步输出logo
        int oneTwoThree = 3;
        for (int ii = 0; ii < oneTwoThree; ii++) {
            if (ii != 1) {
                for (int i = 0;
                     i < getLogger().getLogContext().getLogConfig().getMaxLengthOfRow() * 2;
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
        getLogger().arrive();
    }

    public static <T> void p(T message) {
        getLogger().p(message);
    }

    public static <T> void e(T message) {
        getLogger().e(message);
    }

    public static <T> void info(T message) {
        getLogger().info(message);
    }

    public static <T> void info(Tag tag, T message) {
        getLogger().info(tag, message);
    }

    public static <T> void info(T message, Object... placeholders) {
        getLogger().info(message, placeholders);
    }

    public static <T> void info(Tag tag, T message, Object... placeholders) {
        getLogger().info(tag, message, placeholders);
    }

    public static <T> void warn(T message) {
        getLogger().warn(message);
    }

    public static <T> void warn(Tag tag, T message) {
        getLogger().warn(tag, message);
    }

    public static <T> void warn(T message, Object... placeholders) {
        getLogger().warn(message, placeholders);
    }

    public static <T> void warn(Tag tag, T message, Object... placeholders) {
        getLogger().warn(tag, message, placeholders);
    }

    public static <T> void error(T message) {
        getLogger().error(message);
    }

    public static <T> void error(Tag tag, T message) {
        getLogger().error(tag, message);
    }

    public static <T> void error(T message, Object... placeholders) {
        getLogger().error(message, placeholders);
    }

    public static <T> void error(Tag tag, T message, Object... placeholders) {
        getLogger().error(tag, message, placeholders);
    }

    public static void error(Exception e) {
        getLogger().error(e);
    }

    public static <T> void error(Exception e, T message, Object... placeholders) {
        getLogger().error(e, message, placeholders);
    }

    public static <T> void error(Tag tag, Exception e, T message, Object... placeholders) {
        getLogger().error(tag, e, message, placeholders);
    }

    public static <T> void debug(T message) {
        getLogger().debug(message);
    }

    public static <T> void debug(Tag tag, T message) {
        getLogger().debug(tag, message);
    }

    public static <T> void debug(T message, Object... placeholders) {
        getLogger().debug(message, placeholders);
    }

    public static <T> void debug(Tag tag, T message, Object... placeholders) {
        getLogger().debug(tag, message, placeholders);
    }

    @SafeVarargs
    public static <T> void debugs(T... messages) {
        getLogger().debug(messages);
    }

    public static <T> void verbose(T message) {
        getLogger().verbose(message);
    }

    public static <T> void verbose(Tag tag, T message) {
        getLogger().verbose(tag, message);
    }

    public static <T> void verbose(T message, Object... placeholders) {
        getLogger().verbose(message, placeholders);
    }

    public static <T> void verbose(Tag tag, T message, Object... placeholders) {
        getLogger().verbose(tag, message, placeholders);
    }

    public static LogContext getLogContext() {
        return getLogger().getLogContext();
    }


    //*************

    private static Logger getLogger() {
        Logger logger = LoggerManager.getLogger(L.class);

        //设置偏移量为1
        logger.getLogContext().getLogConfig().setStackTraceOffset(1);
        return logger;
    }

}
