package test;

import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.message.MessageWrapper;
import com.jeramtough.jtlog.with.WithLogger;
import org.junit.jupiter.api.Test;

/**
 * <pre>
 * Created on 2021/3/8 10:51
 * by @author WeiBoWen
 * </pre>
 */
public class CallTest implements WithLogger {

    @Test
    public void test2() {
       /* LogLevel minVisibleLevel=getLogger().getLogContext().getLogConfig().getMinVisibleLevel();
        boolean isAble= LogLevel.compare(minVisibleLevel,LogLevel.INFO)>0;

        boolean isAble1=getLogger().getLogContext().getLogConfig().isEnabled();

        if (isAble){
            getLogger().info("耗时日志操作");
        }*/

        getLogger().getLogContext().getLogConfig().setMinVisibleLevel(LogLevel.INFO);

        getLogger().info(() -> "输出信息，%s","占位符");

        getLogger().debug(() -> "因为设置了最低输出等级是Info，所以这条信息不回输出");

        getLogger().debug(new MessageWrapper() {
            @Override
            public Object message() {
                try {
                    Thread.sleep(60000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "因为设置了最低输出等级是Info，所以这条信息不回输出，上面的线程休眠都没有执行到";
            }
        });

    }

}
