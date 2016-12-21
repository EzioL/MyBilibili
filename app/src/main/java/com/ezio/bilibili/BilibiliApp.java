package com.ezio.bilibili;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Author：Ezio on 2016/12/20.
 */
public class BilibiliApp extends Application {

    public static BilibiliApp mInstance;

    public static BilibiliApp getInstance() {

        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        init();
    }

    private void init() {
        Stetho.initializeWithDefaults(this);
    }
}
