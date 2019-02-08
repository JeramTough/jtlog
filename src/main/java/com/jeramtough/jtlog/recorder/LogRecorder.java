package com.jeramtough.jtlog.recorder;

import com.jeramtough.jtlog.bean.LogInformation;

/**
 * 实现日志信息持久化功能
 * Created on 2018-09-06 00:22
 * by @author JeramTough
 */
public interface LogRecorder {

    /**
     * 重写该方法以实现记录逻辑
     *
     * @param logInformation {@link LogInformation}
     * @param stylizedText 格式化后的日志文本信息
     */
    void record(LogInformation logInformation, String stylizedText);

}
