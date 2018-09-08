package test;

import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.with.WithJtLogger;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 使用
 */
@JtLoggerConfig(recorderHandleClass = MyRecordHandler.class,
isEnabled = true)
public class FileTst implements WithJtLogger {
    @Test
    public void test() {
        getJtLogger().info("saving log information in somewhere");
    }
}
