package com.jeramtough.jtlog.recorder;

import com.jeramtough.jtlog.log.LogInformation;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created on 2018-09-06 00:22
 * by @author JeramTough
 */
public interface Recorder {

    /**
     * 重写该方法以实现记录功能
     *
     * @param logInformation {@link LogInformation}
     * @param stylizedText 格式化后的日志文本信息
     */
    void record(LogInformation logInformation, String stylizedText) throws IOException;

}
