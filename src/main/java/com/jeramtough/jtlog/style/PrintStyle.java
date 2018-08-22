package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.log.LogInformation;

/**
 * 负责输出样式，以什么样的风格输出
 *
 * @author 11718
 */
public interface PrintStyle {
    String stylize(LogInformation logInformation);
}
