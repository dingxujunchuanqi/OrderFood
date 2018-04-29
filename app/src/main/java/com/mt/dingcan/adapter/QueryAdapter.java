package com.mt.dingcan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.mt.dingcan.R;
import com.mt.dingcan.entity.AddShopCartbean;
import com.mt.dingcan.entity.QueryCanpinBean;
import com.mt.dingcan.httpnet.HttpNetApi;
import com.mt.dingcan.utils.SharedPreferences;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/27.
 */

public class QueryAdapter extends BaseAdapter {
    private final List<QueryCanpinBean.ReturnDataBean> list;
    private Context context;
    private int mumber;
    private MyHolder finalHolder;

    public QueryAdapter(Context context, List<QueryCanpinBean.ReturnDataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_shop, null);
            holder = new MyHolder();
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.tv_count = view.findViewById(R.id.tv_count);
            holder.tv_type = view.findViewById(R.id.tv_type);
            holder.tv_shop = view.findViewById(R.id.tv_shop);
            holder.tv_price = view.findViewById(R.id.tv_price);
            holder.btn_add = view.findViewById(R.id.btn_add);
            holder.delete_shop = view.findViewById(R.id.delete_shop);

            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        holder.delete_shop.setVisibility(View.GONE);
        holder.tv_count.setText(String.valueOf(list.get(i).getMumber()));
        holder.tv_name.setText(list.get(i).getVegetname());
        holder.tv_type.setText(list.get(i).getTypename());
        holder.tv_shop.setText(list.get(i).getShopname());
        holder.tv_price.setText(String.valueOf(list.get(i).getPrice())+" ¥");

        finalHolder = holder;
        holder.btn_add.setOnClickListener(v -> {
            QueryCanpinBean.ReturnDataBean itemBean = list.get(i);
            String userid = SharedPreferences.getInstance().getString("userid", "");
            mumber = itemBean.getMumber() + 1;
            itemBean.setMumber(mumber);
            OkGo.post(HttpNetApi.addOrder)
                    .params("userid", userid)
                    .params("vegetid", itemBean.getId())
                    .params("num", mumber)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Gson gson = new Gson();
                            AddShopCartbean bean = gson.fromJson(s, AddShopCartbean.class);
                            if (bean.getReturnCode().equals("1")) {
                                finalHolder.tv_count.setText("" + itemBean.getMumber());
                                notifyDataSetChanged();
                            }
                        }
                    });
        });

        return view;
    }


    public class MyHolder {
        private TextView tv_name;
        private TextView tv_shop;
        private TextView tv_count;
        private TextView tv_type;
        private TextView tv_price;
        private TextView btn_add;
        private TextView delete_shop;
    }
}
