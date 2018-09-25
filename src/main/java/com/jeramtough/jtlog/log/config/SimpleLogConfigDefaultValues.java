package com.jeramtough.jtlog.log.config;

import com.jeramtough.jtlog.level.LogLevel;

/**
 * Created on 2018-09-25 02:00
 * by @author JeramTough
 */
public class SimpleLogConfigDefaultValues implements LogConfigDefaultValues {
    @Override
    public int loadMaxLengthOfRow() {
        return JtLogConfigDefaultValues.MAX_LENGTH_OF_ROM;
    }

    @Override
    public boolean loadIsEnabled() {
        return JtLogConfigDefaultValues.IS_ENABLED;
    }

    @Override
    public boolean loadIsUsedJtloggerApi() {
        return JtLogConfigDefaultValues.IS_USED_JTLOGGER_API;
    }

    @Override
    public LogLevel loadMinVisibleLevel() {
        return JtLogConfigDefaultValues.MIN_VISIBLE_LEVEL;
    }

    @Override
    public int loadCallerPlus() {
        return JtLogConfigDefaultValues.CALLER_PLUS;
    }
}
