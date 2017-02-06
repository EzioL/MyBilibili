package com.ezio.bilibili.home.video;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ezio.bilibili.R;
import com.ezio.bilibili.base.RxBaseActivity;
import com.ezio.bilibili.entity.recommend.RecommendInfo;
import com.ezio.bilibili.utils.SystemBarHelper;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VideoDetailsActivity extends RxBaseActivity {

    @Bind(R.id.video_preview)
    ImageView mVideoPreview;
    @Bind(R.id.tv_av)
    TextView mTvAv;
    @Bind(R.id.tv_player)
    TextView mTvPlayer;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.tab_layout)
    SlidingTabLayout mTabLayout;
    @Bind(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.main_content)
    CoordinatorLayout mMainContent;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    RecommendInfo.BodyBean mBodyBean;


    @Override
    public int getLayoutId() {
        return R.layout.activity_video_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mBodyBean = (RecommendInfo.BodyBean) getIntent().getExtras().get("data");

        Glide.with(VideoDetailsActivity.this)
                .load(mBodyBean.getCover())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(mVideoPreview);

    }

    @Override
    public void initToolBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.setDisplayHomeAsUpEnabled(true);

        //设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        //设置收缩后Toolbar上字体的颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        //设置StatusBar透明
        SystemBarHelper.immersiveStatusBar(this);
        SystemBarHelper.setHeightAndPadding(this, mToolbar);

        mTvAv.setText("av" + mBodyBean.getParam());
    }

}
