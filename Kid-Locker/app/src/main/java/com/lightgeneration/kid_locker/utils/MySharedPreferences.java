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
    public static void clearAppOn()
    {
        getEditor().remove(Constant.ON_APP).commit();
    }
    public static void addData(String name)
    {

        String app=getPreferences().getString(Constant.ALL_APP_LOCKED,"");

        if(app.equals(""))
        {
            getEditor().putString(Constant.ALL_APP_LOCKED,name).commit();
        }
        else {

            getEditor().putString(Constant.ALL_APP_LOCKED,app+","+name).commit();
        }
    }
    public static void editData(String data)
    {
        getEditor().putString(Constant.ALL_APP_LOCKED,data).commit();
    }
    public static String getData()
    {
        return getPreferences().getString(Constant.ALL_APP_LOCKED,"@@");
    }
    public static void putBoolen(String key,boolean value)
    {
        getEditor().putBoolean(key,value).commit();
    }
    public static boolean isLockApp()
    {
        return getPreferences().getBoolean(Constant.OPEN_LOCK_APP,false);
    }
    public static boolean isLockMyApp()
    {
        return getPreferences().getBoolean(Constant.OPEN_LOCK_MY_APP,false);
    }
    public static void putInt(String key,int value)
    {
        getEditor().putInt(key,value).commit();
    }
    public static int getInt(String key,int value)
    {
        return getPreferences().getInt(key,value);
    }
    public static boolean getBoolean(String key)
    {
        return getPreferences().getBoolean(key,false);
    }
    public static boolean isActive()
    {
        return getPreferences().getBoolean(Constant.ACTIVE,false);
    }
}
