package com.mt.dingcan.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.mt.dingcan.OrderActivity;
import com.mt.dingcan.R;
import com.mt.dingcan.adapter.StoreAdapter;
import com.mt.dingcan.entity.AddOrderBean;
import com.mt.dingcan.entity.DataBean;
import com.mt.dingcan.entity.OddorDanBean;
import com.mt.dingcan.entity.QueryShopCartBean;
import com.mt.dingcan.httpnet.HttpNetApi;
import com.mt.dingcan.myapp.Myapp;
import com.mt.dingcan.utils.AssetsLoadingDialog;
import com.mt.dingcan.utils.SharedPreferences;
import com.mt.dingcan.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/27.
 */

public class ShoppingListFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private ListView lv_shop;
    private TextView tv_total_price;
    private Button btn_deal;
    private List<QueryShopCartBean.ReturnDataBean> returnData;
    private List<DataBean> dataBeanList;
    private OddorDanBean oddorDanBean;
    private Gson gson;
    private StoreAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.shop_frag, null);
        initView(view);
        setListener();
        initData();
        return view;
    }

    private void setListener() {
        btn_deal.setOnClickListener(this);
    }

    private void initView(View view) {
        EventBus.getDefault().register(this);
        lv_shop = (ListView) view.findViewById(R.id.lv_shop);
        tv_total_price = (TextView) view.findViewById(R.id.tv_total_price);
        btn_deal = (Button) view.findViewById(R.id.btn_deal);
        btn_deal.setVisibility(View.VISIBLE);
    }

    private void initData() {
        queryShopCart();
    }


    /**
     * 查询购物车
     *
     * @date 创建时间 2018/4/28
     * @author dingxujun
     */
    private void queryShopCart() {
        AssetsLoadingDialog.showProgressDialog(getActivity(), "拼命加载中....");
        OkGo.post(HttpNetApi.queryShopCart).params("userid", SharedPreferences.getInstance().getString("userid", "")).

                execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                try {
                                    if (s != null) {
                                        Gson gson = new Gson();
                                        QueryShopCartBean bean = gson.fromJson(s, QueryShopCartBean.class);
                                        if (bean.getReturnCode().equals("1")) {
                                            showData(bean);
                                            AssetsLoadingDialog.dismiss();
                                        }
                                    }else {
                                        btn_deal.setVisibility(View.GONE);
                                        ToastUtils.showToast(Myapp.getInstance(),"服务器异常");
                                        AssetsLoadingDialog.dismiss();
                                    }
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    btn_deal.setVisibility(View.GONE);
                                    ToastUtils.showToast(Myapp.getInstance(),"没有餐品信息哦");
                                    if (adapter!=null&&adapter.list!=null){
                                        adapter.list.clear();
                                        adapter.notifyDataSetChanged();
                                    }
                                    AssetsLoadingDialog.dismiss();
                                }
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                AssetsLoadingDialog.dismiss();
                            }
                        }
                );

    }

    private void showData(QueryShopCartBean bean) {
        returnData = bean.getReturnData();
        if (returnData.size()==0){
            btn_deal.setVisibility(View.GONE);
        }
        if (adapter==null) {
            adapter = new StoreAdapter(context, returnData);
            lv_shop.setAdapter(adapter);
        }else {
            adapter.list=returnData;
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_deal:
                SelleDta();
                addOrder();
                break;
        }
    }

    /**
     * 整理下单传的数据
     *
     * @date 创建时间 2018/4/28
     * @author dingxujun
     */
    private void SelleDta() {


        dataBeanList = new ArrayList<>();
        for (QueryShopCartBean.ReturnDataBean bean : returnData) {
            DataBean porderBean = new DataBean();
            porderBean.setNum(bean.getNum());
            porderBean.setPrice(bean.getPrice());
            porderBean.setVegetid(bean.getId() + "");
            dataBeanList.add(porderBean);
        }

        oddorDanBean = new OddorDanBean();
        oddorDanBean.setData(dataBeanList);
        oddorDanBean.setUserid(SharedPreferences.getInstance().getString("userid", ""));
    }

    /**
     * 下单
     *
     * @date 创建时间 2018/4/28
     * @author dingxujun
     */
    private void addOrder() {
        AssetsLoadingDialog.showProgressDialog(getActivity(), "下单中.....");
        gson = new Gson();
        String json = gson.toJson(oddorDanBean);
        OkGo.post(HttpNetApi.XaddOrder).tag(this)
                .params("param", json).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                AddOrderBean bean = gson.fromJson(s, AddOrderBean.class);
                if (null != bean) {
                    if (bean.getReturnCode().equals("1")) {
                        ToastUtils.showToast(context, "下单成功");
                        Intent intent = new Intent(Myapp.getInstance(), OrderActivity.class);
                        intent.putExtra("totalprice", bean.getReturnData().getTotal());
                        intent.putExtra("orderid", bean.getReturnData().getOrderid());
                        startActivity(intent);
                        AssetsLoadingDialog.dismiss();
                    }
                }
            }
        });
    }
    /**
     * 刷新界面
     *
     * @data 创建时间 2017/10/29
     * @author dingxujun
     */
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void receiver(String s) {
        if (s.equals("delete")){
          initData();
            System.out.println("weeeeeeeeeeeeeeeeeeeee");
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
