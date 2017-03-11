package com.lightgeneration.kid_locker.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.service.LockService;
import com.lightgeneration.kid_locker.utils.Constant;
import com.lightgeneration.kid_locker.utils.MySharedPreferences;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class SettingAcitivity extends AppCompatActivity {
    private SwitchCompat switchCompat,switchOpenLockApp;
  private RelativeLayout changePass;
    private ImageView btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        findViews();
        init();
        declareClick();



    }
    private void init()
    {
        if (MySharedPreferences.isLockApp()) {
            switchCompat.setChecked(true);

        }
        if(MySharedPreferences.isLockMyApp())
        {
            switchOpenLockApp.setChecked(true);
            changePass.setVisibility(View.VISIBLE);
        }
    }
    private void findViews()
    {
        switchCompat = (SwitchCompat) findViewById(R.id.switch_btn);
        switchOpenLockApp=(SwitchCompat)findViewById(R.id.switch_btn_lock_app);
        changePass=(RelativeLayout)findViewById(R.id.rl_change_pass);
        btnBack=(ImageView)findViewById(R.id.btn_back_setting);
    }
    private void declareClick()
    {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
            }
        });
        switchOpenLockApp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    if(!MySharedPreferences.isLockMyApp())
                    {
                        MySharedPreferences.putBoolen(Constant.OPEN_LOCK_MY_APP,true);
                        startScreenPassWord();

                    }
                }
                else {
                    if(MySharedPreferences.isLockMyApp())
                    {
                        MySharedPreferences.putBoolen(Constant.OPEN_LOCK_MY_APP,false);
                    }
                }

            }
        });
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if(!MySharedPreferences.isLockApp())
                    {
                        startLockService();
                        MySharedPreferences.putBoolen(Constant.OPEN_LOCK_APP,true);
                        if (Build.VERSION.SDK_INT > 20) {
                            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                            startActivity(intent);
                        }
                    }


                } else {
                    if(MySharedPreferences.isLockApp())
                    {
                        MySharedPreferences.putBoolen(Constant.OPEN_LOCK_APP,false);
                        stopLockService();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    private void startLockService() {
        Intent intent = new Intent(SettingAcitivity.this, LockService.class);
        startService(intent);
    }

    private void stopLockService() {
        Intent intent = new Intent(SettingAcitivity.this, LockService.class);
        this.stopService(intent);
    }
    private void startScreenPassWord()
    {
      Intent intent=new Intent(SettingAcitivity.this,PassWordActivity.class);
        intent.putExtra("isInSetting",true);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
}
