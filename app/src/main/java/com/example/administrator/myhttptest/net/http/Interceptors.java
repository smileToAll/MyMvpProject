package com.example.administrator.myhttptest.net.http;


import android.text.TextUtils;

import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2016/3/9.
 * 拦截器
 */
public class Interceptors {

    private ArrayList<Interceptor> mInterceptors = new ArrayList<>();

    public Interceptors() {
        //添加公共参数
//        Interceptor queryParameterInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                // Request originalRequest = chain.request();
//                Request.Builder builder = chain.request().newBuilder();
//                builder.addHeader("version", BuildConfig.VERSION_NAME);
//                String userId = SpUtil.find(AppConstant.KEY_USERID);
//                String sessionKey = SpUtil.find(AppConstant.KEY_SESSION_KEY);
//                if(!TextUtils.isEmpty(userId)&&!TextUtils.isEmpty(sessionKey)){
//
//                    Log.e("header",""+userId +"   "+ EncryptUtils.encryptMD5ToString((userId+sessionKey).toUpperCase()));
//                    builder.addHeader("userid", userId);
//                    builder.addHeader("appsign", EncryptUtils.encryptMD5ToString((userId+sessionKey).toUpperCase()));
//                }
//                Request request = builder.build();
//                return chain.proceed(request);
//                /*
//                 String urlString =  originalRequest.url().toString();
//                if((!TextUtils.isEmpty(urlString))&&urlString.contains(ApiConstant.URLPREFIX)) {
//                    HttpUrl modifiedUrl = originalRequest.url().newBuilder()
//                            .addQueryParameter("sign", String.valueOf(AppConstant.SIGN))
//                            .addQueryParameter("version", String.valueOf(AppConstant.VERSION_CODE))
//                            .addQueryParameter("imei", DeviceUtil.getDeviceid(XqzApplication.getInstance()))
//                            .addQueryParameter("token", SpUtil.find(AppConstant.KEY_LOGIN_TOKEN))
//                            .addQueryParameter("userid", SpUtil.find(AppConstant.KEY_USERID))
//                            .addQueryParameter("imeimsg", DeviceUtil.getPhoneinfo())
//                            .build();
//                    Request request = originalRequest.newBuilder().url(modifiedUrl).build();
//                    back chain.proceed(request);
//                }*/
//            }
//        };
//        mInterceptors.add(queryParameterInterceptor);

        //添加请求log打印
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (TextUtils.isEmpty(message))
                    return;
                //如果收到想响应是json才打印
                if (message.startsWith("{") || message.startsWith("[")) {
                   // Log.e("收到响应:",message);
                }
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mInterceptors.add(interceptor);
    }


    public ArrayList<Interceptor> getInterceptors() {
        return mInterceptors;
    }
}
