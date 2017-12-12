package com.example.administrator.myhttptest.model;


import com.example.administrator.myhttptest.bean.MeiZhi;
import com.example.administrator.myhttptest.model.home.IHome;
import com.example.administrator.myhttptest.net.IdeaApi;

import io.reactivex.Observable;

/**
 * Created by lijie on 2017/8/15.
 * Model层
 */

public class HomeModel implements IHome {
    @Override
    public Observable<MeiZhi> getListData(int page) {
        Observable<MeiZhi> meiziData = IdeaApi.getApiService().getMeiziData(page);
        return meiziData;
    }
}
