package com.jeramtough.jtlog.config;

import com.jeramtough.jtlog.level.LogLevel;

/**
 * Created on 2018-09-25 01:15
 * by @author JeramTough
 */
public class JtLogConfigDefaultValues implements LogConfigDefaultValues {

    public static final int MAX_LENGTH_OF_ROM = 0;

    public static final boolean IS_ENABLED = true;

    public static final boolean IS_PRINTED_TRACE = true;

    public static final boolean IS_USED_JTLOGGER_API = true;

    public static final LogLevel MIN_VISIBLE_LEVEL = LogLevel.DEBUG;

    public static final int CALLER_PLUS = 0;

    @Override
    public int decidedMaxLengthOfRow() {
        return MAX_LENGTH_OF_ROM;
    }

    @Override
    public boolean decidedIsEnabled() {
        return IS_ENABLED;
    }

    @Override
    public boolean decidedIsPrintedTrace() {
        return IS_PRINTED_TRACE;
    }

    @Override
    public boolean decidedIsUsedJtloggerApi() {
        return IS_USED_JTLOGGER_API;
    }

    @Override
    public LogLevel decidedMinVisibleLevel() {
        return MIN_VISIBLE_LEVEL;
    }

    @Override
    public int decidedCallerPlus() {
        return CALLER_PLUS;
    }
}
