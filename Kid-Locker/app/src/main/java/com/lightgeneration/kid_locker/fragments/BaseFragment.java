package com.lightgeneration.kid_locker.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class BaseFragment extends Fragment{
    protected View contentView;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        init();
        declareClick();
        onLoad();

    }
    protected void init()
    {

    };
    protected void declareClick()
    {

    };
    protected void findViews()
    {

    }
    protected void onLoad()
    {

    };

}
