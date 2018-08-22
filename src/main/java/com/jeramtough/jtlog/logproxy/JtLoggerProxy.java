package com.jeramtough.jtlog.logproxy;

import com.jeramtough.jtlog.jtlogger.JtLogger;

/**
 * Created on 2018-08-22 20:06
 * by @author JeramTough
 */
public interface JtLoggerProxy {

    JtLogger doProxy(JtLogger jtLogger);
}
