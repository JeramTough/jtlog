package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

public class PrintlnPrintStyle extends BasePrintStyle {


    @Override
    public String stylize(LogContext logContext, LogInformation logInformation) {
        String formattedMessage = logInformation.getMessageStr();
        for (int i = 0; i < logContext.getLogConfig().getWrapCount(); i++) {
            formattedMessage = formattedMessage + System.lineSeparator();
        }
        return formattedMessage;
    }
}
