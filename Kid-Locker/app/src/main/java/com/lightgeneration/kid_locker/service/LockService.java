package com.lightgeneration.kid_locker.service;

import android.app.ActivityManager;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lightgeneration.kid_locker.activities.LockScreenActivity;
import com.lightgeneration.kid_locker.activities.PassWordActivity;
import com.lightgeneration.kid_locker.utils.Constant;
import com.lightgeneration.kid_locker.utils.MySharedPreferences;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ngoc Sang on 3/9/2017.
 */

public class LockService extends Service {
    private static Timer timer = new Timer();
    private ActivityManager activityManager = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        startService();

    }

    private void startService()
    {
        timer.scheduleAtFixedRate(new mainTask(), 0, 500);
    }
    private class mainTask extends TimerTask
    {
        public void run()
        {
            toastHandler.sendEmptyMessage(0);
        }
    }

    private final Handler toastHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(Build.VERSION.SDK_INT > 20){
                String currentApp="";
               currentApp=getLollipopFGAppPackageName(getApplicationContext());
                Log.e("name",currentApp);
                Log.e("all",MySharedPreferences.getData());
                if(currentApp.equals("com.sec.android.app.launcher")||currentApp.equals("@@"))
                {
                    MySharedPreferences.putString(Constant.ON_APP,"");
                }
                if(MySharedPreferences.getData().contains("@@")&&currentApp.contains("@@"))
                {
                    return;
                }

                if(MySharedPreferences.getData().contains(currentApp))
                {

                    String str=MySharedPreferences.getString(Constant.ON_APP,"");
                    if(str.equals(""))
                    {
                        Intent i = new Intent(LockService.this, LockScreenActivity.class);
                        i.putExtra("namepackage",currentApp);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       startActivity(i);
                    }

                }
//                 if(currentApp.equals("com.lightgeneration.kid_locker"))
//                {
//                    if(MySharedPreferences.isLockMyApp())
//                    {
//
//                        if(MySharedPreferences.getBoolean(Constant.ON_TEST))
//                        {
//                            return;
//                        }
//                        else {
//                            if(MySharedPreferences.isActive())
//                            {
//                                return;
//                            }
//                            else {
//                                Intent i = new Intent(LockService.this, PassWordActivity.class);
//                                i.putExtra("isInSetting",false);
//                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(i);
//                            }
//
//                        }
//
//                    }
//
//                }
            }
            else{
                String mpackageName = activityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
                Log.e("name",mpackageName);
                if(MySharedPreferences.getData().contains(mpackageName))
                {
                    Intent i = new Intent(LockService.this, LockScreenActivity.class);

                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
//                else if(mpackageName.contains("com.lightgeneration.kid_locker")&&MySharedPreferences.isLockMyApp()&&!MySharedPreferences.isActive())
//                {
//                    Intent i = new Intent(LockService.this, PassWordActivity.class);
//                    i.putExtra("isInSetting",false);
//                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(i);
//                }

            }
        }
        }

        ;

    private static String getLollipopFGAppPackageName(Context ctx) {

        try {
            UsageStatsManager usageStatsManager = (UsageStatsManager) ctx.getSystemService(Context.USAGE_STATS_SERVICE);
            long milliSecs = 60 * 1000;
            Date date = new Date();
            List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, date.getTime() - milliSecs, date.getTime());
            if (queryUsageStats.size() > 0) {
                Log.i("LPU", "queryUsageStats size: " + queryUsageStats.size());
            }
            long recentTime = 0;
            String recentPkg = "@@";
            for (int i = 0; i < queryUsageStats.size(); i++) {
                UsageStats stats = queryUsageStats.get(i);
                if (i == 0 && !"org.pervacio.pvadiag".equals(stats.getPackageName())) {
                    Log.i("LPU", "PackageName: " + stats.getPackageName() + " " + stats.getLastTimeStamp());
                }
                if (stats.getLastTimeStamp() > recentTime) {
                    recentTime = stats.getLastTimeStamp();
                    recentPkg = stats.getPackageName();
                }
            }
            return recentPkg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "@@";
    }


}

