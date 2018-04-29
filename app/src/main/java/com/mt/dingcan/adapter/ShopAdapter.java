package com.mt.dingcan.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mt.dingcan.R;
import com.mt.dingcan.entity.QueryCanpinTypeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/27.
 */

public class ShopAdapter extends BaseAdapter {
    private final List<QueryCanpinTypeBean.ReturnDataBean.ShopnameArrayBean> list;
    private Context context;
    public ShopAdapter(Context context, List<QueryCanpinTypeBean.ReturnDataBean.ShopnameArrayBean>list) {
        this.context = context;
        this.list=list;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_type,null);
            holder = new MyHolder();
            holder.tv_shop = view.findViewById(R.id.tv_name);
            holder.iv_pic = view.findViewById(R.id.iv_pic);

            view.setTag(holder);
        }else {
            holder = (MyHolder) view.getTag();
        }
        holder.tv_shop.setText(list.get(i).getShopname());
        holder.iv_pic.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.shop));
        return view;
    }
    public class MyHolder{
        private TextView tv_shop;
        private ImageView iv_pic;
    }
}
