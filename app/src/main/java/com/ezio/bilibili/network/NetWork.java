package com.ezio.bilibili.network;

import android.os.Bundle;
import android.util.Log;

import com.ezio.bilibili.network.api.GankApi;
import com.ezio.bilibili.network.api.LiveService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.functions.Func1;

/**
 * Author：Ezio on 2016/12/23.
 */
public class NetWork {
    private static LiveService liveService;
    private static GankApi gankApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static Retrofit newInstance(String base_url) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(base_url)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
        return retrofit;
    }
//
//    public static class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
//        @Override
//        public T call(HttpResult<T> httpResult) {
//            Log.e("error", httpResult.getData().toString() + "");
//            if (httpResult.getCode() != 0) {
//                //throw new ApiException(httpResult.getCode());
//            }
//            return httpResult.getData();
//        }
//    }


    public static LiveService getLiveService() {
        if (liveService == null) {
            liveService = newInstance(LiveService.LIVE_BASE_URL).create(LiveService.class);
        }
        return liveService;
    }

    public static GankApi getGankApi() {
        if (gankApi == null) {
            gankApi = newInstance(GankApi.LIVE_BASE_URL).create(GankApi.class);
        }
        return gankApi;
    }
}
