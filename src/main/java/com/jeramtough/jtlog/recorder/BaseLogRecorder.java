package com.jeramtough.jtlog.recorder;

import com.jeramtough.jtlog.recorder.LogRecorder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * Created on 2021/8/19 下午6:07
 * by @author WeiBoWen
 * </pre>
 */
public abstract class BaseLogRecorder implements LogRecorder {

    protected final ExecutorService executorService;

    public BaseLogRecorder() {

        executorService = new ThreadPoolExecutor(1, 10000,
                120L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), r -> {
            Thread thread = new Thread(r);
            thread.setName("FileLogRecorder-" + thread.getId());
            return thread;
        });
    }

}
