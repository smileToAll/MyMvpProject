package com.example.administrator.myhttptest.amvp.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.administrator.myhttptest.handler.progresshandler.ProgressHandlerImp;
import com.example.administrator.myhttptest.widget.ptrcustomheader.PtrMyTextHeader;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Administrator on 2017/5/23.
 */

public class BaseFragment extends LazysFragment  implements View.OnClickListener{
    private boolean isInit = false;
    private Bundle savedInstanceState;
    public static final String INTENT_BOOLEAN_LAZYLOAD = "intent_boolean_lazyLoad";
    private boolean isLazyLoad = true;
    private FrameLayout layout;
    protected Activity activity;
    protected ProgressHandlerImp progressHandlerImp;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }

    @Deprecated
    protected final void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        Bundle bundle = getArguments();
        if (bundle != null) {
            isLazyLoad = bundle.getBoolean(INTENT_BOOLEAN_LAZYLOAD, isLazyLoad);
        }
        //为什么不直接getUserVisibleHint();而是通过自己存isVisibleToUserState变量判断
        //因为v4的25的版本 已经调用 setUserVisibleHint(true)，结果到这里getUserVisibleHint是false
        // （ps:看了FragmentManager源码Fragment被重新创建有直接赋值isVisibleToUser不知道是不是那里和之前v4有改动的地方）
        //所以我默认VISIBLE_STATE_NOTSET，之前没有调用setUserVisibleHint方法，就用系统的getUserVisibleHint，否则就用setUserVisibleHint后保存的值
        //总之就是调用了setUserVisibleHint 就使用setUserVisibleHint的值
        boolean isVisibleToUser;
        progressHandlerImp = new ProgressHandlerImp(activity);
        if (isVisibleToUserState == VISIBLE_STATE_NOTSET) {
            isVisibleToUser = getUserVisibleHint();
        } else {
            isVisibleToUser = isVisibleToUserState == VISIBLE_STATE_VISIABLE;
        }
        if (isLazyLoad) {
            if (isVisibleToUser && !isInit) {
                isInit = true;
                onCreateViews(savedInstanceState);
            } else {
                LayoutInflater layoutInflater = inflater;
                if (layoutInflater == null) {
                    layoutInflater = LayoutInflater.from(getApplicationContext());
                }
                layout = new FrameLayout(layoutInflater.getContext());
                View view = getPreviewLayout(layoutInflater, layout);
                if (view != null) {
                    layout.addView(view);
                }
                layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                super.setContentView(layout);
            }
        } else {
            isInit = true;
            onCreateViews(savedInstanceState);
        }
    }

    private int isVisibleToUserState = VISIBLE_STATE_NOTSET;
    //未设置值
    private static final int VISIBLE_STATE_NOTSET = -1;
    //可见
    private static final int VISIBLE_STATE_VISIABLE = 1;
    //不可见
    private static final int VISIBLE_STATE_GONE = 0;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisibleToUserState = isVisibleToUser ? VISIBLE_STATE_VISIABLE : VISIBLE_STATE_GONE;
        if (isVisibleToUser && !isInit && getContentView() != null) {
            isInit = true;
            onCreateViews(savedInstanceState);
            onResumeView();
        }
        if (isInit && getContentView() != null) {
            if (isVisibleToUser) {
                isStart = true;
                onFragmentStartView();
            } else {
                isStart = false;
                onFragmentStopView();
            }
        }
    }

    protected View getPreviewLayout(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    @Deprecated
    @Override
    public final void onStart() {
        super.onStart();
        if (isInit && !isStart && getUserVisibleHint()) {
            isStart = true;
            onFragmentStartView();
        }
    }

    @Deprecated
    @Override
    public final void onStop() {
        super.onStop();
        if (isInit && isStart && getUserVisibleHint()) {
            isStart = false;
            onFragmentStopView();
        }
    }

    private boolean isStart = false;

    protected void onFragmentStartView() {

    }

    protected void onFragmentStopView() {

    }

    protected void onCreateViews(Bundle savedInstanceState) {

    }

    protected void onResumeView() {

    }

    protected void onPauseView() {

    }

    protected void onDestroyViews() {

    }

    @Override
    public void setContentView(int layoutResID) {
        if (isLazyLoad && getContentView() != null && getContentView().getParent() != null) {
            layout.removeAllViews();
            View view = inflater.inflate(layoutResID, layout, false);
            layout.addView(view);
        } else {
            super.setContentView(layoutResID);
        }
    }

    @Override
    public void setContentView(View view) {
        if (isLazyLoad && getContentView() != null && getContentView().getParent() != null) {
            layout.removeAllViews();
            layout.addView(view);
        } else {
            super.setContentView(view);
        }
    }

    @Override
    @Deprecated
    public final void onResume() {
        super.onResume();
        if (isInit) {
            onResumeView();
        }
    }

    @Override
    @Deprecated
    public final void onPause() {
        super.onPause();
        if (isInit) {
            onPauseView();
        }
    }

    @Override
    @Deprecated
    public final void onDestroyView() {
        super.onDestroyView();
        if (isInit) {
            onDestroyViews();
        }
        isInit = false;
    }

    @Override
    public void onClick(View v) {

    }



    public void requestSuccess(Object result, int requestCode) {

    }



    protected void initPtrRefreshLayout(Context context, PtrFrameLayout frameLayout, PtrHandler handler) {
        PtrMyTextHeader header = new PtrMyTextHeader(context);
        frameLayout.setHeaderView(header);
        frameLayout.addPtrUIHandler(header);
        frameLayout.disableWhenHorizontalMove(true);
        frameLayout.setPtrHandler(handler);
        frameLayout.setRatioOfHeaderHeightToRefresh(0.2f);
    }
    protected synchronized void showDialog() {
        progressHandlerImp.loadingStart();
    }

    protected synchronized void dismissDialog() {
        progressHandlerImp.loadingComplete();
    }
}
