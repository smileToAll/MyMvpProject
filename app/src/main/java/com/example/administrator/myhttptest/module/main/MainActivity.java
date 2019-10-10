package com.example.administrator.myhttptest.module.main;

import com.example.administrator.myhttptest.R;
import com.example.administrator.myhttptest.module.guide.GuideActivity;
import com.example.administrator.myhttptest.common.BaseActivity;
import com.example.administrator.myhttptest.common.constant.AppConstant;
import com.example.administrator.myhttptest.databinding.ActivityMainBinding;
import com.example.administrator.myhttptest.common.widget.utils.IntentUtils;
import com.example.administrator.myhttptest.common.widget.utils.SpUtil;
import com.example.administrator.myhttptest.common.widget.utils.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    Fragment currentFragment;
    MenuItem currentMenuItem;
    Bundle savedInstanceState;
    private long times;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    public void setDataBinding() {
        super.setDataBinding();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }

    @Override
    public void initListener() {
        super.initListener();
        binding.mainNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@android.support.annotation.NonNull MenuItem item) {
                if (currentMenuItem != null && currentMenuItem != item) {
                    currentMenuItem = item;
                    currentMenuItem.setChecked(true);
                    SpUtil.saveOrUpdateInt(AppConstant.CURRENT_MENU, currentMenuItem.getItemId());
                    switchFragment(getFragmentById(currentMenuItem.getItemId()));
                }
                binding.drawlayout.closeDrawer(Gravity.LEFT, true);
                return true;
            }
        });
        binding.toggle.setOnClickListener(this);
    }

    @Override
    public void initView() {
        super.initView();
        binding.mainNav.setItemIconTintList(null);//为了使侧边栏里的图标显示原有的颜色
        binding.title.setText("首页");
        int anInt = SpUtil.findInt(AppConstant.CURRENT_MENU);
        if (anInt != -1) {
            currentMenuItem = binding.mainNav.getMenu().findItem(anInt);
        }
        if (currentMenuItem == null) {
            currentMenuItem = binding.mainNav.getMenu().findItem(R.id.meizhi);
        }
        if (currentMenuItem != null) {
            currentMenuItem.setChecked(true);
            Fragment fragment = getFragmentById(currentMenuItem.getItemId());
            switchFragment(fragment);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.toggle:
                binding.drawlayout.openDrawer(Gravity.LEFT);
                break;
        }
    }

    private void switchFragment(Fragment fragment) {
        if (fragment != null) {
            if (currentFragment == null || !fragment.getClass()
                    .getName()
                    .equals(currentFragment.getClass().getName())) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                currentFragment = fragment;
            }
        }
    }

    private Fragment getFragmentById(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.meizhi:
                fragment = new MeizhiFragment();
                break;
            case R.id.news:
                fragment = new MultiFragment();
                break;
        }
        return fragment;
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - times < 2000) {
            finish();
        } else {
            times = System.currentTimeMillis();
            ToastUtils.showShortToast(this, "再点一次，退出App");
        }
    }

    public void requestRxPermissions(String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(permissions).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean granted) throws Exception {
                if (granted) {
                    Toast.makeText(MainActivity.this, "已获取权限", Toast.LENGTH_SHORT).show();
                    IntentUtils.goActivity(new Intent(MainActivity.this, GuideActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "已拒绝一个或以上权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
