package com.jeramtough.jtlog.util;

/**
 * Created on 2019-07-08 18:46
 * by @author JeramTough
 */
public class ExceptionUtil {

    public static String getDetail(Exception e) {
        StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");
        StackTraceElement[] messages = e.getStackTrace();
        int length = messages.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append("\t" + messages[i].toString() + "\n");
        }
        return stringBuffer.toString();
    }

}
