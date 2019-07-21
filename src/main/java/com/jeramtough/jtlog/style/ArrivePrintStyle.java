package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

public class ArrivePrintStyle extends BasePrintStyle {

    public static final String ARRIVE_LOG_FORMAT = "{headers}";


    @Override
    public String stylize(LogContext logContext, LogInformation logInformation) {
        String formattedMessage = ARRIVE_LOG_FORMAT.replace("{headers}",
                getLogHeaders(logContext, logInformation));
        for (int i = 0; i < logContext.getLogConfig().getWrapCount(); i++) {
            formattedMessage = formattedMessage + System.lineSeparator();
        }
        return formattedMessage;
    }
}
