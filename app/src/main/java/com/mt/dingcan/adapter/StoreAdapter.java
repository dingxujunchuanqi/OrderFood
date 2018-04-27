package com.mt.dingcan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mt.dingcan.R;

/**
 * Created by Administrator on 2018/4/27.
 */

public class StoreAdapter extends BaseAdapter {
    private Context context;

    public StoreAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_store, null);
            holder = new MyHolder();
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.tv_count = view.findViewById(R.id.tv_count);

            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }

        return view;
    }

    private class MyHolder {
        private TextView tv_name;
        private TextView tv_count;
    }
}
