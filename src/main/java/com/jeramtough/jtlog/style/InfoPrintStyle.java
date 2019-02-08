package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

public class InfoPrintStyle extends BasePrintStyle {
    public InfoPrintStyle(LogContext logContext) {
        super(logContext);
    }

    @Override
    public String stylize(LogInformation logInformation) {
        return getFormattedMessage(logInformation);
    }
}
