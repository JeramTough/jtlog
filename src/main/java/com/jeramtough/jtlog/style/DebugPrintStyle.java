package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

public class DebugPrintStyle extends BasePrintStyle {

    @Override
    public String stylize(LogContext logContext, LogInformation logInformation) {
        return getFormattedMessage(logContext, logInformation);
    }
}
