package com.ezio.bilibili.entity.recommend;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Author：Ezio on 2017/1/3.
 */
public class RecommendInfo implements Serializable, MultiItemEntity {
    public static final int TYPE_NONE = 0;
    public static final int TYPE_R_L_B_R = 1;
    public static final int TYPE_WEB_ACTIVITY = 2;
    public static final String RECOMMEND = "recommend";
    public static final String LIVE = "live";
    public static final String BANGUMI_2 = "bangumi_2";
    public static final String WEBLINK = "weblink";
    public static final String REGION = "region";
    public static final String ACTIVITY = "activity";

    @Override
    public int getItemType() {
        if (type.equals(RECOMMEND) || type.equals(LIVE) || type.equals(BANGUMI_2) || type.equals(REGION)) {
            return TYPE_R_L_B_R;
        } else if (type.equals(WEBLINK) || type.equals(ACTIVITY)) {
            return TYPE_WEB_ACTIVITY;
        }
        return TYPE_NONE;
    }


    /**
     * type : recommend
     * head : {"param":"","goto":"","style":"gm_av","title":"热门焦点"}
     * body : [{"title":"【毒舌老外系列】【gigguk】2016年的日本动画【Part 2】【夏+秋】","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/07427c8a5179c6047fe72b9e0a538a561f4c8724.jpg","param":"7821724","goto":"av","width":350,"height":219,"play":"3.6万","danmaku":"1188"},{"title":"【少年霜×Mario】若当来世·《狐妖小红娘》OP3","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/079048c37c250b2ab8ee421b82a300776d550c0f.jpg","param":"7671725","goto":"av","width":350,"height":219,"play":"3.4万","danmaku":"324"},{"title":"【小青×E.X】Lost one的号哭【❤新年合作第一弹❤水逆三人组祝大家新年大吉吧！】","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/e093d317502b0e07ae36940229f144ccdcf520fc.jpg","param":"7792284","goto":"av","width":350,"height":219,"play":"2.4万","danmaku":"266"},{"title":"【拔总】卢西奥的新年计划","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/6cc456faba55efcf4a6882fd1e7a6d378ea39b87.jpg","param":"7793709","goto":"av","width":350,"height":219,"play":"1.2万","danmaku":"588"}]
     */

    private String type;
    /**
     * param :
     * goto :
     * style : gm_av
     * title : 热门焦点
     */

    private HeadBean head;
    /**
     * title : 【毒舌老外系列】【gigguk】2016年的日本动画【Part 2】【夏+秋】
     * style : gm_av
     * cover : http://i0.hdslb.com/bfs/archive/07427c8a5179c6047fe72b9e0a538a561f4c8724.jpg
     * param : 7821724
     * goto : av
     * width : 350
     * height : 219
     * play : 3.6万
     * danmaku : 1188
     */

    private List<BodyBean> body;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }


    public static class HeadBean {
        private String param;
        @SerializedName("goto")
        private String gotoX;
        private String style;
        private String title;

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getGotoX() {
            return gotoX;
        }

        public void setGotoX(String gotoX) {
            this.gotoX = gotoX;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class BodyBean {
        private String title;
        private String style;
        private String cover;
        private String param;
        @SerializedName("goto")
        private String gotoX;
        private int width;
        private int height;
        private String play;
        private String danmaku;
        /**
         * up_face : http://i0.hdslb.com/bfs/face/2159751e32fc196afa47f564a00c41cf4806da7d.jpg
         * up : 星妈克
         * online : 20889
         * area : 电子竞技
         * area_id : 4
         */

        private String up_face;
        private String up;
        private int online;
        private String area;
        private int area_id;
        /**
         * desc1 : 更新到第1话
         * status : 2
         */

        private String desc1;
        private int status;




        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getGotoX() {
            return gotoX;
        }

        public void setGotoX(String gotoX) {
            this.gotoX = gotoX;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getPlay() {
            return play;
        }

        public void setPlay(String play) {
            this.play = play;
        }

        public String getDanmaku() {
            return danmaku;
        }

        public void setDanmaku(String danmaku) {
            this.danmaku = danmaku;
        }

        public String getUp_face() {
            return up_face;
        }

        public void setUp_face(String up_face) {
            this.up_face = up_face;
        }

        public String getUp() {
//            if (up == null || up.equals("")) {
//                return "";
//            }
            return up;
        }

        public void setUp(String up) {
            this.up = up;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getDesc1() {
            return desc1;
        }

        public void setDesc1(String desc1) {
            this.desc1 = desc1;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
