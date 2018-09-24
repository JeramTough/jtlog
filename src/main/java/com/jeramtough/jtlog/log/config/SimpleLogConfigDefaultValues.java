package com.jeramtough.jtlog.log.config;

import com.jeramtough.jtlog.level.LogLevel;

/**
 * Created on 2018-09-25 02:00
 * by @author JeramTough
 */
public  class SimpleLogConfigDefaultValues implements LogConfigDefaultValues {
    @Override
    public int loadMaxLengthOfRow() {
        return 0;
    }

    @Override
    public boolean loadIsEnabled() {
        return false;
    }

    @Override
    public boolean loadIsUsedJtloggerApi() {
        return false;
    }

    @Override
    public LogLevel loadMinVisibleLevel() {
        return null;
    }

    @Override
    public int loadCallerPlus() {
        return 0;
    }
}
