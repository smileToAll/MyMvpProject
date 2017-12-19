package com.example.administrator.myhttptest.amvp.activity.detail;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.myhttptest.R;
import com.example.administrator.myhttptest.base.BaseActivity;
import com.example.administrator.myhttptest.databinding.ActivityImageDetailBinding;
import com.example.administrator.myhttptest.bean.MeiZhiItemData;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageDetailActivity extends BaseActivity {
    ActivityImageDetailBinding binding;
    MeiZhiItemData data;
    PhotoViewAttacher attacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setDataBinding() {
        super.setDataBinding();
        binding= DataBindingUtil.setContentView(this,R.layout.activity_image_detail);
    }

    @Override
    public void initView() {
        super.initView();
        getIntentData();
        initImage();
    }

    private void initImage() {
        attacher = new PhotoViewAttacher(binding.detailImage);
        Glide.with(this).load(data.getUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                binding.detailImage.setImageBitmap(resource);
                attacher.update();
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                binding.detailImage.setImageDrawable(errorDrawable);
            }
        });
    }

    private void getIntentData() {
        data = (MeiZhiItemData) getIntent().getSerializableExtra("data");
    }
}
