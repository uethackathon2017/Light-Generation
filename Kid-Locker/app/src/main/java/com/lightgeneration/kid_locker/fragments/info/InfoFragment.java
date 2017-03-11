package com.lightgeneration.kid_locker.fragments.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.activities.RegisterInfoBabyActivity;
import com.lightgeneration.kid_locker.fragments.BaseFragment;

/**
 * Created by PhamVanLong on 3/10/2017.
 */

public class InfoFragment extends BaseFragment implements View.OnClickListener {
    private Button btnSignUp;
    private Button btnUpdate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.info_fragment, container, false);
        return contentView;
    }

    @Override
    protected void findViews() {
        super.findViews();
        btnSignUp = (Button) contentView.findViewById(R.id.btn_sign_up);
        btnUpdate = (Button) contentView.findViewById(R.id.btn_update);


    }


    @Override
    protected void declareClick() {
        super.declareClick();
        btnSignUp.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up: {
                registerInfoBaby();
                break;
            }

            case R.id.btn_update: {
                break;
            }

            default: {
                break;
            }
        }
    }

    private void registerInfoBaby() {
        Intent intent = new Intent(getActivity(), RegisterInfoBabyActivity.class);
        startActivity(intent);
    }
}
