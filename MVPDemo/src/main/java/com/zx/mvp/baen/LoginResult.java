package com.zx.mvp.baen;

/**
 * 类描述：登入返回结果实体类
 * 创建人：郭富东
 * 创建时间：2016/11/22 09:32
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LoginResult {

     /*
        "code": 1, //结果码
    "msg": "succeed",
    "data": {
        "other": "/images/8A572F08CE874555A80034CDC104CB82/D28584D018.jpg", //头像路径
        "name": "yt6e2ea5da01e846e198d5cc9146a0919b", //环信ID
        "nickname": "翡翠产品",  //昵称
        "uuid": "8A572F08CE874555A80034CDC104CB82",  //用户表中主键
        "username": "feicuicp" //用户名
    }
   */

    public int code;
    public String msg;
    public DataBean data;

    public static class DataBean {


        public String other;
        public String name;
        public String nickname;
        public String uuid;
        public String username;
    }



}
