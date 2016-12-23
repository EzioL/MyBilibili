package com.ezio.bilibili.network.api;

import java.util.List;

/**
 * Author：Ezio on 2016/12/16.
 */
public class AndroidDataResult {

    /**
     * error : false
     * results : [{"_id":"585b6f28421aa9723d29b98d","createdAt":"2016-12-22T14:14:00.935Z","desc":"Android上专为视屏直播打造的轻量级弹幕库（100多kb）","images":["http://img.gank.io/46af56ae-e5dd-414b-bb4d-3446fce5d8d2"],"publishedAt":"2016-12-23T11:41:19.908Z","source":"web","type":"Android","url":"https://github.com/hpdx/DanmukuLight","used":true,"who":"android_ls"},{"_id":"585b7213421aa9723a5a77ac","createdAt":"2016-12-22T14:26:27.638Z","desc":"一款 FlipBoard 翻页风格的 Gank.io 客户端","images":["http://img.gank.io/60632446-0486-4b27-be66-28cb09cf8440"],"publishedAt":"2016-12-23T11:41:19.908Z","source":"web","type":"Android","url":"https://github.com/yiyuanliu/FlipGank","used":true,"who":"Yiyuan Liu"},{"_id":"585b89d8421aa97240ef9f1a","createdAt":"2016-12-22T16:07:52.14Z","desc":"一个方便使用的跑马灯效果library","images":["http://img.gank.io/0934d64a-6ebb-4dc0-9c77-a69aaddeb8f3"],"publishedAt":"2016-12-23T11:41:19.908Z","source":"web","type":"Android","url":"https://github.com/gongwen/MarqueeViewDemo","used":true,"who":"龚文"},{"_id":"585c8fea421aa97240ef9f22","createdAt":"2016-12-23T10:46:02.158Z","desc":"Android 卡片滑动出现和消失效果，很好看哟。","images":["http://img.gank.io/6a6c4171-550e-41e3-b9ef-6cb013e904a6","http://img.gank.io/ac470892-4909-49e5-bdc6-24705eb6e497"],"publishedAt":"2016-12-23T11:41:19.908Z","source":"chrome","type":"Android","url":"https://github.com/mancj/SlideUp-Android","used":true,"who":"代码家"},{"_id":"585a44dd421aa97237bca8e3","createdAt":"2016-12-21T17:01:17.988Z","desc":"极速增量编译工具 Freeline 的超详细说明文档！","publishedAt":"2016-12-22T11:34:37.39Z","source":"chrome","type":"Android","url":"https://www.freelinebuild.com/docs/zh_cn/###","used":true,"who":"q"},{"_id":"585b37e1421aa9723d29b98b","createdAt":"2016-12-22T10:18:09.712Z","desc":"支持自定义主题的 Google Map 工具类。","images":["http://img.gank.io/e350a5a9-a260-4bbc-b44d-3d532e44d662","http://img.gank.io/f18652eb-e4cf-46ea-b254-678d56321b4d"],"publishedAt":"2016-12-22T11:34:37.39Z","source":"chrome","type":"Android","url":"https://github.com/jineshfrancs/ThemedGoogleMap","used":true,"who":"代码家"},{"_id":"585b3802421aa9723a5a77a8","createdAt":"2016-12-22T10:18:42.995Z","desc":"时间选择器，选择特定间隔时间段。","images":["http://img.gank.io/76fde4d6-dadf-4de9-b7d1-08415aa193bf","http://img.gank.io/1ccb67ef-b2e4-4e3c-9d83-735ccea5737b"],"publishedAt":"2016-12-22T11:34:37.39Z","source":"chrome","type":"Android","url":"https://github.com/skedgo/DateTimeRangePicker","used":true,"who":"代码家"},{"_id":"585b38a5421aa9723a5a77aa","createdAt":"2016-12-22T10:21:25.68Z","desc":"帮助你在 App 端，查看 Logcat 输出信息，测试的时候会有帮助。","images":["http://img.gank.io/9c406acf-b67f-4341-beda-c6fb8f573024"],"publishedAt":"2016-12-22T11:34:37.39Z","source":"chrome","type":"Android","url":"https://github.com/munix/LogcatTextView","used":true,"who":"代码家"},{"_id":"5857dd91421aa9723d29b978","createdAt":"2016-12-19T21:16:01.2Z","desc":"仿微信掉落表情包效果","images":["http://img.gank.io/6b479506-1f46-49d4-b8a9-34e21c4b566b"],"publishedAt":"2016-12-21T11:37:35.629Z","source":"chrome","type":"Android","url":"https://github.com/Luolc/EmojiRain","used":true,"who":"Jason"},{"_id":"5859f3ad421aa97237bca8da","createdAt":"2016-12-21T11:14:53.515Z","desc":"锤子开源了自家 Rom 的 Kernel 部分代码 Build。","publishedAt":"2016-12-21T11:37:35.629Z","source":"chrome","type":"Android","url":"https://github.com/SmartisanTech","used":true,"who":"daimajia"}]
     */

    private boolean error;
    /**
     * _id : 585b6f28421aa9723d29b98d
     * createdAt : 2016-12-22T14:14:00.935Z
     * desc : Android上专为视屏直播打造的轻量级弹幕库（100多kb）
     * images : ["http://img.gank.io/46af56ae-e5dd-414b-bb4d-3446fce5d8d2"]
     * publishedAt : 2016-12-23T11:41:19.908Z
     * source : web
     * type : Android
     * url : https://github.com/hpdx/DanmukuLight
     * used : true
     * who : android_ls
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
