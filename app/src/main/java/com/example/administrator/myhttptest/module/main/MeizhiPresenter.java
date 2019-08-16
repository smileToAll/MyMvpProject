package com.example.administrator.myhttptest.module.main;

import com.example.administrator.myhttptest.common.handler.progresshandler.ProgressHandler;
import com.example.administrator.myhttptest.net.bean.MeiZhi;
import com.example.administrator.myhttptest.net.http.BaseDataObserver;
import com.example.administrator.myhttptest.net.model.home.HomeModel;

import android.content.Context;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lijie on 2017/12/19.
 */

public class MeizhiPresenter implements MeiZhiContract.Presenter {
    MeiZhiContract.View view;
    Context context;
    HomeModel model;

    public MeizhiPresenter(MeiZhiContract.View view, Context context) {
        this.view = view;
        this.context = context;
        model = new HomeModel();
    }

    @Override
    public void getData(int page) {
        model.getListData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseDataObserver<MeiZhi>(new ProgressHandler() {
                    @Override
                    public void loadingStart() {
                        view.showProgressDialog();
                    }

                    @Override
                    public void loadingComplete() {
                        view.dismissProgressDialog();
                        view.refreshEnd();
                    }
                }) {
                    @Override
                    public void onDataNext(MeiZhi meiZhi) {
                        view.setListData(meiZhi);
                    }
                });
    }
}
