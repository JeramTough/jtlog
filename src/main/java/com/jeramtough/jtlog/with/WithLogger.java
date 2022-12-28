package com.jeramtough.jtlog.with;

import com.jeramtough.jtlog.jtlogger.Logger;
import com.jeramtough.jtlog.jtlogger.LoggerManager;

/**
 * 附加日志接口，默认contextName为当前类名
 *
 * @author 11718
 */
public interface WithLogger {

    default Logger getLogger() {
        Logger logger = LoggerManager.getLogger(this.getClass());
        return logger;
    }

    default Logger getLogger(Object o) {
        Logger logger = LoggerManager.getLogger(o.getClass());
        return logger;
    }

}
