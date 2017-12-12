package com.example.administrator.myhttptest.model.home;

import com.example.administrator.myhttptest.bean.MeiZhi;

import io.reactivex.Observable;

/**
 * Created by lijie on 2017/12/11.
 */

public interface IHome {
    Observable<MeiZhi> getListData(int page);
}
