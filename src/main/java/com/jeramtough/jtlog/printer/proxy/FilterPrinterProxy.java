package com.jeramtough.jtlog.printer.proxy;

import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.bean.LogContext;
import com.jeramtough.jtlog.printer.Printer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created on 2018-08-23 10:10
 * by @author JeramTough
 * 
 */
public class FilterPrinterProxy extends BasePrinterProxy {
    public FilterPrinterProxy(LogContext logContext) {
        super(logContext);
    }

    @Override
    Object invoke(LogContext logContext, Printer printer, Object proxy, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        boolean isPrinted = true;
        for (LogFilter logFilter : logContext.getLogConfig().getLogFilters()) {
            if (!logFilter.isPrinted(logContext, getLogInformation())) {
                isPrinted = false;
                break;
            }
        }

        if (isPrinted) {
            return method.invoke(printer, args);
        }
        return null;
    }
}
