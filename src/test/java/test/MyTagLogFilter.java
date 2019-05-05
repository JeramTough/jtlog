package test;

import com.jeramtough.jtlog.filter.TagLogFilter;
import com.jeramtough.jtlog.tag.Tag;

/**
 * Created on 2019-02-09 00:50
 * by @author JeramTough
 */
public class MyTagLogFilter extends TagLogFilter {
    public MyTagLogFilter() {
        super(Tag.get("my"));
    }
}
