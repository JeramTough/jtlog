package com.jeramtough.jtlog.recorder;

import com.jeramtough.jtlog.log.LogInformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 将日志信息保存到指定文件
 * <p>
 * Created on 2018-09-06 00:25
 * by @author JeramTough
 */
public class FileRecorder implements Recorder {

    private File logFile;
    private int maxCacheRecordedCount;

    private List<String> cacheRecordedLogs;
    private ExecutorService executorService;

    /**
     * @param logFile               日志保存位置
     * @param maxCacheRecordedCount 最大缓存日志信息数量，当到达最大数量就写入文件
     */
    public FileRecorder(File logFile, int maxCacheRecordedCount) {
        this.logFile = logFile;
        this.maxCacheRecordedCount = maxCacheRecordedCount;

        cacheRecordedLogs = new ArrayList<>();
        executorService = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    @Override
    public void record(LogInformation logInformation, String stylizedText) {
        cacheRecordedLogs.add(stylizedText);

        if (cacheRecordedLogs.size() >= maxCacheRecordedCount) {
            executorService.submit(new WirteLogThread());
        }
    }

    /**
     * 手动添加写入日志信息文件任务，当保存日志信息线程池空闲时执行。
     */
    public void wirteLogFile() {
        executorService.submit(new WirteLogThread());
    }

    //{{{{{{{{{{{}}}}}}}}}}
    private class WirteLogThread implements Runnable {

        @Override
        public void run() {
            if (!logFile.exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(logFile);
                File temporaryFile =
                        new File(logFile.getParentFile().getAbsolutePath()
                                + File.pathSeparator + logFile.getName() + ".temporary");
                FileOutputStream fileOutputStream = new FileOutputStream(temporaryFile);
                byte[] readBytes = new byte[255];
                while (fileInputStream.read(readBytes) > 0) {
                    fileOutputStream.write(readBytes);
                }

                for (String log : cacheRecordedLogs) {
                    fileOutputStream.write(log.getBytes());
                }

                logFile.delete();
                temporaryFile.renameTo(logFile);

                fileInputStream.close();
                fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
