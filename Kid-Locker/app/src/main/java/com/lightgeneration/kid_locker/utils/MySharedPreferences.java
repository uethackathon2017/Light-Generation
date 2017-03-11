package com.lightgeneration.kid_locker.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class MySharedPreferences {
    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor editor;
    private static MySharedPreferences data;

    private static SharedPreferences getPreferences() {
        if (mPreferences == null) {
            mPreferences = LockKidApplication.getAppContext().getSharedPreferences(Constant.KID_LOCK,
                    Activity.MODE_PRIVATE);
        }
        return mPreferences;
    }
   private static SharedPreferences.Editor getEditor()
   {
       return getPreferences().edit();
   }
    public static String getString(String key,String valueDefault)
    {
        return getPreferences().getString(key,valueDefault);
    }
    public static void putString(String key,String value)
    {
        getEditor().putString(key,value).commit();
    }
    public static void addData(String name)
    {

        String app=getPreferences().getString("app","");

        if(app.equals(""))
        {
            getEditor().putString("app",name).commit();
        }
        else {

            getEditor().putString("app",app+","+name).commit();
        }
    }
    public static String getData()
    {
        return getPreferences().getString("app","@@");
    }
}
