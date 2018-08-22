package com.jeramtough.jtlog;

import com.jeramtough.jtlog.jtlogger.JtLogger;

public interface WithLogger {


    default JtLogger getJtLogger() {
        return null;
    }


}
