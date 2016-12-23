package com.ezio.bilibili.home.region;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ezio.bilibili.R;
import com.ezio.bilibili.adapter.HomeRegionItemAdapter;
import com.ezio.bilibili.base.RxLazyFragment;
import com.ezio.bilibili.entity.region.RegionTypesInfo;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author：Ezio on 2016/12/20.
 */
public class HomeRegionFragment extends RxLazyFragment {
    @Bind(R.id.recycle)
    RecyclerView mRecyclerView;
    HomeRegionItemAdapter mAdapter;


    public static HomeRegionFragment newInstance() {

        Bundle args = new Bundle();

        HomeRegionFragment fragment = new HomeRegionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_region;
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
        initRecyclerView();
        loadData();
    }

    @Override
    protected void loadData() {
        Observable.just(readAssetsJson())
                .compose(this.<String>bindToLifecycle())
                .map(new Func1<String, RegionTypesInfo>() {
                    @Override
                    public RegionTypesInfo call(String s) {
                        return new Gson().fromJson(s, RegionTypesInfo.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegionTypesInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RegionTypesInfo regionTypesInfo) {

                        mAdapter.setNewData(regionTypesInfo.getData());
                    }
                });
    }

    @Override
    protected void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mAdapter = new HomeRegionItemAdapter(null);
        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     * 读取assets下的json数据
     *
     * @return
     */
    private String readAssetsJson() {

        AssetManager assetManager = getActivity().getAssets();
        try {
            InputStream is = assetManager.open("region.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder stringBuilder = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
