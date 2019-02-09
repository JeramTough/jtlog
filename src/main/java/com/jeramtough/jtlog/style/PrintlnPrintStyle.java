package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

public class PrintlnPrintStyle extends BasePrintStyle {
    public PrintlnPrintStyle(LogContext logContext) {
        super(logContext);
    }

    @Override
    public String stylize(LogInformation logInformation) {
        String formattedMessage = logInformation.getMessageStr();
        for (int i = 0; i < getLogContext().getLogConfig().getWrapCount(); i++) {
            formattedMessage = formattedMessage + System.lineSeparator();
        }
        return formattedMessage;
    }
}
