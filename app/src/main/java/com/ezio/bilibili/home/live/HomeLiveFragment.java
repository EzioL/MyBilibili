package com.ezio.bilibili.home.live;

import android.os.Bundle;

import com.ezio.bilibili.R;
import com.ezio.bilibili.base.RxLazyFragment;

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

    }
}
