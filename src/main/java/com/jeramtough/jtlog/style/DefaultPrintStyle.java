package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;

/**
 * Created on 2019-02-08 16:35
 * by @author JeramTough
 */
public class DefaultPrintStyle implements PrintStyle {

    private String logHeaderFormat;

    public DefaultPrintStyle(String logHeaderFormat) {
        this.logHeaderFormat = logHeaderFormat;
    }

    @Override
    public String stylize(LogInformation logInformation) {
        String message=logInformation.getLogLevel().getFlag()+"\n"+logInformation.getMessage()+"\n";
        return message;
    }
}
