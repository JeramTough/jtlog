package com.jeramtough.jtlog.style;

import com.jeramtough.jtlog.context.LogContext;

public class PrintStyleManager {
    private static volatile InfoPrintStyle infoPrintStyle;
    private static volatile WarnPrintStyle warnPrintStyle;
    private static volatile ErrorPrintStyle errorPrintStyle;
    private static volatile DebugPrintStyle debugPrintStyle;
    private static volatile PrintlnPrintStyle printlnPrintStyle;
    private static volatile ArrivePrintStyle arrivePrintStyle;
    private static volatile VerbosePrintStyle verbosePrintStyle;
    private static volatile DefaultPrintStyle defaultPrintStyle;

    public static PrintStyle getPrintlnPrintStyle() {
        if (printlnPrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (printlnPrintStyle == null) {
                    printlnPrintStyle = new PrintlnPrintStyle(null);
                }
            }
        }
        return printlnPrintStyle;
    }

    public static PrintStyle getInfoPrintStyle() {
        if (infoPrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (infoPrintStyle == null) {
                    infoPrintStyle = new InfoPrintStyle(null);
                }
            }
        }
        return infoPrintStyle;
    }

    public static PrintStyle getWarnPrintStyle() {
        if (warnPrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (warnPrintStyle == null) {
                    warnPrintStyle = new WarnPrintStyle(null);
                }
            }
        }
        return warnPrintStyle;
    }

    public static PrintStyle getErrorPrintStyle() {
        if (errorPrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (errorPrintStyle == null) {
                    errorPrintStyle = new ErrorPrintStyle(null);
                }
            }
        }
        return errorPrintStyle;
    }

    public static PrintStyle getDebugPrintStyle() {
        if (debugPrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (debugPrintStyle == null) {
                    debugPrintStyle = new DebugPrintStyle(null);
                }
            }
        }
        return debugPrintStyle;
    }

    public static ArrivePrintStyle getArrivePrintStyle() {
        if (arrivePrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (arrivePrintStyle == null) {
                    arrivePrintStyle = new ArrivePrintStyle(null);
                }
            }
        }
        return arrivePrintStyle;
    }

    public static VerbosePrintStyle getVerbosePrintStyle() {
        if (verbosePrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (verbosePrintStyle == null) {
                    verbosePrintStyle = new VerbosePrintStyle(null);
                }
            }
        }
        return verbosePrintStyle;
    }

    public static DefaultPrintStyle getDefaultPrintStyle(LogContext logContext) {
        if (defaultPrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (defaultPrintStyle == null) {
                    defaultPrintStyle = new DefaultPrintStyle(
                            logContext.getLogConfig().getLogHeaderFormat());
                }
            }
        }
        return defaultPrintStyle;
    }
}
