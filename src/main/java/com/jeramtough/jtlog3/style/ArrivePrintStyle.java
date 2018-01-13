package com.jeramtough.jtlog3.style;

import com.jeramtough.jtlog3.LogInformation;

public class ArrivePrintStyle extends BasePrintStyle
{
	@Override
	public String stylize(LogInformation logInformation)
	{
		String message = getHead(logInformation) + getPosition(logInformation) +
				getCaller(logInformation);
		return message;
	}
}
