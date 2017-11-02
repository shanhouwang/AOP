package com.devin.tool_aop;

import android.app.Application;

/**
 * Created by Devin on 2017/11/2.
 *
 * @author Devin
 */
public class AopUtils {

    public static Application mApplication;
    public static boolean DEBUG;

    /**
     * 初始化
     *
     * @param app
     */
    public static void init(Application app, boolean debug) {
        mApplication = app;
        DEBUG = debug;
    }
}
