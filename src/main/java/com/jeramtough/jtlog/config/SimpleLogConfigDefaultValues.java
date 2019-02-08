package com.jeramtough.jtlog.config;

import com.jeramtough.jtlog.header.LogHeader;
import com.jeramtough.jtlog.level.LogLevel;

/**
 * Created on 2018-09-25 01:15
 * by @author JeramTough
 */
public class SimpleLogConfigDefaultValues implements LogConfigDefaultValues {

    public static final int MAX_LENGTH_OF_ROM = 0;

    public static final boolean IS_ENABLED = true;

    public static final boolean IS_USED_JTLOGGER_API = true;

    public static final LogLevel MIN_VISIBLE_LEVEL = LogLevel.VERBOSE;

    public static final int WRAP_COUNT = 1;

    public static final LogHeader[] LOG_HEADERS = new LogHeader[]{LogHeader.CONTEXT,
            LogHeader.TAG, LogHeader.TIME, LogHeader.THREAD, LogHeader.TRACE};

    @Override
    public int loadMaxLengthOfRow() {
        return MAX_LENGTH_OF_ROM;
    }

    @Override
    public boolean loadIsEnabled() {
        return IS_ENABLED;
    }

    @Override
    public boolean loadIsUsedJtloggerApi() {
        return IS_USED_JTLOGGER_API;
    }

    @Override
    public LogLevel loadMinVisibleLevel() {
        return MIN_VISIBLE_LEVEL;
    }

    @Override
    public LogHeader[] loadLogHeaders() {
        return LOG_HEADERS;
    }

    @Override
    public int loadWrapCount() {
        return WRAP_COUNT;
    }


}
