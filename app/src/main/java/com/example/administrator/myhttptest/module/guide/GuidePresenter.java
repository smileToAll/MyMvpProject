package com.example.administrator.myhttptest.module.guide;

import com.example.administrator.myhttptest.net.bean.MeiZhi;
import com.example.administrator.myhttptest.common.handler.progresshandler.ProgressHandler;
import com.example.administrator.myhttptest.net.model.home.HomeModel;
import com.example.administrator.myhttptest.net.http.BaseDataObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lijie on 2017/12/13.
 */

public class GuidePresenter implements GuideContract.Presenter {
    GuideContract.View view;
    HomeModel model;

    public GuidePresenter(GuideContract.View view) {
        this.view = view;
        model=new HomeModel();
    }

    @Override
    public void getGuideImages(int page) {
        model.getListData(page) .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseDataObserver<MeiZhi>(new ProgressHandler() {
                    @Override
                    public void loadingStart() {
                        view.showProgressDialog();
                    }

                    @Override
                    public void loadingComplete() {
                        view.dismissProgressDialog();
                    }
                }) {
                    @Override
                    public void onDataNext(MeiZhi meiZhi) {
                        view.setGuideData(meiZhi);
                    }
                });

    }
}
