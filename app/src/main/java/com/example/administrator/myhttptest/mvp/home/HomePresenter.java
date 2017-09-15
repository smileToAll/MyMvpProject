package com.example.administrator.myhttptest.mvp.home;

import android.content.Context;

import com.example.administrator.myhttptest.mvp.home.bean.BaseResponse;
import com.example.administrator.myhttptest.mvp.home.bean.MeiZhi;

/**
 * Created by lijie on 2017/9/11.
 */

public class HomePresenter implements HomeContract.Presenter {
    HomeContract.View view;
    Context context;
    HomeContract.Model model;

    public HomePresenter(HomeContract.View view, Context context) {
        this.view = view;
        this.context = context;
        model=new HomeModel(context,this);
    }

    @Override
    public void getData(int page) {
        view.showProgress();
        model.getListData(page);
    }

    @Override
    public void getDataSuccess(MeiZhi meiZhi) {
        view.hideProgress();
        view.setData(meiZhi);
    }

    @Override
    public void onHttpError(BaseResponse response) {
        view.onHttpError(response);
    }

    @Override
    public void onHttpException(Throwable e) {
        view.onHttpException(e);
    }


}
