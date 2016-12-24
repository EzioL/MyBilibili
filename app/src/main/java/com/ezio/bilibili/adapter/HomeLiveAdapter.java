package com.ezio.bilibili.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ezio.bilibili.R;
import com.ezio.bilibili.entity.live.LiveAppIndexInfo;
import com.ezio.bilibili.widget.banner.BannerView;

import java.util.List;

/**
 * Author：Ezio on 2016/12/24.
 */
public class HomeLiveAdapter extends BaseMultiItemQuickAdapter<LiveAppIndexInfo, BaseViewHolder> {


    public HomeLiveAdapter(List<LiveAppIndexInfo> data) {
        super(data);
        addItemType(LiveAppIndexInfo.BANNER, R.layout.layout_banner);
        addItemType(LiveAppIndexInfo.ENTRABCE, R.layout.layout_recyclerview);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, LiveAppIndexInfo liveAppIndexInfo) {
        switch (liveAppIndexInfo.getItemType()) {
            case LiveAppIndexInfo.BANNER:
                initBanner(baseViewHolder, liveAppIndexInfo);
                break;
            case LiveAppIndexInfo.ENTRABCE:
                initEntrance(baseViewHolder, liveAppIndexInfo);
                break;
        }
    }

    private void initBanner(BaseViewHolder baseViewHolder, LiveAppIndexInfo liveAppIndexInfo) {

        BannerView bannerView = baseViewHolder.getView(R.id.bannerView);
        bannerView.delayTime(5).build(liveAppIndexInfo.getBanner());
    }

    private void initEntrance(BaseViewHolder baseViewHolder, LiveAppIndexInfo liveAppIndexInfo) {
        liveAppIndexInfo.getEntranceIcons();
    }
}
