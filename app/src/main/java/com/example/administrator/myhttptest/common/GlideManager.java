package com.example.administrator.myhttptest.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.example.administrator.myhttptest.R;

import static com.bumptech.glide.Glide.with;

/**
 * Created by wangce on 2015/9/24.
 */
public class GlideManager {

    //placeholder(R.mipmap.icon)
    //.error(R.mipmap.icon)
    public static DrawableRequestBuilder<String> getDefaultRequest(Context context,String url){
        return  with(context).fromString().load(url);
    }

    public static void loadImageDefault(ImageView imageView, String url){
        loadImageDefault(imageView,url, R.mipmap.loading_image);
    }
    public static void loadImageDefault(ImageView imageView, String url , int placeholder){
        if(placeholder!=-1){
            getDefaultRequest(imageView.getContext(),url).placeholder(placeholder).error(placeholder).into(imageView);
        }else {
            getDefaultRequest(imageView.getContext(),url).into(imageView);
        }

    }


  /*  public static void loadCircleImage( final ImageView imageView,String url) {
        loadCircleImage(imageView,url,-1);
    }*/


}
