package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.tag.ErrPrintlnTag;
import com.jeramtough.jtlog.tag.OutPrintlnTag;

/**
 * Created on 2018-08-21 16:55
 * by @author JeramTough
 */
public class JtPrinter extends BasePrinter {

    JtPrinter() {
    }

    @Override
    public void verbose(LogInformation logInformation, String stylizedText) {
        System.out.println(stylizedText);
    }

    @Override
    public void arrive(LogInformation logInformation, String stylizedText) {
        System.err.println(stylizedText);
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
        System.err.println(stylizedText);
    }

    @Override
    public void println(LogInformation logInformation, String stylizedText) {
        if (OutPrintlnTag.getInstance().equals(logInformation.getTag())) {
            System.out.println(stylizedText);
        }
        else if (ErrPrintlnTag.getInstance().equals(logInformation.getTag())) {
            System.err.println(stylizedText);
        }
    }

}
