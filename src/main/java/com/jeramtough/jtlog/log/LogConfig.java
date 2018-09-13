package com.jeramtough.jtlog.log;

import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.recorder.LogRecorder;

import java.util.ArrayList;

/**
 * 日志框架的设置类
 * <p>
 * Created on 2018-08-21 15:52
 * by @author JeramTough
 */
public class LogConfig {

    /**
     * 日志内容输出，每行最大长度，超过这个长度就会换行
     */
    private int maxLengthOfRow = 130;


    /**
     * 是否允许输出日志
     */
    private boolean isEnabled = true;


    /**
     * 是否使用JtLogger框架的Api,当为Android框架时强制使用Logcat的Api实现输出，
     * false的话会自适应使用Logback或者Log4j2
     * 的api实现输出
     */
    private boolean isUsedJtloggerApi = true;


    /**
     * 最低可见日志等级，默认为DEBUG等级，日志等级优先级就参考
     * {@link LogLevel}
     */
    private LogLevel minVisibleLevel = LogLevel.DEBUG;

    /**
     * 决定输出位置caller的标记
     * {caller}=(L.java:71)
     */
    private int callerPlus = 0;

    /**
     * 日志过滤器集合
     */
    private ArrayList<LogFilter> logFilters;

    /**
     * 日志记录器集合
     */
    private ArrayList<LogRecorder> logRecorders;

    public LogConfig() {
        logFilters = new ArrayList<>();
        logRecorders = new ArrayList<>();
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

    public int getCallerPlus() {
        return callerPlus;
    }

    public void setCallerPlus(int callerPlus) {
        this.callerPlus = callerPlus;
    }

    public void addLogRecord(LogRecorder logRecorder) {
        logRecorders.add(logRecorder);
    }

    public void removeLogRecord(LogRecorder logRecorder) {
        logRecorders.remove(logRecorder);
    }

    public ArrayList<LogRecorder> getLogRecorders() {
        return logRecorders;
    }
}
