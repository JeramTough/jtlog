package com.jeramtough.jtlog.recorder.strategy;

import com.jeramtough.jtlog.util.FileUtil;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * Created on 2021/8/19 下午5:53
 * by @author WeiBoWen
 * </pre>
 */
public class SortedDateFileStrategy implements FileStrategy {

    private File logsDirectory;
    private final String fileName;
    private final DateFormat dateFormat;

    public SortedDateFileStrategy(File logsDirectory, String fileName) {
        this.logsDirectory = logsDirectory;
        this.fileName = fileName;

        if (logsDirectory.getPath().startsWith("./")) {
            String path = logsDirectory.getPath().substring(2);
            this.logsDirectory = new File(path);
        }
        else if (logsDirectory.getPath().startsWith(".")) {
            String path = logsDirectory.getPath().substring(1);
            this.logsDirectory = new File(path);
        }

        dateFormat = new SimpleDateFormat("yyyyMMdd");
    }


    @Override
    public File getFile() {
        return getFile(this.fileName);
    }

    @Override
    public File getErrorFile() {
        return getFile("error_" + this.fileName);
    }

    @Override
    public void printComplete() {

    }

    //************

    private File getFile(String fileName) {
        String date = dateFormat.format(new Date());
        String directory = logsDirectory.getAbsolutePath() + File.separator + date;
        File directoryFile = new File(directory);
        if (!directoryFile.exists()) {
            boolean isOk = directoryFile.mkdirs();
            if (isOk) {
                File logFile = new File(
                        directoryFile.getAbsolutePath() + File.separator + fileName);
                FileUtil.createFileIfNotExists(logFile);
                return logFile;
            }
            else {
                return null;
            }
        }
        else {
            File logFile = new File(
                    directoryFile.getAbsolutePath() + File.separator + fileName);
            FileUtil.createFileIfNotExists(logFile);
            return logFile;
        }
    }

}
