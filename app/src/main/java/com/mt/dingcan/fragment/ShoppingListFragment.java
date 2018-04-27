package com.mt.dingcan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.mt.dingcan.R;
import com.mt.dingcan.adapter.ShopAdapter;

/**
 * Created by Administrator on 2018/4/27.
 */

public class ShoppingListFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private ListView lv_shop;
    private TextView tv_total_price;
    private Button btn_deal;

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
        lv_shop = (ListView) view.findViewById(R.id.lv_shop);
        tv_total_price = (TextView) view.findViewById(R.id.tv_total_price);
        btn_deal = (Button) view.findViewById(R.id.btn_deal);
    }

    private void initData() {
        ShopAdapter shopAdapter = new ShopAdapter(context);
        lv_shop.setAdapter(shopAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_deal:
                break;
        }
    }
}
