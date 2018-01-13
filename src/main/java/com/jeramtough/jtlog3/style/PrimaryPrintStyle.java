package com.jeramtough.jtlog3.style;

import com.jeramtough.jtlog3.LogInformation;

public class PrimaryPrintStyle extends BasePrintStyle
{
	@Override
	public String stylize(LogInformation logInformation)
	{
		String messsage=logInformation.getMessage();
		return messsage;
	}
}
