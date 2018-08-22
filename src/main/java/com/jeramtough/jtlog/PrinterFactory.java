package com.jeramtough.jtlog;

import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.style.PrintStyleManager;

/**
 * Created on 2018-08-21 17:31
 * by @author JeramTough
 */
public class PrinterFactory {

    private static volatile JtPrinter jtPrinter;
    private static volatile LogbackPrinter logbackPrinter;
    private static volatile Log4j2Printer log4j2Printer;
    private static volatile AndroidPrinter androidPrinter;

    private static final String ANDROID_LOGCAT_PACKAGE_NAME = "android.util" +
            ".Log";

    private PrinterFactory() {
    }

    public static Printer getPrinter(LogContext logContext) {
        Printer printer;
        if (logContext.getLogConfig().isBridgedLogback()) {
            printer = getLogbackPrinter(logContext);
        } else if (logContext.getLogConfig().isBridgedLog4j2()) {
            printer = getLog4j2Printer(logContext);
        } else if (isUsedAndroidLoccat()) {
            printer = getAndroidPrinter(logContext);
        }
        else {
            printer = getJtPrinter(logContext);
        }
        return printer;
    }

    //***********************
    //***********************
    private static JtPrinter getJtPrinter(LogContext logContext) {
        if (jtPrinter == null) {
            synchronized (PrintStyleManager.class) {
                if (jtPrinter == null) {
                    jtPrinter = new JtPrinter(logContext);
                }
            }
        }
        return jtPrinter;
    }

    private static LogbackPrinter getLogbackPrinter(LogContext logContext) {
        if (logbackPrinter == null) {
            synchronized (PrintStyleManager.class) {
                if (logbackPrinter == null) {
                    logbackPrinter = new LogbackPrinter(logContext);
                }
            }
        }
        return logbackPrinter;
    }

    private static Log4j2Printer getLog4j2Printer(LogContext logContext) {
        if (log4j2Printer == null) {
            synchronized (PrintStyleManager.class) {
                if (log4j2Printer == null) {
                    log4j2Printer = new Log4j2Printer(logContext);
                }
            }
        }
        return log4j2Printer;
    }

    private static AndroidPrinter getAndroidPrinter(LogContext logContext) {
        if (androidPrinter == null) {
            synchronized (PrintStyleManager.class) {
                if (androidPrinter == null) {
                    androidPrinter = new AndroidPrinter(logContext);
                }
            }
        }
        return androidPrinter;
    }

    private static boolean isUsedAndroidLoccat() {
        try {
            Class androidLogcatClass = Class.forName(ANDROID_LOGCAT_PACKAGE_NAME);
            if (androidLogcatClass != null) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}