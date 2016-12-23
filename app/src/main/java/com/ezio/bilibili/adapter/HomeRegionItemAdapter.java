package com.ezio.bilibili.adapter;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ezio.bilibili.R;
import com.ezio.bilibili.entity.region.RegionTypesInfo;

import java.util.List;

/**
 * Author：Ezio on 2016/12/23.
 */
public class HomeRegionItemAdapter extends BaseQuickAdapter<RegionTypesInfo.DataBean, BaseViewHolder> {
    private String[] itemNames = new String[]{
            "直播", "番剧", "动画",
            "音乐", "舞蹈", "游戏",
            "科技", "生活", "鬼畜",
            "时尚", "广告", "娱乐",
            "电影", "电视剧", "游戏中心",
    };

    private int[] itemIcons = new int[]{
            R.drawable.ic_category_live, R.drawable.ic_category_t13, R.drawable.ic_category_t1,
            R.drawable.ic_category_t3, R.drawable.ic_category_t129, R.drawable.ic_category_t4,
            R.drawable.ic_category_t36, R.drawable.ic_category_t160, R.drawable.ic_category_t119,
            R.drawable.ic_category_t155, R.drawable.ic_category_t165, R.drawable.ic_category_t5,
            R.drawable.ic_category_t23, R.drawable.ic_category_t11, R.drawable.ic_category_game_center
    };


    public HomeRegionItemAdapter(List<RegionTypesInfo.DataBean> data) {
        super(R.layout.item_home_region, data);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(BaseViewHolder baseViewHolder, final RegionTypesInfo.DataBean dataBean) {
        int position = baseViewHolder.getLayoutPosition();
        baseViewHolder.setText(R.id.item_title, dataBean.getName())
                .setImageDrawable(R.id.item_icon, mContext.getDrawable(itemIcons[position]));
        baseViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, dataBean.getName()+"", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
