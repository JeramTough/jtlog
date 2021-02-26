package com.jeramtough.jtlog.config;

import com.jeramtough.jtlog.header.LogHeader;
import com.jeramtough.jtlog.level.LogLevel;

import java.util.Arrays;

/**
 * 每个JtLogger类的配置类
 * <p>
 * Created on 2019-01-11 09:53
 * by @author JeramTough
 */
public class LogConfig {

    /**
     * 日志内容输出，每行最大长度，超过这个长度就会换行
     */
    private Integer maxLengthOfRow;


    /**
     * 是否允许输出日志
     */
    private Boolean isEnabled;


    /**
     * 是否使用JtLogger框架的Api,当为Android框架时强制使用Logcat的Api实现输出，
     * false的话会自适应使用Logback或者Log4j2
     * 的api实现输出
     */
    private Boolean isUsedJtloggerApi;


    /**
     * 最低可见日志等级，默认为DEBUG等级，日志等级优先级就参考
     * {@link LogLevel}
     */
    private LogLevel minVisibleLevel;


    /**
     * 决定要输出的日志信息头及顺序，有则输出，没有则不输出，
     * 日志信息头见{@link LogLevel}
     */
    private LogHeader[] logHeaders;


    /**
     * 每条新日志间的空行数, 0则两条日志间无空行
     */
    private int wrapCount;

    /**
     * Trace信息的回溯下标偏移量，默认是0
     */
    private int stackTraceOffset;

    /**
     * 日志时间信息头的输出格式
     */
    private String dateFormat;


    public Integer getMaxLengthOfRow() {
        return maxLengthOfRow;
    }

    public void setMaxLengthOfRow(Integer maxLengthOfRow) {
        this.maxLengthOfRow = maxLengthOfRow;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public Boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Boolean getUsedJtloggerApi() {
        return isUsedJtloggerApi;
    }

    public Boolean isUsedJtloggerApi() {
        return isUsedJtloggerApi;
    }

    public void setUsedJtloggerApi(Boolean usedJtloggerApi) {
        isUsedJtloggerApi = usedJtloggerApi;
    }

    public LogLevel getMinVisibleLevel() {
        return minVisibleLevel;
    }

    public void setMinVisibleLevel(LogLevel minVisibleLevel) {
        this.minVisibleLevel = minVisibleLevel;
    }

    public LogHeader[] getLogHeaders() {
        return logHeaders;
    }

    public void setLogHeaders(LogHeader[] logHeaders) {
        this.logHeaders = logHeaders;
    }

    public int getWrapCount() {
        return wrapCount;
    }

    public void setWrapCount(int wrapCount) {
        this.wrapCount = wrapCount;
    }

    public int getStackTraceOffset() {
        return stackTraceOffset;
    }

    public void setStackTraceOffset(int stackTraceOffset) {
        this.stackTraceOffset = stackTraceOffset;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LogConfig logConfig = (LogConfig) o;

        if (wrapCount != logConfig.wrapCount) {
            return false;
        }
        if (stackTraceOffset != logConfig.stackTraceOffset) {
            return false;
        }
        if (!maxLengthOfRow.equals(logConfig.maxLengthOfRow)) {
            return false;
        }
        if (!isEnabled.equals(logConfig.isEnabled)) {
            return false;
        }
        if (!isUsedJtloggerApi.equals(logConfig.isUsedJtloggerApi)) {
            return false;
        }
        if (minVisibleLevel != logConfig.minVisibleLevel) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(logHeaders, logConfig.logHeaders)) {
            return false;
        }
        return dateFormat.equals(logConfig.dateFormat);
    }

    @Override
    public int hashCode() {
        int result = maxLengthOfRow.hashCode();
        result = 31 * result + isEnabled.hashCode();
        result = 31 * result + isUsedJtloggerApi.hashCode();
        result = 31 * result + minVisibleLevel.hashCode();
        result = 31 * result + Arrays.hashCode(logHeaders);
        result = 31 * result + wrapCount;
        result = 31 * result + stackTraceOffset;
        result = 31 * result + dateFormat.hashCode();
        return result;
    }
}
