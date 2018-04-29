package com.mt.dingcan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.mt.dingcan.adapter.OrderAdapter;
import com.mt.dingcan.entity.AllOrderBean;
import com.mt.dingcan.entity.PayBean;
import com.mt.dingcan.httpnet.HttpNetApi;
import com.mt.dingcan.myapp.Myapp;
import com.mt.dingcan.utils.AssetsLoadingDialog;
import com.mt.dingcan.utils.SharedPreferences;
import com.mt.dingcan.utils.ToastUtils;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dingxujun on 2018/4/29.
 *
 * @project dingcan
 */

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvPay;
    private ListView lvOrder;
    private TextView tv_total_price;
    private String totalprice;
    private String orderid;
    private AllOrderBean allOrderBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        initView();
        initData();
    }

    private void initData() {
        if (getIntent()!=null){
            totalprice = getIntent().getStringExtra("totalprice");
            orderid = getIntent().getStringExtra("orderid");
        }
        getAllOrderList();
    }


    private void getAllOrderList() {
        AssetsLoadingDialog.showProgressDialog(this, "拼命加载中....");
        OkGo.post(HttpNetApi.getAllOrderList).
                params("userid", SharedPreferences.getInstance().getString("userid", ""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (s != null) {
                            Gson gson = new Gson();
                            allOrderBean = gson.fromJson(s, AllOrderBean.class);
                            if (allOrderBean.getReturnCode().equals("1")) {
                                ToastUtils.showToast(Myapp.getInstance(), "请求成功");
                                System.out.println("--------------------"+s);
                                showData(allOrderBean);
                            } else {
                                ToastUtils.showToast(Myapp.getInstance(), "请求失败");
                            }
                        }
                        AssetsLoadingDialog.dismiss();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        AssetsLoadingDialog.dismiss();
                    }
                });
    }

    private void showData(AllOrderBean allOrderBean) {
        OrderAdapter adapter =new OrderAdapter(this,allOrderBean.getReturnData());
         lvOrder.setAdapter(adapter);

    }

    private void initView() {
        tvPay = (TextView) findViewById(R.id.tv_pay);
        tvPay.setOnClickListener(this);
        lvOrder = (ListView) findViewById(R.id.lv_order);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        tv_total_price.setText("总价 ："+totalprice+" ¥");
    }


    @Override
    public void onClick(View v) {
        int vId = v.getId();
        switch (vId) {
            case R.id.tv_pay:
                placeOrder();
                break;
        }
    }

    private void placeOrder() {
        AssetsLoadingDialog.showProgressDialog(this,"正在支付中....");
        OkGo.post(HttpNetApi.placeOrder).
                params("userid",SharedPreferences.getInstance().getString("userid",""))
                .params("orderid",allOrderBean.getReturnData().get(0).getOrderid()).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (s!=null){
                    Gson gson =new Gson();
                    PayBean payBean = gson.fromJson(s, PayBean.class);
                    if (payBean.getReturnCode().equals("1")){
                        ToastUtils.showToast(Myapp.getInstance(),"支付成功....");
                        Intent intent =new Intent(OrderActivity.this,PaySucceedActivity.class);
                        startActivity(intent);
                        AssetsLoadingDialog.dismiss();
                    }
                }
            }
        });

    }


}
