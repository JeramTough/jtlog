package test;

import com.jeramtough.jtlog.annotation.LogConfiguration;
import com.jeramtough.jtlog.lang.DefaultBoolean;
import com.jeramtough.jtlog.with.WithLogger;

/**
 * Created on 2018-09-17 00:00
 * by @author JeramTough
 */
@LogConfiguration(contextName = "", isEnabled = DefaultBoolean.TRUE,
dateFormat = "YYYY-MM-dd")
public class A implements WithLogger {
    public void a() {
        getLogger().info("a");
    }
}
