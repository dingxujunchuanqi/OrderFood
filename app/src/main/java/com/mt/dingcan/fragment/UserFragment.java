package com.mt.dingcan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mt.dingcan.R;
import com.mt.dingcan.utils.SharedPreferences;

/**
 * Created by admin on 2018/4/27.
 */

public class UserFragment extends Fragment {
    private Context context;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_address;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.user_frag, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_name.setText(SharedPreferences.getInstance().getString("username",""));
        tv_phone.setText(SharedPreferences.getInstance().getString("phone",""));
        tv_address.setText(SharedPreferences.getInstance().getString("address",""));
    }
}
