package com.ds.xingzuo;

/**
 * Created by Administrator on 2016/11/4.
 */

public class DataBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"star":"shizi","day":{"love_txt":"爱情旺旺日，今日的异性缘太好了，快对喜欢的人放电吧！","work_txt":"一切都很正常，实力会得到真实反应。","work_star":4,"money_star":4,"lucky_color":"玉米黄","lucky_time":"上午10:00--11:00","love_star":5,"lucky_direction":"正西方","summary_star":5,"time":"20161104","money_txt":"财运不错的一天，只要积极的开发就能有所收获，对于商务、业务、开发工作者影响较大。","general_txt":"说话有说服力，易得到他人赏识，名声有机会得到彰显。有时间不妨多阅读，可激发灵感，让你收获不少。文案、策划工作者易有灵感，特别是晚上文思泉涌，易有不错的创作出炉。","grxz":"处女座","lucky_num":"7","day_notice":"灵感不断，有利创作。"}}
     */

    public int showapi_res_code;
    public String showapi_res_error;
    /**
     * ret_code : 0
     * star : shizi
     * day : {"love_txt":"爱情旺旺日，今日的异性缘太好了，快对喜欢的人放电吧！","work_txt":"一切都很正常，实力会得到真实反应。","work_star":4,"money_star":4,"lucky_color":"玉米黄","lucky_time":"上午10:00--11:00","love_star":5,"lucky_direction":"正西方","summary_star":5,"time":"20161104","money_txt":"财运不错的一天，只要积极的开发就能有所收获，对于商务、业务、开发工作者影响较大。","general_txt":"说话有说服力，易得到他人赏识，名声有机会得到彰显。有时间不妨多阅读，可激发灵感，让你收获不少。文案、策划工作者易有灵感，特别是晚上文思泉涌，易有不错的创作出炉。","grxz":"处女座","lucky_num":"7","day_notice":"灵感不断，有利创作。"}
     */

    public ShowapiResBodyBean showapi_res_body;

    public static class ShowapiResBodyBean {
        public int ret_code;
        public String star;
        /**
         * love_txt : 爱情旺旺日，今日的异性缘太好了，快对喜欢的人放电吧！
         * work_txt : 一切都很正常，实力会得到真实反应。
         * work_star : 4
         * money_star : 4
         * lucky_color : 玉米黄
         * lucky_time : 上午10:00--11:00
         * love_star : 5
         * lucky_direction : 正西方
         * summary_star : 5
         * time : 20161104
         * money_txt : 财运不错的一天，只要积极的开发就能有所收获，对于商务、业务、开发工作者影响较大。
         * general_txt : 说话有说服力，易得到他人赏识，名声有机会得到彰显。有时间不妨多阅读，可激发灵感，让你收获不少。文案、策划工作者易有灵感，特别是晚上文思泉涌，易有不错的创作出炉。
         * grxz : 处女座
         * lucky_num : 7
         * day_notice : 灵感不断，有利创作。
         */

        public DayBean day;

        public static class DayBean {
            public String love_txt;
            public String work_txt;
            public int work_star;
            public int money_star;
            public String lucky_color;
            public String lucky_time;
            public int love_star;
            public String lucky_direction;
            public int summary_star;
            public String time;
            public String money_txt;
            public String general_txt;
            public String grxz;
            public String lucky_num;
            public String day_notice;
        }
    }
}
