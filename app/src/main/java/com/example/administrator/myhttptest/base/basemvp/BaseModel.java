package com.example.administrator.myhttptest.base.basemvp;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * Created by lijie on 2017/8/15.
 */

public class BaseModel {
    public Context mContext;

    public BaseModel(Context mContext) {
        this.mContext = mContext;
    }

    public LifecycleProvider getActivityLifecycleProvider(){
        LifecycleProvider provider=null;
        if(null!=mContext&&mContext instanceof LifecycleProvider){
            provider=(LifecycleProvider)mContext;
        }
        return provider;
    }
    public void doDestroy(){
        this.mContext = null;
    }
}
