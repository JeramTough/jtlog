package com.jeramtough.jtlog.printer.proxy;

import com.jeramtough.jtlog.jtlogger.JtLogger;
import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.logproxy.BaseJtLoggerProxy;
import com.jeramtough.jtlog.printer.Printer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created on 2018-08-22 21:51
 * by @author JeramTough
 */
public abstract class BasePrinterProxy implements PrinterProxy {

    private LogContext logContext;

    public BasePrinterProxy(LogContext logContext) {
        this.logContext = logContext;
    }

    @Override
    public Printer doProxy(Printer printer) {
        Printer finalPrinter = printer;
        printer = (Printer) Proxy.newProxyInstance(printer.getClass().getClassLoader(),
                new Class[]{Printer.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) {
                        return BasePrinterProxy.this.invoke(logContext, finalPrinter, proxy,
                                method,
                                args);
                    }
                });
        return printer;
    }

    abstract Object invoke(LogContext logContext, Printer printer, Object proxy, Method method,
                           Object[] args);
}
