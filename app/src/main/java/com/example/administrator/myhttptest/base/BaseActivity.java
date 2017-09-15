package com.example.administrator.myhttptest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.myhttptest.handler.HttpErrorHandler;
import com.example.administrator.myhttptest.handler.ProgressHandler;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by lijie on 2017/8/14.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    public HttpErrorHandler httpErrorHandler;
    public ProgressHandler progressHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDataBinding();
        progressHandler=new ProgressHandler(this);
        httpErrorHandler=new HttpErrorHandler(this);
        initView();
    }
    public void setDataBinding(){

    }
    public void initView(){

    }
}
