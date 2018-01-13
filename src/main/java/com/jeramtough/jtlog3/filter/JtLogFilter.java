package com.jeramtough.jtlog3.filter;

import com.jeramtough.jtlog3.LogInformation;

/**
 * @author 11718
 * on 2018  January 13 Saturday 13:13.
 */

public interface JtLogFilter
{
	boolean canPrint(LogInformation logInformation);
}
