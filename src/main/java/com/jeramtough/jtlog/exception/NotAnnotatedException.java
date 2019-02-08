package com.jeramtough.jtlog.exception;

/**
 * Created on 2019-02-08 14:52
 * by @author JeramTough
 */
public class NotAnnotatedException extends RuntimeException {

    public NotAnnotatedException() {
        super("make sure class has the LogConfiguration annotation");
    }
}
