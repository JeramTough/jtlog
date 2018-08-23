package com.jeramtough.jtlog.with;

import com.jeramtough.jtlog.jtlogger.JtLogger;
import com.jeramtough.jtlog.jtlogger.JtLoggerManager;

/**
 * @author 11718
 */
public interface WithJtLogger {

    default JtLogger getJtLogger() {
        JtLogger jtLogger = JtLoggerManager.getJtLogger(this.getClass());
        return jtLogger;
    }

}
