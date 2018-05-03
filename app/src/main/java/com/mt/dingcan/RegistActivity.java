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
import com.mt.dingcan.entity.RegisterBean;
import com.mt.dingcan.httpnet.HttpNetApi;
import com.mt.dingcan.myapp.Myapp;
import com.mt.dingcan.utils.AssetsLoadingDialog;
import com.mt.dingcan.utils.SharedPreferences;
import com.mt.dingcan.utils.ToastUtils;

import okhttp3.Call;
import okhttp3.Response;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_phone;
    private EditText et_pwd;
    private Button btn_regist;
    private String pwd;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_activity);
        initView();
        setListener();
    }

    private void setListener() {
        btn_regist.setOnClickListener(this);
    }

    private void initView() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_regist = (Button) findViewById(R.id.btn_regist);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_regist:
                submit();
                break;
        }
    }

    /**
     * 注册
     *
     * @date 创建时间 2018/4/28
     * @author dingxujun
     */
    private void registerPost() {
        AssetsLoadingDialog.showProgressDialog(this, "网络接中....");
        OkGo.post(HttpNetApi.register)
                .tag(this).params("phone", phone)
                .params("password", pwd)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        RegisterBean registerBean = gson.fromJson(s, RegisterBean.class);
                        if (registerBean.getReturnCode().equals("1")) {
                            ToastUtils.showToast(getApplicationContext(), registerBean.getReturnMsg());
                            AssetsLoadingDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        } else {
                            ToastUtils.showToast(getApplicationContext(), "注册失败,用户已经存在");
                            AssetsLoadingDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.showToast(getApplicationContext(), "网络连接超时");
                        AssetsLoadingDialog.dismiss();
                    }
                });

    }


    private void submit() {
        phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        pwd = et_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        registerPost();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}
