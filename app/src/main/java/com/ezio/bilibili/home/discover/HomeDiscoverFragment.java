package com.ezio.bilibili.home.discover;

import android.os.Bundle;

import com.ezio.bilibili.R;
import com.ezio.bilibili.base.RxLazyFragment;

/**
 * Author：Ezio on 2016/12/20.
 */
public class HomeDiscoverFragment extends RxLazyFragment {
    public static HomeDiscoverFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeDiscoverFragment fragment = new HomeDiscoverFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_discover;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}
