package test;

import com.jeramtough.jtlog.config.LogConfig;
import com.jeramtough.jtlog.config.SimpleLogConfigDefaultValues;
import com.jeramtough.jtlog.facade.L;
import com.jeramtough.jtlog.jtlogger.LoggerManager;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;

/**
 * Created on 2019-08-01 11:23
 * by @author JeramTough
 */
public class ConfigTest {

    @Test
    public void test() {
        Map<String, LogConfig> logConfigMap = new HashMap<>();
        logConfigMap.put("config1", L.getLogContext().getLogConfig());
        logConfigMap.put("config2",
                LoggerManager.getLogger(A.class).getLogContext().getLogConfig());
        logConfigMap.put("config3",
                LoggerManager.getLogger(B.class).getLogContext().getLogConfig());

        JSONObject jsonObject = new JSONObject(logConfigMap);
        L.debug(jsonObject.toString(2));
        Map<String,Object> a=jsonObject.toMap();
    }

    @Test
    public void test1(){
        LoggerManager.setLogConfigDefaultValues(new SimpleLogConfigDefaultValues(){
            @Override
            public File decideCoverConfigFile() {
                return new File("./jtlog-config.json");
//                return null;
            }
        });
        L.arrive();

        LoggerManager.getLogger(A.class).debug("ggggffff");
    }


}
