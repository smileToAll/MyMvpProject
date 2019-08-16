package com.example.administrator.myhttptest.net.http;

import com.example.administrator.myhttptest.net.bean.BaseResponse;
import com.example.administrator.myhttptest.common.handler.httperrorhandler.HttpErrorHandler;
import com.example.administrator.myhttptest.common.handler.progresshandler.ProgressHandler;

import io.reactivex.observers.DefaultObserver;


/**
 * Created by lijie on 2017/8/11.
 */

public abstract class BaseDataObserver<T extends BaseResponse> extends DefaultObserver<T> {

    ProgressHandler progressHandler;
    HttpErrorHandler httpErrorHandler;


    public BaseDataObserver() {
    }

    public BaseDataObserver(HttpErrorHandler httpErrorHandler) {
        this.httpErrorHandler = httpErrorHandler;
    }

    public BaseDataObserver(ProgressHandler progressHandler, HttpErrorHandler httpErrorHandler) {
        this.progressHandler = progressHandler;
        this.httpErrorHandler = httpErrorHandler;

    }
    public BaseDataObserver(ProgressHandler progressHandler){
        this.progressHandler=progressHandler;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(progressHandler !=null) {
            progressHandler.loadingStart();
        }
    }
    @Override
    public void onNext(T t) {
//        if (t != null && (t.isSuccess() || t.isDataEmpty()))


        if(progressHandler !=null) {
            progressHandler.loadingStart();
        }
            if (t != null) {
                onDataNext(t);
            } else {
                if(httpErrorHandler !=null)
                httpErrorHandler.onHttpError(t);
            }
    }

    @Override
    public void onError(Throwable t) {
        if(progressHandler !=null){
            progressHandler.loadingComplete();
        }
        t.printStackTrace();
        try {
            httpErrorHandler.onHttpException(t);
        }catch (NullPointerException e){

        }

    }

    @Override
    public void onComplete() {
        if(progressHandler !=null) {
            progressHandler.loadingComplete();
        }
    }
    public abstract void onDataNext(T t);
}
