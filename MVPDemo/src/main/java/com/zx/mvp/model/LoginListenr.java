package com.zx.mvp.model;

/**
 * 类描述：登入接口
 * 创建人：郭富东
 * 创建时间：2016/11/22 09:11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface LoginListenr {
    /**
     *  用户登入方法
     * @param name  ：用户名
     * @param password ：用户密码
     * @param listener ：登入结果监听
     */
    void login(String name,String password,OnLoginListener listener);
}
