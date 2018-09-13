package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.log.LogInformation;

/**
 * 负责输出样式，以什么样的风格输出
 *
 * @author 11718
 */
public interface PrintStyle {
    /**
     * 格式化日志信息成为String
     *
     * @param logInformation 日志信息类
     * @return 风格化后的日志信息
     */
    String stylize(LogInformation logInformation);
}
