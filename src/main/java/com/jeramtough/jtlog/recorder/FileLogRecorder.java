package com.jeramtough.jtlog.recorder;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;

import java.io.*;
import java.util.concurrent.*;

/**
 * Created on 2019-02-09 09:37
 * by @author JeramTough
 */
public class FileLogRecorder implements LogRecorder {

    private File logFile;
    private PrintWriter printWriter;
    private ExecutorService executorService;

    public FileLogRecorder(File logFile) {
        this.logFile = logFile;
        executorService = new ThreadPoolExecutor(1, 10000,
                120L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), r -> {
                    Thread thread = new Thread(r);
                    thread.setName("FileLogRecorder-" + thread.getId());
                    return thread;
                });

        init();
    }

    protected void init() {
        try {
            File parentDirectory = logFile.getParentFile();
            if (!parentDirectory.exists()) {
                boolean isOk = parentDirectory.mkdirs();
                if (isOk) {
                    logFile.createNewFile();
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void record(LogContext logContext, LogInformation logInformation,
                       String stylizedText) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(logFile, true);
                    printWriter = new PrintWriter(fileWriter);
                    printWriter.println(stylizedText);
                    printWriter.flush();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (printWriter != null) {

                        printWriter.close();
                    }
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
