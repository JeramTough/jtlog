package com.jeramtough.jtlog.printer;


import com.jeramtough.jtlog.log.LogInformation;

/**
 * 这个接口负责输出方式，比如java输出，android输出
 *
 * @author 11718
 */
public interface Printer {

    /**
     * 输出日志到控制台
     *
     * @param logInformation 日志信息
     * @return 格式化日志信息成为的String
     */
    String print(LogInformation logInformation);
}
