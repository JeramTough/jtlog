package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

public class ArrivePrintStyle extends BasePrintStyle {

    public static final String ARRIVE_LOG_FORMAT = "{headers}";

    public ArrivePrintStyle(LogContext logContext) {
        super(logContext);
    }

    @Override
    public String stylize(LogInformation logInformation) {
        String formattedMessage = ARRIVE_LOG_FORMAT.replace("{headers}",
                getLogHeaders(logInformation));
        for (int i = 0; i < getLogContext().getLogConfig().getWrapCount(); i++) {
            formattedMessage = formattedMessage + "\n";
        }
        return formattedMessage;
    }
}
