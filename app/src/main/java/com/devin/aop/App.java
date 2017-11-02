package com.devin.aop;

import android.app.Application;

import com.devin.tool_aop.AopUtils;

/**
 * Created by Devin on 2017/11/2.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AopUtils.init(this, true);
    }
}
