package com.jeramtough.jtlog3.style;

import com.jeramtough.jtlog3.LogInformation;
import com.jeramtough.jtlog3.level.JtLogLevel;

/**
 * 负责输出样式，以什么样的风格输出
 * @author 11718
 */
public interface PrintStyle
{
	String stylize(LogInformation logInformation);
}
