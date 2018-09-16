package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.facade.L;
import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.printer.proxy.FilterPrinterProxy;
import com.jeramtough.jtlog.printer.proxy.PrinterProxy;
import com.jeramtough.jtlog.printer.proxy.RecorderPrinterProxy;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created on 2018-08-21 17:31
 * by @author JeramTough
 */
public class PrinterFactory {

    private static HashMap<String, Printer> printerHashMap;

    static {
        printerHashMap = new HashMap<>();
    }

    private PrinterFactory() {
    }

    public static Printer getPrinter(LogContext logContext) {
        Printer printer;
        if (printerHashMap.containsKey(logContext.getContextName())) {
            printer = printerHashMap.get(logContext.getContextName());
        }
        else {

            if (logContext.getLogConfig().isUsedJtloggerApi()) {
                if (isUsedEspecialLogApi(AndroidPrinter.LOGCAT_PACKAGE_NAME) &&
                        !isUsedEspecialLogApi(LogbackPrinter.LOGBACK_FACTORY_PACKAGE_NAME)) {
                    printer = new AndroidPrinter(logContext);
                }
                else {
                    printer = new JtPrinter(logContext);
                }
            }
            else if (isUsedEspecialLogApi(LogbackPrinter.LOGBACK_FACTORY_PACKAGE_NAME)) {
                printer = new LogbackPrinter(logContext);
            }
            else if (isUsedEspecialLogApi(Log4j2Printer.LOG4J2_LOG_MANAGER_PACKAGE_NAME)) {
                printer = new Log4j2Printer(logContext);
            }
            else {
                printer = new JtPrinter(logContext);
            }

            printer = loadPrinterProxy(logContext, printer);
            printerHashMap.put(logContext.getContextName(), printer);
        }

        return printer;
    }

    //***********************
    //***********************

    /*private static Printer getJtPrinter(LogContext logContext) {
        System.out.println(logContext.getContextName()+"2");
        if (jtPrinter == null) {
            synchronized (PrintStyleManager.class) {
                if (jtPrinter == null) {
                    jtPrinter = new JtPrinter(logContext);
                    jtPrinter = loadPrinterProxy(logContext, jtPrinter);
                }
            }
        }
        return jtPrinter;
    }

    private static Printer getLogbackPrinter(LogContext logContext) {
        if (logbackPrinter == null) {
            synchronized (PrintStyleManager.class) {
                if (logbackPrinter == null) {
                    logbackPrinter = new LogbackPrinter(logContext);
                    logbackPrinter = loadPrinterProxy(logContext, logbackPrinter);
                }
            }
        }
        return logbackPrinter;
    }

    private static Printer getLog4j2Printer(LogContext logContext) {
        if (log4j2Printer == null) {
            synchronized (PrintStyleManager.class) {
                if (log4j2Printer == null) {
                    log4j2Printer = new Log4j2Printer(logContext);
                    log4j2Printer = loadPrinterProxy(logContext, log4j2Printer);
                }
            }
        }
        return log4j2Printer;
    }

    private static Printer getAndroidPrinter(LogContext logContext) {
        if (androidPrinter == null) {
            synchronized (PrintStyleManager.class) {
                if (androidPrinter == null) {
                    androidPrinter = new AndroidPrinter(logContext);
                    androidPrinter = loadPrinterProxy(logContext,
                            androidPrinter);
                }
            }
        }
        return androidPrinter;
    }*/


    private static boolean isUsedEspecialLogApi(String packageName) {
        try {
            Class androidLogcatClass = Class.forName(packageName);
            if (androidLogcatClass != null) {
                return true;
            }
        }
        catch (ClassNotFoundException ignored) {
        }
        return false;
    }

    private static Printer loadPrinterProxy(LogContext logContext, Printer printer) {
        ArrayList<PrinterProxy> printerProxies = new ArrayList<>();

        FilterPrinterProxy filterPrinterProxy = new FilterPrinterProxy(logContext);
        printerProxies.add(filterPrinterProxy);

        RecorderPrinterProxy recorderPrinterProxy = new RecorderPrinterProxy(logContext);
        printerProxies.add(recorderPrinterProxy);

        for (PrinterProxy printerProxy : printerProxies) {
            printer = printerProxy.doProxy(printer);
        }
        return printer;
    }
}