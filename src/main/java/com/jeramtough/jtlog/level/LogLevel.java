package com.jeramtough.jtlog.level;

/**
 * @author 11718
 */

public enum LogLevel {
    INFO("I"), WARN("W"), ERROR("E"), DEBUG("D"), PRINTLN("P"), ARRIVE("Arrive"), VERBOSE("V");

    private String flag;

    LogLevel(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }
}
