package com.example.administrator.myhttptest;

import android.app.Application;
import android.databinding.DataBindingUtil;

import com.example.administrator.myhttptest.base.AppCompat;

/**
 * Created by lijie on 2017/8/11.
 */

public class MyApp extends Application {
    public static MyApp instance;


    @Override
    public void onCreate() {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new AppCompat());
        if(instance == null){
            instance =this;
        }
    }
    public static Application getInstance() {
        return instance;
    }

}
