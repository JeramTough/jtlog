package test;

import com.jeramtough.jtlog.annotation.LogConfiguration;
import com.jeramtough.jtlog.jtlogger.Logger;
import com.jeramtough.jtlog.jtlogger.LoggerManager;
import org.junit.jupiter.api.Test;

/**
 * Created on 2019-02-08 16:20
 * by @author JeramTough
 */
@LogConfiguration(isEnabled = 0,maxLengthOfRow = 200)
public class MainTest {

    @Test
    public void test1() {
        Logger logger = LoggerManager.getLogger(this.getClass());
        logger.debug("test message");
    }
}
