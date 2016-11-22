package com.zx.mvp.presenter;

import com.google.gson.Gson;
import com.zx.mvp.baen.LoginResult;
import com.zx.mvp.model.LoginModel;
import com.zx.mvp.model.OnLoginListener;
import com.zx.mvp.view.LoginView;

/**
 * 类描述：展示层：根据View的状态，展示数据
 * 创建人：郭富东
 * 创建时间：2016/11/22 09:30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LoginPresenter implements OnLoginListener{

    /** View引用 */
    private LoginView mLoginView;
    /** 数据模型引用 */
    private final LoginModel mLoginModel;

    public LoginPresenter(LoginView loginView) {
        //实例化
        mLoginView = loginView;
        mLoginModel = new LoginModel();
    }

    /** 登入的方法 */
    public  void login()
    {
        mLoginModel.login(mLoginView.getName(),mLoginView.getPassword(),this);
    }

    @Override
    public void succeed(String msg) {
        Gson gson = new Gson();
        LoginResult loginResult = gson.fromJson(msg, LoginResult.class);
        if(loginResult.code == 1){
            mLoginView.MoveToIndex();
        }else if(loginResult.code == 2){
            mLoginView.showToask("用户名或者密码错误");
        }
    }

    @Override
    public void failure() {
        mLoginView.showToask("访问失败");
    }

    @Override
    public void onUserNameErrot() {
        mLoginView.showToask("用户名错误");
    }

    @Override
    public void onUserPasswordErrot() {
        mLoginView.showToask("密码错误");
    }
}
