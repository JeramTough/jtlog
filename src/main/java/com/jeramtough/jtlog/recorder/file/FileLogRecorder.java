package com.jeramtough.jtlog.recorder.file;

import com.jeramtough.jtlog.bean.LogInformation;
import com.jeramtough.jtlog.context.LogContext;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.recorder.BaseLogRecorder;
import com.jeramtough.jtlog.recorder.LogRecorder;
import com.jeramtough.jtlog.recorder.strategy.FileStrategy;
import com.jeramtough.jtlog.util.FileUtil;

import java.io.*;
import java.util.concurrent.*;

/**
 * Created on 2019-02-09 09:37
 * by @author JeramTough
 */
public class FileLogRecorder extends BaseLogRecorder {

    private final FileStrategy fileStrategy;


    public FileLogRecorder(FileStrategy fileStrategy) {
        this.fileStrategy = fileStrategy;
    }


    @Override
    public void record(LogContext logContext, LogInformation logInformation,
                       String stylizedText) {
        super.executorService.submit(new Runnable() {
            @Override
            public void run() {
                FileWriter fileWriter = null;
                PrintWriter printWriter = null;
                try {
                    if (fileStrategy != null) {
                        LogLevel logLevel = logInformation.getLogLevel();
                        if (logLevel != LogLevel.ERROR) {
                            fileWriter = new FileWriter(fileStrategy.getFile(), true);
                            printWriter = new PrintWriter(fileWriter);
                            printWriter.println(stylizedText);
                            printWriter.flush();
                        }
                        else {
                            fileWriter = new FileWriter(fileStrategy.getErrorFile(), true);
                            printWriter = new PrintWriter(fileWriter);
                            printWriter.println(stylizedText);
                            printWriter.flush();
                        }

                        fileStrategy.printComplete();

                    }
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
