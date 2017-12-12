package com.example.administrator.myhttptest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.myhttptest.handler.progresshandler.ProgressHandlerImp;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by lijie on 2017/8/14.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    protected ProgressHandlerImp progressHandlerImp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDataBinding();
        progressHandlerImp=new ProgressHandlerImp(this);
        initData();
        initView();
    }

    public void initData() {

    }

    public void setDataBinding(){

    }
    public void initView(){

    }
    protected synchronized void dismissDialog() {
        progressHandlerImp.loadingComplete();
    }
    protected synchronized void showDialog() {
        progressHandlerImp.loadingStart();
    }
}
