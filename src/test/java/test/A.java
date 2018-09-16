package test;

import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.with.WithJtLogger;

/**
 * Created on 2018-09-17 00:00
 * by @author JeramTough
 */
@JtLoggerConfig(isEnabled = false)
public class A implements WithJtLogger {
    public void a(){
        getJtLogger().info("a");
    }
}
