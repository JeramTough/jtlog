package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

@Deprecated
public class WarnPrintStyle extends BasePrintStyle {

    public WarnPrintStyle(LogContext logContext) {
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
