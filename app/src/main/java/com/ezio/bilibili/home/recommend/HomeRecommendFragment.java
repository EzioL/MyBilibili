package com.ezio.bilibili.home.recommend;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezio.bilibili.R;
import com.ezio.bilibili.adapter.HomeRecommendAdapter;
import com.ezio.bilibili.base.RxLazyFragment;
import com.ezio.bilibili.entity.RecommendBannerInfo;
import com.ezio.bilibili.entity.recommend.RecommendInfo;
import com.ezio.bilibili.network.HttpResult;
import com.ezio.bilibili.network.RetrofitHelper;
import com.ezio.bilibili.widget.banner.BannerEntity;
import com.ezio.bilibili.widget.banner.BannerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author：Ezio on 2016/12/20.
 */
public class HomeRecommendFragment extends RxLazyFragment {
    HomeRecommendAdapter mAdapter;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    View mHeadBannerView;
    BannerView mBannerView;
    private List<RecommendBannerInfo> mBannerInfoList = new ArrayList<>();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void finishCreateView(Bundle state) {

        isPrepared = true;
        lazyLoad();
        isPrepared = false;
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        initRecyclerView();
        initBannerView();
        initRefreshLayout();
        loadData();
    }

    @Override
    protected void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    public void setRefreshing(final boolean refreshing) {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(refreshing);
            }
        });
    }

    @Override
    protected void initRecyclerView() {
        mAdapter = new HomeRecommendAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initBannerView() {
        mHeadBannerView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_banner, (ViewGroup) mRecyclerView.getParent(), false);
        mBannerView = (BannerView) mHeadBannerView.findViewById(R.id.bannerView);
        mAdapter.addHeaderView(mHeadBannerView);
    }

    @Override
    protected void loadData() {
        RetrofitHelper.getBiliAppAPI()
                .getRecommendedBannerInfo()
                .compose(this.<HttpResult<RecommendBannerInfo[]>>bindToLifecycle())
                .flatMap(new Func1<HttpResult<RecommendBannerInfo[]>, Observable<HttpResult<RecommendInfo[]>>>() {
                    @Override
                    public Observable<HttpResult<RecommendInfo[]>> call(HttpResult<RecommendBannerInfo[]> httpResult) {
                        mBannerInfoList = Arrays.asList(httpResult.getData());
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
                        if (mSwipeRefreshLayout.isRefreshing()){
                            setRefreshing(false);
                        }

                        //加入轮播
                        addBanner(mBannerInfoList);
                        //...
                        List<RecommendInfo> list = Arrays.asList(httpResult.getResult());
                        mAdapter.setNewData(list);

                    }
                });

    }

    private void addBanner(List<RecommendBannerInfo> list) {
        List<BannerEntity> entityList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            BannerEntity entity = new BannerEntity();
            entity.setTitle(list.get(i).getTitle());
            entity.setImg(list.get(i).getImage());
            entity.setLink(list.get(i).getValue());
            entityList.add(entity);
        }
        mBannerView.delayTime(5).build(entityList);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
