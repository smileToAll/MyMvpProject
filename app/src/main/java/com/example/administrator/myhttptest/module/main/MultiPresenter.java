package com.example.administrator.myhttptest.module.main;

import android.content.Context;

import com.example.administrator.myhttptest.net.bean.MeiZhi;
import com.example.administrator.myhttptest.common.handler.progresshandler.ProgressHandler;
import com.example.administrator.myhttptest.net.model.home.HomeModel;
import com.example.administrator.myhttptest.net.http.BaseDataObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lijie on 2017/12/19.
 */

public class MultiPresenter implements MultiContract.presenter {
    HomeModel mModel;
    MultiContract.view mView;
    Context mContext;

    public MultiPresenter(MultiContract.view view, Context context) {
        mView = view;
        mContext=context;
        mModel =new HomeModel();
    }

    @Override
    public void getData(int page) {
        mModel.getListData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseDataObserver<MeiZhi>(new ProgressHandler() {
                    @Override
                    public void loadingStart() {
                        mView.showProgressDialog();
                    }

                    @Override
                    public void loadingComplete() {
                        mView.dismissProgressDialog();
                    }
                }) {
                    @Override
                    public void onDataNext(MeiZhi meiZhi) {
                        mView.setListData(meiZhi);
                    }
                });
    }
}
