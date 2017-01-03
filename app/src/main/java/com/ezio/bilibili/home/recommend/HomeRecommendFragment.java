package com.ezio.bilibili.home.recommend;

import android.os.Bundle;
import android.util.Log;

import com.ezio.bilibili.R;
import com.ezio.bilibili.base.RxLazyFragment;
import com.ezio.bilibili.entity.RecommendBannerInfo;
import com.ezio.bilibili.entity.recommend.RecommendInfo;
import com.ezio.bilibili.network.HttpResult;
import com.ezio.bilibili.network.RetrofitHelper;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author：Ezio on 2016/12/20.
 */
public class HomeRecommendFragment extends RxLazyFragment {
    public static HomeRecommendFragment newInstance() {

        Bundle args = new Bundle();
        HomeRecommendFragment fragment = new HomeRecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_recommend;
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
//        initRefreshLayout();
//        initRecyclerView();
        loadData();
    }

    @Override
    protected void loadData() {
        RetrofitHelper.getBiliAppAPI()
                .getRecommendedBannerInfo()
                .compose(this.<HttpResult<RecommendBannerInfo[]>>bindToLifecycle())
                .flatMap(new Func1<HttpResult<RecommendBannerInfo[]>, Observable<HttpResult<RecommendInfo[]>>>() {
                    @Override
                    public Observable<HttpResult<RecommendInfo[]>> call(HttpResult<RecommendBannerInfo[]> httpResult) {
                        //加入轮播图
                        //...
                        return RetrofitHelper.getBiliAppAPI().getRecommendedInfo();
                    }
                })
                .compose(this.<HttpResult<RecommendInfo[]>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResult<RecommendInfo[]>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Ezio", "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(HttpResult<RecommendInfo[]> httpResult) {
                        Log.e("Ezio", "onNext: " + httpResult.getData().length);
                    }
                });

//        RetrofitHelper.getBiliAppAPI()
//                .getRecommendedBannerInfo()
//                .compose(this.<HttpResult<RecommendBannerInfo[]>>bindToLifecycle())
//                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
//                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
//                .subscribe(new Subscriber<HttpResult<RecommendBannerInfo[]>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("Ezio", "onError: " + e.toString());
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<RecommendBannerInfo[]> httpResult) {
//                        Log.e("Ezio", "onNext: " + httpResult.getData().length);
//                    }
//                });
//        RetrofitHelper.getBiliAppAPI().getRecommendedInfo()
//                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
//                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
//                .subscribe(new Subscriber<HttpResult<RecommendInfo[]>>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("Ezio", "onError: " + e.toString());
//                    }
//
//                    @Override
//                    public void onNext(HttpResult<RecommendInfo[]> httpResult) {
//                        Log.e("Ezio", "onNext: " + httpResult.getResult().length);
//                    }
//                });
    }
}
