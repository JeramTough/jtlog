package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.style.PrintStyleManager;

/**
 * Created on 2018-08-21 18:23
 * by @author JeramTough
 */
public abstract class BasePrinter implements Printer {
    private LogContext logContext;

    public BasePrinter(LogContext logContext) {
        this.logContext = logContext;
    }


    public LogContext getLogContext() {
        return logContext;
    }

    @Override
    public String print(LogInformation logInformation) {
        String stylizedText = null;
        switch (logInformation.getLogLevel()) {
            case ARRIVE:
                stylizedText =
                        PrintStyleManager.getArrivePrintStyle(logContext).stylize(
                                logInformation);
                arrive(logInformation, stylizedText);
                break;
            case DEBUG:
                stylizedText =
                        PrintStyleManager.getDebugPrintStyle(logContext).stylize(
                                logInformation);
                debug(logInformation, stylizedText);
                break;
            case PRINTLN:
                stylizedText =
                        PrintStyleManager.getPrintlnPrintStyle(logContext).stylize(
                                logInformation);
                println(logInformation, stylizedText);
                break;
            case INFO:
                stylizedText =
                        PrintStyleManager.getInfoPrintStyle(logContext).stylize(
                                logInformation);
                info(logInformation, stylizedText);
                break;
            case WARN:
                stylizedText =
                        PrintStyleManager.getWarnPrintStyle(logContext).stylize(
                                logInformation);
                warn(logInformation, stylizedText);
                break;
            case ERROR:
                stylizedText =
                        PrintStyleManager.getErrorPrintStyle(logContext).stylize(
                                logInformation);
                error(logInformation, stylizedText);
                break;
            case VERBOSE:
                stylizedText =
                        PrintStyleManager.getVerbosePrintStyle(logContext).stylize(
                                logInformation);
                verbose(logInformation, stylizedText);
                break;
            default:
        }
        return stylizedText;
    }

    public abstract void verbose(LogInformation logInformation, String stylizedText);

    public abstract void arrive(LogInformation logInformation, String stylizedText);

    public abstract void debug(LogInformation logInformation, String stylizedText);

    public abstract void info(LogInformation logInformation, String stylizedText);

    public abstract void warn(LogInformation logInformation, String stylizedText);

    public abstract void error(LogInformation logInformation, String stylizedText);

    public abstract void println(LogInformation logInformation, String stylizedText);
}
