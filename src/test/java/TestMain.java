import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.facade.L;
import com.jeramtough.jtlog.filter.TagLogFilter;
import com.jeramtough.jtlog.jtlogger.JtLogger;
import com.jeramtough.jtlog.jtlogger.JtLoggerManager;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.with.WithJtLogger;

@JtLoggerConfig(isUsedJtloggerApi = true, isEnabled = true,
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

        jtLogger.arrive();
        jtLogger.info("11111");
        jtLogger.warn("22222");
        jtLogger.debug("tag", "3333");
        jtLogger.debug("44444");
        jtLogger.error("tag", "55555");
        jtLogger.verbose("tag", "66666");
        jtLogger.p("77777");
    }

    private void test1() {
        L.debug("this is a debug information");
    }
    private void test2() {
        getJtLogger().debug("with.www");
        getJtLogger().debugs("Strinds",1,12.1f,false);
    }

}

