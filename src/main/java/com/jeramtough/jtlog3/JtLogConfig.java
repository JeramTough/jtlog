package com.jeramtough.jtlog3;

import com.jeramtough.jtlog3.filter.JtLogFilter;

import java.util.ArrayList;

/**
 * Created by 11718
 * on 2017  October 03 Tuesday 21:26.
 */
public class JtLogConfig
{
	private boolean isColouredMode = false;
	private static volatile JtLogConfig jtLogConfig;
	private int maxLengthOfRow = 130;
	private ArrayList<JtLogFilter> jtLogFilters;
	
	private JtLogConfig()
	{
		jtLogFilters = new ArrayList<>();
	}
	
	public static JtLogConfig getJtLogConfig()
	{
		if (jtLogConfig == null)
		{
			synchronized (JtLogConfig.class)
			{
				if (jtLogConfig == null)
				{
					jtLogConfig = new JtLogConfig();
				}
			}
		}
		return jtLogConfig;
	}
	
	public boolean isColouredMode()
	{
		return isColouredMode;
	}
	
	public void setColouredMode(boolean colouredMode)
	{
		isColouredMode = colouredMode;
	}
	
	public int getMaxLengthOfRow()
	{
		return maxLengthOfRow;
	}
	
	public void setMaxLengthOfRow(int maxLengthOfRow)
	{
		this.maxLengthOfRow = maxLengthOfRow;
	}
	
	public void addFilter(JtLogFilter jtLogFilter)
	{
		jtLogFilters.add(jtLogFilter);
	}
	
	public ArrayList<JtLogFilter> getJtLogFilters()
	{
		return jtLogFilters;
	}
}
