package com.jeramtough.jtlog.config;

import com.jeramtough.jtlog.header.LogHeader;
import com.jeramtough.jtlog.level.LogLevel;
import com.jeramtough.jtlog.util.FileUtil;
import com.sun.istack.internal.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created on 2019-08-01 12:35
 * by @author JeramTough
 */
public class CoverConfigHandler {

    private File coverFile;
    private LogConfigDefaultValues logConfigDefaultValues;
    private Map<String, LogConfig> configMap;

    public CoverConfigHandler(@NotNull File coverFile,
                              LogConfigDefaultValues logConfigDefaultValues) {
        this.coverFile = coverFile;
        this.logConfigDefaultValues = logConfigDefaultValues;
        configMap = new HashMap<>();
        init();
    }

    protected void init() {
        if (!coverFile.exists()) {
            FileUtil.createFile(coverFile);
        }
        else {
            JSONObject jsonObject = parseJSONObject(coverFile);

            Objects.requireNonNull(jsonObject).keySet().forEach(key -> {
                JSONObject jsonLogConfig = jsonObject.getJSONObject(key);
                configMap.put(key, toLogConfig(jsonLogConfig));
            });
        }
    }

    public LogConfig getLogConfig(String contextName) {
        return configMap.get(contextName);
    }

    public void putLogConfig(String contextName, LogConfig logConfig) {
        configMap.put(contextName, logConfig);
        writeJsonConfigToFile(coverFile, configMap);
    }

    //**************************************

    private synchronized JSONObject parseJSONObject(File jsonFile) {
        try {
            FileReader fileReader = new FileReader(jsonFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader.lines().forEach(stringBuilder::append);
            bufferedReader.close();
            return new JSONObject(stringBuilder.toString());

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private LogConfig toLogConfig(JSONObject jsonObject) {
        LogConfig logConfig = new LogConfig();

        String fieldName = "wrapCount";
        if (jsonObject.opt(fieldName) == null) {
            logConfig.setWrapCount(logConfigDefaultValues.decideWrapCount());
        }
        else {
            logConfig.setWrapCount(jsonObject.getNumber(fieldName).intValue());
        }

        fieldName = "logHeaders";
        if (jsonObject.opt(fieldName) == null) {
            logConfig.setLogHeaders(logConfigDefaultValues.decideLogHeaders());
        }
        else {
            JSONArray logHeaderJsonArray = jsonObject.getJSONArray(fieldName);
            LogHeader[] logHeaders = new LogHeader[logHeaderJsonArray.length()];
            for (int i = 0; i < logHeaderJsonArray.length(); i++) {
                logHeaders[i] = LogHeader.valueOf(logHeaderJsonArray.getString(i));
            }
            logConfig.setLogHeaders(logHeaders);
        }

        fieldName = "minVisibleLevel";
        if (jsonObject.opt(fieldName) == null) {
            logConfig.setMinVisibleLevel(logConfigDefaultValues.decideMinVisibleLevel());
        }
        else {
            logConfig.setMinVisibleLevel(
                    LogLevel.valueOf(jsonObject.getString(fieldName)));
        }

        fieldName = "maxLengthOfRow";
        if (jsonObject.opt(fieldName) == null) {
            logConfig.setMaxLengthOfRow(logConfigDefaultValues.decideMaxLengthOfRow());
        }
        else {
            logConfig.setMaxLengthOfRow(jsonObject.getInt(fieldName));
        }


        fieldName = "dateFormat";
        if (jsonObject.opt(fieldName) == null) {
            logConfig.setDateFormat(logConfigDefaultValues.decideDateFormat());
        }
        else {
            logConfig.setDateFormat(jsonObject.getString(fieldName));
        }

        fieldName = "stackTraceOffset";
        if (jsonObject.opt(fieldName) == null) {
            logConfig.setStackTraceOffset(logConfigDefaultValues.decideStackTraceOffset());
        }
        else {
            logConfig.setStackTraceOffset(jsonObject.getInt(fieldName));
        }

        fieldName = "enabled";
        if (jsonObject.opt(fieldName) == null) {
            logConfig.setEnabled(logConfigDefaultValues.decideIsEnabled());
        }
        else {
            logConfig.setEnabled(jsonObject.getBoolean(fieldName));
        }


        fieldName = "usedJtloggerApi";
        if (jsonObject.opt(fieldName) == null) {
            logConfig.setUsedJtloggerApi(logConfigDefaultValues.decideIsUsedJtloggerApi());
        }
        else {
            logConfig.setUsedJtloggerApi(jsonObject.getBoolean(fieldName));
        }

        return logConfig;
    }

    private synchronized void writeJsonConfigToFile(File file,
                                                    Map<String, LogConfig> logConfigMap) {
        JSONObject jsonObject = new JSONObject(logConfigMap);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(jsonObject.toString(2));

            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
