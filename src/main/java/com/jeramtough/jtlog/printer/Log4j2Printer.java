package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.log.LogInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created on 2018-08-21 19:27
 * by @author JeramTough
 */
public class Log4j2Printer extends BasePrinter {

    private Logger logger;

    public Log4j2Printer(LogContext logContext) {
        super(logContext);

        logger = LogManager.getLogger(logContext.getContextName());
    }

    @Override
    public void verbose(LogInformation logInformation, String stylizedText) {
        logger.trace(stylizedText);
    }

    @Override
    public void arrive(LogInformation logInformation, String stylizedText) {
        logger.debug(stylizedText);
    }

    @Override
    public void debug(LogInformation logInformation, String stylizedText) {
        logger.debug(stylizedText);
    }

    @Override
    public void info(LogInformation logInformation, String stylizedText) {
        logger.info(stylizedText);
    }

    @Override
    public void warn(LogInformation logInformation, String stylizedText) {
        logger.warn(stylizedText);
    }

    @Override
    public void error(LogInformation logInformation, String stylizedText) {
        logger.error(stylizedText);
    }

    @Override
    public void println(LogInformation logInformation, String stylizedText) {
        System.out.println(stylizedText);
    }
}
