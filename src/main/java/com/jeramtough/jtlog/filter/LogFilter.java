package com.jeramtough.jtlog.filter;

import com.jeramtough.jtlog.log.LogInformation;

/**
 * 日志过滤器接口
 *
 * @author 11718
 * on 2018  January 13 Saturday 13:13.
 */
public interface LogFilter {

    /**
     * 返回true则输出
     * 
     * @param logInformation 日志信息类
     */
    boolean isPrinted(LogInformation logInformation);
}
