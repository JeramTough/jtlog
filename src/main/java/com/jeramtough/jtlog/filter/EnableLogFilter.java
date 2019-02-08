package com.jeramtough.jtlog.filter;

import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.bean.LogInformation;

/**
 * 是否允许输出过滤器
 * <p>
 * Created on 2018-09-13 16:56
 * by @author JeramTough
 */
public class EnableLogFilter implements LogFilter {

    @Override
    public boolean isPrinted(LogContext logContext, LogInformation logInformation) {
        if (logContext.getLogConfig().isEnabled()) {
            return true;
        }
        else {
            return false;
        }
    }
}
