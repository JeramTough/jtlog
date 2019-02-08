package test;

import com.jeramtough.jtlog.annotation.LogConfiguration;
import com.jeramtough.jtlog.with.WithLogger;

/**
 * Created on 2018-09-17 00:00
 * by @author JeramTough
 */
@LogConfiguration()
public class B implements WithLogger {
    public void b(){
        getLogger().info("b");
        getLogger().debug("aa");
        getLogger().debug("ccc");
    }
}
