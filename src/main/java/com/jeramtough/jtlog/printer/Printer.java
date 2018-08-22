package com.jeramtough.jtlog.printer;


import com.jeramtough.jtlog.log.LogInformation;

/**
 * 这个接口负责输出方式，比如java输出，android输出
 *
 * @author 11718
 */
public interface Printer {
    void print(LogInformation logInformation);
}
