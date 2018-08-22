package com.jeramtough.jtlog.filter;


import com.jeramtough.jtlog.log.LogInformation;

public class TagLogFilter implements LogFilter {
    private String noPrintedTag;

    public TagLogFilter(String noPrintedTag) {
        this.noPrintedTag = noPrintedTag;
    }

    @Override
    public boolean isPrinted(LogInformation logInformation) {
        if (logInformation.getTag().equals(noPrintedTag)) {
            return false;
        } else {
            return true;
        }
    }
}
