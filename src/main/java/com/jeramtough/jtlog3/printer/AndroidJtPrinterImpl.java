package com.jeramtough.jtlog3.printer;

import com.jeramtough.jtlog3.level.JtLogLevel;

public class AndroidJtPrinterImpl implements JtPrinter
{
	private final String logcatTag = "jtlog3";
	
	@Override
	public void print(JtLogLevel jtLogLevel, String logText)
	{
		if (jtLogLevel == JtLogLevel.INFO)
		{
			android.util.Log.i(logcatTag, logText);
		}
		else if (jtLogLevel == JtLogLevel.WARN)
		{
			android.util.Log.w(logcatTag, logText);
		}
		else if (jtLogLevel == JtLogLevel.ERROR)
		{
			android.util.Log.e(logcatTag, logText);
		}
		else if (jtLogLevel == JtLogLevel.PRIMARY)
		{
			android.util.Log.v(logcatTag, logText);
		}
		else if (jtLogLevel == JtLogLevel.DEBUG)
		{
			android.util.Log.d(logcatTag, logText);
		}
		else if (jtLogLevel == JtLogLevel.ARRIVE)
		{
			android.util.Log.d(logcatTag, logText);
		}
	}
}
