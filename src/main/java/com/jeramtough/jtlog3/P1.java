package com.jeramtough.jtlog3;

/**
 * @author 11718
 */
public class P1
{
	private String tag;
	
	public P1(String tag)
	{
		this.tag = tag;
	}
	
	public void info(String message)
	{
		P.info(tag, message);
	}
	
	public <T extends Number> void info(T message)
	{
		P.info(tag, message);
	}
	
	
	public void info(Object message)
	{
		P.info(tag, message);
	}
	
	public void warn(String message)
	{
		P.warn(tag, message);
	}
	
	public <T extends Number> void warn(T message)
	{
		P.warn(tag, message);
	}
	
	
	public void warn(Object message)
	{
		P.warn(tag, message);
	}
	
	public void error(String message)
	{
		P.error(tag, message);
	}
	
	public <T extends Number> void error(T message)
	{
		P.error(tag, message);
	}
	
	
	public void error(Object message)
	{
		P.error(tag, message);
	}
}
