package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.log.LogInformation;
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
                        PrintStyleManager.getArrivePrintStyle().stylize(logInformation);
                arrive(logInformation, stylizedText);
                break;
            case DEBUG:
                stylizedText = PrintStyleManager.getDebugPrintStyle().stylize(logInformation);
                debug(logInformation, stylizedText);
                break;
            case PRINTLN:
                stylizedText = PrintStyleManager.getPrintlnPrintStyle().stylize(logInformation);
                println(logInformation, stylizedText);
                break;
            case INFO:
                stylizedText = PrintStyleManager.getInfoPrintStyle().stylize(logInformation);
                info(logInformation, stylizedText);
                break;
            case WARN:
                stylizedText = PrintStyleManager.getWarnPrintStyle().stylize(logInformation);
                warn(logInformation, stylizedText);
                break;
            case ERROR:
                stylizedText = PrintStyleManager.getErrorPrintStyle().stylize(logInformation);
                error(logInformation, stylizedText);
                break;
            case VERBOSE:
                stylizedText = PrintStyleManager.getVerbosePrintStyle().stylize(logInformation);
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
