package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

@Deprecated
public class PrintlnPrintStyle extends BasePrintStyle {
    public PrintlnPrintStyle(LogContext logContext) {
        super(logContext);
    }

    @Override
    public String stylize(LogInformation logInformation) {
        String messsage = logInformation.getMessage();
        return messsage;
    }
}
