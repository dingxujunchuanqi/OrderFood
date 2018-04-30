package com.mt.dingcan;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.mt.dingcan.entity.LoginBean;
import com.mt.dingcan.httpnet.HttpNetApi;
import com.mt.dingcan.myapp.Myapp;
import com.mt.dingcan.utils.AssetsLoadingDialog;
import com.mt.dingcan.utils.SharedPreferences;
import com.mt.dingcan.utils.ToastUtils;

import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login;
    private EditText et_name;
    private EditText et_pwd;
    private String pwd;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initView();
        setListener();
    }

    private void setListener() {
        btn_login.setOnClickListener(this);
    }

    private void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                finshActivity();
                submit();
                break;
        }
    }

    private void finshActivity() {
        int size = Myapp.activityList.size();
        for (int i = 0; i < size; i++) {
            Myapp.activityList.get(i).finish();
        }
        Myapp.activityList.clear();
    }

    private void submit() {
        name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        pwd = et_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        loging();

    }

    /**
     * 登陆
     *
     * @date 创建时间 2018/4/28
     * @author dingxujun
     */
    private void loging() {
        AssetsLoadingDialog.showProgressDialog(this,"登录中....");
        OkGo.post(HttpNetApi.accountLogin).tag(this)
                .params("phone",name)
                .params("password",pwd)
                .execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson =new Gson();
                LoginBean loginBean = gson.fromJson(s, LoginBean.class);
                if (loginBean.getReturnCode().equals("1")){
                    saveUserInfo(loginBean);
                    Toast.makeText(getApplicationContext(), loginBean.getReturnMsg(), Toast.LENGTH_SHORT).show();
                    AssetsLoadingDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(), ContentActivty.class));
                    finish();
                }else {
                    ToastUtils.showToast(Myapp.getInstance(),"登录失败,密码错误");
                    AssetsLoadingDialog.dismiss();
                }

            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                Toast.makeText(getApplicationContext(), "登录超时", Toast.LENGTH_SHORT).show();
                AssetsLoadingDialog.dismiss();
            }
        });
    }

    /**
     *
     * 保存用户信息
     *@date 创建时间 2018/4/28
     *@author dingxujun
     *
     */

    private void saveUserInfo( LoginBean registerBean) {
       SharedPreferences.getInstance().putString("username",registerBean.getReturnData().getUsername());
        SharedPreferences.getInstance().putString("phone",registerBean.getReturnData().getPhone());
        SharedPreferences.getInstance().putString("userid",registerBean.getReturnData().getUserid());
        SharedPreferences.getInstance().putString("address",registerBean.getReturnData().getAddress());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}
