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
import com.example.administrator.myhttptest.utils.IntentUtils;
import com.example.administrator.myhttptest.viewmodel.MainItemVIewModel;
import com.example.administrator.myhttptest.widget.quickadapter.BaseQuickAdapter;
import com.example.administrator.myhttptest.widget.quickadapter.BaseViewHolder;
import com.example.administrator.myhttptest.widget.quickadapter.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class HomeActivity extends BaseActivity implements HomeContract.View {
    ActivityMainBinding binding;
    HomeContract.Presenter presenter;
    List<MeiZhiItemData> totalList=new ArrayList<>();
    int page=1;
    BaseQuickAdapter<MeiZhiItemData> adapter;


    @Override
    public void initView() {
        super.initView();
        initPtrRefreshLayout(this, binding.ptrFrame, new PtrDefaultHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                page=1;
                totalList.clear();
                presenter.getData(page);
            }
        });
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(layoutManager);
        adapter = new BaseQuickAdapter<MeiZhiItemData>(R.layout.item_home_recy) {

            @Override
            protected void onBindContentViewHolder(BaseViewHolder holder, int realPosition) {
                holder.bindTo(new MainItemVIewModel(adapter.getData().get(realPosition),HomeActivity.this));
            }
        };

        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                IntentUtils.goActivity(new Intent(HomeActivity.this,ImageDetailActivity.class).
                        putExtra("data",adapter.getData().get(position)));
            }
        });
        adapter.openLoadAnimation(new ScaleInAnimation());
        adapter.addData(totalList);
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
        presenter=new HomePresenter(this,this);
        presenter.getData(page);
    }

    @Override
    public void setDataBinding() {
        super.setDataBinding();
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void setListData(MeiZhi meiZhi) {
        page=page+1;
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

    @Override
    public void showProgressDialog() {
        progressHandlerImp.loadingStart();
    }

    @Override
    public void dismissProgressDialog() {
        progressHandlerImp.loadingComplete();
    }
}
