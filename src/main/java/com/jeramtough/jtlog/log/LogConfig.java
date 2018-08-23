package com.jeramtough.jtlog.log;

import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.level.LogLevel;

import java.util.ArrayList;

/**
 * Created on 2018-08-21 15:52
 * by @author JeramTough
 */
public class LogConfig {
    private int maxLengthOfRow = 130;
    private boolean isEnabled = true;
    private boolean isUsedJtloggerApi = false;
    private ArrayList<LogFilter> logFilters;
    private LogLevel minVisibleLevel = LogLevel.DEBUG;

    public LogConfig() {
        logFilters = new ArrayList<>();
    }

    public int getMaxLengthOfRow() {
        return maxLengthOfRow;
    }

    public void setMaxLengthOfRow(int maxLengthOfRow) {
        this.maxLengthOfRow = maxLengthOfRow;
    }

    public boolean isUsedJtloggerApi() {
        return isUsedJtloggerApi;
    }

    public void setUsedJtloggerApi(boolean usedJtloggerApi) {
        isUsedJtloggerApi = usedJtloggerApi;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }


    public void addLogFilter(LogFilter logFilter) {
        logFilters.add(logFilter);
    }

    public void removeLogFilter(LogFilter logFilter) {
        logFilters.remove(logFilter);
    }

    public ArrayList<LogFilter> getLogFilters() {
        return logFilters;
    }

    public LogLevel getMinVisibleLevel() {
        return minVisibleLevel;
    }

    public void setMinVisibleLevel(LogLevel minVisibleLevel) {
        this.minVisibleLevel = minVisibleLevel;
    }
}
