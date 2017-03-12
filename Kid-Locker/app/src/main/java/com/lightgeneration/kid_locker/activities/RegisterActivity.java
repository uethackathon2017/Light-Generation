package com.lightgeneration.kid_locker.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.models.Account;
import com.lightgeneration.kid_locker.models.ResponceAccount;
import com.lightgeneration.kid_locker.networks.ApiClient;
import com.lightgeneration.kid_locker.networks.ApiClientUtils;
import com.lightgeneration.kid_locker.utils.Constant;
import com.lightgeneration.kid_locker.utils.LockKidApplication;
import com.lightgeneration.kid_locker.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class RegisterActivity extends AppCompatActivity{
    private EditText name,pass,user,age;
    private Button register;
    private ImageView btnback;
    private ProgressDialog progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        declareClicks();

    }
    private void findViews()
    {
        name=(EditText)findViewById(R.id.edit_name_baby);
        age=(EditText)findViewById(R.id.edit_age_baby);
        user=(EditText)findViewById(R.id.edit_username);
        pass=(EditText)findViewById(R.id.edit_password);
        register=(Button)findViewById(R.id.btnRegister);
        btnback=(ImageView)findViewById(R.id.btn_back_register);
    }
    private void declareClicks()
    {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRegister();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                finish();
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
            }
        });
    }
    private void doRegister()
    {
        if(user.getText().toString().isEmpty())
        {
            Toast.makeText(LockKidApplication.getAppContext(),"Bạn phải nhập tài khoản !",Toast.LENGTH_LONG).show();
            return;
        }
        if(pass.getText().toString().isEmpty())
        {
            Toast.makeText(LockKidApplication.getAppContext(),"Bạn phải nhập mật khẩu !",Toast.LENGTH_LONG).show();
            return;
        }
        if(name.getText().toString().isEmpty())
        {
            Toast.makeText(LockKidApplication.getAppContext(),"Bạn phải nhập tên của bé !",Toast.LENGTH_LONG).show();
            return;
        }
        if(age.getText().toString().isEmpty())
        {
            Toast.makeText(LockKidApplication.getAppContext(),"Bạn phải nhập tuổi của bé !",Toast.LENGTH_LONG).show();
            return;
        }
        progress=new ProgressDialog(RegisterActivity.this);
        progress.setMessage("Loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCanceledOnTouchOutside(false);
        progress.setCancelable(false);
        progress.show();
        Account account=new Account(user.getText().toString(),pass.getText().toString(),name.getText().toString(),Integer.valueOf(age.getText().toString()));
        ApiClientUtils.getApiClient().register(account).enqueue(new Callback<ResponceAccount>() {
            @Override
            public void onResponse(Call<ResponceAccount> call, Response<ResponceAccount> response) {
                Toast.makeText(LockKidApplication.getAppContext(),"Đăng kí thành công!!",Toast.LENGTH_LONG).show();
                closeKeyboard();
                MySharedPreferences.putInt(Constant.AGE_BABY,Integer.valueOf(age.getText().toString()));
                MySharedPreferences.putString(Constant.NAME_BABY,name.getText().toString());
                progress.dismiss();
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
        closeKeyboard();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
