package com.xghls.util;

import android.util.Log;

/**
 * 用于控制Log打印, 通过设置LEVEL来控制需要打印出的日志
 * Created by fengyun on 2015/10/26.
 */
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;

    // TODO: 2016/5/31 关闭log
    public static final int LEVEL = VERBOSE;


    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            if (msg.length() > 3000) {
                for (int i = 0; i < msg.length(); i += 3000) {
                    if (i + 3000 < msg.length())
                        Log.e(tag + i, msg.substring(i, i + 3000));
                    else
                        Log.e(tag + i, msg.substring(i, msg.length()));
                }
            } else {
                Log.e(tag, msg);
            }
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            if (msg.length() > 3000) {
                for (int i = 0; i < msg.length(); i += 3000) {
                    if (i + 3000 < msg.length())
                        Log.e(tag + i, msg.substring(i, i + 3000));
                    else
                        Log.e(tag + i, msg.substring(i, msg.length()));
                }
            } else {
                Log.e(tag, msg);
            }
        }
    }
}
