package com.jeramtough.jtlog.filter;

import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.log.LogInformation;

/**
 *
 * 根据日志最低等级进行过滤
 *
 * Created on 2018-09-13 17:02
 * by @author JeramTough
 */
public class MinLevelLogFilter implements LogFilter {
    @Override
    public boolean isPrinted(LogContext logContext, LogInformation logInformation) {
        int result = LogLevel.compare(logContext.getLogConfig().getMinVisibleLevel(),
                logInformation.getLogLevel());
        if (result <= 0) {
            return true;
        }
        return false;
    }
}
