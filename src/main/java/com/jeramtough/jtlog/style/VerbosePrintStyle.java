package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.log.LogInformation;

/**
 * Created on 2018-08-21 21:25
 * by @author JeramTough
 */
public class VerbosePrintStyle extends BasePrintStyle {
    @Override
    public String stylize(LogInformation logInformation) {
        String message =
                getHead(logInformation) + getTraceIfEnable(logInformation) + getMessage(
                        logInformation);
        return message;
    }
}
