package com.example.administrator.myhttptest.widget.ptrcustomheader;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.myhttptest.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;


/**
 *
 */
public class PtrMyTextHeader extends FrameLayout implements PtrUIHandler {

    private String text_refreshing = "正在刷新...";
    private String text_refresh_complete = "刷新成功";
    private String text_pull_to_refresh = "下拉刷新";
    private String text_release_to_refresh = "松开刷新";

    public void setText_refreshing(String text_refreshing) {
        this.text_refreshing = text_refreshing;
    }

    public void setText_refresh_complete(String text_refresh_complete) {
        this.text_refresh_complete = text_refresh_complete;
    }

    public void setText_pull_to_refresh(String text_pull_to_refresh) {
        this.text_pull_to_refresh = text_pull_to_refresh;
    }

    public void setText_release_to_refresh(String text_release_to_refresh) {
        this.text_release_to_refresh = text_release_to_refresh;
    }

    ArrowView mArrowView;
    TextView mTextView;


    public PtrMyTextHeader(Context context) {
        this(context,null);
    }

    public PtrMyTextHeader(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PtrMyTextHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.view_ptr_my_image_arrow_header, this);
        mArrowView = (ArrowView) header.findViewById(R.id.arrow_view);
        mTextView = (TextView) header.findViewById(R.id.tv_text);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        if(mArrowView.isRunning()){
            mArrowView.stopAnim();
        }
        mArrowView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mArrowView.setVisibility(View.VISIBLE);
        mArrowView.setStartAngle(-90);
        mArrowView.setSwpeeAngle(5);
        mTextView.setText(text_pull_to_refresh);
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mArrowView.setVisibility(View.VISIBLE);
        mTextView.setText(text_refreshing);
        if(!mArrowView.isRunning()){
            mArrowView.startAnim();
        }
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        if(mArrowView.isRunning()){
            mArrowView.stopAnim();
        }
        mArrowView.setVisibility(View.INVISIBLE);
        mTextView.setText(text_refresh_complete);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        if(status == PtrFrameLayout.PTR_STATUS_INIT){
        }else if (status == PtrFrameLayout.PTR_STATUS_PREPARE) {
            final int mOffsetToRefresh = frame.getOffsetToRefresh();
            final int currentPos = ptrIndicator.getCurrentPosY();
            final int lastPos = ptrIndicator.getLastPosY();
            double percent = currentPos / (double) mOffsetToRefresh;
            double lastPercent = lastPos / (double) mOffsetToRefresh;
            if (percent<1) {
                mTextView.setText(text_pull_to_refresh);
                mArrowView.onArrowAngelChanged(percent);
            }else if(percent>=1){
                mTextView.setText(text_release_to_refresh);
                if(lastPercent<1){
                    mArrowView.onArrowAngelChanged(percent);
                }
            }
        }else if(status == PtrFrameLayout.PTR_STATUS_LOADING){
        }else if(status == PtrFrameLayout.PTR_STATUS_COMPLETE){
        }else {
        }
    }
}
