package com.jeramtough.jtlog.factory;

import com.jeramtough.jtlog.bean.LogConfig;
import com.jeramtough.jtlog.constant.LogConfigDefaultValues;

/**
 * Created on 2019-01-11 15:22
 * by @author JeramTough
 */
public class LogConfigFactory {

    public static LogConfig getDefaultValueLogConfig() {
        LogConfig logConfig = new LogConfig();
        logConfig.setMaxLengthOfRow(LogConfigDefaultValues.MAX_LENGTH_OF_ROM);
        logConfig.setCallerPlus(LogConfigDefaultValues.CALLER_PLUS);
        logConfig.setEnabled(LogConfigDefaultValues.IS_ENABLED);
        logConfig.setMinVisibleLevel(LogConfigDefaultValues.MIN_VISIBLE_LEVEL);
        logConfig.setPrintedTrace(LogConfigDefaultValues.IS_PRINTED_TRACE);
        logConfig.setUsedJtloggerApi(LogConfigDefaultValues.IS_USED_JTLOGGER_API);
        return logConfig;
    }

}
