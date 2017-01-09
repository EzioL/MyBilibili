package com.ezio.bilibili.adapter;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ezio.bilibili.BrowserActivity;
import com.ezio.bilibili.R;
import com.ezio.bilibili.entity.recommend.RecommendInfo;
import com.ezio.bilibili.widget.banner.BannerEntity;
import com.ezio.bilibili.widget.banner.BannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Ezio on 2017/1/9.
 */
public class HomeRecommendAdapter extends BaseMultiItemQuickAdapter<RecommendInfo, BaseViewHolder> {

    public HomeRecommendAdapter(List<RecommendInfo> data) {
        super(data);
        addItemType(RecommendInfo.TYPE_R_L_B_R, R.layout.item_home_recommend_rlbr);
        addItemType(RecommendInfo.TYPE_WEB_ACTIVITY, R.layout.item_home_recommend_aw);
        addItemType(RecommendInfo.TYPE_NONE, R.layout.layout_none);
    }

    @Override
    public void setEmptyView(int layoutResId, ViewGroup viewGroup) {
        super.setEmptyView(layoutResId, viewGroup);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RecommendInfo recommendInfo) {
        switch (baseViewHolder.getItemViewType()) {
            case RecommendInfo.TYPE_R_L_B_R:
                initRLBR(baseViewHolder, recommendInfo);
                break;
            case RecommendInfo.TYPE_WEB_ACTIVITY:
                initWebLinkActivity(baseViewHolder, recommendInfo);
                break;
        }
    }

    private void initWebLinkActivity(BaseViewHolder baseViewHolder, final RecommendInfo recommendInfo) {
        BannerView bannerView = baseViewHolder.getView(R.id.bannerView);
        ImageView imageWebLink = baseViewHolder.getView(R.id.image_webLink);
        baseViewHolder.setVisible(R.id.bannerView, false).setVisible(R.id.image_webLink, false);
        if (recommendInfo.getType().equals(RecommendInfo.WEBLINK)) {
            baseViewHolder.setVisible(R.id.image_webLink, true)
                    .setText(R.id.item_type_tv, "话题")
                    .setImageResource(R.id.item_type_img, R.drawable.ic_header_topic);

            Glide.with(mContext)
                    .load(recommendInfo.getBody().get(0).getCover())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .dontAnimate()
                    .into(imageWebLink);

            imageWebLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, BrowserActivity.class);
                    intent.putExtra("url", recommendInfo.getBody().get(0).getParam());
                    intent.putExtra("title", recommendInfo.getBody().get(0).getTitle() + "");
                    mContext.startActivity(intent);
                }
            });

        } else {
            baseViewHolder.setVisible(R.id.bannerView, true)
                    .setText(R.id.item_type_tv, "活动中心")
                    .setImageResource(R.id.item_type_img, R.drawable.ic_header_activity_center);
            List<BannerEntity> entityList = new ArrayList<>();
            for (int i = 0; i < recommendInfo.getBody().size(); i++) {
                BannerEntity entity = new BannerEntity();
                entity.setTitle(recommendInfo.getBody().get(i).getTitle());
                entity.setImg(recommendInfo.getBody().get(i).getCover());
                entity.setLink(recommendInfo.getBody().get(i).getParam());
                entityList.add(entity);
            }
            bannerView.delayTime(5).build(entityList);
        }

    }


    private void initRLBR(BaseViewHolder baseViewHolder, RecommendInfo recommendInfo) {

        baseViewHolder.setText(R.id.item_type_tv, recommendInfo.getHead().getTitle())
                .setVisible(R.id.item_type_rank_btn, false)
                .setVisible(R.id.item_type_more, false);
        String[] regionTitles = new String[]{"动画区", "音乐区", "舞蹈区", "游戏区", "鬼畜区", "生活区", "科技区", "时尚区", "广告区", "娱乐区", "电视剧区", "电影区"};
        int[] regionImages = new int[]{};
        if (recommendInfo.getType().equals(RecommendInfo.RECOMMEND)) {
            baseViewHolder.setImageResource(R.id.item_type_img, R.drawable.ic_header_hot)
                    .setVisible(R.id.item_type_rank_btn, true);
        } else if (recommendInfo.getType().equals(RecommendInfo.LIVE)) {
            baseViewHolder.setImageResource(R.id.item_type_img, R.drawable.ic_header_movie_fall);
        } else if (recommendInfo.getType().equals(RecommendInfo.BANGUMI_2)) {
            baseViewHolder.setImageResource(R.id.item_type_img, R.drawable.search_result_ic_bangumi);
        } else if (recommendInfo.getType().equals(RecommendInfo.REGION)) {
            baseViewHolder.setImageResource(R.id.item_type_img, R.drawable.ic_header_movie_recommned);
        } else {
            baseViewHolder.setImageResource(R.id.item_type_img, R.drawable.ic_header_movie_fall);
        }

        RecyclerView recyclerView = baseViewHolder.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        RLRAdapter adapter = new RLRAdapter(recommendInfo.getBody(), recommendInfo.getType());
        recyclerView.setAdapter(adapter);


    }

    public class RLRAdapter extends BaseQuickAdapter<RecommendInfo.BodyBean, BaseViewHolder> {
        private String type;

        public RLRAdapter(List<RecommendInfo.BodyBean> data, String type) {
            super(R.layout.item_home_recommend_body, data);
            this.type = type;
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, RecommendInfo.BodyBean bodyBean) {
            ImageView videoRreImage = baseViewHolder.getView(R.id.video_preview);
            Glide.with(mContext)
                    .load(bodyBean.getCover())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .dontAnimate()
                    .into(videoRreImage);
            baseViewHolder.setText(R.id.video_title, bodyBean.getTitle())
                    .setVisible(R.id.layout_video, false)
                    .setVisible(R.id.layout_live, false)
                    .setVisible(R.id.layout_bangumi, false);
            if (type.equals(RecommendInfo.LIVE)) {
                baseViewHolder.setVisible(R.id.layout_live, true)
                        .setText(R.id.item_live_up, bodyBean.getUp())
                        .setText(R.id.item_live_online_num, bodyBean.getOnline() + "");
            } else if (type.equals(RecommendInfo.BANGUMI_2)) {
                baseViewHolder.setVisible(R.id.layout_bangumi, true)
                        .setText(R.id.item_bangumi_update, bodyBean.getDesc1());
            } else {
                baseViewHolder.setVisible(R.id.layout_video, true)
                        .setText(R.id.video_play_num, bodyBean.getPlay())
                        .setText(R.id.video_review_count, bodyBean.getDanmaku());
            }

        }
    }


}
