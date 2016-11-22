package com.zx.mvp.model;

/**
 * 类描述：登入结果监听接口
 * 创建人：郭富东
 * 创建时间：2016/11/22 09:12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface OnLoginListener {

    /**
     * 访问成功
     * @param msg ：服务器返回结果
     */
    void succeed(String msg);
    /** 访问失败 */
    void failure();
    /** 用户名错误 */
    void onUserNameErrot();
    /** 密码错误 */
    void onUserPasswordErrot();
}
