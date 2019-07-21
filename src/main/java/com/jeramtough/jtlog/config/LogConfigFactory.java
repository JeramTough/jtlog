package com.jeramtough.jtlog.config;

import com.jeramtough.jtlog.annotation.LogConfiguration;
import com.jeramtough.jtlog.header.LogHeader;
import com.jeramtough.jtlog.lang.DefaultBoolean;
import com.jeramtough.jtlog.level.LogLevel;

/**
 * Created on 2019-01-11 15:22
 * by @author JeramTough
 */
public class LogConfigFactory {

    public static LogConfig getDefaultValueLogConfig(
            LogConfigDefaultValues logConfigDefaultValues) {
        LogConfig logConfig = new LogConfig();
        logConfig.setMaxLengthOfRow(logConfigDefaultValues.decideMaxLengthOfRow());
        logConfig.setEnabled(logConfigDefaultValues.decideIsEnabled());
        logConfig.setMinVisibleLevel(logConfigDefaultValues.decideMinVisibleLevel());
        logConfig.setUsedJtloggerApi(logConfigDefaultValues.decideIsUsedJtloggerApi());
        logConfig.setLogHeaders(logConfigDefaultValues.decideLogHeaders());
        logConfig.setWrapCount(logConfigDefaultValues.decideWrapCount());
        logConfig.setStackTraceOffset(logConfigDefaultValues.decideStackTraceOffset());
        logConfig.setDateFormat(logConfigDefaultValues.decideDateFormat());
        return logConfig;
    }

    public static LogConfig getLogConfigByAnnotation(
            LogConfigDefaultValues logConfigDefaultValues,
            Class classWithLogConfigerAnnotation) {
        LogConfig logConfig = new LogConfig();
        LogConfiguration logConfiguration = (LogConfiguration) classWithLogConfigerAnnotation.getAnnotation(
                LogConfiguration.class);
        if (logConfiguration != null) {
            //set isEnabled
            if (logConfiguration.isEnabled() == DefaultBoolean.DEFAULT) {
                logConfig.setEnabled(logConfigDefaultValues.decideIsEnabled());
            }
            else if (logConfiguration.isEnabled() == DefaultBoolean.FALSE) {
                logConfig.setEnabled(false);
            }
            else {
                logConfig.setEnabled(true);
            }

            //set isUsedJtloggerApi
            if (logConfiguration.isUsedJtloggerApi() == DefaultBoolean.DEFAULT) {
                logConfig.setUsedJtloggerApi(logConfigDefaultValues.decideIsEnabled());
            }
            else if (logConfiguration.isUsedJtloggerApi() == DefaultBoolean.FALSE) {
                logConfig.setUsedJtloggerApi(false);
            }
            else {
                logConfig.setUsedJtloggerApi(true);
            }

            //set minVisibleLevel
            if (logConfiguration.minVisibleLevel() == LogLevel.DEFAULT) {
                logConfig.setMinVisibleLevel(logConfigDefaultValues.decideMinVisibleLevel());
            }
            else {
                logConfig.setMinVisibleLevel(logConfiguration.minVisibleLevel());
            }

            //set maxLengthOfRow
            if (logConfiguration.maxLengthOfRow() < 0) {
                logConfig.setMaxLengthOfRow(logConfigDefaultValues.decideMaxLengthOfRow());
            }
            else {
                logConfig.setMaxLengthOfRow(logConfiguration.maxLengthOfRow());
            }

            //set logHeaders
            if (logConfiguration.logHeaders().length == 1 && logConfiguration.logHeaders()[0] == LogHeader.DEFAULT) {
                logConfig.setLogHeaders(logConfigDefaultValues.decideLogHeaders());
            }
            else {
                logConfig.setLogHeaders(logConfiguration.logHeaders());
            }

            //set wrapCount
            if (logConfiguration.wrapCount() < 0) {
                logConfig.setWrapCount(logConfigDefaultValues.decideWrapCount());
            }
            else {
                logConfig.setWrapCount(logConfiguration.wrapCount());
            }

            //set stackTraceOffset
            logConfig.setStackTraceOffset(logConfiguration.stackTraceOffset());

            //set dateFormat
            String defaultDateFormat = "default";
            if (defaultDateFormat.equals(logConfiguration.dateFormat())) {
                logConfig.setDateFormat(logConfigDefaultValues.decideDateFormat());
            }
            else {
                logConfig.setDateFormat(logConfiguration.dateFormat());
            }


        }
        else {
            return getDefaultValueLogConfig(logConfigDefaultValues);
        }

        return logConfig;
    }

}
