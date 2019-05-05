package test;

import com.jeramtough.jtlog.annotation.LogConfiguration;
import com.jeramtough.jtlog.config.LogConfigDefaultValues;
import com.jeramtough.jtlog.config.SimpleLogConfigDefaultValues;
import com.jeramtough.jtlog.facade.L;
import com.jeramtough.jtlog.filter.TagLogFilter;
import com.jeramtough.jtlog.header.LogHeader;
import com.jeramtough.jtlog.jtlogger.Logger;
import com.jeramtough.jtlog.jtlogger.LoggerManager;
import com.jeramtough.jtlog.lang.DefaultBoolean;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.recorder.FileLogRecorder;
import com.jeramtough.jtlog.with.WithLogger;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.helpers.BasicMarker;

import java.io.File;

@LogConfiguration(isUsedJtloggerApi = DefaultBoolean.TRUE, isEnabled = DefaultBoolean.TRUE,
        maxLengthOfRow = 130, contextName = "MyLogger",
        minVisibleLevel = LogLevel.VERBOSE, logFilters = {MyTagLogFilter.class},
        dataFormat = "YYYY:MM:HH:mm:ss")
public class TestMain implements WithLogger {

    @Test
    public void test() {
        Logger logger = LoggerManager.getLogger("JtloggerInterface");
        logger.getLogContext().getLogConfig().setWrapCount(1);
        logger.arrive();
        logger.info("11111");
        logger.warn("22222");
        logger.debugT("tag", "3333");
        logger.debug("44444");
        logger.errorT("tag", "55555");
        logger.verboseT("tag", "66666");
        logger.p("77777"); //不带任何格式输出
    }

    @Test
    public void test1() {
        L.debug(null, null);
        L.error("tag", "gggggggggg");
        L.info(88888.f);
        L.verbose("99999");
        L.arrive();
        L.debugs(null, 111111, 121212);
    }

    @Test
    public void test2() {
        //使用接口的默认方法getLogger()得到Logger
        getLogger().debug("with.www");
        getLogger().debugs("Strinds", 1, 12.1f, false);
    }

    @Test
    public void test3() {
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(
                "testlogback");
        logger.debug("aaa");
    }


    @Test
    public void test4() {
        A a = new A();
        a.a();
        B b = new B();
        b.b();
    }

    @Test
    public void test5() {
        getLogger().getLogContext().getLogFilters().add(new TagLogFilter("lalala"));
        getLogger().infoT("lalala", "这句不会被输出");
        getLogger().infoT("my", "这句也不会被输出");
        getLogger().infoT("lalala00000", "bbbbbbbbbbb");
    }


    @Test
    public void test6() {
        getLogger().getLogContext().getLogRecorders().add(new FileLogRecorder(new File(
                "E:\\Codes\\IdeaCodes\\JtlogForMaven\\src\\test\\resources\\test.log")));
        getLogger().debug("abc");

        //因为添加了过滤器，这句其实不会打印出来
        getLogger().infoT("my", "what");

        getLogger().info("lalala00000", ";nani");
    }

    @Test
    public void test7() {
        //Logger logger = LoggerManager.getLogger(TestMain.class);
        Logger logger = LoggerManager.getLogger("MyLogger");
        logger.arrive();
        logger.info("11111");
        logger.warn("22222");
        logger.debugT("tag", "3333");
        logger.debug("44444");
        logger.errorT("tag", "55555");
        logger.verboseT("tag", "66666");
        logger.p("77777"); //不带任何格式输出
    }

    @Test
    public void test8() {
        LoggerManager.setLogConfigDefaultValues(new LogConfigDefaultValues() {
            @Override
            public int decideMaxLengthOfRow() {
                return 0;
            }

            @Override
            public boolean decideIsEnabled() {
                return true;
            }

            @Override
            public boolean decideIsUsedJtloggerApi() {
                return true;
            }

            @Override
            public LogLevel decideMinVisibleLevel() {
                return LogLevel.DEBUG;
            }

            @Override
            public LogHeader[] decideLogHeaders() {
                return new LogHeader[]{LogHeader.TIME, LogHeader.CONTEXT,
                        LogHeader.TAG, LogHeader.THREAD, LogHeader.TRACE};
            }

            @Override
            public int decideWrapCount() {
                return 0;
            }

            @Override
            public String decideDataFormat() {
                return "HH:mm:ss:SSS";
            }
        });
        LoggerManager.setLogConfigDefaultValues(new SimpleLogConfigDefaultValues() {
            @Override
            public boolean decideIsEnabled() {
                return true;
            }
        });
        L.arrive();
        L.arrive();

    }

    @Test
    public void test9(){
        //测试占位符
        getLogger().debug("aaaa%sbbbbb%d","JeramTough",123);
    }


}

