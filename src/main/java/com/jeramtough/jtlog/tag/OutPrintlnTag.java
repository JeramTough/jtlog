package com.jeramtough.jtlog.tag;

/**
 * Created on 2019-07-21 15:16
 * by @author JeramTough
 */
public class OutPrintlnTag extends Tag {

    private static final String SYSTEM_OUT_PRINTLN = "System.out.println";

    private static OutPrintlnTag ourInstance = new OutPrintlnTag();

    public static OutPrintlnTag getInstance() {
        return ourInstance;
    }

    private OutPrintlnTag() {
        super(SYSTEM_OUT_PRINTLN);
    }
}
