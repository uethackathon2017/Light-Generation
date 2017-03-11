package com.lightgeneration.kid_locker.fragments.locks;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.adapter.AppAdapter;
import com.lightgeneration.kid_locker.callbacks.GetResultListener;
import com.lightgeneration.kid_locker.fragments.BaseFragment;
import com.lightgeneration.kid_locker.models.AppInfo;
import com.lightgeneration.kid_locker.utils.GetAppAsynTask;
import com.lightgeneration.kid_locker.utils.LockKidApplication;
import com.lightgeneration.kid_locker.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class AllAppFragment extends BaseAppLockFragment implements GetResultListener{

    @Override
    protected void declareClick() {
        super.declareClick();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MySharedPreferences.addData(arr.get(i).getPackageName());
                arr.remove(i);
                appAdapter.notifyDataSetChanged();
                onLoadData.loadData(1);
            }
        });
    }

    @Override
    protected void loadData() {
        super.loadData();
     new GetAppAsynTask(LockKidApplication.getAppContext(),this,false).execute();
    }

    @Override
    public void onResult(ArrayList<AppInfo> appInfos) {
        lv.setVisibility(View.VISIBLE);
        arr.clear();
        arr.addAll(appInfos);
        appAdapter.notifyDataSetChanged();

    }
}
