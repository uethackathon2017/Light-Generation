package com.lightgeneration.kid_locker.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.models.ResponceAccount;
import com.lightgeneration.kid_locker.networks.ApiClientUtils;
import com.lightgeneration.kid_locker.utils.Constant;
import com.lightgeneration.kid_locker.utils.LockKidApplication;
import com.lightgeneration.kid_locker.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ngoc Sang on 3/12/2017.
 */

public class EditInfoAcitivity extends AppCompatActivity{
    private ImageView btnBack;
    private Button edit;
    private EditText name,age;
    private ProgressDialog progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info_activity);
        findViews();
        init();
        declareClicks();

    }
    private void findViews()
    {
    name=(EditText)findViewById(R.id.edit_username_edit);
        age=(EditText)findViewById(R.id.edit_password_edit);
        btnBack=(ImageView)findViewById(R.id.btn_back_edit);
        edit=(Button)findViewById(R.id.btnEdit);
    }
    private void init()
    {
         String  nameBaby=getIntent().getStringExtra("nameBaby");
         int ageBaby=getIntent().getIntExtra("ageBaby",0);
        name.setText(nameBaby);
        age.setText(ageBaby+"");

    }
    private void declareClicks()
    {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
            }
        });
         edit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 doEdit();
             }
         });
    }
    private void doEdit()
    {
        if(name.getText().toString().isEmpty())
        {
            Toast.makeText(LockKidApplication.getAppContext(),"Bạn Phải Nhập Tên Bé!",Toast.LENGTH_LONG).show();
            return;
        }
        if(age.getText().toString().isEmpty())
        {
            Toast.makeText(LockKidApplication.getAppContext(),"Bạn Phải Nhập Tuổi Bé!",Toast.LENGTH_LONG).show();
            return;
        }
        progress=new ProgressDialog(EditInfoAcitivity.this);
        progress.setMessage("Loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCanceledOnTouchOutside(false);
        progress.setCancelable(false);
        progress.show();
        final String nameBaby=name.getText().toString();
        int ageBaby=Integer.valueOf(age.getText().toString());
        ApiClientUtils.getApiClient().editInfo(nameBaby,ageBaby).enqueue(new Callback<ResponceAccount>() {
            @Override
            public void onResponse(Call<ResponceAccount> call, Response<ResponceAccount> response) {
                progress.dismiss();
                MySharedPreferences.putInt(Constant.AGE_BABY,Integer.valueOf(age.getText().toString()));
                MySharedPreferences.putString(Constant.NAME_BABY,name.getText().toString());
                finish();
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

            }

            @Override
            public void onFailure(Call<ResponceAccount> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
}
