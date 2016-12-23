package com.ezio.bilibili.widget.banner;

import java.io.Serializable;

/**
 * Author：Ezio on 2016/12/22.
 */
public class BannerEntity implements Serializable {

    /**
     * title : Last Order 我要成为看板娘!
     * img : http://i0.hdslb.com/bfs/live/6318078c434cce19d77c3e0e22cd954f32b78e0d.jpg
     * remark : 看板娘
     * link : http://live.bilibili.com/AppBanner/index?id=387
     */

    private String title;
    private String img;
    private String remark;
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
