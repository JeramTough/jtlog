package com.jeramtough.jtlog.filter;


import com.jeramtough.jtlog.log.LogInformation;

public class TagLogFilter implements LogFilter {
    private String noPrintedTag;

    public TagLogFilter(String noPrintedTag) {
        this.noPrintedTag = noPrintedTag;
    }

    @Override
    public boolean isPrinted(LogInformation logInformation) {
        if (noPrintedTag.equals(logInformation.getTag())) {
            return false;
        } else {
            return true;
        }
    }
}
