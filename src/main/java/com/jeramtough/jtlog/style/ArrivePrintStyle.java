package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

@Deprecated
public class ArrivePrintStyle extends BasePrintStyle
{
	public ArrivePrintStyle(LogContext logContext) {
		super(logContext);
	}

	@Override
	public String stylize(LogInformation logInformation)
	{
		String message = getHead(logInformation) + getTraceIfEnable(logInformation)+"\n";
		return message;
	}
}
