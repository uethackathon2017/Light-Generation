package com.lightgeneration.kid_locker.fragments.locks;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class AppLockFragment extends BaseAppLockFragment implements GetResultListener{

    @Override
    protected void declareClick() {
        super.declareClick();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    @Override
    protected void loadData() {
        super.loadData();
        new GetAppAsynTask(LockKidApplication.getAppContext(),this,true).execute();

    }

    @Override
    public void onResult(ArrayList<AppInfo> appInfos) {
        arr.addAll(appInfos);
        appAdapter.notifyDataSetChanged();
    }
}
