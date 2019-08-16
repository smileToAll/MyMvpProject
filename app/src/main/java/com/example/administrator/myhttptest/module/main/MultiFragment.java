package com.example.administrator.myhttptest.module.main;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myhttptest.R;
import com.example.administrator.myhttptest.module.detail.ImageDetailActivity;
import com.example.administrator.myhttptest.common.base.BaseFragment;
import com.example.administrator.myhttptest.net.bean.MeiZhi;
import com.example.administrator.myhttptest.net.bean.MeiZhiItemData;
import com.example.administrator.myhttptest.databinding.FragmentNewsBinding;
import com.example.administrator.myhttptest.common.viewmodel.MainItemVIewModel;
import com.example.administrator.myhttptest.common.widget.quickadapter.BaseQuickAdapter;
import com.example.administrator.myhttptest.common.widget.quickadapter.BaseViewHolder;
import com.example.administrator.myhttptest.common.widget.quickadapter.animation.SlideInRightAnimation;
import com.example.administrator.myhttptest.common.widget.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultiFragment extends BaseFragment implements MultiContract.view{

    FragmentNewsBinding binding;
    MultiPresenter mPresenter;
    List<MeiZhiItemData> totalList=new ArrayList<>();
    BaseQuickAdapter<MeiZhiItemData> adapter;
    List<Integer> heights;
    @Override
    protected void onCreateViews(Bundle savedInstanceState) {
        super.onCreateViews(savedInstanceState);
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_news,container,false);
        setContentView(binding.getRoot());
        initData();
        initView();
    }

    private void initView() {
        RecyclerView.LayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        binding.multiRec.setLayoutManager(manager);
        adapter = new BaseQuickAdapter<MeiZhiItemData>(R.layout.item_home_recy) {

            @Override
            protected void onBindContentViewHolder(BaseViewHolder holder, int realPosition) {
                holder.bindTo(new MainItemVIewModel(adapter.getData().get(realPosition),activity));
                ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
                params.height=heights.get(realPosition);
                holder.itemView.setLayoutParams(params);
            }
        };

        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                IntentUtils.goActivity(new Intent(activity,ImageDetailActivity.class).
                        putExtra("data",adapter.getData().get(position)));
            }
        });
        adapter.openLoadAnimation(new SlideInRightAnimation());
        adapter.addData(totalList);
        binding.multiRec.setAdapter(adapter);
    }

    private void initData() {
        mPresenter=new MultiPresenter(this,activity);
        mPresenter.getData(3);

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
        totalList.addAll(meiZhi.getResults());
        adapter.setNewData(totalList);
        adapter.notifyDataSetChanged();
        getRandomHeights(totalList);
    }

    private void getRandomHeights(List<MeiZhiItemData> list) {
        heights=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            heights.add((int)(500+Math.random()*400));
        }
    }
}
