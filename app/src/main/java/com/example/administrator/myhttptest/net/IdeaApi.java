package com.example.administrator.myhttptest.net;

import com.example.administrator.myhttptest.base.MyApp;
import com.example.administrator.myhttptest.constant.ApiConstant;
import com.example.administrator.myhttptest.constant.AppConstant;
import com.example.administrator.myhttptest.widget.utils.LogUtils;
import com.example.administrator.myhttptest.widget.utils.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lijie on 2017/8/11.
 */

public class IdeaApi {
    private static Retrofit retrofit;
    private IdeaApiService apiService;
    private IdeaApi(){
        File cacheFile=new File(MyApp.getInstance().getApplicationContext().getCacheDir(),"cache");
        Cache cache=new Cache(cacheFile,1024*1024*100);//100Mb

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Interceptors interceptors=new Interceptors();
        ArrayList<Interceptor> interceptorsArr = interceptors.getInterceptors();
        for(Interceptor inter:interceptorsArr){
            builder.addInterceptor(inter);
        }
        OkHttpClient okHttpClient= builder.readTimeout(AppConstant.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                      .connectTimeout(AppConstant.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                      .addNetworkInterceptor(new HttpCacheInterceptor())
                      .cache(cache)
                      .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(IdeaApiService.class);
    }
    //  创建单例
    private static class SingletonHolder {
        private static final IdeaApi INSTANCE = new IdeaApi();
    }
    public static IdeaApiService getApiService() {
        return SingletonHolder.INSTANCE.apiService;
    }

    class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isConnected(MyApp.getInstance())) {  //没网强制从缓存读取
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                LogUtils.d("Okhttp", "no network");
            }
            Response originalResponse = chain.proceed(request);
            if (NetworkUtils.isConnected(MyApp.getInstance())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    }
}
