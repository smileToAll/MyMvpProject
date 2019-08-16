package com.example.administrator.myhttptest.common.handler.httperrorhandler;

import com.example.administrator.myhttptest.net.bean.BaseResponse;

/**
 * Created by lijie on 2017/8/14.
 */

public interface HttpErrorHandler {
    void onHttpError(BaseResponse response);
    void onHttpException(Throwable e);
}
