package com.ds.myapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public class ShuffliingInfo {

    /**
     * imgurl : http://192.168.1.114:8080/news/img/carousel/new04.jpg
     * title : 超级周来临 汇市或剧烈波动
     */

    private List<ImagesBean> images;

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean {
        private String imgurl;
        private String title;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
