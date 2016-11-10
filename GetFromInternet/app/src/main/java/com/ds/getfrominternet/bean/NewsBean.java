package com.ds.getfrominternet.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */

public class NewsBean {

    /**
     * comment : 381
     * listimage : http://192.168.1.116:8080/img/a.jpg
     * pubdate : 2016-10-26:18
     * title : 南非沙滩上出现大片“美味”，当地人垂涎三尺却无人拾捡
     */

    private List<News> news;


    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }



    public static class News {
        private boolean isClick;
        private String comment;
        private String listimage;
        private String pubdate;
        private String title;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getListimage() {
            return listimage;
        }

        public void setListimage(String listimage) {
            this.listimage = listimage;
        }

        public String getPubdate() {
            return pubdate;
        }

        public void setPubdate(String pubdate) {
            this.pubdate = pubdate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean getIsClick(){
            return isClick;
        }
        public void setIsClick(boolean isClick) {
            this.isClick = isClick;
        }
    }
}
