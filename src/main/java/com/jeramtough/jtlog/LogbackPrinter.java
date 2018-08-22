package com.jeramtough.jtlog;

import ch.qos.logback.classic.Logger;
import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.log.LogInformation;
import org.slf4j.LoggerFactory;

/**
 * Created on 2018-08-21 18:21
 * by @author JeramTough
 */
public class LogbackPrinter extends BasePrinter {

    private Logger logger;

    public LogbackPrinter(LogContext logContext) {
        super(logContext);
        logger = (Logger) LoggerFactory.getLogger(getLogContext().getContextName());
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
