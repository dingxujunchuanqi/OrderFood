package com.mt.dingcan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mt.dingcan.fragment.ShoppingListFragment;
import com.mt.dingcan.fragment.StoreListFragment;
import com.mt.dingcan.fragment.UserFragment;

/**
 * Created by admin on 2018/4/27.
 */

public class ContentActivty extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout fl_content;
    private TextView tv_list;
    private TextView tv_user;
    private TextView tv_shop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity);
        initView();
        initData();
        setViewState();
        setListener();
    }

    private void setViewState() {
        setTextColor(R.color.tv_select_color,R.color.tv_unselect_color,R.color.tv_unselect_color);
    }

    private void initData() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        StoreListFragment listFragment = new StoreListFragment();
        transaction.replace(R.id.fl_content, listFragment).commit();
    }

    private void setListener() {
        tv_list.setOnClickListener(this);
        tv_user.setOnClickListener(this);
        tv_shop.setOnClickListener(this);
    }

    private void initView() {
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        tv_list = (TextView) findViewById(R.id.tv_list);
        tv_user = (TextView) findViewById(R.id.tv_user);
        tv_shop = (TextView) findViewById(R.id.tv_shop);
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        switch (vId) {
            case R.id.tv_list:
                setTextColor(R.color.tv_select_color,R.color.tv_unselect_color,R.color.tv_unselect_color);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                StoreListFragment listFragment = new StoreListFragment();
                transaction.replace(R.id.fl_content, listFragment).commit();
                break;
            case R.id.tv_user:
                setTextColor(R.color.tv_unselect_color, R.color.tv_unselect_color,R.color.tv_select_color);
                FragmentTransaction userTransaction = getSupportFragmentManager().beginTransaction();
                UserFragment userFragment = new UserFragment();
                userTransaction.replace(R.id.fl_content, userFragment).commit();
                break;
            case R.id.tv_shop:
                setTextColor(R.color.tv_unselect_color, R.color.tv_select_color,R.color.tv_unselect_color);
                FragmentTransaction shopTransaction = getSupportFragmentManager().beginTransaction();
                ShoppingListFragment shopFragment = new ShoppingListFragment();
                shopTransaction.replace(R.id.fl_content, shopFragment).commit();
                break;
        }
    }

    @SuppressLint("ResourceType")
    private void setTextColor(int select, int unselect, int unselect1) {
        tv_list.setTextColor(getResources().getColor(select));
        tv_shop.setTextColor(getResources().getColor(unselect));
        tv_user.setTextColor(getResources().getColor(unselect1));
    }
}
