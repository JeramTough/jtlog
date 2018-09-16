package test;

import ch.qos.logback.classic.Logger;
import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.facade.L;
import com.jeramtough.jtlog.filter.TagLogFilter;
import com.jeramtough.jtlog.jtlogger.JtLogger;
import com.jeramtough.jtlog.jtlogger.JtLoggerManager;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.recorder.FileLogRecorder;
import com.jeramtough.jtlog.with.WithJtLogger;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.TRANSACTION_MODE;
import org.slf4j.LoggerFactory;

@JtLoggerConfig(isUsedJtloggerApi = false, isEnabled = true,
        maxLengthOfRow = 130, contextName = "MyLogger",
        minVisibleLevel = LogLevel.VERBOSE)
public class TestMain implements WithJtLogger {

    @Test
    public void test() {
        JtLogger jtLogger = JtLoggerManager.getJtLogger(TestMain.class);

        //过滤掉标签标记为“aaa”的日志
        TagLogFilter tagLogFilter = new TagLogFilter("aaa");
        jtLogger.getLogContext().getLogConfig().addLogFilter(tagLogFilter);


        jtLogger.info("aaa", "这句日志信息将会被过滤掉");

        jtLogger.arrive();
        jtLogger.info("11111");
        jtLogger.warn("22222");
        jtLogger.debug("tag", "3333");
        jtLogger.debug("44444");
        jtLogger.error("tag", "55555");
        jtLogger.verbose("tag", "66666");
        jtLogger.p("77777"); //不带任何格式输出
    }

    @Test
    public void test1() {
        L.debug(null);
        L.info(88888.f);
        L.verbose("99999");
        L.debugs(null, 111111, 121212);
    }

    @Test
    public void test2() {
        getJtLogger().debug("with.www");
        getJtLogger().debugs("Strinds", 1, 12.1f, false);
    }

    @Test
    public void test3() {
        Logger logger = (Logger) LoggerFactory.getLogger("testlogback");
        logger.debug("aaa");
    }

    @Test
    public void testComponentHandler() {
        JtLogger jtLogger = JtLoggerManager.getJtLogger(MyComponentHandler.class);
        jtLogger.debug("abc111111");
        jtLogger.debug("abc222222");
        jtLogger.debug("abc3333");
        jtLogger.debug("abc44444444");

        jtLogger.debug("bbb", "这条日志将会被过滤掉");

        //手动触发保存
        ((FileLogRecorder) jtLogger.getLogContext().getLogConfig().getLogRecorders().get(
                0)).wirteLogFile();

        //5次后触发
        jtLogger.debug("abc555555555");
        jtLogger.debug("abc666666666");
        jtLogger.debug("abc777777777");
        jtLogger.debug("abc888888888");
        jtLogger.debug("abc999999999");
    }

    @Test
    public void test4() {
        A a = new A();
        a.a();
        B b = new B();
        b.b();

    }

}

