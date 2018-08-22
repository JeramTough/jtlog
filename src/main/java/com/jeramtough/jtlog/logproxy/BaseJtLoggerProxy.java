package com.jeramtough.jtlog.logproxy;

import com.jeramtough.jtlog.jtlogger.JtLogger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created on 2018-08-22 20:16
 * by @author JeramTough
 */
public abstract class BaseJtLoggerProxy implements JtLoggerProxy {

    static final String GET_LOG_CONTEXT_METHOD = "getLogContext";

    @Override
    public JtLogger doProxy(JtLogger jtLogger) {
        final JtLogger finalJtLogger = jtLogger;
        jtLogger = (JtLogger) Proxy.newProxyInstance(jtLogger.getClass().getClassLoader(),
                new Class[]{JtLogger.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) {
                        return BaseJtLoggerProxy.this.invoke(finalJtLogger, proxy, method, args);
                    }
                });
        return jtLogger;
    }

    abstract Object invoke(JtLogger jtLogger, Object proxy, Method method,
                           Object[] args);

}
