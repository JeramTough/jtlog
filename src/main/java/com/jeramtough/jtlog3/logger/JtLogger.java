package com.jeramtough.jtlog3.logger;

import com.jeramtough.jtlog3.JtLogConfig;
import com.jeramtough.jtlog3.LogInformation;
import com.jeramtough.jtlog3.filter.JtLogFilter;
import com.jeramtough.jtlog3.printer.JtPrinter;
import com.jeramtough.jtlog3.style.PrintStyle;

import java.util.ArrayList;

/**
 * 这个类负责组装各个组件，最终完成输出需求
 *
 * @author 11718
 */
public class JtLogger
{
	private PrintStyle printStyle;
	private JtPrinter jtPrinter;
	
	private static volatile JtLogger jtLogger;
	
	private JtLogger()
	{
	}
	
	public static JtLogger getJtLogger()
	{
		if (jtLogger == null)
		{
			synchronized (JtLogger.class)
			{
				if (jtLogger == null)
				{
					jtLogger = new JtLogger();
				}
			}
		}
		return jtLogger;
	}
	
	public synchronized void log(LogInformation logInformation)
	{
		ArrayList<JtLogFilter> jtLogFilters = JtLogConfig.getJtLogConfig().getJtLogFilters();
		if (jtLogFilters.size() > 0)
		{
			for (JtLogFilter jtLogFilter : jtLogFilters)
			{
				boolean canPrint = jtLogFilter.canPrint(logInformation);
				if (canPrint)
				{
					jtPrinter.print(logInformation.getJtLogLevel(),
							printStyle.stylize(logInformation));
				}
			}
		}
		else
		{
			jtPrinter.print(logInformation.getJtLogLevel(),
					printStyle.stylize(logInformation));
		}
		
	}
	
	public synchronized void setPrintStyle(PrintStyle printStyle)
	{
		this.printStyle = printStyle;
	}
	
	public synchronized void setJtPrinter(JtPrinter jtPrinter)
	{
		this.jtPrinter = jtPrinter;
	}
}
