package com.lightgeneration.kid_locker.fragments.locks;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.adapter.AppAdapter;
import com.lightgeneration.kid_locker.callbacks.OnLoadData;
import com.lightgeneration.kid_locker.fragments.BaseFragment;
import com.lightgeneration.kid_locker.models.AppInfo;
import com.lightgeneration.kid_locker.utils.LockKidApplication;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class BaseAppLockFragment extends BaseFragment{
    protected ListView lv;
    protected ArrayList<AppInfo> arr;
    protected OnLoadData onLoadData;
    protected AppAdapter appAdapter;
    protected TextView textView;
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
        textView=(TextView)contentView.findViewById(R.id.tv_lock);
    }
    @Override
    protected void init() {
        super.init();
        arr = new ArrayList<>();
        appAdapter = new AppAdapter(LockKidApplication.getAppContext(), R.layout.item_app, arr);
        lv.setAdapter(appAdapter);
        loadData();
    }

    public void setOnLoadData(OnLoadData onLoadData) {
        this.onLoadData = onLoadData;
    }

    protected void loadData()
    {

    }
}
