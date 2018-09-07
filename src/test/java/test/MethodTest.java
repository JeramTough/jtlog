package test;

import ch.qos.logback.classic.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Created on 2018-09-07 08:37
 * by @author JeramTough
 */
public class MethodTest {

    @Test
    public void test() {
        try {
            Class c = Class.forName("test.A");
            c.getMethod("method3").invoke(null);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        Logger logger = (Logger) LoggerFactory.getLogger("loggerName");
        try {
            String loggerName = "a";
            Object loggerObject = Class.forName("org.slf4j.LoggerFactory").getMethod(
                    "getLogger",String.class).invoke(null, loggerName);
            loggerObject.getClass().getMethod("debug",String.class).invoke(loggerObject,"abc");
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        logger.debug("dfafa");

    }

    @Test
    public void test2() {
        org.apache.logging.log4j.Logger logger1=LogManager.getLogger("fsa");
        logger1.info("");
        try {
            String loggerName = "a";
            Object loggerObject = Class.forName("org.apache.logging.log4j.LogManager").getMethod(
                    "getLogger",String.class).invoke(null, loggerName);
            loggerObject.getClass().getMethod("info",String.class).invoke(loggerObject,"abc");
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
