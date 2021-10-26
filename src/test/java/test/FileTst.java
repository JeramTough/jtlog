package test;

import com.jeramtough.jtlog.recorder.file.FileLogRecorder;
import com.jeramtough.jtlog.recorder.strategy.OneFileStrategy;
import com.jeramtough.jtlog.recorder.strategy.SortedDateFileStrategy;
import com.jeramtough.jtlog.with.WithLogger;
import org.junit.jupiter.api.Test;

import java.io.File;


public class FileTst implements WithLogger {
    @Test
    public void test() {
        getLogger().getLogContext().getLogRecorders().add(
                new FileLogRecorder(new OneFileStrategy(new File(
                        "./logs/abc.log"))));
        getLogger().info("cccc");
    }

    @Test
    public void test1() {
        getLogger().getLogContext().getLogRecorders().add(
                new FileLogRecorder(new SortedDateFileStrategy(new File(
                        "./logs"),"system.log")));
        getLogger().info("cccc");
        getLogger().error(new IllegalStateException("testesetssdfd"));
    }
}
