package com.lightgeneration.kid_locker.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class LockKidApplication extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        LockKidApplication.mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return LockKidApplication.mContext;
    }

}
