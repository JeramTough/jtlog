package com.jeramtough.jtlog.log;

import com.jeramtough.jtlog.filter.LogFilter;

/**
 * Created on 2018-08-21 15:52
 * by @author JeramTough
 */
public class LogConfig {
    private int maxLengthOfRow = 130;
    private boolean isEnabled = true;
    private boolean isUsedJtloggerApi = false;
    private LogFilter[] logFilters;

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

    public LogFilter[] getLogFilters() {
        return logFilters;
    }

    public void setLogFilters(LogFilter[] logFilters) {
        this.logFilters = logFilters;
    }
}
