package com.ezio.bilibili.utils;

import android.content.Context;

/**
 * Author：Ezio on 2016/12/24.
 * 屏幕像素转换
 */
public class DisplayUtil {
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
