package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

/**
 * Created on 2018-08-21 21:25
 * by @author JeramTough
 */
@Deprecated
public class VerbosePrintStyle extends BasePrintStyle {
    public VerbosePrintStyle(LogContext logContext) {
        super(logContext);
    }

    @Override
    public String stylize(LogInformation logInformation) {
        String message =
                getHead(logInformation) + getTraceIfEnable(logInformation) + getMessage(
                        logInformation);
        return message;
    }
}
