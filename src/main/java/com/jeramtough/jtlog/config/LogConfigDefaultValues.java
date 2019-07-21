package com.jeramtough.jtlog.config;

import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.header.LogHeader;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.recorder.LogRecorder;

import java.util.List;

/**
 * 该接口决定日志配置默认值，系统默认的实现类为{@link SimpleLogConfigDefaultValues}。
 * <p>
 * 实现该接口并调用静态方法LoggerManager.setLogConfigDefaultValues(LogConfigDefaultValues logConfigDefaultValues)
 * , 就可以覆盖系统默认配置值。
 * <p>
 * by @author JeramTough
 */
public interface LogConfigDefaultValues {

    /**
     * 日志内容输出，每行最大长度，超过这个长度就会换行
     *
     * @return 加载默认值
     */
    int decideMaxLengthOfRow();

    /**
     * 是否允许输出日志
     *
     * @return 加载默认值
     */
    boolean decideIsEnabled();

    /**
     * 是否使用JtLogger框架的Api,当为Android框架时强制使用Logcat的Api实现输出，
     * false的话会自适应使用Logback或者Log4j2
     * 的api实现输出
     *
     * @return 加载默认值
     */
    boolean decideIsUsedJtloggerApi();


    /**
     * 最低可见日志等级，默认为DEBUG等级，日志等级优先级就参考
     * {@link LogLevel}
     *
     * @return 加载默认值
     */
    LogLevel decideMinVisibleLevel();

    /**
     * 决定要输出的日志信息头及顺序，有则输出，没有则不输出，
     * 日志信息头见{@link LogLevel}
     */
    LogHeader[] decideLogHeaders();

    /**
     * 每条新日志间的空行数, 0则两条日志间无空行
     */
    int decideWrapCount();


    /**
     * Trace信息的回溯下标偏移量,默认是0
     *
     * @return 返回0则代表默认，以调用api的stack输出为准
     */
    int decideStackTraceOffset();

    /**
     * 决定日志时间信息头的输出格式
     */
    String decideDateFormat();

    /**
     * 添加全局的日志过滤器
     */
    void decideGlobalLogFilters(List<LogFilter> logFilters);

    /**
     * 添加全局的日志过滤器
     */
    void decideGlobalLogRecoders(List<LogRecorder> logRecorders);
}
