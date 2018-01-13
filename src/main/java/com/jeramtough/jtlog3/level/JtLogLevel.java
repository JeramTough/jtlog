package com.jeramtough.jtlog3.level;

/**
 * @author 11718
 */

public enum JtLogLevel
{
	INFO("I"), WARN("W"), ERROR("E"), DEBUG("D"), PRIMARY("P"), ARRIVE("Arrive");
	
	private String flag;
	
	JtLogLevel(String flag)
	{
		this.flag = flag;
	}
	
	public String getFlag()
	{
		return flag;
	}
}
