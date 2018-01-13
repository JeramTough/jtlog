package com.jeramtough.jtlog3.style;

import com.jeramtough.jtlog3.JtLogConfig;
import com.jeramtough.jtlog3.LogInformation;
import com.jeramtough.jtlog3.logger.JtLogger;
import com.jeramtough.jtlog3.util.MyStringUtil;

/**
 * Created by 11718
 * on 2017  October 14 Saturday 17:44.
 *
 * @author 11718
 */

public abstract class BasePrintStyle implements PrintStyle
{
	
	protected String getHead(LogInformation logInformation)
	{
		String head = logInformation.getJtLogLevel().getFlag() + ":{time}=" +
				logInformation.getTime() + " , " + "{thread}=" + logInformation.getThread();
		
		return head;
	}
	
	protected String getMessage(LogInformation logInformation)
	{
		int limitNumber = JtLogConfig.getJtLogConfig().getMaxLengthOfRow();
		
		String message = "\n" + MyStringUtil.splitByNumberOfRow(
				logInformation.getMessage() == null ? "[null]" : logInformation.getMessage(),
				limitNumber) + "\n";
		return message;
	}
	
	
	protected String getPosition(LogInformation logInformation)
	{
		return " , {location}=" + logInformation.getClassName() + "." +
				logInformation.getMethodName() + "()" + "." + logInformation.getLine();
	}
	
	protected String getCaller(LogInformation logInformation)
	{
		return " , {caller}=(" + logInformation.getFileName() + ":" +
				logInformation.getLine() + ")";
	}
	
	protected String getTag(LogInformation logInformation)
	{
		return " , {tag}=" + logInformation.getTag();
	}
	
}
