package com.example.administrator.myhttptest.viewmodel;

import android.content.Context;
import android.databinding.Bindable;

import com.example.administrator.myhttptest.BR;
import com.example.administrator.myhttptest.mvp.home.bean.MeiZhiItemData;

/**
 * Created by lijie on 2017/8/15.
 */

public class MainItemVIewModel extends BaseViewModel {
    MeiZhiItemData meiZhiItemData;
    Context context;

    public MainItemVIewModel(MeiZhiItemData meiZhiItemData, Context context) {
        this.meiZhiItemData = meiZhiItemData;
        this.context=context;
        notifyPropertyChanged(BR.setImage);
    }
    @Bindable
    public String getSetImage(){
        if(meiZhiItemData==null){
            return null;
        }
        return meiZhiItemData.getUrl();
    }

}
