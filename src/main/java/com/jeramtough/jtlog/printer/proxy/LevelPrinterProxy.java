package com.jeramtough.jtlog.printer.proxy;

import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.printer.Printer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created on 2018-08-22 22:35
 * by @author JeramTough
 */
public class LevelPrinterProxy extends BasePrinterProxy {
    public LevelPrinterProxy(LogContext logContext) {
        super(logContext);
    }

    @Override
    Object invoke(LogContext logContext, Printer printer, Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        int result = LogLevel.compare(logContext.getLogConfig().getMinVisibleLevel(),
                getLogInformation().getLogLevel());
        if (result <= 0) {
            method.invoke(printer, args);
        }
        return null;
    }
}
