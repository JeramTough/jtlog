package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.log.LogInformation;

public class PrintlnPrintStyle extends BasePrintStyle
{
	@Override
	public String stylize(LogInformation logInformation)
	{
		String messsage= logInformation.getMessage();
		return messsage;
	}
}
