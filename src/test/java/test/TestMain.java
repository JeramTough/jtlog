package test;

import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.facade.L;
import com.jeramtough.jtlog.filter.TagLogFilter;
import com.jeramtough.jtlog.jtlogger.Logger;
import com.jeramtough.jtlog.jtlogger.LoggerManager;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.recorder.FileLogRecorder;
import com.jeramtough.jtlog.with.WithJtLogger;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

@JtLoggerConfig(isUsedJtloggerApi = true, isEnabled = true,
        maxLengthOfRow = 130, contextName = "MyLogger",
        minVisibleLevel = LogLevel.VERBOSE)
public class TestMain implements WithJtLogger {

    @Test
    public void test() {
        Logger logger = LoggerManager.getJtLogger("JtloggerInterface");

        //过滤掉标签标记为“aaa”的日志
        TagLogFilter tagLogFilter = new TagLogFilter("aaa");
        logger.getLogContext().getLogConfig().addLogFilter(tagLogFilter);

        logger.info("aaa", "这句日志信息将会被过滤掉");

        logger.arrive();
        logger.info("11111");
        logger.warn("22222");
        logger.debug("tag", "3333");
        logger.debug("44444");
        logger.error("tag", "55555");
        logger.verbose("tag", "66666");
        logger.p("77777"); //不带任何格式输出
    }

    @Test
    public void test1() {
        L.debug(null,null);
        L.info(88888.f);
        L.verbose("99999");
        L.arrive();
        L.debugs(null, 111111, 121212);
    }

    @Test
    public void test2() {
        getJtLogger().debug("with.www");
        getJtLogger().debugs("Strinds", 1, 12.1f, false);
    }

    @Test
    public void test3() {
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("testlogback");
        logger.debug("aaa");
    }

    @Test
    public void testComponentHandler() {
        Logger logger = LoggerManager.getJtLogger(MyComponentHandler.class);
        logger.debug("abc111111");
        logger.debug("abc222222");
        logger.debug("abc3333");
        logger.debug("abc44444444");

        logger.debug("bbb", "这条日志将会被过滤掉");

        //手动触发保存
        ((FileLogRecorder) logger.getLogContext().getLogConfig().getLogRecorders().get(
                0)).wirteLogFile();

        //5次后触发
        logger.debug("abc555555555");
        logger.debug("abc666666666");
        logger.debug("abc777777777");
        logger.debug("abc888888888");
        logger.debug("abc999999999");
    }

    @Test
    public void test4() {
        A a = new A();
        a.a();
        B b = new B();
        b.b();

    }

}

