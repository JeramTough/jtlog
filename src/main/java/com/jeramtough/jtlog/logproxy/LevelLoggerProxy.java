package com.jeramtough.jtlog.logproxy;

import com.jeramtough.jtlog.jtlogger.JtLogger;
import com.jeramtough.jtlog.level.LogLevel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created on 2018-08-22 21:33
 * by @author JeramTough
 */
public class LevelLoggerProxy extends BaseJtLoggerProxy {
    @Override
    Object invoke(JtLogger jtLogger, Object proxy, Method method, Object[] args) {
        try {
            if (method.getName().equals(GET_LOG_CONTEXT_METHOD)) {
                return method.invoke(jtLogger, args);
            } else {
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
