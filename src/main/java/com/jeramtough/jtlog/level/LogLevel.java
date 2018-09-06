package com.jeramtough.jtlog.level;

/**
 * 日志可见等级排名：
 * PRINTLN   ERROR   WARN   INFO   DEBUG   ARRIVE   VERBOSE
 *
 * @author 11718
 */

public enum LogLevel {
    /**
     *
     */
    INFO("I", "info", 3),

    /**
     *
     */
    WARN("W", "warn", 4),

    /**
     *
     */
    ERROR("E", "error", 5),

    /**
     *
     */
    DEBUG("D", "debug", 2),


    /**
     *
     */
    PRINTLN("P", "p", 6),

    /**
     *
     */
    ARRIVE("Arrive", "arrive", 1),

    /**
     *
     */
    VERBOSE("V", "verbose", 0);

    private String flag;
    private String name;
    private int priority;

    LogLevel(String flag, String name, int priority) {
        this.flag = flag;
        this.name = name;
        this.priority = priority;
    }

    public String getFlag() {
        return flag;
    }


    public static int compare(LogLevel o1, LogLevel o2) {
        return o1.priority - o2.priority;
    }

    public static LogLevel getLogLevelByName(String name) {
        if (INFO.name.equals(name)) {
            return INFO;
        } else if (WARN.name.equals(name)) {
            return WARN;

        } else if (ERROR.name.equals(name)) {
            return ERROR;

        } else if (DEBUG.name.equals(name)) {
            return DEBUG;

        } else if (ARRIVE.name.equals(name)) {
            return ARRIVE;

        } else if (PRINTLN.name.equals(name)) {
            return PRINTLN;

        } else if (VERBOSE.name.equals(name)) {
            return VERBOSE;
        }
        return VERBOSE;
    }
}
