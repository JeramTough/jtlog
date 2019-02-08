package com.jeramtough.jtlog.bean;

import com.jeramtough.jtlog.level.LogLevel;

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
     * 是否允许输出日志Trace信息.
     */
    private Boolean isPrintedTrace;


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
     * 决定输出位置caller的标记
     * {caller}=(L.java:71)
     */
    private Integer callerPlus;


    public Integer getMaxLengthOfRow() {
        return maxLengthOfRow;
    }

    public void setMaxLengthOfRow(Integer maxLengthOfRow) {
        this.maxLengthOfRow = maxLengthOfRow;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Boolean getPrintedTrace() {
        return isPrintedTrace;
    }

    public void setPrintedTrace(Boolean printedTrace) {
        isPrintedTrace = printedTrace;
    }

    public Boolean getUsedJtloggerApi() {
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

    public Integer getCallerPlus() {
        return callerPlus;
    }

    public void setCallerPlus(Integer callerPlus) {
        this.callerPlus = callerPlus;
    }
}
