package com.jeramtough.jtlog.config;

/**
 * 既然使用这个配置工厂，那么CoverConfigFile一定不为null
 * <p>
 * Created on 2019-08-01 15:08
 * by @author JeramTough
 */
public class CoverLogConfigFactory extends DefalutLogConfigFactory
        implements LogConfigFactory {

    private CoverConfigHandler coverConfigHandler;


    public CoverLogConfigFactory(
            LogConfigDefaultValues logConfigDefaultValues) {
        super(logConfigDefaultValues);

        coverConfigHandler =
                new CoverConfigHandler(logConfigDefaultValues.decideCoverConfigFile(),
                        logConfigDefaultValues);
    }

    @Override
    public LogConfig getDefaultValueLogConfig(String contextName) {
        LogConfig logConfig = coverConfigHandler.getLogConfig(contextName);
        if (logConfig == null) {
            logConfig = super.getDefaultValueLogConfig(contextName);
            coverConfigHandler.putLogConfig(contextName, logConfig);
        }
        return logConfig;
    }

    @Override
    public LogConfig getLogConfigByAnnotation(String contextName,
                                              Class classWithLogConfigerAnnotation) {
        LogConfig logConfig = coverConfigHandler.getLogConfig(contextName);
        if (logConfig == null) {
            logConfig = super.getLogConfigByAnnotation(contextName,
                    classWithLogConfigerAnnotation);
            coverConfigHandler.putLogConfig(contextName, logConfig);
        }
        return logConfig;

    }
}
