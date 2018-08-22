package com.jeramtough.jtlog.filter;


import com.jeramtough.jtlog.log.LogInformation;

public class TagJtLogFilter implements JtLogFilter
{
	private String noPrintTag;
	
	public TagJtLogFilter(String noPrintTag)
	{
		this.noPrintTag = noPrintTag;
	}
	
	@Override
	public boolean isPrinted(LogInformation logInformation)
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
