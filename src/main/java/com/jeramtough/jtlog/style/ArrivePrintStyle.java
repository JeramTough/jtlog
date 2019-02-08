package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;

public class ArrivePrintStyle extends BasePrintStyle
{
	@Override
	public String stylize(LogInformation logInformation)
	{
		String message = getHead(logInformation) + getTraceIfEnable(logInformation)+"\n";
		return message;
	}
}
