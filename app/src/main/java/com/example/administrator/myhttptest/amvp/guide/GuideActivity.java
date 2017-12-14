package com.example.administrator.myhttptest.amvp.guide;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.myhttptest.R;
import com.example.administrator.myhttptest.amvp.home.HomeActivity;
import com.example.administrator.myhttptest.base.BaseActivity;
import com.example.administrator.myhttptest.bean.MeiZhi;
import com.example.administrator.myhttptest.bean.MeiZhiItemData;
import com.example.administrator.myhttptest.databinding.ActivityGuideBinding;
import com.example.administrator.myhttptest.utils.IntentUtils;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements GuideContract.View {
    ActivityGuideBinding binding;
    GuideContract.Presenter presenter;
    IndicatorViewPager indicatorViewPager;
    private IndicatorViewPager.IndicatorViewPagerAdapter adapter;
    LayoutInflater inflater;
    List<MeiZhiItemData> list=new ArrayList<>();
    @Override
    public void setDataBinding() {
        super.setDataBinding();
        // 使屏幕不显示标题栏(必须要在setContentView方法执行前执行)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        // 隐藏状态栏，使内容全屏显示(必须要在setContentView方法执行前执行)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_guide);
    }

    @Override
    public void initListener() {
        super.initListener();
        binding.btnGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_go:
                IntentUtils.goActivity(GuideActivity.this,HomeActivity.class);
                finish();
                break;
        }
    }

    @Override
    public void initData() {
        super.initData();
        presenter=new GuidePresenter(this);
        presenter.getGuideImages(1);
    }

    @Override
    public void initView() {
        super.initView();
        indicatorViewPager=new IndicatorViewPager(binding.guideFiv,binding.guideSvp);
        inflater= LayoutInflater.from(getApplicationContext());
        adapter=new IndicatorViewPager.IndicatorViewPagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public View getViewForTab(int position, View convertView, ViewGroup container) {
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.tab_guide,container,false);
                }
                return convertView;
            }

            @Override
            public View getViewForPage(int position, View convertView, ViewGroup container) {
                if(convertView==null){
//                    convertView=new ImageView(getApplicationContext());
//                    convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    convertView=getLayoutInflater().inflate(R.layout.item_guide_layout,container,false);
                }
                ImageView image= (ImageView) convertView.findViewById(R.id.guide_image);
                Glide.with(GuideActivity.this).load(list.get(position).getUrl()).into(image);
                return convertView;
            }

            @Override
            public int getItemPosition(Object object) {
                return PagerAdapter.POSITION_NONE;
            }
        };
        indicatorViewPager.setAdapter(adapter);
        indicatorViewPager.setOnIndicatorPageChangeListener(new IndicatorViewPager.OnIndicatorPageChangeListener() {
            @Override
            public void onIndicatorPageChange(int preItem, int currentItem) {
                if(currentItem==list.size()-1){
                    binding.btnGo.setVisibility(View.VISIBLE);
                }else{
                    binding.btnGo.setVisibility(View.GONE);
                }
            }
        });
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
    public void setGuideData(MeiZhi meiZhi) {
        for (int i = 0; i < 5; i++) {
            list.add(meiZhi.getResults().get(i));
        }
        if(list.size()==0){
            binding.btnGo.setVisibility(View.VISIBLE);
            Intent intent=new Intent(GuideActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
        if(list.size()==1){
            binding.btnGo.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }
}
