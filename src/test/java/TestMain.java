import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.jtlogger.JtLogger;
import com.jeramtough.jtlog.jtlogger.JtLoggerManager;
import com.jeramtough.jtlog.l.L;

@JtLoggerConfig(isUsedJtloggerApi = true)
public class TestMain {
    public static void main(String[] args) {
        new TestMain();
    }

    public TestMain() {
        test();
//        test1();
//				test2();
        //		test3();
    }

    private void test() {
        JtLogger jtLogger = JtLoggerManager.getJtLogger(TestMain.class);
        jtLogger.info("infoinfo");
        jtLogger.warn("warnwarn");
        jtLogger.debug("adfsa", "debugdebug");
        jtLogger.error("aaa", "error error");
        jtLogger.verbose("bb", "verbose,verbose");
        jtLogger.arrive();
        jtLogger.p(1 + 6);
    }

    private void test1() {
    }

}

