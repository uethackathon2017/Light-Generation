package com.lightgeneration.kid_locker.utils;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class Utils {
    public static String formatString(String str)
    {
        if(str.startsWith(","))
        {
            str.replaceFirst(",","");
        }
        if(str.endsWith(",")==true)
        {
            str=str.substring(0,str.length()-1);
        }
        return str;
    }
}
