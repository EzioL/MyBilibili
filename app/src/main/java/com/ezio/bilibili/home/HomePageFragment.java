package com.ezio.bilibili.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ezio.bilibili.MainActivity;
import com.ezio.bilibili.R;
import com.ezio.bilibili.adapter.HomePagerAdapter;
import com.ezio.bilibili.base.RxLazyFragment;
import com.ezio.bilibili.widget.CircleImageView;
import com.ezio.bilibili.widget.NoScrollViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author：Ezio on 2016/12/19.
 * <p>
 * 首页模块主界面
 */
public class HomePageFragment extends RxLazyFragment {

    @Bind(R.id.toolbar_user_avatar)
    CircleImageView mToolbarUserAvatar;
    @Bind(R.id.navigation_layout)
    LinearLayout mNavigationLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.sliding_tabs)
    SlidingTabLayout mSlidingTab;
    @Bind(R.id.view_pager)
    NoScrollViewPager mViewPager;
    @Bind(R.id.search_view)
    MaterialSearchView mSearchView;

    public static HomePageFragment newInstance() {

        Bundle args = new Bundle();
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    public void finishCreateView(Bundle state) {
        setHasOptionsMenu(true);
        initToolBar();
        initSearchView();
        initViewPager();
    }

    private void initToolBar() {
        mToolbar.setTitle("");
        ((MainActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbarUserAvatar.setImageResource(R.drawable.ic_avatar1);
    }

    private void initSearchView() {
        //初始化SearchBar
        mSearchView.setVoiceSearch(false);
        mSearchView.setCursorDrawable(R.drawable.custom_cursor);
        mSearchView.setEllipsize(true);
        mSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
//        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener()
//        {
//
//            @Override
//            public boolean onQueryTextSubmit(String query)
//            {
//
//                TotalStationSearchActivity.launch(getActivity(), query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText)
//            {
//
//                return false;
//            }
//        });
    }

    private void initViewPager() {
        HomePagerAdapter mHomeAdapter = new HomePagerAdapter(getChildFragmentManager(),
                getApplicationContext());
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(mHomeAdapter);
        mSlidingTab.setViewPager(mViewPager);
        mViewPager.setCurrentItem(1);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);

        // 设置SearchViewItemMenu
        MenuItem item = menu.findItem(R.id.id_action_search);
        mSearchView.setMenuItem(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.id_action_game:
                //游戏中心
                //   startActivity(new Intent(getActivity(), GameCentreActivity.class));
                break;

            case R.id.id_action_download:
                //离线缓存
                //startActivity(new Intent(getActivity(), OffLineDownloadActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.navigation_layout)
    void toggleDrawer() {

        Activity activity = getActivity();
        if (activity instanceof MainActivity)
            ((MainActivity) activity).toggleDrawer();
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
