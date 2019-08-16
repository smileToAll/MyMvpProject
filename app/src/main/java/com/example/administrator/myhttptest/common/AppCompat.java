package com.example.administrator.myhttptest.common;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.example.administrator.myhttptest.R;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Administrator on 2017/5/31.
 */

public class AppCompat implements android.databinding.DataBindingComponent {
    NormalImageAdapter normalImageAdapter = new NormalImageAdapter();

    @Override
    public NormalImageAdapter getNormalImageAdapter() {
        return normalImageAdapter;
    }


    public class NormalImageAdapter {
        @BindingAdapter(value = {"android:src"}, requireAll = true)
        public void setImageUrl(ImageView imageView, String url) {
            DrawableRequestBuilder builder = GlideManager.getDefaultRequest(imageView.getContext(), url);
            builder.error(R.mipmap.loading_image);
            builder.placeholder(R.mipmap.loading_image);
            builder.into(imageView);
        }
    }


    NormalCircleImageAdapter normalCircleImageAdapter = new NormalCircleImageAdapter();

    @Override
    public NormalCircleImageAdapter getNormalCircleImageAdapter() {
        return normalCircleImageAdapter;
    }


    public class NormalCircleImageAdapter {
        @BindingAdapter(value = {"android:src"}, requireAll = true)
        public void setImageUrl(CircleImageView imageView, String url) {
            DrawableRequestBuilder builder = GlideManager.getDefaultRequest(imageView.getContext(), url);
            builder.error(R.mipmap.loading_image);
            builder.placeholder(R.mipmap.loading_image);
            builder.into(imageView);
        }
    }


    ImageAdapter imageAdapter = new ImageAdapter();

    @Override
    public ImageAdapter getImageAdapter() {
        return imageAdapter;
    }


    public class ImageAdapter {
        @BindingAdapter(value = {"android:src","placeholder"}, requireAll = true)
        public void setImageUrl(ImageView imageView, String url, int placeholder ) {
            DrawableRequestBuilder builder = GlideManager.getDefaultRequest(imageView.getContext(),url);
            if(placeholder!=0&&placeholder!=-1){
                builder.error(placeholder);
                builder.placeholder(placeholder);
            }else {
                builder.error(R.mipmap.loading_image);
                builder.placeholder(R.mipmap.loading_image);
            }

            builder.into(imageView);
        }
    }


    ImageResAdapter imageResAdapter = new ImageResAdapter();

    public class ImageResAdapter {
        @BindingAdapter(value = {"android:src", "error"}, requireAll = false)
        public void setImageUrl(ImageView imageView, int res, int error) {


            /**
             final ObjectAnimator anim = ObjectAnimator.ofInt(img, "ImageLevel", 0, MAX_LEVEL);
             anim.setDuration(800);
             anim.setRepeatCount(ObjectAnimator.INFINITE);
             anim.start();
             */

            DrawableRequestBuilder builder = Glide.with(imageView.getContext()).load(res);
            if (error != -1) {
                builder.error(error);
            }
            builder.into(imageView);
        }
    }

    @Override
    public ImageResAdapter getImageResAdapter() {
        return imageResAdapter;
    }


    CircleImageAdapter circleImageAdapter = new CircleImageAdapter();

    @Override
    public CircleImageAdapter getCircleImageAdapter() {
        return circleImageAdapter;
    }

    public class CircleImageAdapter{
        @BindingAdapter(value = {"android:src","placeholder"}, requireAll = true)
        public void setImageUrl(CircleImageView imageView, String url, int placeholder) {

            DrawableRequestBuilder builder = GlideManager.getDefaultRequest(imageView.getContext(),url).dontAnimate();
            builder.error(placeholder);
            builder.placeholder(placeholder);
            builder.into(imageView);
        }
    }


    BitmapAdapter bitmapAdapter = new BitmapAdapter();

    @Override
    public BitmapAdapter getBitmapAdapter() {
        return bitmapAdapter;
    }

    public class BitmapAdapter {
        @BindingAdapter(value = {"android:src"}, requireAll = true)
        public void setImageUrl(ImageView imageView, Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
