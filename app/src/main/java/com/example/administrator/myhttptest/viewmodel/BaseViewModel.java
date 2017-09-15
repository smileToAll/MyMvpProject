package com.example.administrator.myhttptest.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.administrator.myhttptest.R;

/**
 * Created by lijie on 2017/8/15.
 */

public class BaseViewModel extends BaseObservable {
    @Bindable
    public int getAvatarError(){
        return R.mipmap.loading_image;
    }
}
