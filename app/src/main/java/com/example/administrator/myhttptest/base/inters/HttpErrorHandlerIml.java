package com.example.administrator.myhttptest.base.inters;

import com.example.administrator.myhttptest.mvp.home.bean.BaseResponse;

/**
 * Created by lijie on 2017/8/14.
 */

public interface HttpErrorHandlerIml {
    void onHttpError(BaseResponse response);
    void onHttpException(Throwable e);
}
