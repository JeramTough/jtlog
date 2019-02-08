package com.jeramtough.jtlog.printer.proxy;

import com.jeramtough.jtlog.bean.LogContext;
import com.jeramtough.jtlog.printer.Printer;
import com.jeramtough.jtlog.recorder.LogRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created on 2018-09-07 13:09
 * by @author JeramTough
 */
public class RecorderPrinterProxy extends BasePrinterProxy {

    public RecorderPrinterProxy(LogContext logContext) {
        super(logContext);
    }

    @Override
    Object invoke(LogContext logContext, Printer printer, Object proxy, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        String stylizedText = (String) method.invoke(printer, args);

        for (LogRecorder logRecorder : logContext.getLogConfig().getLogRecorders()) {
            logRecorder.record(getLogInformation(), stylizedText);
        }

        return stylizedText;
    }
}
