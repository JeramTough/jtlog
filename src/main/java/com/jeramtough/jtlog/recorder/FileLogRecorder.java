package com.jeramtough.jtlog.recorder;

import com.jeramtough.jtlog.bean.LogInformation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 将日志信息保存到指定文件
 * <p>
 * Created on 2018-09-06 00:25
 * by @author JeramTough
 */
public class FileLogRecorder implements LogRecorder {

    private File logFile;
    private int maxCacheRecordedCount;

    private List<String> cacheRecordedLogs;
    private ExecutorService executorService;

    /**
     * @param logFile               日志保存位置
     * @param maxCacheRecordedCount 最大缓存日志信息数量，当到达最大数量就写入文件
     */
    public FileLogRecorder(File logFile, int maxCacheRecordedCount) {
        this.logFile = logFile;
        this.maxCacheRecordedCount = maxCacheRecordedCount;

        cacheRecordedLogs = new ArrayList<>();
        executorService = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    @Override
    public synchronized void record(LogInformation logInformation, String stylizedText) {
        cacheRecordedLogs.add(stylizedText);
        if (cacheRecordedLogs.size() >= maxCacheRecordedCount) {
            writing();
        }
    }

    /**
     * 手动添加写入日志信息文件任务，当保存日志信息线程池空闲时执行。
     */
    public synchronized void wirteLogFile() {
        writing();
    }

    //**********************
    private void writing() {
        System.out.println("start writing logs to file");
        String[] logs = new String[cacheRecordedLogs.size()];
        for (int i = 0; i < cacheRecordedLogs.size(); i++) {
            logs[i] = cacheRecordedLogs.get(i);
        }
        cacheRecordedLogs.clear();
        executorService.submit(new WriteLogThread(logs));
    }

    //{{{{{{{{{{{}}}}}}}}}}
    private class WriteLogThread implements Runnable {

        private final String[] logs;

        private WriteLogThread(String[] logs) {
            this.logs = logs;
        }

        @Override
        public void run() {
            try {
                if (!logFile.exists()) {
                    logFile.createNewFile();
                }
                FileInputStream fileInputStream = new FileInputStream(logFile);

                File temporaryFile =
                        new File(logFile.getParentFile().getAbsolutePath()
                                + "\\" + logFile.getName() + ".temporary");
//                System.out.println(temporaryFile.getAbsolutePath());
                temporaryFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(temporaryFile);


                if (logFile.length() > 0) {
                    InputStreamReader reader = new InputStreamReader(
                            fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line != null) {
                            fileOutputStream.write((line + "\n").getBytes());
                        }
                        else {
                            break;
                        }
                    }
                }

                for (String log : logs) {
                    fileOutputStream.write(log.getBytes());
                }


                fileInputStream.close();
                fileOutputStream.close();
                logFile.delete();
                temporaryFile.renameTo(logFile);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
