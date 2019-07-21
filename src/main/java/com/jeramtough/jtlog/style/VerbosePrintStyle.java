package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

/**
 * Created on 2018-08-21 21:25
 * by @author JeramTough
 */
public class VerbosePrintStyle extends BasePrintStyle {

    @Override
    public String stylize(LogContext logContext, LogInformation logInformation) {
        return getFormattedMessage(logContext, logInformation);
    }
}
