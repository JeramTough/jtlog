package com.jeramtough.jtlog.header;

/**
 * Created on 2019-02-08 21:20
 * by @author JeramTough
 */
public enum LogHeader {

    /**
     *
     */
    CONTEXT("context"),

    /**
     *
     */
    TAG("tag"),


    /**
     *
     */
    THREAD("thread"),


    /**
     *
     */
    TIME("time"),


    /**
     *
     */
    TRACE("trace"),

    /**
     *
     */
    DEFAULT("");

    private String headerName;

    LogHeader(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }

    @Override
    public String toString() {
        return headerName;
    }
}

