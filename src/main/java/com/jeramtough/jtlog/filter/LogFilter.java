package com.jeramtough.jtlog.filter;

import com.jeramtough.jtlog.bean.LogContext;
import com.jeramtough.jtlog.bean.LogInformation;

/**
 * 日志过滤器接口
 *
 * @author 11718
 * on 2018  January 13 Saturday 13:13.
 */
public interface LogFilter {

    /**
     * 决定日志是否输出
     *
     * @param logContext     Logger的环境类
     * @param logInformation 日志信息类
     * @return 返回true则输出
     */
    boolean isPrinted(LogContext logContext, LogInformation logInformation);
}
