package com.jeramtough.jtlog.config;

import com.jeramtough.jtlog.facade.L;
import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.header.LogHeader;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.recorder.LogRecorder;

import java.io.File;
import java.util.List;

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

    public static final int STACK_TRACE_OFFSET = 0;

    public static final String DATE_FORMAT = "MM/dd HH:mm:ss:SSS";

    public static final File COVER_FILE = null;

    public static final LogHeader[] LOG_HEADERS = new LogHeader[]{LogHeader.TIME, LogHeader.CONTEXT,
            LogHeader.TAG, LogHeader.THREAD, LogHeader.TRACE};

    @Override
    public int decideMaxLengthOfRow() {
        return MAX_LENGTH_OF_ROM;
    }

    @Override
    public boolean decideIsEnabled() {
        return IS_ENABLED;
    }

    @Override
    public boolean decideIsUsedJtloggerApi() {
        return IS_USED_JTLOGGER_API;
    }

    @Override
    public LogLevel decideMinVisibleLevel() {
        return MIN_VISIBLE_LEVEL;
    }

    @Override
    public LogHeader[] decideLogHeaders() {
        return LOG_HEADERS;
    }

    @Override
    public int decideWrapCount() {
        return WRAP_COUNT;
    }

    @Override
    public int decideStackTraceOffset() {
        return STACK_TRACE_OFFSET;
    }

    @Override
    public String decideDateFormat() {
        return DATE_FORMAT;
    }

    @Override
    public void additionGlobalLogFilters(List<LogFilter> logFilters) {
        //添加全局过滤器
    }

    @Override
    public void additionGlobalLogRecorders(List<LogRecorder> logRecorders) {
        //添加全局记录器
    }

    @Override
    public File decideCoverConfigFile() {
        return COVER_FILE;
    }


}
