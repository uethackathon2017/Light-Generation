package com.lightgeneration.kid_locker.fragments.locks;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.adapter.AppAdapter;
import com.lightgeneration.kid_locker.fragments.BaseFragment;
import com.lightgeneration.kid_locker.models.AppInfo;
import com.lightgeneration.kid_locker.utils.LockKidApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class AllAppFragment extends BaseFragment{
    private ListView lv;
    private ArrayList<AppInfo> arr;
    private PackageManager packageManager;
    private AppAdapter appAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.all_app_fragment,container,false);
        return contentView;
    }

    @Override
    protected void findViews() {
        super.findViews();
        lv=(ListView)contentView.findViewById(R.id.lvAllApp);
    }

    @Override
    protected void declareClick() {
        super.declareClick();

    }

    @Override
    protected void init() {
        super.init();
        packageManager=LockKidApplication.getAppContext().getPackageManager();
        arr=new ArrayList<>();
        appAdapter=new AppAdapter(LockKidApplication.getAppContext(),R.layout.item_app,arr);
        lv.setAdapter(appAdapter);
        if(Build.VERSION.SDK_INT > 20)
        {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }
        loadData();
        appAdapter.notifyDataSetChanged();
    }
    private void loadData()
    {
        AppInfo info;
        List<PackageInfo> list=packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo:list)
        {

            if(packageManager.getLaunchIntentForPackage(packageInfo.packageName.toString())!=null)
            {
                info=new AppInfo(packageInfo.applicationInfo.loadLabel(packageManager).toString(),packageInfo.packageName,packageInfo.applicationInfo.loadIcon(packageManager));
                arr.add(info);
            }



        }
    }
}
