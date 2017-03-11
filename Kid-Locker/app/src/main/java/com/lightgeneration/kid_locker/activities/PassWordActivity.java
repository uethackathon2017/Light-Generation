package com.lightgeneration.kid_locker.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lightgeneration.kid_locker.R;
import com.takwolf.android.lock9.Lock9View;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class PassWordActivity extends AppCompatActivity{
    private ImageView btnBack;
    private Lock9View lock9View;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_word_activity);
        findViews();
        init();
        declareClick();
    }
    private void init()
    {

    }
    private void findViews()
    {
        btnBack=(ImageView)findViewById(R.id.btn_back_pass_word);
        lock9View=(Lock9View)findViewById(R.id.lock_view);
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
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

}
