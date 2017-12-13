package com.example.administrator.myhttptest.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.myhttptest.handler.progresshandler.ProgressHandlerImp;
import com.example.administrator.myhttptest.widget.ptrcustomheader.PtrMyTextHeader;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

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
        initListener();
    }

    public void initListener() {

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
    protected void initPtrRefreshLayout(Context context, PtrFrameLayout frameLayout, PtrHandler handler) {
        PtrMyTextHeader header = new PtrMyTextHeader(context);
        frameLayout.setHeaderView(header);
        frameLayout.addPtrUIHandler(header);
        frameLayout.disableWhenHorizontalMove(true);
        frameLayout.setPtrHandler(handler);
        frameLayout.setRatioOfHeaderHeightToRefresh(0.2f);
    }
}
