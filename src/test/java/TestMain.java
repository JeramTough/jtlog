import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.facade.L;
import com.jeramtough.jtlog.filter.TagLogFilter;
import com.jeramtough.jtlog.jtlogger.JtLogger;
import com.jeramtough.jtlog.jtlogger.JtLoggerManager;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.with.WithJtLogger;

@JtLoggerConfig(isUsedJtloggerApi = false, isEnabled = true,
        minVisibleLevel = LogLevel.VERBOSE)
public class TestMain implements WithJtLogger {
    public static void main(String[] args) {
        new TestMain();
    }

    public TestMain() {
        test();
        test1();
        test2();
        //		test3();
    }

    private void test() {
        JtLogger jtLogger = JtLoggerManager.getJtLogger(TestMain.class);

        TagLogFilter tagLogFilter = new TagLogFilter("aaa");
        jtLogger.getLogContext().getLogConfig().addLogFilter(tagLogFilter);

        jtLogger.info("infoinfo");
        jtLogger.warn("warnwarn");
        jtLogger.debug("adfsa", "debugdebug");
        jtLogger.error("aaa", "error error");
        jtLogger.verbose("bb", "verbose,verbose");
        jtLogger.arrive();
        jtLogger.p(1 + 6);
    }

    private void test1() {
        L.debug("L.infoinfoinfo");
    }
    private void test2() {
        getJtLogger().debug("with.www");
    }

}

