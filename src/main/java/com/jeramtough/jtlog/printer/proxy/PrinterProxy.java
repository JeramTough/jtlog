package com.jeramtough.jtlog.printer.proxy;

import com.jeramtough.jtlog.printer.Printer;

/**
 * Created on 2018-08-22 21:50
 * by @author JeramTough
 */
public interface PrinterProxy {

    Printer doProxy(Printer printer);
}
