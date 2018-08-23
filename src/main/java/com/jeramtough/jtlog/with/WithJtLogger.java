package com.jeramtough.jtlog.with;

import com.jeramtough.jtlog.jtlogger.JtLogger;
import com.jeramtough.jtlog.jtlogger.JtLoggerManager;

/**
 * 附加日志接口，默认contextName为当前类名
 * @author 11718
 */
public interface WithJtLogger {

    default JtLogger getJtLogger() {
        JtLogger jtLogger = JtLoggerManager.getJtLogger(this.getClass());
        return jtLogger;
    }

}
