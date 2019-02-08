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

    public static PrintStyle getPrintlnPrintStyle(LogContext logContext) {
        if (printlnPrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (printlnPrintStyle == null) {
                    printlnPrintStyle = new PrintlnPrintStyle(logContext);
                }
            }
        }
        return printlnPrintStyle;
    }

    public static PrintStyle getInfoPrintStyle(LogContext logContext) {
        if (infoPrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (infoPrintStyle == null) {
                    infoPrintStyle = new InfoPrintStyle(logContext);
                }
            }
        }
        return infoPrintStyle;
    }

    public static PrintStyle getWarnPrintStyle(LogContext logContext) {
        if (warnPrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (warnPrintStyle == null) {
                    warnPrintStyle = new WarnPrintStyle(logContext);
                }
            }
        }
        return warnPrintStyle;
    }

    public static PrintStyle getErrorPrintStyle(LogContext logContext) {
        if (errorPrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (errorPrintStyle == null) {
                    errorPrintStyle = new ErrorPrintStyle(logContext);
                }
            }
        }
        return errorPrintStyle;
    }

    public static PrintStyle getDebugPrintStyle(LogContext logContext) {
        if (debugPrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (debugPrintStyle == null) {
                    debugPrintStyle = new DebugPrintStyle(logContext);
                }
            }
        }
        return debugPrintStyle;
    }

    public static ArrivePrintStyle getArrivePrintStyle(LogContext logContext) {
        if (arrivePrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (arrivePrintStyle == null) {
                    arrivePrintStyle = new ArrivePrintStyle(logContext);
                }
            }
        }
        return arrivePrintStyle;
    }

    public static VerbosePrintStyle getVerbosePrintStyle(LogContext logContext) {
        if (verbosePrintStyle == null) {
            synchronized (PrintStyleManager.class) {
                if (verbosePrintStyle == null) {
                    verbosePrintStyle = new VerbosePrintStyle(logContext);
                }
            }
        }
        return verbosePrintStyle;
    }

}
