package com.jeramtough.jtlog.log;

/**
 * Created on 2018-08-21 15:52
 * by @author JeramTough
 */
public class LogConfig {
    private int maxLengthOfRow = 130;
    private boolean isBridgedLogback = false;
    private boolean isBridgedLog4j2 = false;
    private boolean isBridgedLogcat = true;

    public int getMaxLengthOfRow() {
        return maxLengthOfRow;
    }

    public void setMaxLengthOfRow(int maxLengthOfRow) {
        this.maxLengthOfRow = maxLengthOfRow;
    }

    public boolean isBridgedLogback() {
        return isBridgedLogback;
    }

    public void setBridgedLogback(boolean bridgedLogback) {
        isBridgedLogback = bridgedLogback;
    }

    public boolean isBridgedLog4j2() {
        return isBridgedLog4j2;
    }

    public void setBridgedLog4j2(boolean bridgedLog4j2) {
        isBridgedLog4j2 = bridgedLog4j2;
    }

    public boolean isBridgedLogcat() {
        return isBridgedLogcat;
    }

    public void setBridgedLogcat(boolean bridgedLogcat) {
        isBridgedLogcat = bridgedLogcat;
    }
}
