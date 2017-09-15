package com.example.administrator.myhttptest.net;

import com.example.administrator.myhttptest.mvp.home.bean.BaseResponse;
import com.example.administrator.myhttptest.base.inters.HttpErrorHandlerIml;
import com.example.administrator.myhttptest.base.inters.ProgressHandlerIml;

import io.reactivex.observers.DefaultObserver;


/**
 * Created by lijie on 2017/8/11.
 */

public abstract class BaseDataObserver<T extends BaseResponse> extends DefaultObserver<T> {

    ProgressHandlerIml progressHandlerIml;
    HttpErrorHandlerIml httpErrorHandlerIml;


    public BaseDataObserver() {
    }

    public BaseDataObserver(HttpErrorHandlerIml httpErrorHandlerIml) {
        this.httpErrorHandlerIml = httpErrorHandlerIml;
    }

    public BaseDataObserver(ProgressHandlerIml progressHandlerIml, HttpErrorHandlerIml httpErrorHandlerIml) {
        this.progressHandlerIml = progressHandlerIml;
        this.httpErrorHandlerIml = httpErrorHandlerIml;

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(progressHandlerIml!=null) {
            progressHandlerIml.loadingStart();
        }
    }
    @Override
    public void onNext(T t) {
//        if (t != null && (t.isSuccess() || t.isDataEmpty()))


        if(progressHandlerIml!=null) {
            progressHandlerIml.loadingStart();
        }
            if (t != null) {
                onDataNext(t);
            } else {
                if(httpErrorHandlerIml!=null)
                httpErrorHandlerIml.onHttpError(t);
            }
    }

    @Override
    public void onError(Throwable t) {
        if(progressHandlerIml!=null){
            progressHandlerIml.loadingComplete();
        }
        t.printStackTrace();
        try {
            httpErrorHandlerIml.onHttpException(t);
        }catch (NullPointerException e){

        }

    }

    @Override
    public void onComplete() {
        if(progressHandlerIml!=null) {
            progressHandlerIml.loadingComplete();
        }
    }
    public abstract void onDataNext(T t);
}
