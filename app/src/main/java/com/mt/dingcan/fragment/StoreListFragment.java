package com.mt.dingcan.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mt.dingcan.R;
import com.mt.dingcan.adapter.StoreAdapter;

/**
 * Created by admin on 2018/4/27.
 */

public class StoreListFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private EditText et_name;
    private Button btn_search;
    private TextView tv_name;
    private TextView tv_type;
    private TextView tv_store;
    private ListView lv_store;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        view = LayoutInflater.from(context).inflate(R.layout.search_frag, null);
        initView(view);
        setViewState();
        setListener();
        initData();
        return view;
    }

    private void setViewState() {
        setTextColor(R.color.tv_select_color, R.color.tv_unselect_color, R.color.tv_unselect_color);
    }

    private void setListener() {
        btn_search.setOnClickListener(this);
        tv_name.setOnClickListener(this);
        tv_type.setOnClickListener(this);
        tv_store.setOnClickListener(this);
    }

    private void initView(View view) {
        et_name = (EditText) view.findViewById(R.id.et_name);
        btn_search = (Button) view.findViewById(R.id.btn_search);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_type = (TextView) view.findViewById(R.id.tv_type);
        tv_store = (TextView) view.findViewById(R.id.tv_store);
        lv_store = (ListView) view.findViewById(R.id.lv_store);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                submit();
                break;
            case R.id.tv_name:
                setTextColor(R.color.tv_select_color, R.color.tv_unselect_color, R.color.tv_unselect_color);
                break;
            case R.id.tv_type:
                setTextColor(R.color.tv_unselect_color, R.color.tv_select_color, R.color.tv_unselect_color);
                break;
            case R.id.tv_store:
                setTextColor(R.color.tv_unselect_color, R.color.tv_unselect_color, R.color.tv_select_color);
                break;
        }
    }

    private void submit() {
        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getContext(), "请输入餐品名", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something
    }

    @SuppressLint("ResourceType")
    private void setTextColor(int select, int unselect, int unselect1) {
        tv_name.setTextColor(getResources().getColor(select));
        tv_type.setTextColor(getResources().getColor(unselect));
        tv_store.setTextColor(getResources().getColor(unselect1));
    }

    private void initData() {
        StoreAdapter storeAdapter = new StoreAdapter(context);
        lv_store.setAdapter(storeAdapter);
    }
}
