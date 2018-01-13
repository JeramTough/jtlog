package com.jeramtough.jtlog3.printer;

import com.jeramtough.jtlog3.level.JtLogLevel;

public class JavaJtPrinterImpl implements JtPrinter
{
	@Override
	public synchronized void print(JtLogLevel jtLogLevel, String logText)
	{
		System.out.println(logText);
	}
}
