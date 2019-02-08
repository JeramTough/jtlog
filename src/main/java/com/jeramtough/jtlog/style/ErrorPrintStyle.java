package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

public class ErrorPrintStyle extends BasePrintStyle {

    public ErrorPrintStyle(LogContext logContext) {
        super(logContext);
    }

    @Override
    public String stylize(LogInformation logInformation) {
        return getFormattedMessage(logInformation);
    }
}
