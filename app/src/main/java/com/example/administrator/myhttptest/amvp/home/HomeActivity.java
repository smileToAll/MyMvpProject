package com.example.administrator.myhttptest.amvp.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.myhttptest.R;
import com.example.administrator.myhttptest.amvp.detail.ImageDetailActivity;
import com.example.administrator.myhttptest.base.BaseActivity;
import com.example.administrator.myhttptest.bean.MeiZhi;
import com.example.administrator.myhttptest.bean.MeiZhiItemData;
import com.example.administrator.myhttptest.databinding.ActivityMainBinding;
import com.example.administrator.myhttptest.quickadapter.BaseQuickAdapter;
import com.example.administrator.myhttptest.quickadapter.BaseViewHolder;
import com.example.administrator.myhttptest.viewmodel.MainItemVIewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements HomeContract.View {
    ActivityMainBinding binding;
    BaseQuickAdapter<MeiZhiItemData> adapter;
    HomeContract.Presenter presenter;
    List<MeiZhiItemData> totalList=new ArrayList<>();



    @Override
    public void initView() {
        super.initView();
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
    public void initData() {
        super.initData();
        presenter=new HomePresenter(this,this);
        presenter.getData(1);
    }

    @Override
    public void setDataBinding() {
        super.setDataBinding();
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void setListData(MeiZhi meiZhi) {
        totalList.addAll(meiZhi.getResults());
        adapter.setNewData(totalList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressDialog() {
        progressHandlerImp.loadingStart();
    }

    @Override
    public void dismissProgressDialog() {
        progressHandlerImp.loadingComplete();
    }
}
