package com.zx.mvp.view;

/**
 * 类描述：
 * 创建人：郭富东
 * 创建时间：2016/11/22 09:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface LoginView {

    String getName();
    String getPassword();
    void showToask(String msg);
    /** 成功之后要做的操作 */
    void MoveToIndex();


}
