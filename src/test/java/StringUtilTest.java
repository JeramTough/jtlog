import com.jeramtough.jtlog.facade.L;
import com.jeramtough.jtlog.util.MyStringUtil;
import org.junit.jupiter.api.Test;

/**
 * Created on 2018-08-23 15:06
 * by @author JeramTough
 */
public class StringUtilTest {

    @Test
    public void test() {
        String content = "一二\n三\n四五六七八九十";
       /* for (int i = 0; i < 200; i++) {
            content = content + (i + "");
            if (i%50==0)
            {
                content=content+"\n";
            }
        }*/
        content=MyStringUtil.splitTextByCounterOfRow(content, 3);
        L.debug(content);
    }
}
