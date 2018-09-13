package com.jeramtough.jtlog.printer.proxy;

import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.printer.Printer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * this class is deprecated, see {@link com.jeramtough.jtlog.filter.EnableLogFilter}
 *
 * Created on 2018-08-22 21:55
 * by @author JeramTough
 */
@Deprecated
public class EnabledPrinterProxy extends BasePrinterProxy {
    public EnabledPrinterProxy(LogContext logContext) {
        super(logContext);
    }

    @Override
    Object invoke(LogContext logContext, Printer printer, Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        if (logContext.getLogConfig().isEnabled()) {
            return method.invoke(printer, args);
        }
        return null;
    }
}
