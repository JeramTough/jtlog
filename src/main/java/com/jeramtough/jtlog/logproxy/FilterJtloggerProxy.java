package com.jeramtough.jtlog.logproxy;

import com.jeramtough.jtlog.jtlogger.JtLogger;
import com.jeramtough.jtlog.log.LogContext;

/**
 * Created on 2018-08-22 19:28
 * by @author JeramTough
 */
public class FilterJtloggerProxy {

    private LogContext logContext;

    public FilterJtloggerProxy(LogContext logContext) {
        this.logContext = logContext;
    }

    public void doFilterProxy(JtLogger jtLogger) {

    }

}
