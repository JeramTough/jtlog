package com.jeramtough.jtlog.tag;

/**
 * Created on 2019-07-21 15:17
 * by @author JeramTough
 */
public class ErrPrintlnTag extends Tag {

    private static final String SYSTEM_ERR_PRINTLN = "System.err.println";

    private static ErrPrintlnTag ourInstance = new ErrPrintlnTag();

    public static ErrPrintlnTag getInstance() {
        return ourInstance;
    }

    private ErrPrintlnTag() {
        super(SYSTEM_ERR_PRINTLN);
    }
}
