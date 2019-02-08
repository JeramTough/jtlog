package test;

import com.jeramtough.jtlog.annotation.LogConfiguration;
import com.jeramtough.jtlog.facade.L;
import com.jeramtough.jtlog.jtlogger.Logger;
import com.jeramtough.jtlog.jtlogger.LoggerManager;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.with.WithLogger;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

@LogConfiguration(isUsedJtloggerApi = 1, isEnabled = 1,
        maxLengthOfRow = 130, contextName = "MyLogger",
        minVisibleLevel = LogLevel.VERBOSE)
public class TestMain implements WithLogger {

    @Test
    public void test() {
        Logger logger = LoggerManager.getLogger("JtloggerInterface");

      /*  logger.arrive();
        logger.info("11111");
        logger.warn("22222");
        logger.debug("tag", "3333");
        logger.debug("44444");
        logger.error("tag", "55555");
        logger.verbose("tag", "66666");
        logger.p("77777"); //不带任何格式输出*/
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
        getLogger().debug("with.www");
        getLogger().debugs("Strinds", 1, 12.1f, false);
    }

    @Test
    public void test3() {
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("testlogback");
        logger.debug("aaa");
    }



    @Test
    public void test4() {
        A a = new A();
        a.a();
        B b = new B();
        b.b();

    }

}

