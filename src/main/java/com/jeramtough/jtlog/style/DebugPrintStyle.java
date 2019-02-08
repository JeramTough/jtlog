package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

public class DebugPrintStyle extends BasePrintStyle {
    public DebugPrintStyle(LogContext logContext) {
        super(logContext);
    }

    @Override
    public String stylize(LogInformation logInformation) {
        return getFormattedMessage(logInformation);
    }
}
