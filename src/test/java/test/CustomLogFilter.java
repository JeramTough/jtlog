package test;

import com.jeramtough.jtlog.filter.LogFilter;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.log.LogInformation;

/**
 * Created on 2018-08-25 21:01
 * by @author JeramTough
 */
public class CustomLogFilter implements LogFilter {
    @Override
    public boolean isPrinted(LogInformation logInformation) {
        if (logInformation.getLogLevel() == LogLevel.DEBUG) {
            return false;
        }
        return true;
    }
}
