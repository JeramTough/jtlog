package com.jeramtough.jtlog3.printer;

import com.jeramtough.jtlog3.JtLogConfig;
import com.jeramtough.jtlog3.style.PrintStyleManager;

public class JtPrinterManager
{
	private static final String androidLogcatPackageName = "android.util.Log";
	
	private static volatile JtPrinter jtPrinter;
	
	private JtPrinterManager()
	{
	}
	
	public static JtPrinter getJtPrinter()
	{
		if (jtPrinter == null)
		{
			synchronized (PrintStyleManager.class)
			{
				if (jtPrinter == null)
				{
					try
					{
						if (JtLogConfig.getJtLogConfig().isUsedJavaPrinter())
						{
							jtPrinter = new JavaJtPrinterImpl();
						}
						else
						{
							Class androidLogcatClass = Class.forName(androidLogcatPackageName);
							jtPrinter = new AndroidJtPrinterImpl();
						}
					}
					catch (ClassNotFoundException e)
					{
						jtPrinter = new JavaJtPrinterImpl();
					}
				}
			}
		}
		return jtPrinter;
	}
	
}
