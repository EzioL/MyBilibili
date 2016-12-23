package com.ezio.bilibili.entity;

/**
 * Created by hcc on 2016/12/22 19:25
 * <p>
 * 首页Banner推荐
 */

public class RecommendBannerInfo {

    /**
     * title : 周末放映室
     * value : http://www.bilibili.com/blackboard/activity-BJjqWeKEl.html
     * image : http://i0.hdslb.com/bfs/archive/4648fdd3b3f5daf214ccb2a4f5f745aa1401121b.jpg
     * type : 2
     * weight : 1
     * remark :
     * hash : d761aa8bdc51c4653d5c8717d1fa19a6
     */

    private String title;
    private String value;
    private String image;
    private int type;
    private int weight;
    private String remark;
    private String hash;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
