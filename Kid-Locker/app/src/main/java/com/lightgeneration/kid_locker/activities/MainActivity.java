package com.lightgeneration.kid_locker.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.models.ResponceLogin;
import com.lightgeneration.kid_locker.models.User;
import com.lightgeneration.kid_locker.networks.ApiClientUtils;
import com.lightgeneration.kid_locker.utils.Constant;
import com.lightgeneration.kid_locker.utils.DialogUtil;
import com.lightgeneration.kid_locker.utils.LockKidApplication;
import com.lightgeneration.kid_locker.utils.MySharedPreferences;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin,btnRegister;
    private EditText userName,passWord;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       if(MySharedPreferences.getBoolean(Constant.IS_LOGIN))
       {
           Intent intent = new Intent(this, ComponentActivity.class);
           startActivity(intent);
           finish();
       }
        else {
           setContentView(R.layout.activity_main);
           findViews();
           init();
           declareClick();
       }
    }
    private void init()
    {

    }
    private void declareClick()
    {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent(LockKidApplication.getAppContext(),RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  doLogin();
            }
        });
    }
    private void doLogin()
    {
        if(userName.getText().toString().isEmpty())
        {
            Toast.makeText(LockKidApplication.getAppContext(),"Bạn phải nhập tài khoản !",Toast.LENGTH_LONG).show();
            return;
        }
        if(passWord.getText().toString().isEmpty())
        {
            Toast.makeText(LockKidApplication.getAppContext(),"Bạn phải nhập mật khẩu!",Toast.LENGTH_LONG).show();
            return;
        }
        closeKeyboard();
        progress=new ProgressDialog(MainActivity.this);
        progress.setMessage("Loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCanceledOnTouchOutside(false);
        progress.setCancelable(false);
        progress.show();
        User user=new User(userName.getText().toString(),passWord.getText().toString());
        ApiClientUtils.getApiClient().login(user).enqueue(new Callback<ResponceLogin>() {
            @Override
            public void onResponse(Call<ResponceLogin> call, retrofit2.Response<ResponceLogin> response) {
              //  DialogUtil.getInstance().dismissLoading();
                ResponceLogin login=response.body();
                MySharedPreferences.putString(Constant.USER_NAME,login.getUsername());
                MySharedPreferences.putBoolen(Constant.IS_LOGIN,true);
                closeKeyboard();
                progress.dismiss();
                Intent intent = new Intent(LockKidApplication.getAppContext(), ComponentActivity.class);
                startActivity(intent);
                  finish();

            }

            @Override
            public void onFailure(Call<ResponceLogin> call, Throwable t) {

            }
        });

    }
    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    private void findViews()
    {
        btnLogin=(Button)findViewById(R.id.btn_login);
        btnRegister=(Button)findViewById(R.id.btn_register);
        userName=(EditText)findViewById(R.id.edt_username);
        passWord=(EditText)findViewById(R.id.edt_password);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySharedPreferences.putBoolen(Constant.ACTIVE,false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MySharedPreferences.putBoolen(Constant.ACTIVE,true);
    }
}
