package com.jeramtough.jtlog.printer.proxy;

import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.bean.LogContext;
import com.jeramtough.jtlog.printer.Printer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * this class is deprecated, see {@link com.jeramtough.jtlog.filter.MinLevelLogFilter}
 * <p>
 * Created on 2018-08-22 22:35
 * by @author JeramTough
 */
@Deprecated
public class LevelPrinterProxy extends BasePrinterProxy {
    public LevelPrinterProxy(LogContext logContext) {
        super(logContext);
    }

    @Override
    Object invoke(LogContext logContext, Printer printer, Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        int result = LogLevel.compare(logContext.getLogConfig().getMinVisibleLevel(),
                getLogInformation().getLogLevel());
        if (result <= 0) {
            return method.invoke(printer, args);
        }
        return null;
    }
}
