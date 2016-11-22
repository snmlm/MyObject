package com.zx.mvp.common;

/**
 * 类描述：公共的网络访问地址
 * 创建人：郭富东
 * 创建时间：2016/11/22 09:22
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface Common {

     /** 服务器地址 */
     String BASE_URL = "http://wx.feicuiedu.com:9094/yitao/";

     /** 注册接口*/
     String REGISTER = "UserWeb?method=register";

    /** 登录接口*/
     String LOGIN = "UserWeb?method=login";
}
