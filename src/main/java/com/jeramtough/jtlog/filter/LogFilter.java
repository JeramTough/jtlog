package com.jeramtough.jtlog.filter;

import com.jeramtough.jtlog.log.LogInformation;

/**
 * @author 11718
 * on 2018  January 13 Saturday 13:13.
 */

public interface LogFilter {
    boolean isPrinted(LogInformation logInformation);
}
