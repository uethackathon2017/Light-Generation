package com.lightgeneration.kid_locker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.utils.Constant;
import com.lightgeneration.kid_locker.utils.LockKidApplication;
import com.lightgeneration.kid_locker.utils.MySharedPreferences;
import com.takwolf.android.lock9.Lock9View;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class PassWordActivity extends AppCompatActivity {
    private ImageView btnBack;
    private Lock9View lock9View;
    private boolean isInSetting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_word_activity);
        findViews();
        init();
        declareClick();
    }

    private void init() {
        isInSetting = getIntent().getBooleanExtra("isInSetting", false);
        if (isInSetting) {

        } else {
            btnBack.setVisibility(View.GONE);
        }
    }

    private void findViews() {
        btnBack = (ImageView) findViewById(R.id.btn_back_pass_word);
        lock9View = (Lock9View) findViewById(R.id.lock_view);
    }

    private void declareClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
            }
        });
        lock9View.setCallBack(new Lock9View.CallBack() {
            @Override
            public void onFinish(String password) {
                if (isInSetting) {
                    MySharedPreferences.putString(Constant.PASS_WORD, password);
                } else {
                    if (!password.equals(MySharedPreferences.getString(Constant.PASS_WORD, ""))) {
                        Toast.makeText(LockKidApplication.getAppContext(), "Sai mật khẩu !!!", Toast.LENGTH_LONG).show();
                    } else {
                        MySharedPreferences.putBoolen(Constant.ACTIVE, true);
                        finish();
                    }

                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isInSetting) {
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        } else {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        }
    }



}
