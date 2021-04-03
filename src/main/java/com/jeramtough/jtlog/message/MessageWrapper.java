package com.jeramtough.jtlog.message;

/**
 *
 * 消息体的包装类，只有真正执行的时候，才会获取message，避免在业务打印中进行耗时message格式操作
 *
 * <pre>
 * Created on 2021/3/8 13:32
 * by @author WeiBoWen
 * </pre>
 */
public interface MessageWrapper {

    Object message();

}
