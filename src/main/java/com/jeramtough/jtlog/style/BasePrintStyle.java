package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.header.LogHeader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created on 2019-02-08 16:35
 * by @author JeramTough
 */
public abstract class BasePrintStyle implements PrintStyle {

    public static final String DEFAULT_LOG_FORMAT = "{headers}" + System.lineSeparator() + "{message}";
    private LogContext logContext;

    public BasePrintStyle(LogContext logContext) {
        this.logContext = logContext;
    }

    public String getLogHeaders(LogInformation logInformation) {
        StringBuilder logHeadersBuilder = new StringBuilder(
                logInformation.getLogLevel().getFlag() + ":");
        for (LogHeader logHeader : logContext.getLogConfig().getLogHeaders()) {
            switch (logHeader) {
                case CONTEXT:
                    logHeadersBuilder.append("{").append(logHeader.getHeaderName()).append(
                            "}=").append(logContext.getContextName()).append(" .");
                    break;
                case TAG:
                    if (logInformation.getTag() != null) {
                        logHeadersBuilder.append("{").append(logHeader.getHeaderName()).append(
                                "}=").append(logInformation.getTag()).append(" .");
                    }
                    break;
                case TIME:
                    //processing time
                    DateFormat format = new SimpleDateFormat(
                            logContext.getLogConfig().getDataFormat());
                    String time = format.format(logInformation.getDate());
                    logHeadersBuilder.append("{").append(logHeader.getHeaderName()).append(
                            "}=").append(time).append(" .");
                    break;
                case THREAD:
                    logHeadersBuilder.append("{").append(logHeader.getHeaderName()).append(
                            "}=").append(logInformation.getThreadName()).append(" .");
                    break;
                case TRACE:
                    logHeadersBuilder.append("{").append(logHeader.getHeaderName()).append(
                            "}=").append(logInformation.getTrace()).append(" .");
                    break;
                default:
            }
        }
        return logHeadersBuilder.toString();
    }

    public String getFormattedMessage(LogInformation logInformation) {
        String formattedMessage = DEFAULT_LOG_FORMAT.replace("{headers}",
                getLogHeaders(logInformation)).replace("{message}",
                logInformation.getMessageStr());
        for (int i = 0; i < getLogContext().getLogConfig().getWrapCount(); i++) {
            formattedMessage = formattedMessage + System.lineSeparator();
        }
        return formattedMessage;
    }

    public LogContext getLogContext() {
        return logContext;
    }
}
