package test;

import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.log.LogInformation;
import com.jeramtough.jtlog.recorder.FileRecorder;
import com.jeramtough.jtlog.recorder.Recorder;
import com.jeramtough.jtlog.recorder.RecorderHandler;
import com.jeramtough.jtlog.util.IOUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

/**
 * 实现自己的持久化逻辑
 */
class MyRecorder implements Recorder {
    @Override
    public void record(LogInformation logInformation, String stylizedText) throws IOException {
        //saved log to where you want.
        File file=new File("/someWhere/info.log");
        IOUtil.write(stylizedText.getBytes(),new FileOutputStream(file));
    }
}
