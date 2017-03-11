package com.lightgeneration.kid_locker.fragments.locks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.adapter.ViewPagerAdapter;
import com.lightgeneration.kid_locker.callbacks.OnLoadData;
import com.lightgeneration.kid_locker.custom.CustomViewPager;
import com.lightgeneration.kid_locker.fragments.BaseFragment;

import java.util.ArrayList;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class LockFragment extends BaseFragment implements OnLoadData{
    private SegmentedGroup segmentedGroup;
    private CustomViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private ArrayList<Fragment> arrPage;
    private AllAppFragment allAppFragment;
    private AppLockFragment appLockFragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.lock_fragment,container,false);
        return contentView;
    }

    @Override
    protected void findViews() {
        super.findViews();
        segmentedGroup=(SegmentedGroup)contentView.findViewById(R.id.rdGroup);
        viewPager=(CustomViewPager)contentView.findViewById(R.id.view_pager);
    }

    @Override
    protected void init() {
        super.init();
        arrPage=new ArrayList<>();
        allAppFragment=new AllAppFragment();
        appLockFragment=new AppLockFragment();
        allAppFragment.setOnLoadData(this);
        appLockFragment.setOnLoadData(this);
        arrPage.add(allAppFragment);
        arrPage.add(appLockFragment);
        pagerAdapter=new ViewPagerAdapter(getChildFragmentManager(),arrPage);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void declareClick() {
        super.declareClick();
        segmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.rd_all_app:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rd_app_lock:
                        viewPager.setCurrentItem(1);
                        break;
                }
            }
        });
    }

    @Override
    public void loadData(int type) {
        switch (type)
        {
            case 0:
                allAppFragment.loadData();
                break;
            case 1:
                appLockFragment.loadData();
                break;
        }
    }
}
