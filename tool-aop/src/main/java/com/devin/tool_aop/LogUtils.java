package com.devin.tool_aop;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Devin on 17/3/23.
 */
public class LogUtils {

    public static final String TAG = LogUtils.class.getSimpleName();
    public static final boolean DEBUG = AopUtils.DEBUG;

    public static void d(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            Log.i(tag, msg);
        }
    }
}