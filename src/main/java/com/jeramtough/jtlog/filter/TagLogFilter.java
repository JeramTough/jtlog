package com.jeramtough.jtlog.filter;


import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.bean.LogInformation;

/**
 * 日志标签过滤器，根据tag过滤日志
 *
 * @author 11718
 */
public class TagLogFilter implements LogFilter {
    private String noPrintedTag;

    public TagLogFilter(String noPrintedTag) {
        this.noPrintedTag = noPrintedTag;
    }

    @Override
    public boolean isPrinted(LogContext logContext, LogInformation logInformation) {
        if (noPrintedTag.equals(logInformation.getTag())) {
            return false;
        }
        else {
            return true;
        }
    }
}
