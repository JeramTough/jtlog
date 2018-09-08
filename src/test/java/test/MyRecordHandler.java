package test;

import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.recorder.FileRecorder;
import com.jeramtough.jtlog.recorder.Recorder;
import com.jeramtough.jtlog.recorder.RecorderHandler;

import java.io.File;

/**
 * Created on 2018-09-07 13:22
 * by @author JeramTough
 */
@JtLoggerConfig(recorderHandleClass = MyRecordHandler.class,
contextName = "RecorderHanderTest")
public class MyRecordHandler implements RecorderHandler {
    @Override
    public Recorder[] handleRecorders() {
        File file=new File("C:\\Users\\11718\\Desktop\\New folder (2)\\jtlog.log");
        return new Recorder[]{new FileRecorder(file,5)};
    }

}
