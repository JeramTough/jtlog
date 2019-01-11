package com.jeramtough.jtlog.config;

import com.jeramtough.jtlog.level.LogLevel;

/**
 * Created on 2018-09-25 02:00
 * by @author JeramTough
 */
public class SimpleLogConfigDefaultValues implements LogConfigDefaultValues {
    @Override
    public int decidedMaxLengthOfRow() {
        return JtLogConfigDefaultValues.MAX_LENGTH_OF_ROM;
    }

    @Override
    public boolean decidedIsEnabled() {
        return JtLogConfigDefaultValues.IS_ENABLED;
    }

    @Override
    public boolean decidedIsPrintedTrace() {
        return JtLogConfigDefaultValues.IS_PRINTED_TRACE;
    }

    @Override
    public boolean decidedIsUsedJtloggerApi() {
        return JtLogConfigDefaultValues.IS_USED_JTLOGGER_API;
    }

    @Override
    public LogLevel decidedMinVisibleLevel() {
        return JtLogConfigDefaultValues.MIN_VISIBLE_LEVEL;
    }

    @Override
    public int decidedCallerPlus() {
        return JtLogConfigDefaultValues.CALLER_PLUS;
    }
}
