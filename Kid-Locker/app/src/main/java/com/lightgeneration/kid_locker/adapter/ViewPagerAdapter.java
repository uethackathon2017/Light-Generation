package com.lightgeneration.kid_locker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> arr;
    public ViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> arr) {
        super(fm);
        this.arr=arr;
    }

    @Override
    public Fragment getItem(int position) {
        return arr.get(position);
    }

    @Override
    public int getCount() {
        return arr.size();
    }
}
