package com.example.administrator.myhttptest.net;

import com.example.administrator.myhttptest.bean.MeiZhi;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lijie on 2017/8/11.
 * 构建Retrofit的service接口
 */

public interface IdeaApiService {
    // http://gank.io/api/data/数据类型/请求个数/第几页
    @GET(value = "data/福利/" + 200 + "/{page}")
    Observable<MeiZhi> getMeiziData(@Path("page") int page);
}
