package com.ezio.bilibili.home.attendtion;

import android.os.Bundle;

import com.ezio.bilibili.R;
import com.ezio.bilibili.base.RxLazyFragment;

/**
 * Author：Ezio on 2016/12/20.
 */
public class HomeAttentionFragment extends RxLazyFragment {
    public static HomeAttentionFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeAttentionFragment fragment = new HomeAttentionFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_attention;
    }

    @Override
    public void finishCreateView(Bundle state) {

    }
}
