package com.jeramtough.jtlog3.filter;

import com.jeramtough.jtlog3.LogInformation;

public class TagJtLogFilter implements JtLogFilter
{
	private String noPrintTag;
	
	public TagJtLogFilter(String noPrintTag)
	{
		this.noPrintTag = noPrintTag;
	}
	
	@Override
	public boolean canPrint(LogInformation logInformation)
	{
		if (logInformation.getTag().equals(noPrintTag))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
