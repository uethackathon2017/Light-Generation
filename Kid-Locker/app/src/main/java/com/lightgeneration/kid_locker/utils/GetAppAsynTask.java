package com.lightgeneration.kid_locker.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import com.lightgeneration.kid_locker.callbacks.GetResultListener;
import com.lightgeneration.kid_locker.models.AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class GetAppAsynTask extends AsyncTask<Void,Void,ArrayList<AppInfo>>{
    private Context context;
    private GetResultListener resultListener;
    private PackageManager packageManager;
    private boolean isGetAppLock;
    public GetAppAsynTask(Context context,GetResultListener resultListener,boolean isGetAppLock)
    {  this.isGetAppLock=isGetAppLock;
        this.context=context;
        packageManager=context.getPackageManager();
        this.resultListener=resultListener;
    }

    @Override
    protected void onPostExecute(ArrayList<AppInfo> appInfos) {
        super.onPostExecute(appInfos);
        resultListener.onResult(appInfos);
    }

    @Override
    protected ArrayList<AppInfo> doInBackground(Void... voids) {
        ArrayList<AppInfo> arr=new ArrayList<>();
        AppInfo info;
        List<PackageInfo> list=packageManager.getInstalledPackages(0);
        String packge=MySharedPreferences.getData();
        for (PackageInfo packageInfo:list)
        {
            if(isGetAppLock)
            {
                if(packageManager.getLaunchIntentForPackage(packageInfo.packageName.toString())!=null&&packge.contains(packageInfo.packageName))
                {
                    info=new AppInfo(packageInfo.applicationInfo.loadLabel(packageManager).toString(),packageInfo.packageName,packageInfo.applicationInfo.loadIcon(packageManager));
                    info.setClick(true);
                    arr.add(info);
//                Log.d("des:",packageInfo.applicationInfo.loadDescription(packageManager).toString());
                }
            }
            else {
                if(packageManager.getLaunchIntentForPackage(packageInfo.packageName.toString())!=null&&!packge.contains(packageInfo.packageName))
                {
                    info=new AppInfo(packageInfo.applicationInfo.loadLabel(packageManager).toString(),packageInfo.packageName,packageInfo.applicationInfo.loadIcon(packageManager));
                    arr.add(info);
//                Log.d("des:",packageInfo.applicationInfo.loadDescription(packageManager).toString());
                }
            }




        }
        return arr;
    }

}
