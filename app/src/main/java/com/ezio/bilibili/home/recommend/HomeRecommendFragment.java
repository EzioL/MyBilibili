package com.ezio.bilibili.home.recommend;

import android.os.Bundle;

import com.ezio.bilibili.R;
import com.ezio.bilibili.base.RxLazyFragment;

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

    }
}
