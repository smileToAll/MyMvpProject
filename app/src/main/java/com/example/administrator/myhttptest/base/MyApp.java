package com.example.administrator.myhttptest.base;

import android.app.Application;
import android.databinding.DataBindingUtil;

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
