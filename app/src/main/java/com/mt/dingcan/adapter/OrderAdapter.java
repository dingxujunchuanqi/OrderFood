package com.mt.dingcan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
    private OnChildClickListener listener;
    private final OrderTwoAdapter twoAdapter;

    public OrderAdapter(Context context, List<AllOrderBean.ReturnDataBean> list) {
        this.list = list;
        this.context = context;
        twoAdapter = new OrderTwoAdapter(context);
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
            holder.shipmentstatus = view.findViewById(R.id.shipmentstatus);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        AllOrderBean.ReturnDataBean bean = list.get(i);
        holder.order_mum.setText(bean.getOrderid());
        twoAdapter.addAll(bean.getOrderinfo());
        holder.lv_allorder.setAdapter(twoAdapter);

        holder.lv_allorder.setFocusable(false);
        holder.lv_allorder.setClickable(false);
        holder.lv_allorder.setPressed(false);
        holder.lv_allorder.setEnabled(false);
        if (bean.getStatus().equals("1")) {
            holder.shipmentstatus.setText("待支付");
            holder.shipmentstatus.setBackgroundDrawable(context.getResources().
                    getDrawable(R.drawable.shape_corners));
        } else if (bean.getStatus().equals("3")) {
            holder.shipmentstatus.setText("签收");
            holder.shipmentstatus.setBackgroundDrawable(context.getResources().
                    getDrawable(R.drawable.shape_corners_yellow));
        } else {
            holder.shipmentstatus.setText("请签收");
            holder.shipmentstatus.setBackgroundDrawable(context.getResources().
                    getDrawable(R.drawable.shape_corners_yellow));
        }

        holder.shipmentstatus.setOnClickListener(v -> {
            listener.onChildClick(bean.getStatus(),bean.getOrderid());
        });
        return view;
    }

    public class MyHolder {
        private TextView order_mum;
        private MyListView lv_allorder;
        private TextView shipmentstatus;

    }
    public   interface  OnChildClickListener {
        void onChildClick(String status,String orderid);
    }
    public void setOnChildClickListener(OnChildClickListener listener){
        this.listener=listener;
    }
}
