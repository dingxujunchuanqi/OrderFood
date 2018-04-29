package com.mt.dingcan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.mt.dingcan.R;
import com.mt.dingcan.entity.AllOrderBean;
import com.mt.dingcan.view.MyListView;

import java.util.List;

/**
 * Created by dingxujun on 2018/4/28.
 *
 * @project dingcan
 */

public class OrderAdapter extends BaseAdapter {
    private final List<AllOrderBean.ReturnDataBean> list;
    private Context context;

    public OrderAdapter(Context context, List<AllOrderBean.ReturnDataBean> list) {
        this.list = list;
        this.context = context;
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
            view = LayoutInflater.from(context).inflate(R.layout.allordertitle_item, null);
            holder.order_mum = view.findViewById(R.id.order_mum);
            holder.lv_allorder = view.findViewById(R.id.lv_allorder);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        AllOrderBean.ReturnDataBean bean = list.get(i);
        holder.order_mum.setText(bean.getOrderid());
        OrderTwoAdapter twoAdapter =new OrderTwoAdapter(context,bean.getOrderinfo());
        holder.lv_allorder.setAdapter(twoAdapter);

        return view;
    }

    public class MyHolder {
        private TextView order_mum;
        private MyListView lv_allorder;

    }
}
