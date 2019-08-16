package com.example.administrator.myhttptest.module.main;


import com.example.administrator.myhttptest.R;
import com.example.administrator.myhttptest.common.base.BaseFragment;
import com.example.administrator.myhttptest.common.viewmodel.MainItemVIewModel;
import com.example.administrator.myhttptest.common.widget.quickadapter.BaseQuickAdapter;
import com.example.administrator.myhttptest.common.widget.quickadapter.BaseViewHolder;
import com.example.administrator.myhttptest.common.widget.quickadapter.animation.ScaleInAnimation;
import com.example.administrator.myhttptest.common.widget.utils.IntentUtils;
import com.example.administrator.myhttptest.databinding.FragmentMeizhiBinding;
import com.example.administrator.myhttptest.module.detail.ImageDetailActivity;
import com.example.administrator.myhttptest.net.bean.MeiZhi;
import com.example.administrator.myhttptest.net.bean.MeiZhiItemData;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeizhiFragment extends BaseFragment implements MeiZhiContract.View {
    FragmentMeizhiBinding binding;
    MeiZhiContract.Presenter mPresenter;
    int page = 1;
    List<MeiZhiItemData> totalList = new ArrayList<>();
    BaseQuickAdapter<MeiZhiItemData> adapter;

    @Override
    protected void onCreateViews(Bundle savedInstanceState) {
        super.onCreateViews(savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_meizhi, container, false);
        setContentView(binding.getRoot());
        initData();
        initView();
    }

    private void initView() {
        initPtrRefreshLayout(activity, binding.ptrFrame, new PtrDefaultHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                page = 1;
                totalList.clear();
                mPresenter.getData(page);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        binding.recycler.setLayoutManager(layoutManager);
        adapter = new BaseQuickAdapter<MeiZhiItemData>(R.layout.item_home_recy) {

            @Override
            protected void onBindContentViewHolder(BaseViewHolder holder, int realPosition) {
                holder.bindTo(new MainItemVIewModel(adapter.getData().get(realPosition), activity));
            }
        };

        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                IntentUtils.goActivity(new Intent(activity, ImageDetailActivity.class).
                        putExtra("data", adapter.getData().get(position)));
            }
        });
        adapter.openLoadAnimation(new ScaleInAnimation());
        adapter.addData(totalList);
        binding.recycler.setAdapter(adapter);

    }

    private void initData() {
        mPresenter = new MeizhiPresenter(this, activity);
        mPresenter.getData(page);
    }

    @Override
    public void showProgressDialog() {
        progressHandlerImp.loadingStart();
    }

    @Override
    public void dismissProgressDialog() {
        progressHandlerImp.loadingComplete();
    }

    @Override
    public void setListData(MeiZhi meiZhi) {
        page = page + 1;
        totalList.addAll(meiZhi.getResults());
        adapter.setNewData(totalList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshEnd() {
        binding.ptrFrame.refreshComplete();
    }

    @Override
    public void loadEnd() {

    }
}
