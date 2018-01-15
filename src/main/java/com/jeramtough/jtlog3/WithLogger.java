package com.jeramtough.jtlog3;

import com.jeramtough.jtlog3.filter.TagJtLogFilter;

public interface WithLogger
{
	
	default void unableLog()
	{
		String tag=getClass().getSimpleName();
		TagJtLogFilter tagJtLogFilter=new TagJtLogFilter(tag);
		JtLogConfig.getJtLogConfig().addFilter(tagJtLogFilter);
	}
	
	
	default P1 getP()
	{
		String tag=getClass().getSimpleName();
		return new P1(tag);
	}
	
	
	
}
