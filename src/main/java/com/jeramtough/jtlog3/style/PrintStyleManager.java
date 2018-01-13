package com.jeramtough.jtlog3.style;

public class PrintStyleManager
{
	private static volatile InfoPrintStyle infoPrintStyle;
	private static volatile WarnPrintStyle warnPrintStyle;
	private static volatile ErrorPrintStyle errorPrintStyle;
	private static volatile DebugPrintStyle debugPrintStyle;
	private static volatile PrimaryPrintStyle primaryPrintStyle;
	private static volatile ArrivePrintStyle arrivePrintStyle;
	
	public static PrintStyle getPrimaryPrintStyle()
	{
		if (primaryPrintStyle == null)
		{
			synchronized (PrintStyleManager.class)
			{
				if (primaryPrintStyle == null)
				{
					primaryPrintStyle = new PrimaryPrintStyle();
				}
			}
		}
		return primaryPrintStyle;
	}
	
	public static PrintStyle getInfoPrintStyle()
	{
		if (infoPrintStyle == null)
		{
			synchronized (PrintStyleManager.class)
			{
				if (infoPrintStyle == null)
				{
					infoPrintStyle = new InfoPrintStyle();
				}
			}
		}
		return infoPrintStyle;
	}
	
	public static PrintStyle getWarnPrintStyle()
	{
		if (warnPrintStyle == null)
		{
			synchronized (PrintStyleManager.class)
			{
				if (warnPrintStyle == null)
				{
					warnPrintStyle = new WarnPrintStyle();
				}
			}
		}
		return warnPrintStyle;
	}
	
	public static PrintStyle getErrorPrintStyle()
	{
		if (errorPrintStyle == null)
		{
			synchronized (PrintStyleManager.class)
			{
				if (errorPrintStyle == null)
				{
					errorPrintStyle = new ErrorPrintStyle();
				}
			}
		}
		return errorPrintStyle;
	}
	
	public static PrintStyle getDebugPrintStyle()
	{
		if (debugPrintStyle == null)
		{
			synchronized (PrintStyleManager.class)
			{
				if (debugPrintStyle == null)
				{
					debugPrintStyle = new DebugPrintStyle();
				}
			}
		}
		return debugPrintStyle;
	}
	
	public static ArrivePrintStyle getArrivePrintStyle()
	{
		if (arrivePrintStyle == null)
		{
			synchronized (PrintStyleManager.class)
			{
				if (arrivePrintStyle == null)
				{
					arrivePrintStyle = new ArrivePrintStyle();
				}
			}
		}
		return arrivePrintStyle;
	}
}
