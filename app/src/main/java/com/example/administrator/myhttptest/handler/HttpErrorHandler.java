package com.example.administrator.myhttptest.handler;

import android.content.Context;

import com.example.administrator.myhttptest.base.inters.HttpErrorHandlerIml;
import com.example.administrator.myhttptest.mvp.home.bean.BaseResponse;
import com.example.administrator.myhttptest.utils.NetworkUtils;
import com.example.administrator.myhttptest.utils.ToastUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

/**
 * Created by lijie on 2017/8/14.
 */

public class HttpErrorHandler implements HttpErrorHandlerIml {
    private Context context;

    public HttpErrorHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onHttpError(BaseResponse response) {
        //在这里处理网络访问错误的情况，由于现在的BaseResponse中没有内容，所以这里不做操作

//        String message = response.getMessage();
//        if(!TextUtils.isEmpty(message)){
//            //token失效
//            if(response.getCode()==9002){
//                //1.清除用户数据
//                ToastUtils.showShortToast(context,"授权失败，请重新登录");//2
//                //3.回应用首页
//            }else{
//                ToastUtils.showShortToast(context,response.getMessage());
//            }
//        }else{
//            ToastUtils.showShortToast(context, "出小差了，请稍等哦~");
//            Log.e("==message=","===="+message);
//        }
    }

    @Override
    public void onHttpException(Throwable e) {
        e.printStackTrace();
        if (!NetworkUtils.isConnected(context)) {
            ToastUtils.showShortToast(context, "当前无可用的网络连接\n请打开手机网络开关");
            return;
        }
        if (e instanceof HttpException) {
            HttpException e1 = (HttpException) e;
            //ToastUtils.showShortToast(context, String.format("Code %s\n请检查网络连接并重试", e1.code()));
            ToastUtils.showShortToast(context, "请检查网络连接并重试哦~");
        } else if (e instanceof IOException) {
            //ToastUtils.showShortToast(context, e.getClass().getSimpleName() + "\n请检查网络连接并重试");
            ToastUtils.showShortToast(context, "请检查网络连接并重试~");
        } else {
            // ToastUtils.showShortToast(context, "出小差了，请稍等哦~"+e.getMessage());
        }

    }
}
