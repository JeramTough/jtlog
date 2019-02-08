package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.context.LogContext;
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

    private static HashMap<LogContext, Printer> printerHashMap;

    static {
        printerHashMap = new HashMap<>();
    }

    private PrinterFactory() {
    }

    public static Printer getPrinter(LogContext logContext) {
        Printer printer;
        if (printerHashMap.containsKey(logContext)) {
            printer = printerHashMap.get(logContext);
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
            printerHashMap.put(logContext, printer);
        }

        return printer;
    }

    //***********************

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