package com.jeramtough.jtlog3;

import com.jeramtough.jtlog3.level.JtLogLevel;
import com.jeramtough.jtlog3.logger.JtLogger;
import com.jeramtough.jtlog3.printer.JtPrinter;
import com.jeramtough.jtlog3.printer.JtPrinterManager;
import com.jeramtough.jtlog3.style.PrintStyle;
import com.jeramtough.jtlog3.style.PrintStyleManager;
import com.jeramtough.jtlog3.util.MyStringUtil;

public class P
{
	
	static
	{
	}
	
	public static void p(String message)
	{
		LogInformation logInformation = new LogInformation(message);
		logInformation.setJtLogLevel(JtLogLevel.PRIMARY);
		
		PrintStyle printStyle = PrintStyleManager.getPrimaryPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	public static <T extends Number> void p(T message)
	{
		LogInformation logInformation = new LogInformation(message + "");
		logInformation.setJtLogLevel(JtLogLevel.PRIMARY);
		
		PrintStyle printStyle = PrintStyleManager.getPrimaryPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	public static void p(Object message)
	{
		LogInformation logInformation = new LogInformation(MyStringUtil.ObjectsToString(message));
		logInformation.setJtLogLevel(JtLogLevel.PRIMARY);
		
		PrintStyle printStyle = PrintStyleManager.getPrimaryPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	
	public static void info(String tag, String message)
	{
		LogInformation logInformation = new LogInformation(message);
		logInformation.setTag(tag);
		logInformation.setJtLogLevel(JtLogLevel.INFO);
		
		PrintStyle printStyle = PrintStyleManager.getInfoPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	public static <T extends Number> void info(String tag, T message)
	{
		LogInformation logInformation = new LogInformation(message + "");
		logInformation.setTag(tag);
		logInformation.setJtLogLevel(JtLogLevel.INFO);
		
		PrintStyle printStyle = PrintStyleManager.getInfoPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	
	public static void info(String tag, Object message)
	{
		LogInformation logInformation = new LogInformation(MyStringUtil.ObjectsToString(message));
		logInformation.setTag(tag);
		logInformation.setJtLogLevel(JtLogLevel.INFO);
		
		PrintStyle printStyle = PrintStyleManager.getInfoPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	public static void warn(String tag, String message)
	{
		LogInformation logInformation = new LogInformation(message);
		logInformation.setTag(tag);
		logInformation.setJtLogLevel(JtLogLevel.WARN);
		
		PrintStyle printStyle = PrintStyleManager.getWarnPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	public static <T extends Number> void warn(String tag, T message)
	{
		LogInformation logInformation = new LogInformation(message + "");
		logInformation.setTag(tag);
		logInformation.setJtLogLevel(JtLogLevel.WARN);
		
		PrintStyle printStyle = PrintStyleManager.getWarnPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	
	public static void warn(String tag, Object message)
	{
		LogInformation logInformation = new LogInformation(MyStringUtil.ObjectsToString(message));
		logInformation.setTag(tag);
		logInformation.setJtLogLevel(JtLogLevel.WARN);
		
		PrintStyle printStyle = PrintStyleManager.getWarnPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	public static void error(String tag, String message)
	{
		LogInformation logInformation = new LogInformation(message);
		logInformation.setTag(tag);
		logInformation.setJtLogLevel(JtLogLevel.ERROR);
		
		PrintStyle printStyle = PrintStyleManager.getErrorPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	public static <T extends Number> void error(String tag, T message)
	{
		LogInformation logInformation = new LogInformation(message + "");
		logInformation.setTag(tag);
		logInformation.setJtLogLevel(JtLogLevel.ERROR);
		
		PrintStyle printStyle = PrintStyleManager.getErrorPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	
	public static void error(String tag, Object message)
	{
		LogInformation logInformation = new LogInformation(MyStringUtil.ObjectsToString(message));
		logInformation.setTag(tag);
		logInformation.setJtLogLevel(JtLogLevel.ERROR);
		
		PrintStyle printStyle = PrintStyleManager.getErrorPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	public static void debug(String message)
	{
		LogInformation logInformation = new LogInformation(message);
		logInformation.setJtLogLevel(JtLogLevel.DEBUG);
		
		PrintStyle printStyle = PrintStyleManager.getDebugPrintStyle();
		
		processLog(logInformation, printStyle);
		
	}
	
	public static <T extends Number> void debug(T message)
	{
		LogInformation logInformation = new LogInformation(message + "");
		logInformation.setJtLogLevel(JtLogLevel.DEBUG);
		
		PrintStyle printStyle = PrintStyleManager.getDebugPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	public static void debug(Object... messages)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (Object message : messages)
		{
			stringBuilder.append(MyStringUtil.ObjectsToString(message)).append(" , ");
		}
		String message =
				stringBuilder.toString().substring(0, stringBuilder.toString().length() - 3);
		LogInformation logInformation = new LogInformation(message);
		logInformation.setJtLogLevel(JtLogLevel.DEBUG);
		
		PrintStyle printStyle = PrintStyleManager.getDebugPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	
	public static void debug(Object message)
	{
		LogInformation logInformation = new LogInformation(MyStringUtil.ObjectsToString(message));
		logInformation.setJtLogLevel(JtLogLevel.DEBUG);
		
		PrintStyle printStyle = PrintStyleManager.getDebugPrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	public static void arrive()
	{
		LogInformation logInformation = new LogInformation("");
		logInformation.setJtLogLevel(JtLogLevel.ARRIVE);
		
		PrintStyle printStyle = PrintStyleManager.getArrivePrintStyle();
		
		processLog(logInformation, printStyle);
	}
	
	//**********************************
	private static void processLog(LogInformation logInformation, PrintStyle printStyle)
	{
		JtPrinter jtPrinter = JtPrinterManager.getJtPrinter();
		JtLogger jtLogger = JtLogger.getJtLogger();
		jtLogger.setJtPrinter(jtPrinter);
		jtLogger.setPrintStyle(printStyle);
		jtLogger.log(logInformation);
	}
}
