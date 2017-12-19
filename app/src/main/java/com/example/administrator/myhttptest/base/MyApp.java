package com.example.administrator.myhttptest.base;

import android.app.Application;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;

import com.example.administrator.myhttptest.widget.utils.SpUtil;

/**
 * Created by lijie on 2017/8/11.
 */

public class MyApp extends Application {
    public static MyApp instance;


    @Override
    public void onCreate() {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new AppCompat());
        SpUtil.init(PreferenceManager.getDefaultSharedPreferences(this));
        if(instance == null){
            instance =this;
        }
    }
    public static Application getInstance() {
        return instance;
    }

}
