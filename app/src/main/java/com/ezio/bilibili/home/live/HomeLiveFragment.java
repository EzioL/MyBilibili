package com.ezio.bilibili.home.live;

import android.os.Bundle;
import android.util.Log;

import com.ezio.bilibili.R;
import com.ezio.bilibili.base.RxLazyFragment;
import com.ezio.bilibili.entity.LiveAppIndexInfo;
import com.ezio.bilibili.network.HttpResult;
import com.ezio.bilibili.network.NetWork;
import com.ezio.bilibili.network.RetrofitHelper;
import com.ezio.bilibili.network.api.AndroidDataResult;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author：Ezio on 2016/12/20.
 */
public class HomeLiveFragment extends RxLazyFragment {
    public static HomeLiveFragment newInstance() {

        Bundle args = new Bundle();

        HomeLiveFragment fragment = new HomeLiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_live;
    }

    @Override
    public void finishCreateView(Bundle state) {
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        loadData();
    }

    @Override
    protected void loadData() {

        
//        NetWork.getLiveService()
//                .getLiveAppIndex()
//                .compose(this.<HttpResult<LiveAppIndexInfo>>bindToLifecycle())
//                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
//                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
//                .subscribe(new Subscriber<HttpResult<LiveAppIndexInfo>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("Ezio", "onError: " + e.getMessage());
//
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<LiveAppIndexInfo> liveAppIndexInfoHttpResult) {
//                        Log.e("Ezio", "onNext: " + liveAppIndexInfoHttpResult.getCode());
//                    }
//                });


//        NetWork.getGankApi()
//                .getAndroidData(20, 1)
//                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
//                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
//                .subscribe(new Subscriber<AndroidDataResult>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("Ezio", "onError: " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(AndroidDataResult androidDataResult) {
//                        Log.e("Ezio", "onNext: " + androidDataResult.getResults().size());
//                    }
//                });
    }
}
