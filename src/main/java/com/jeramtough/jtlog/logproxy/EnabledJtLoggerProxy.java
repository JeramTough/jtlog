package com.jeramtough.jtlog.logproxy;

import com.jeramtough.jtlog.jtlogger.JtLogger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created on 2018-08-22 20:05
 * by @author JeramTough
 */
public class EnabledJtLoggerProxy extends BaseJtLoggerProxy {

    private static final String GET_LOG_CONTEXT_METHOD = "getLogContext";

    @Override
    public Object invoke(JtLogger jtLogger,Object proxy, Method method, Object[] args) {
        try {
            if (method.getName().equals(GET_LOG_CONTEXT_METHOD)) {
                return method.invoke(jtLogger, args);
            } else {
                if (jtLogger.getLogContext().getLogConfig().isEnabled()) {
                    method.invoke(jtLogger, args);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
