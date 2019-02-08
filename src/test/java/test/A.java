package test;

import com.jeramtough.jtlog.annotation.LogConfiguration;
import com.jeramtough.jtlog.with.WithLogger;

/**
 * Created on 2018-09-17 00:00
 * by @author JeramTough
 */
@LogConfiguration(contextName = "", isEnabled = 1)
public class A implements WithLogger {
    public void a(){
        getLogger().info("a");
    }
}
