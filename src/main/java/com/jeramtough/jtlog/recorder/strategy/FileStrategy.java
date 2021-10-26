package com.jeramtough.jtlog.recorder.strategy;

import java.io.File;

/**
 * <pre>
 * Created on 2021/8/19 下午5:51
 * by @author WeiBoWen
 * </pre>
 */
public interface FileStrategy {

    File getFile();

    File getErrorFile();

    void printComplete();

}
