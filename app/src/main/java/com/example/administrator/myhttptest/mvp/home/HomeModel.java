package com.example.administrator.myhttptest.mvp.home;

import android.content.Context;

import com.example.administrator.myhttptest.base.inters.HttpErrorHandlerIml;
import com.example.administrator.myhttptest.mvp.base.BaseModel;
import com.example.administrator.myhttptest.mvp.home.bean.BaseResponse;
import com.example.administrator.myhttptest.mvp.home.bean.MeiZhi;
import com.example.administrator.myhttptest.net.BaseDataObserver;
import com.example.administrator.myhttptest.net.IdeaApi;
import com.example.administrator.myhttptest.net.RxUtil;

/**
 * Created by lijie on 2017/8/15.
 * Model层
 */

public class HomeModel extends BaseModel implements HomeContract.Model {
    HomePresenter presenter;

    public HomeModel(Context mContext, HomePresenter presenter) {
        super(mContext);
        this.presenter = presenter;
    }

    @Override
    public void getListData(final int page) {
        IdeaApi.getApiService().getMeiziData(page)
                .compose(RxUtil.<MeiZhi>bindLifecycleAndApplySchedulers(getActivityLifecycleProvider()))
                .subscribe(new BaseDataObserver<MeiZhi>(
                    new HttpErrorHandlerIml() {
                    @Override
                    public void onHttpError(BaseResponse response) {
                        presenter.onHttpError(response);
                    }

                    @Override
                    public void onHttpException(Throwable e) {
                        presenter.onHttpException(e);
                    }
                }){
                    @Override
                    public void onDataNext(MeiZhi meiZhi) {
                        presenter.getDataSuccess(meiZhi);
                    }
                });
    }
}
