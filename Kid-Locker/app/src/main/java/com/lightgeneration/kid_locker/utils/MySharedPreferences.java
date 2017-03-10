package com.lightgeneration.kid_locker.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class MySharedPreferences {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static MySharedPreferences data;
    private MySharedPreferences(Context context)
    {
        preferences=context.getSharedPreferences("applock",Context.MODE_PRIVATE);
        editor=preferences.edit();
    }
    public static MySharedPreferences getInstance(Context context)
    {
        if(data==null)
        {
            data=new MySharedPreferences(context);
        }
        return data;
    }
    public static void addData(String name)
    {

        String app=preferences.getString("app","");

        if(app.equals(""))
        {
            editor.putString("app",name);
            editor.commit();
        }
        else {
            editor.putString("app",app+","+name);
            editor.commit();
        }
    }
    public static String getData()
    {
        return preferences.getString("app","");
    }
}
