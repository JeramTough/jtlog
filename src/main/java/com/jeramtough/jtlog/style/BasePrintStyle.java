package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.header.LogHeader;
import com.jeramtough.jtlog.util.MyStringUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created on 2019-02-08 16:35
 * by @author JeramTough
 */
public abstract class BasePrintStyle implements PrintStyle {

    public static final String DEFAULT_LOG_FORMAT = "{headers}" + System.lineSeparator() + "{message}";

    public BasePrintStyle() {
    }

    public String getLogHeaders(LogContext logContext, LogInformation logInformation) {
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
                                "}=").append(logInformation.getTag().getName()).append(" .");
                    }
                    break;
                case TIME:
                    //processing time
                    DateFormat format = new SimpleDateFormat(
                            logContext.getLogConfig().getDateFormat());
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

    public String getFormattedMessage(LogContext logContext, LogInformation logInformation) {

        String messageStr = MyStringUtil.splitTextByCounterOfRow(
                logInformation.getMessageStr(),
                logContext.getLogConfig().getMaxLengthOfRow());

        String formattedMessage = DEFAULT_LOG_FORMAT.replace("{headers}",
                getLogHeaders(logContext, logInformation)).replace("{message}",
                messageStr);


        for (int i = 0; i < logContext.getLogConfig().getWrapCount(); i++) {
            formattedMessage = formattedMessage + System.lineSeparator();
        }
        return formattedMessage;
    }

}
