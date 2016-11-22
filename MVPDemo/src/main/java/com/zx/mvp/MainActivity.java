package com.zx.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zx.mvp.presenter.LoginPresenter;
import com.zx.mvp.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/* MVP模式：
           Model(模型)：处理业务逻辑，获取数据（本地或者网络）
           View(视图)：UI页面，展示数据，监听用户事件通知presenter
           Presenter(主持人，中间者）：展示层，获取Model的数据，
           监听和管理View状态，根据View的状态的展示数据
*/
public class MainActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.ed_mian_name)
    EditText mEdMianName;
    @BindView(R.id.ed_mian_password)
    EditText mEdMianPassword;
    @BindView(R.id.bt_main_login)
    Button mBtMainLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public String getName() {
        return mEdMianName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mEdMianPassword.getText().toString().trim();
    }

    @Override
    public void showToask(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void MoveToIndex() {
        Toast.makeText(this, "登入成功，跳转到首页", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.bt_main_login)
    public void onClick() {//登入按钮
        LoginPresenter loginPresenter = new LoginPresenter(this);
        loginPresenter.login();
    }
}
