package com.mt.dingcan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mt.dingcan.R;
import com.mt.dingcan.entity.AllOrderBean;
import com.mt.dingcan.utils.ImageType;

import java.util.List;

/**
 * Created by dingxujun on 2018/4/28.
 *
 * @project dingcan
 */

public class OrderTwoAdapter extends BaseAdapter {
    private List<AllOrderBean.ReturnDataBean.OrderinfoBean> list;
    private Context context;

    public OrderTwoAdapter(Context context) {
        this.context = context;
    }


    public void addAll(List<AllOrderBean.ReturnDataBean.OrderinfoBean> list) {
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
            holder = new MyHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_order, null);
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.tv_shop = view.findViewById(R.id.tv_shop);
            holder.tv_type = view.findViewById(R.id.tv_type);
            holder.tv_price = view.findViewById(R.id.tv_price);
            holder.tv_num = view.findViewById(R.id.tv_num);
            holder.iv_pic = view.findViewById(R.id.iv_pic);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        AllOrderBean.ReturnDataBean.OrderinfoBean bean = list.get(i);
        holder.tv_shop.setText(bean.getShopname());
        holder.tv_name.setText(bean.getVegetname());
        holder.tv_type.setText(bean.getTypename());
        holder.tv_price.setText(bean.getPrice() + " ¥");
        holder.tv_num.setText(bean.getNum());

        ImageType.setImage1(context, bean, holder.iv_pic);
        return view;
    }

    public class MyHolder {
        private TextView tv_name;
        private TextView tv_num;
        private TextView tv_price;
        private TextView tv_type;
        private TextView tv_shop;
        private ImageView iv_pic;
    }
}
