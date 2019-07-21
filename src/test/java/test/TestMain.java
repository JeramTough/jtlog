package test;

import com.jeramtough.jtlog.annotation.LogConfiguration;
import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.config.LogConfigDefaultValues;
import com.jeramtough.jtlog.config.SimpleLogConfigDefaultValues;
import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.facade.L;
import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.filter.TagLogFilter;
import com.jeramtough.jtlog.header.LogHeader;
import com.jeramtough.jtlog.jtlogger.Logger;
import com.jeramtough.jtlog.jtlogger.LoggerManager;
import com.jeramtough.jtlog.lang.DefaultBoolean;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.recorder.FileLogRecorder;
import com.jeramtough.jtlog.recorder.LogRecorder;
import com.jeramtough.jtlog.tag.Tag;
import com.jeramtough.jtlog.with.WithLogger;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

@LogConfiguration(isUsedJtloggerApi = DefaultBoolean.TRUE, isEnabled = DefaultBoolean.TRUE,
        contextName = "MyLogger",
        minVisibleLevel = LogLevel.VERBOSE, logFilters = {MyTagLogFilter.class},
        dateFormat = "YYYY:MM:HH:mm:ss")
public class TestMain implements WithLogger {

    @Test
    public void test() {
        Logger logger = LoggerManager.getLogger("JtloggerInterface");
        logger.arrive();
        logger.info("1111111111111111");
        logger.warn("22222");
        logger.debug((Tag.get("tag")), "3333");
        logger.debug("44444");
        logger.error(Tag.get("tag"), "55555");
        logger.verbose(Tag.get("tag"), "66666");
        //不带任何格式输出
        getLogger().p("相当于System.out.println");
        //不带任何格式输出
        getLogger().e("相当于System.err.println");

        //使用占位符
        logger.info("%d and %s", 12, "字符型");


        //输出异常信息
        try {
            throw new NullPointerException("抛出异常");
        }
        catch (Exception e) {
            logger.error(e, "异常:%s", e.getMessage());
        }

    }

    @Test
    public void test1() {
        String n = null;
        L.debug(Tag.get("null"), n);
        L.error(Tag.get("tag"), "gggggggggg");
        L.info(88888.f);
        L.verbose("99999");
        L.arrive();
        L.debugs(null, 111111, 121212);
        L.error(new Exception("Exception"));
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
        getLogger().getLogContext().getLogFilters().add(new TagLogFilter(Tag.get("lalala")));
        getLogger().info(Tag.get("lalala"), "这句不会被输出");
        getLogger().info(Tag.get("my"), "有注释过滤器，这句也不会被输出");
        getLogger().info(Tag.get("gggggg"), "bbbbbbbbbbb");
    }


    @Test
    public void test6() {
        getLogger().getLogContext().getLogRecorders().add(new FileLogRecorder(new File(
                "E:\\Codes\\IdeaCodes\\JtlogForMaven\\src\\test\\resources\\test.log")));
        getLogger().debug("abc");

        //因为添加了过滤器，这句其实不会打印出来
        getLogger().info(Tag.get("my"), "what");

        getLogger().info("lalala00000", ";nani");
    }

    @Test
    public void test7() {
        //Logger logger = LoggerManager.getLogger(TestMain.class);
        Logger logger = LoggerManager.getLogger("MyLogger");
        logger.arrive();
        logger.info("11111");
        logger.warn("22222");
        logger.debug(Tag.get("tag"), "3333");
        logger.debug("44444");
        logger.error(Tag.get("tag"), "55555");
        logger.verbose(Tag.get("tag"), "66666");
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
            public int decideStackTraceOffset() {
                return 0;
            }

            @Override
            public String decideDateFormat() {
                return "HH:mm";
            }

            @Override
            public void decideGlobalLogFilters(List<LogFilter> logFilters) {

            }

            @Override
            public void decideGlobalLogRecoders(List<LogRecorder> logRecorders) {

            }
        });

        L.arrive();

        LoggerManager.setLogConfigDefaultValues(new SimpleLogConfigDefaultValues() {
            @Override
            public boolean decideIsEnabled() {
                return true;
            }

            @Override
            public String decideDateFormat() {
                return "YYYY-MM-dd";
            }

            @Override
            public void decideGlobalLogFilters(List<LogFilter> logFilters) {
                logFilters.add(new LogFilter() {
                    @Override
                    public boolean isPrinted(LogContext logContext,
                                             LogInformation logInformation) {
                        if (logInformation.getThreadName().equals("main")) {
                            return false;
                        }
                        return true;
                    }
                });
            }
        });
        L.arrive();
    }

    @Test
    public void test9() {
        //测试占位符
        getLogger().debug("aaaa,%s,%s", "hkhjk", "dsfsd");
        L.debug("abcde%s", "Weibowen");
    }

    @Test
    public void testStackTraceOffset() {
        /*getLogger().getLogContext().getLogConfig().setStackTraceOffset(0);
        getLogger().info("aaaa");*/
    }

    @Test
    public void testMaxLengthOfRow() {
        Logger logger = LoggerManager.getLogger("aaaa");
        logger.getLogContext().getLogConfig().setMaxLengthOfRow(1);
        logger.debug("aaaa");
        getLogger().debug("bbbbbbbbbbb");
    }


}

