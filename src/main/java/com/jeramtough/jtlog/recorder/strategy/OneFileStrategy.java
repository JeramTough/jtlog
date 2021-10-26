package com.jeramtough.jtlog.recorder.strategy;

import com.jeramtough.jtlog.util.FileUtil;

import java.io.File;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * <pre>
 * Created on 2021/8/19 下午5:53
 * by @author WeiBoWen
 * </pre>
 */
public class OneFileStrategy implements FileStrategy {

    private final File logFile;


    public OneFileStrategy(File logFile) {
        this.logFile = logFile;
    }

    @Override
    public File getFile() {
        FileUtil.createFileIfNotExists(this.logFile);
        return logFile;
    }

    @Override
    public File getErrorFile() {
        String fileName = "error" + logFile.getName();
        File errorLogFile = new File(this.logFile.getParentFile() + File.separator + fileName);
        FileUtil.createFileIfNotExists(errorLogFile);
        return errorLogFile;
    }

    @Override
    public void printComplete() {

    }


}
