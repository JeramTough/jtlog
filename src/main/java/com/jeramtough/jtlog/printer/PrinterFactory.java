package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.config.LogConfig;
import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.map.MaxSizeHashMap;
import com.jeramtough.jtlog.printer.proxy.FilterPrinterProxy;
import com.jeramtough.jtlog.printer.proxy.PrinterProxy;
import com.jeramtough.jtlog.printer.proxy.RecorderPrinterProxy;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018-08-21 17:31
 * by @author JeramTough
 */
public class PrinterFactory {

    private static final int MAX_CAPACITY_SIZE = 20;

    /**
     * config有时候会变更，所以使用config作为key
     */
    private static final Map<LogConfig, Printer> printerMap;

    static {
        printerMap = new MaxSizeHashMap<>(MAX_CAPACITY_SIZE);
    }

    private PrinterFactory() {
    }

    public static Printer getPrinter(LogContext logContext) {
        Printer printer;
        if (printerMap.containsKey(logContext.getLogConfig())) {
            printer = printerMap.get(logContext.getLogConfig());
        }
        else {

            if (logContext.getLogConfig().isUsedJtloggerApi()) {

                boolean hasTestFrame = false;

                if (isUsedEspecialLogApi(
                        BasePrinter.TEST_PACKAGE_NAME_1) || isUsedEspecialLogApi(
                        BasePrinter.TEST_PACKAGE_NAME_2)) {
                    hasTestFrame = true;
                }

                if (isUsedEspecialLogApi(AndroidPrinter.LOGCAT_PACKAGE_NAME) &&
                        !isUsedEspecialLogApi(LogbackPrinter.LOGBACK_FACTORY_PACKAGE_NAME)
                        && !hasTestFrame) {
                    printer = new AndroidPrinter();
                }
                else {
                    printer = new JtPrinter();
                }
            }
            else if (isUsedEspecialLogApi(LogbackPrinter.LOGBACK_FACTORY_PACKAGE_NAME)
                    && isExistedLogbackConfigFileInClasspath()) {
                printer = new LogbackPrinter();
            }
            else if (isUsedEspecialLogApi(Log4j2Printer.LOG4J2_LOG_MANAGER_PACKAGE_NAME)
                    && isExistedLog4j2ConfigFileInClasspath()) {
                printer = new Log4j2Printer();
            }
            else {
                printer = new JtPrinter();
            }

            printer = loadPrinterProxy(logContext, printer);
            printerMap.put(logContext.getLogConfig(), printer);
        }

        return printer;
    }

    //***********************

    private static boolean isUsedEspecialLogApi(String packageName) {
        try {
            Class especialLogClass = Class.forName(packageName);
            return true;
        }
        catch (ClassNotFoundException ignored) {
        }
        return false;
    }

    /**
     * 是否有Logback存在配置文件在classpath路径下
     */
    private static boolean isExistedLogbackConfigFileInClasspath() {
        Map<String, Boolean> isExistMap = new HashMap<>();
        isExistMap.put("log4j.properties", null);
        isExistMap.put("logback.xml", null);

        for (String key : isExistMap.keySet()) {
            URL url = PrinterFactory.class.getClassLoader().getResource(key);
            isExistMap.put(key, (url != null));
        }

        //只要有一个存在，那就是存在的
        boolean isExisted = false;
        for (Boolean value : isExistMap.values()) {
            if (value) {
                isExisted = true;
                break;
            }
        }

        return isExisted;
    }

    /**
     * 是否有Log4j2存在配置文件在classpath路径下
     */
    private static boolean isExistedLog4j2ConfigFileInClasspath() {
        Map<String, Boolean> isExistMap = new HashMap<>();
        isExistMap.put("log4j2-test.properties", null);
        isExistMap.put("log4j2-test.yaml", null);
        isExistMap.put("log4j2-test.yml", null);
        isExistMap.put("log4j2-test.json", null);
        isExistMap.put("log4j2-test.jsn", null);
        isExistMap.put("log4j2-test.xm", null);
        isExistMap.put("log4j2.properties", null);
        isExistMap.put("log4j2.yaml", null);
        isExistMap.put("log4j2.yml", null);
        isExistMap.put("log4j2.json", null);
        isExistMap.put("log4j2.jsn", null);
        isExistMap.put("log4j2.xml", null);

        for (String key : isExistMap.keySet()) {
            URL url = PrinterFactory.class.getClassLoader().getResource(key);
            isExistMap.put(key, (url != null));
        }

        //只要有一个存在，那就是存在的
        boolean isExisted = false;
        for (Boolean value : isExistMap.values()) {
            if (value) {
                isExisted = true;
                break;
            }
        }

        return isExisted;
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