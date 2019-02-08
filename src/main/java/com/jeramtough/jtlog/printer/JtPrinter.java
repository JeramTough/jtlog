package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.bean.LogContext;
import com.jeramtough.jtlog.bean.LogInformation;

/**
 * Created on 2018-08-21 16:55
 * by @author JeramTough
 */
public class JtPrinter extends BasePrinter {

    JtPrinter(LogContext logContext) {
        super(logContext);
    }

    @Override
    public void verbose(LogInformation logInformation, String stylizedText) {
        System.out.println(stylizedText);
    }

    @Override
    public void arrive(LogInformation logInformation, String stylizedText) {
        System.out.println(stylizedText);
    }

    @Override
    public void debug(LogInformation logInformation, String stylizedText) {
        System.out.println(stylizedText);
    }

    @Override
    public void info(LogInformation logInformation, String stylizedText) {
        System.out.println(stylizedText);
    }

    @Override
    public void warn(LogInformation logInformation, String stylizedText) {
        System.out.println(stylizedText);
    }

    @Override
    public void error(LogInformation logInformation, String stylizedText) {
        System.out.println(stylizedText);
    }

    @Override
    public void println(LogInformation logInformation, String stylizedText) {
        System.out.println(stylizedText);
    }

}
