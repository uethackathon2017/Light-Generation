package com.lightgeneration.kid_locker.fragments.subcribe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.adapter.ViewPagerAdapter;
import com.lightgeneration.kid_locker.fragments.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class SubCribeFragment extends BaseFragment{
    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private ArrayList<Fragment> arrPage;
    private CountFragment countFragment;
    private RecognizeFragment recognizeFragment;
    private UpDownFragment upDownFragment;
    private LeftRightFragment leftRightFragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       contentView=inflater.inflate(R.layout.sub_cribe_fragment,container,false);
        return contentView;
    }

    @Override
    protected void init() {
        super.init();
        arrPage=new ArrayList<>();
        countFragment=new CountFragment();
        countFragment.setTitle("Biểu Đồ Tập Đếm");
        recognizeFragment=new RecognizeFragment();
        recognizeFragment.setTitle("Biều Đồ Nhận Biết");
        upDownFragment=new UpDownFragment();
        upDownFragment.setTitle("Biều Đồ Trên Dưới");
        leftRightFragment=new LeftRightFragment();
        leftRightFragment.setTitle("Biểu Đồ Trái Phải");
        arrPage.add(recognizeFragment);
        arrPage.add(leftRightFragment);
        arrPage.add(upDownFragment);
        arrPage.add(countFragment);
        pagerAdapter=new ViewPagerAdapter(getChildFragmentManager(),arrPage);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void findViews() {
        super.findViews();
        viewPager=(ViewPager)contentView.findViewById(R.id.view_pager_graph);

    }
}
