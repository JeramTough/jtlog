package test;

import com.jeramtough.jtlog.recorder.FileLogRecorder;
import com.jeramtough.jtlog.with.WithLogger;
import org.junit.jupiter.api.Test;

import java.io.File;


public class FileTst implements WithLogger {
    @Test
    public void test() {
        getLogger().getLogContext().getLogRecorders().add(new FileLogRecorder(new File(
                "./logs/abc.log")));
        getLogger().info("cccc");
    }
}
