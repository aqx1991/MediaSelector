package com.lucky_aqx.media.utils;

import android.util.Log;

/**
 * 描述: Log管理类
 * 作者: James
 * 日期: 2019/4/17 11:08
 * 类名: LogUtils
 */
public class LogUtils {

    private static final String TAG = "LogUtils";

    private static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化

    public static void setIsDebug(boolean debug) {
        isDebug = debug;
    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, isEmpry(msg));
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, isEmpry(msg));
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, isEmpry(msg));
    }

    public static void e(String msg, Exception e) {
        if (isDebug)
            Log.w(TAG, isEmpry(msg), e);
    }

    public static void e(String tag, String msg, Exception e) {
        if (isDebug)
            Log.e(tag, isEmpry(msg));
        if (null != e)
            e.printStackTrace();
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, isEmpry(msg));
    }

    public static void w(String msg) {
        if (isDebug)
            Log.w(TAG, isEmpry(msg));
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, isEmpry(msg));
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, isEmpry(msg));
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, isEmpry(msg));
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, isEmpry(msg));
    }

    public static void w(String tag, String msg) {
        if (isDebug)
            Log.w(tag, isEmpry(msg));
    }

    /**
     * 分段打印出较长log文本
     *
     * @param logContent 打印文本
     * @param showLength 规定每段显示的长度（AndroidStudio控制台打印log的最大信息量大小为4k）
     * @param tag        打印log的标记
     */
    public static void showLargeLog(String logContent, int showLength,
                                    String tag) {
        if (!isDebug)
            return;
        if (!isString(logContent))
            return;

        if (logContent.length() > showLength) {
            String show = logContent.substring(0, showLength);
            e(tag, show);
            /* 剩余的字符串如果大于规定显示的长度，截取剩余字符串进行递归，否则打印结果 */
            if ((logContent.length() - showLength) > showLength) {
                String partLog = logContent.substring(showLength,
                        logContent.length());
                showLargeLog(partLog, showLength, tag);
            } else {
                String printLog = logContent.substring(showLength,
                        logContent.length());
                e(tag, printLog);
            }

        } else {
            e(tag, logContent);
        }
    }

    /**
     * 分段打印出较长log文本
     *
     * @param logContent 打印文本
     * @param showLength 规定每段显示的长度（AndroidStudio控制台打印log的最大信息量大小为4k）
     * @param tag        打印log的标记
     */
    public static void showLargeLog(LogType logType, String logContent, int showLength,
                                    String tag) {
        if (!isDebug)
            return;
        if (!isString(logContent))
            return;

        if (logContent.length() > showLength) {
            String show = logContent.substring(0, showLength);
            switch (logType) {
                case LOG_I:
                    i(tag, show);
                    break;
                case LOG_D:
                    d(tag, show);
                    break;
                case LOG_E:
                    e(tag, show);
                case LOG_V:
                    v(tag, show);
                case LOG_W:
                    w(tag, show);
                default:
                    break;
            }
            /* 剩余的字符串如果大于规定显示的长度，截取剩余字符串进行递归，否则打印结果 */
            if ((logContent.length() - showLength) > showLength) {
                String partLog = logContent.substring(showLength, logContent.length());
                showLargeLog(logType, partLog, showLength, tag);
            } else {
                String printLog = logContent.substring(showLength, logContent.length());
                switch (logType) {
                    case LOG_I:
                        i(tag, printLog);
                        break;
                    case LOG_D:
                        d(tag, printLog);
                        break;
                    case LOG_E:
                        e(tag, printLog);
                    case LOG_V:
                        v(tag, printLog);
                    case LOG_W:
                        w(tag, printLog);
                    default:
                        break;
                }
            }

        } else {
            switch (logType) {
                case LOG_I:
                    i(tag, logContent);
                    break;
                case LOG_D:
                    d(tag, logContent);
                    break;
                case LOG_E:
                    e(tag, logContent);
                case LOG_V:
                    v(tag, logContent);
                case LOG_W:
                    w(tag, logContent);
                default:
                    break;
            }
        }
    }

    public enum LogType {
        LOG_I, LOG_D, LOG_E, LOG_V, LOG_W
    }

    /**
     * 判断s字符串是String
     *
     * @param s
     * @return
     */
    public static boolean isString(String s) {
        if (null == s) {
            return false;
        } else if (s.equals("")) {
            return false;
        } else if (s.equals("null")) {
            return false;
        } else return s.length() > 0;
    }

    /**
     * 判断s字符串是否为空
     *
     * @param s
     * @return
     */
    private static String isEmpry(String s) {
        if (null == s) {
            return "";
        } else if (s.equals("")) {
            return "";
        } else if (s.equals("null")) {
            return "";
        } else {
            return s;
        }
    }
}
