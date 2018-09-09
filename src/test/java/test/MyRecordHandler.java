package test;

import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.recorder.FileRecorder;
import com.jeramtough.jtlog.recorder.Recorder;
import com.jeramtough.jtlog.recorder.RecorderHandler;

import java.io.File;

/**
 * 创建RecorderHandler的实现类，并重写handleRecorders()方法，返回一个Recorder接口的数组
 * ，数据里的Recorder接口实现类就是你的日志信息保存逻辑实现类
 */
@JtLoggerConfig(recorderHandleClass = MyRecordHandler.class,
        contextName = "RecorderHanderTest")
public class MyRecordHandler implements RecorderHandler {
    @Override
    public Recorder[] handleRecorders() {
        File file = new File("C:\\Users\\11718\\Desktop\\New folder (2)\\jtlog.log");
        return new Recorder[]{new FileRecorder(file, 5)};
    }
}
