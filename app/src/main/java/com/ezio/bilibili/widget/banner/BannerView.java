package com.ezio.bilibili.widget.banner;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ezio.bilibili.BrowserActivity;
import com.ezio.bilibili.R;
import com.ezio.bilibili.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Author：Ezio on 2016/12/24.
 * 自定义轮播
 */
public class BannerView extends RelativeLayout implements BannerAdapter.ViewPagerOnItemClickListener {

    @Bind(R.id.layout_banner_viewpager)
    ViewPager viewPager;
    @Bind(R.id.layout_banner_points_group)
    LinearLayout points;
    private CompositeSubscription compositeSubscription;
    private Context mContext;
    //默认轮播时间8s
    private int delayTime = 8;
    private List<ImageView> imageViewList;
    private List<BannerEntity> bannerList;
    //选中显示Indicator
    private int selectRes = R.drawable.shape_dots_select;
    //非选中显示Indicator
    private int unSelcetRes = R.drawable.shape_dots_default;
    //当前页的下标
    private int currrentPos;


    public BannerView(Context context) {
        super(context);
        mContext = context;
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_custom_banner, this);
        ButterKnife.bind(this);

    }

    /**
     * 设置轮播间隔时间
     *
     * @param time 轮播间隔时间，单位秒
     */
    public BannerView delayTime(int time) {

        this.delayTime = time;
        return this;
    }

    /**
     * 设置Points资源 Res
     *
     * @param selectRes   选中状态
     * @param unselcetRes 非选中状态
     */
    public void setPointsRes(int selectRes, int unselcetRes) {

        this.selectRes = selectRes;
        this.unSelcetRes = unselcetRes;
    }

    /**
     * 图片轮播需要传入参数
     */
    public void build(List<BannerEntity> list) {
        destory();
        if (list.size() == 0) {
            this.setVisibility(GONE);
            return;
        }

        bannerList = new ArrayList<>();
        bannerList.addAll(list);
        final int pointSize;
        pointSize = bannerList.size();

        if (pointSize == 2) {
            bannerList.addAll(list);
        }
        //判断是否清空 指示器点
        if (points.getChildCount() != 0) {
            points.removeAllViewsInLayout();
        }

        //初始化与个数相同的指示器点

        for (int i = 0; i < pointSize; i++) {
            View dot = new View(getContext());
            dot.setBackgroundResource(unSelcetRes);
            int dp = DisplayUtil.dp2px(getContext(), 5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dp, dp);
            params.leftMargin = 10;
            dot.setLayoutParams(params);
            dot.setEnabled(false);
            points.addView(dot);
        }

        points.getChildAt(0).setBackgroundResource(selectRes);


        imageViewList = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            ImageView mImageView = new ImageView(getContext());
            Glide.with(getContext())
                    .load(bannerList.get(i).getImg())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .dontAnimate()
                    .into(mImageView);
            imageViewList.add(mImageView);
        }

        //监听图片轮播，改变指示器状态

        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int pos) {

                pos = pos % pointSize;
                currrentPos = pos;
                for (int i = 0; i < points.getChildCount(); i++) {
                    points.getChildAt(i).setBackgroundResource(unSelcetRes);
                }
                points.getChildAt(pos).setBackgroundResource(selectRes);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        if (isStopScroll) {
                            startScroll();
                        }
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        stopScroll();
                        compositeSubscription.unsubscribe();
                        break;
                }
            }
        });


        BannerAdapter bannerAdapter = new BannerAdapter(imageViewList);
        viewPager.setAdapter(bannerAdapter);
        bannerAdapter.notifyDataSetChanged();
        bannerAdapter.setmViewPagerOnItemClickListener(this);

        //图片开始轮播

        startScroll();


    }

    private boolean isStopScroll = false;

    /**
     * 图片开始轮播
     */
    private void startScroll() {

        compositeSubscription = new CompositeSubscription();
        isStopScroll = false;
        Subscription subscription = Observable.timer(delayTime, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (isStopScroll) {
                            return;
                        }

                        isStopScroll = true;
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                });
        compositeSubscription.add(subscription);
    }

    /**
     * 图片停止轮播
     */
    private void stopScroll() {

        isStopScroll = true;
    }

    public void destory() {

        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }

    /*
     * ViewPager的Item点击回调事件
     */

    @Override
    public void onItemClick() {
        Intent intent = new Intent(mContext, BrowserActivity.class);
        intent.putExtra("url", bannerList.get(currrentPos).getLink());
        intent.putExtra("title", bannerList.get(currrentPos).getTitle());
        mContext.startActivity(intent);
    }
}
