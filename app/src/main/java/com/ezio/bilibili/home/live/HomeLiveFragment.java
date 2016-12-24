package com.ezio.bilibili.home.live;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezio.bilibili.R;
import com.ezio.bilibili.adapter.HomeLiveAdapter;
import com.ezio.bilibili.base.RxLazyFragment;
import com.ezio.bilibili.entity.live.LiveAppIndexInfo;
import com.ezio.bilibili.network.HttpResult;
import com.ezio.bilibili.network.RetrofitHelper;
import com.ezio.bilibili.widget.banner.BannerEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author：Ezio on 2016/12/20.
 */
public class HomeLiveFragment extends RxLazyFragment {

    HomeLiveAdapter mAdapter;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

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
        initRecyclerView();

        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new HomeLiveAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
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
        RetrofitHelper.getLiveAPI()
                .getLiveAppIndex()
                .compose(this.<HttpResult<LiveAppIndexInfo>>bindToLifecycle())
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Subscriber<HttpResult<LiveAppIndexInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Ezio", "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(HttpResult<LiveAppIndexInfo> liveAppIndexInfoHttpResult) {
                        Log.e("Ezio", "onNext: " + liveAppIndexInfoHttpResult.getCode());
                        List<LiveAppIndexInfo> list = new ArrayList<LiveAppIndexInfo>();
                        LiveAppIndexInfo info = liveAppIndexInfoHttpResult.getData();
                        info.setItemType(LiveAppIndexInfo.BANNER);
                        list.add(info);
//                        info.setItemType(2);
//                        list.add(info);
                        mAdapter.setNewData(list);
                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
