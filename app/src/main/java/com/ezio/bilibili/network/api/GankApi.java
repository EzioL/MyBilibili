package com.ezio.bilibili.network.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Author：Ezio on 2016/12/16.
 */
public interface GankApi {
    public static final String LIVE_BASE_URL = "http://gank.io/api/";

    @GET("data/Android/{number}/{page}")
    Observable<AndroidDataResult> getAndroidData(@Path("number") int number, @Path("page") int page);
}
