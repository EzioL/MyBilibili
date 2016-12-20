package com.ezio.bilibili.home.bangumi;

import android.os.Bundle;

import com.ezio.bilibili.R;
import com.ezio.bilibili.base.RxLazyFragment;

/**
 * Author：Ezio on 2016/12/20.
 */
public class HomeBangumiFragment extends RxLazyFragment {
    public static HomeBangumiFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeBangumiFragment fragment = new HomeBangumiFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_bangumi;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}
