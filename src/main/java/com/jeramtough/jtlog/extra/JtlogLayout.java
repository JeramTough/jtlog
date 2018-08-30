package com.jeramtough.jtlog.extra;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;
import com.jeramtough.jtlog.facade.L;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.log.LogInformation;
import com.jeramtough.jtlog.style.PrintStyle;
import com.jeramtough.jtlog.style.PrintStyleManager;

/**
 * Created on 2018-08-30 20:51
 * by @author JeramTough
 */
public class JtlogLayout extends LayoutBase<ILoggingEvent> {
    @Override
    public String doLayout(ILoggingEvent iLoggingEvent) {
        LogLevel logLevel;
        PrintStyle printStyle;
        Level level = iLoggingEvent.getLevel();
        if (level.equals(Level.INFO)) {
            logLevel = LogLevel.INFO;
            printStyle = PrintStyleManager.getInfoPrintStyle();
        }
        else if (level.equals(Level.WARN)) {
            logLevel = LogLevel.WARN;
            printStyle = PrintStyleManager.getWarnPrintStyle();
        }
        else if (level.equals(Level.ERROR)) {
            logLevel = LogLevel.ERROR;
            printStyle = PrintStyleManager.getErrorPrintStyle();
        }
        else if (level.equals(Level.TRACE)) {
            logLevel = LogLevel.VERBOSE;
            printStyle = PrintStyleManager.getVerbosePrintStyle();
        }
        else if (level.equals(Level.DEBUG)) {
            logLevel = LogLevel.DEBUG;
            printStyle = PrintStyleManager.getDebugPrintStyle();
        }
        else {
            logLevel = LogLevel.DEBUG;
            printStyle = PrintStyleManager.getDebugPrintStyle();
        }
        LogContext logContext=new LogContext();
        logContext.setContextName("jtlog");
        logContext.getLogConfig().setMinVisibleLevel(LogLevel.VERBOSE);
        logContext.getLogConfig().setCallerPlus(0);
        LogInformation logInformation =
                new LogInformation.Builder().setLogContext(logContext)
                        .setJtLogLevel(logLevel)
                        .setMessage(iLoggingEvent.getMessage()).build();

        return printStyle.stylize(logInformation);
    }
}
