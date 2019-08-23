package com.jeramtough.jtlog.printer;

import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.bean.LogInformation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created on 2018-08-21 19:26
 * by @author JeramTough
 */
public class AndroidPrinter extends BasePrinter {

    private final String LOGCAT_TAG = "JTLOG";

    public static final String LOGCAT_PACKAGE_NAME = "android.util.Log";

    private static boolean isUsedAndroidLogApi = true;

    public static void setUsedAndroidLogApi(boolean usedAndroidLogApi) {
        isUsedAndroidLogApi = usedAndroidLogApi;
    }

    public AndroidPrinter(LogContext logContext) {
        super(logContext);
    }

    @Override
    public void verbose(LogInformation logInformation, String stylizedText) {
//        android.util.Log.v(LOGCAT_TAG, stylizedText);
        try {
            Method method = Class.forName(LOGCAT_PACKAGE_NAME).getMethod("v", String.class,
                    String.class);
            method.invoke(null, LOGCAT_TAG, stylizedText);
        }
        catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void arrive(LogInformation logInformation, String stylizedText) {
//        android.util.Log.d(LOGCAT_TAG, stylizedText);
        try {
            Method method = Class.forName(LOGCAT_PACKAGE_NAME).getMethod("d", String.class,
                    String.class);
            method.invoke(null, LOGCAT_TAG, stylizedText);
        }
        catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void debug(LogInformation logInformation, String stylizedText) {
//        android.util.Log.d(LOGCAT_TAG, stylizedText);
        try {
            Method method = Class.forName(LOGCAT_PACKAGE_NAME).getMethod("d", String.class,
                    String.class);
            method.invoke(null, LOGCAT_TAG, stylizedText);
        }
        catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void info(LogInformation logInformation, String stylizedText) {
//        android.util.Log.i(LOGCAT_TAG, stylizedText);
        try {
            Method method = Class.forName(LOGCAT_PACKAGE_NAME).getMethod("i", String.class,
                    String.class);
            method.invoke(null, LOGCAT_TAG, stylizedText);
        }
        catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void warn(LogInformation logInformation, String stylizedText) {
//        android.util.Log.w(LOGCAT_TAG, stylizedText);
        try {
            Method method = Class.forName(LOGCAT_PACKAGE_NAME).getMethod("w", String.class,
                    String.class);
            method.invoke(null, LOGCAT_TAG, stylizedText);
        }
        catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void error(LogInformation logInformation, String stylizedText) {
//        android.util.Log.e(LOGCAT_TAG, stylizedText);
        try {
            Method method = Class.forName(LOGCAT_PACKAGE_NAME).getMethod("e", String.class,
                    String.class);
            method.invoke(null, LOGCAT_TAG, stylizedText);
        }
        catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void println(LogInformation logInformation, String stylizedText) {
//        android.util.Log.v(LOGCAT_TAG, stylizedText);
        System.out.println(stylizedText);
    }
}
