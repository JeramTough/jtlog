package test;

import com.jeramtough.jtlog.annotation.JtLoggerConfig;
import com.jeramtough.jtlog.with.WithJtLogger;

/**
 * Created on 2018-09-17 00:00
 * by @author JeramTough
 */
@JtLoggerConfig()
public class B implements WithJtLogger {
    public void b(){
        getJtLogger().info("b");
        getJtLogger().debug("aa");
        getJtLogger().debug("ccc");
    }
}
