package com.jeramtough.jtlog.printer.proxy;

import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.printer.Printer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.*;

/**
 * Created on 2018-08-22 21:51
 * by @author JeramTough
 */
public abstract class BasePrinterProxy implements PrinterProxy {

    private LogContext logContext;
    private LogInformation logInformation;

    private final ThreadPoolExecutor threadPoolExecutor;

    public BasePrinterProxy(LogContext logContext) {
        this.logContext = logContext;
        threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), (ThreadFactory) Thread::new);
    }

    @Override
    public Printer doProxy(Printer printer) {
        Printer finalPrinter = printer;
        printer = (Printer) Proxy.newProxyInstance(printer.getClass().getClassLoader(),
                new Class[]{Printer.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) {

                        for (Object arg : args) {
                            if (arg instanceof LogInformation) {
                                logInformation = (LogInformation) arg;
                                break;
                            }
                        }

                        try {
                            return BasePrinterProxy.this.invoke(logContext, finalPrinter,
                                    proxy,
                                    method,
                                    args);
                        }
                        catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
        return printer;
    }

    abstract Object invoke(LogContext logContext, Printer printer, Object proxy, Method method,
                           Object[] args) throws IllegalAccessException,
            InvocationTargetException;

    public LogInformation getLogInformation() {
        return logInformation;
    }

    public ThreadPoolExecutor getThreadPool() {
        return threadPoolExecutor;
    }
}
