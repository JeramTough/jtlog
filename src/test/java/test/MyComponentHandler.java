package test;

import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.filter.TagLogFilter;
import com.jeramtough.jtlog.handler.ComponentHandler;
import com.jeramtough.jtlog.recorder.FileLogRecorder;
import com.jeramtough.jtlog.recorder.LogRecorder;

import java.io.File;
import java.util.ArrayList;

/**
 * 创建RecorderHandler的实现类，并重写handleRecorders()方法，返回一个Recorder接口的数组
 * ，数据里的Recorder接口实现类就是你的日志信息保存逻辑实现类
 */
@JtLoggerConfig(recorderHandleClass = MyComponentHandler.class,
        contextName = "ComponentHandlerTest")
public class MyComponentHandler implements ComponentHandler {

    @Override
    public void handleLogFilters(ArrayList<LogFilter> logFilters) {
        TagLogFilter tagLogFilter = new TagLogFilter("bbb");
        logFilters.add(tagLogFilter);
    }

    @Override
    public void handleLogRecorders(ArrayList<LogRecorder> logRecorders) {
        File file = new File("E:\\Codes\\IdeaCodes\\JtlogForMaven\\jtlog.log");
        FileLogRecorder fileLogRecorder = new FileLogRecorder(file, 5);
        logRecorders.add(fileLogRecorder);
    }
}
