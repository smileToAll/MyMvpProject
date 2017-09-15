package com.example.administrator.myhttptest.mvp.base;

import com.example.administrator.myhttptest.mvp.home.bean.BaseResponse;

/**
 * Created by lijie on 2017/9/11.
 */

public interface BaseView {
    void showProgress();
    void hideProgress();
    void onHttpError(BaseResponse response);
    void onHttpException(Throwable e);
}
