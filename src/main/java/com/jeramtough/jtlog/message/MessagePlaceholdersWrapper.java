package com.jeramtough.jtlog.message;

import com.jeramtough.jtlog.util.ExceptionUtil;

/**
 * <pre>
 * Created on 2021/3/8 14:03
 * by @author WeiBoWen
 * </pre>
 */
public class MessagePlaceholdersWrapper implements MessageWrapper {

    private final MessageWrapper messageWrapper;
    private final Object[] placeholders;
    private Exception e;

    public MessagePlaceholdersWrapper(MessageWrapper messageWrapper, Object[] placeholders) {
        this.messageWrapper = messageWrapper;
        this.placeholders = placeholders;
    }

    public MessagePlaceholdersWrapper(Exception e, MessageWrapper messageWrapper, Object[] placeholders) {
        this.messageWrapper = messageWrapper;
        this.placeholders = placeholders;
        this.e = e;
    }

    @Override
    public Object message() {
        Object message = messageWrapper.message();

        if (message == null) {
            return null;
        }

        String messageStr = message.toString();
        if (placeholders != null && placeholders.length > 0) {
            messageStr = String.format(messageStr, placeholders);
        }

        if (e != null) {
            messageStr = messageStr + System.getProperty(
                    "line.separator") + ExceptionUtil.getDetail(e);
        }

        return messageStr;
    }
}
