package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.log.LogInformation;

public class InfoPrintStyle extends BasePrintStyle
{
	@Override
	public String stylize(LogInformation logInformation)
	{
		String message =
				getHead(logInformation) + getMessage(logInformation);
		return message;
	}
}
