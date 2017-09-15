package com.example.administrator.myhttptest.mvp.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.myhttptest.R;
import com.example.administrator.myhttptest.base.BaseActivity;
import com.example.administrator.myhttptest.databinding.ActivityMainBinding;
import com.example.administrator.myhttptest.mvp.detail.ImageDetailActivity;
import com.example.administrator.myhttptest.mvp.home.bean.BaseResponse;
import com.example.administrator.myhttptest.mvp.home.bean.MeiZhi;
import com.example.administrator.myhttptest.mvp.home.bean.MeiZhiItemData;
import com.example.administrator.myhttptest.quickadapter.BaseQuickAdapter;
import com.example.administrator.myhttptest.quickadapter.BaseViewHolder;
import com.example.administrator.myhttptest.viewmodel.MainItemVIewModel;

public class HomeActivity extends BaseActivity implements HomeContract.View {
    ActivityMainBinding binding;
    BaseQuickAdapter<MeiZhiItemData> adapter;
    HomeContract.Presenter presenter;
    @Override
    public void setData(MeiZhi meiZhi) {
        adapter.addData(meiZhi.getResults());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void initView() {
        super.initView();
        presenter=new HomePresenter(this,this);
        presenter.getData(1);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(layoutManager);
        adapter=new BaseQuickAdapter<MeiZhiItemData>(R.layout.item_main_recycler) {
            @Override
            protected void onBindContentViewHolder(BaseViewHolder holder, int realPosition) {
                holder.bindTo(new MainItemVIewModel(adapter.getItem(realPosition),HomeActivity.this));
            }
        };
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(HomeActivity.this,ImageDetailActivity.class);
                intent.putExtra("data",adapter.getData().get(position));
                startActivity(intent);
            }
        });
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void setDataBinding() {
        super.setDataBinding();
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void showProgress() {
        progressHandler.loadingStart();
    }

    @Override
    public void hideProgress() {
        progressHandler.loadingComplete();
    }

    @Override
    public void onHttpError(BaseResponse response) {
        httpErrorHandler.onHttpError(response);
    }

    @Override
    public void onHttpException(Throwable e) {
        httpErrorHandler.onHttpException(e);
    }
}
