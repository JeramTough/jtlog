package com.jeramtough.jtlog.level;

import java.util.Comparator;

/**
 * 日志等级<>br</>
 * 输出优先级:<>br</>
 * PRINTLN > ERROR > WARN > INFO > DEBUG > ARRIVE > VERBOSE
 *
 * @author 11718
 */

public enum LogLevel implements Comparator<LogLevel> {
    INFO("I", 3), WARN("W", 4), ERROR("E", 5), DEBUG("D", 2),
    PRINTLN("P", 6), ARRIVE("Arrive", 1), VERBOSE("V", 0);

    private String flag;
    private int priority;

    LogLevel(String flag, int priority) {
        this.flag = flag;
        this.priority = priority;
    }

    public String getFlag() {
        return flag;
    }


    @Override
    public int compare(LogLevel o1, LogLevel o2) {
        return o1.priority - o2.priority;
    }
}
