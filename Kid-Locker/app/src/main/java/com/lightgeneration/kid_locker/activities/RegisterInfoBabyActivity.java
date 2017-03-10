package com.lightgeneration.kid_locker.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lightgeneration.kid_locker.R;

/**
 * Created by PhamVanLong on 3/10/2017.
 */

public class RegisterInfoBabyActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponent();
    }

    private void initComponent() {
        findViewById(R.id.btn_arrow_left).setOnClickListener(this);
        findViewById(R.id.btn_register_info_baby).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_arrow_left: {
                finish();
                break;
            }

            case R.id.btn_register_info_baby: {
                registerInfoBaby();
                finish();
                break;
            }

            default: {
                break;
            }
        }
    }

    private void registerInfoBaby() {

    }
}
