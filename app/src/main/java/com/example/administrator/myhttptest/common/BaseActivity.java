package com.example.administrator.myhttptest.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.administrator.myhttptest.common.handler.progresshandler.ProgressHandlerImp;
import com.example.administrator.myhttptest.common.widget.ptrcustomheader.PtrMyTextHeader;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by lijie on 2017/8/14.
 */

public abstract class BaseActivity extends RxAppCompatActivity implements View.OnClickListener{

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

    @Override
    public void onClick(View v) {

    }
}
