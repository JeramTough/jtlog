package com.jeramtough.jtlog.filter;


import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.tag.Tag;

/**
 * 日志标签过滤器，根据tag过滤日志
 *
 * @author 11718
 */
public class TagLogFilter implements LogFilter {
    private Tag noPrintedTag;

    public TagLogFilter(Tag noPrintedTag) {
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
