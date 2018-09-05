package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.log.LogContext;
import com.jeramtough.jtlog.log.LogInformation;

/**
 * Created on 2018-08-21 19:26
 * by @author JeramTough
 */
public class AndroidPrinter extends BasePrinter {

    private final String LOGCAT_TAG = "JTLOG";

    public AndroidPrinter(LogContext logContext) {
        super(logContext);
    }

    @Override
    public void verbose(LogInformation logInformation, String stylizedText) {
//        android.util.Log.v(LOGCAT_TAG, stylizedText);
    }

    @Override
    public void arrive(LogInformation logInformation, String stylizedText) {
//        android.util.Log.d(LOGCAT_TAG, stylizedText);
    }

    @Override
    public void debug(LogInformation logInformation, String stylizedText) {
//        android.util.Log.d(LOGCAT_TAG, stylizedText);
    }

    @Override
    public void info(LogInformation logInformation, String stylizedText) {
//        android.util.Log.i(LOGCAT_TAG, stylizedText);
    }

    @Override
    public void warn(LogInformation logInformation, String stylizedText) {
//        android.util.Log.w(LOGCAT_TAG, stylizedText);
    }

    @Override
    public void error(LogInformation logInformation, String stylizedText) {
//        android.util.Log.e(LOGCAT_TAG, stylizedText);
    }

    @Override
    public void println(LogInformation logInformation, String stylizedText) {
//        android.util.Log.v(LOGCAT_TAG, stylizedText);
    }
}
