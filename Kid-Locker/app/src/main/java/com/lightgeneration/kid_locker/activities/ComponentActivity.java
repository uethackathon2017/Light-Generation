package com.lightgeneration.kid_locker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.fragments.graphs.GraphFragment;
import com.lightgeneration.kid_locker.fragments.info.InfoFragment;
import com.lightgeneration.kid_locker.fragments.locks.LockFragment;
import com.lightgeneration.kid_locker.fragments.subcribe.SubCribeFragment;
import com.lightgeneration.kid_locker.utils.Constant;
import com.lightgeneration.kid_locker.utils.MySharedPreferences;

/**
 * Created by PhamVanLong on 3/10/2017.
 */

public class ComponentActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private ImageView btnMenu;
    private DrawerLayout drawerLayout;
    private View header;
    private TextView tvName,tvAge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);
        initComponents();
    }

    private void initComponents() {
        btnMenu = (ImageView) findViewById(R.id.btn_menu_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        btnMenu.setOnClickListener(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        header=navigationView.getHeaderView(0);
        tvAge=(TextView)header.findViewById(R.id.tv_age_header);
        tvName=(TextView)header.findViewById(R.id.tv_name_header);
        tvAge.setText("Tuôỉ : "+MySharedPreferences.getInt(Constant.AGE_BABY,0)+"");
        tvName.setText(MySharedPreferences.getString(Constant.NAME_BABY,""));

        openLockFragment();
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_menu_toolbar: {
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            }

            default: {
                break;
            }
        }
    }

    private void replaceFragment(Fragment fragment) {
//        if (fragment.isVisible()) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.rl_content, fragment)
//                    .commit();
//            return;
//        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rl_content, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_lock: {
                openLockFragment();
                break;
            }

            case R.id.menu_info: {
                openInfoFragment();
                break;
            }

            case R.id.menu_graph: {
                openGraphFragment();
                break;
            }

            case R.id.menu_follow: {
                openSubcribeFragment();
                break;
            }

            case R.id.menu_setting: {
                Intent intent=new Intent(ComponentActivity.this,SettingAcitivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
                break;
            }

            default: {
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    private void openInfoFragment() {
        InfoFragment infoFragment = new InfoFragment();
        replaceFragment(infoFragment);
    }
    private void openLockFragment()
    {
        LockFragment lockFragment=new LockFragment();
        replaceFragment(lockFragment);
    }
    private void openGraphFragment()
    {
        GraphFragment graphFragment=new GraphFragment();
        replaceFragment(graphFragment);
    }
    private void openSubcribeFragment()
    {
        SubCribeFragment subCribeFragment=new SubCribeFragment();
        replaceFragment(subCribeFragment);
    }
}
