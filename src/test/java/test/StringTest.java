package test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created on 2019-02-08 20:23
 * by @author JeramTough
 */
public class StringTest {
    @Test
    public void test() {
        String a = "{a}-{b}-{c}-";
        System.out.println(Arrays.toString(a.split("-")));
    }

    @Test
    public void test1() {
        String logFormat = "aaaa{message}bbbbbbbbbbb{headers}cccccccccccccc";
//        String logFormat = "aaaa{headers}bbbbbbbbbbb{message}cccccccccccccc";
        String[] temps = logFormat.split("\\{message}");
        if (temps[0].contains("{headers}")) {
            logFormat = temps[0];
        }
        else {
            logFormat = temps[1];
        }
        System.out.println(logFormat);
    }
}
