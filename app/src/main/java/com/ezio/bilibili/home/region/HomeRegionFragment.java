package com.ezio.bilibili.home.region;

import android.os.Bundle;

import com.ezio.bilibili.R;
import com.ezio.bilibili.base.RxLazyFragment;

/**
 * Author：Ezio on 2016/12/20.
 */
public class HomeRegionFragment extends RxLazyFragment {
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
    public void finishCreateView(Bundle state) {

    }
}
