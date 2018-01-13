package com.jeramtough.jtlog3.printer;

import com.jeramtough.jtlog3.level.JtLogLevel;

/**
 * 这个接口负责输出方式，比如java输出，android输出
 *
 * @author 11718
 */
public interface JtPrinter
{
	void print(JtLogLevel jtLogLevel, String logText);
	
	
}
