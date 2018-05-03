package com.mt.dingcan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.mt.dingcan.R;
import com.mt.dingcan.adapter.QueryAdapter;
import com.mt.dingcan.entity.QueryCanpinBean;
import com.mt.dingcan.entity.QueryCanpinTypeBean;
import com.mt.dingcan.httpnet.HttpNetApi;
import com.mt.dingcan.myapp.Myapp;
import com.mt.dingcan.utils.AssetsLoadingDialog;
import com.mt.dingcan.utils.ToastUtils;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by admin on 2018/4/27.
 */

public class StoreListFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private EditText et_name;
    private Button btn_search;
    private NiceSpinner tv_name;
    private NiceSpinner tv_type;
    private NiceSpinner tv_store;
    private ListView lv_store;
    private View view;
    private List<QueryCanpinTypeBean.ReturnDataBean.TypenameArrayBean> typeList;
    private List<QueryCanpinTypeBean.ReturnDataBean.VegetnameArrayBean> nameList;
    private List<QueryCanpinTypeBean.ReturnDataBean.ShopnameArrayBean> shopList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        view = LayoutInflater.from(context).inflate(R.layout.search_frag, null);
        initView(view);
        setListener();
        initData();
        return view;
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
        tv_name = (NiceSpinner) view.findViewById(R.id.tv_name);
        tv_type = (NiceSpinner) view.findViewById(R.id.tv_type);
        tv_store = (NiceSpinner) view.findViewById(R.id.tv_store);
        lv_store = (ListView) view.findViewById(R.id.lv_store);
        tv_name.setTextColor(context.getResources().getColor(R.color.tv_select_color));
        tv_type.setTextColor(context.getResources().getColor(R.color.tv_select_color));
        tv_store.setTextColor(context.getResources().getColor(R.color.tv_select_color));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                submit();
                break;
        }
    }

    private void setNameSpiner() {
        List<String> list = new ArrayList<>();
        for (QueryCanpinTypeBean.ReturnDataBean.VegetnameArrayBean s :
                nameList) {
            list.add(s.getVegetname());
        }
        tv_name.attachDataSource(list);//设置数据的第一种样式

        setNameSpinerListenr(list);
    }


    private void setNameSpinerListenr(List<String> list) {
        tv_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                queryCanpin(list.get(position), null, null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    private void setTypeSpiner() {
        List<String> list = new ArrayList<>();
        for (QueryCanpinTypeBean.ReturnDataBean.TypenameArrayBean s :
                typeList) {
            list.add(s.getTypename());
        }
        tv_type.attachDataSource(list);//设置数据的第一种样式

        setTypeSpinerListenr(list);
    }

    private void setTypeSpinerListenr(List<String> list) {
        tv_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                queryCanpin(null, list.get(position), null);
                //  System.out.println("-----------parent--------------" + dataset.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setShopSpiner() {
        List<String> list = new ArrayList<>();
        for (QueryCanpinTypeBean.ReturnDataBean.ShopnameArrayBean s :
                shopList) {
            list.add(s.getShopname());
        }
        tv_store.attachDataSource(list);//设置数据的第一种样式

        setShopSpinerListenr(list);
    }

    private void setShopSpinerListenr(List<String> list) {
        tv_store.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                queryCanpin(null, null, list.get(position));
                //  System.out.println("-----------parent--------------" + dataset.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void submit() {
        String name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getContext(), "请输入餐品名", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something
    }

    private void initData() {
        queryCanpinType();
        queryCanpin(null, null, null);
    }

    /**
     * 请求类型数据
     *
     * @date 创建时间 2018/4/28
     * @author dingxujun
     */

    private void queryCanpinType() {
        AssetsLoadingDialog.showProgressDialog(getActivity(), "拼命加载中...");
        OkGo.post(HttpNetApi.queryCanpinType).tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            Gson gson = new Gson();
                            QueryCanpinTypeBean queryCanpinTypeBean = gson.fromJson(s, QueryCanpinTypeBean.class);
                            if (queryCanpinTypeBean.getReturnCode().equals("1")) {
                                typeList = queryCanpinTypeBean.getReturnData().getTypenameArray();
                                nameList = queryCanpinTypeBean.getReturnData().getVegetnameArray();
                                shopList = queryCanpinTypeBean.getReturnData().getShopnameArray();
                                setNameSpiner();
                                setShopSpiner();
                                setTypeSpiner();
                            } else {
                                ToastUtils.showToast(Myapp.getInstance(), "参数异常");
                            }
                            System.out.println("---------------eeee------------");
                            AssetsLoadingDialog.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtils.showToast(Myapp.getInstance(), "服务器异常");
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        AssetsLoadingDialog.dismiss();
                        ToastUtils.showToast(Myapp.getInstance(), "网络连接超时...");
                    }
                });
    }

    /**
     * 查询接口
     *
     * @date 创建时间 2018/4/28
     * @author dingxujun
     */

    private void queryCanpin(String name, String type, String shop) {
        HashMap map = new HashMap();
        if (null != name) {
            map.put("vegetname", name);
        }
        if (null != type) {
            map.put("typename", type);
        }
        if (null != shop) {
            map.put("shopname", shop);
        }
        OkGo.post(HttpNetApi.queryCanpin).tag(this)
                .params(map)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            Gson gson = new Gson();
                            QueryCanpinBean Bean = gson.fromJson(s, QueryCanpinBean.class);
                            if (Bean.getReturnCode().equals("1")) {
                                showAlldata(Bean);
                            } else {
                                ToastUtils.showToast(context, "网络异常");
                            }
                            AssetsLoadingDialog.dismiss();
                        } catch (Exception e) {
                            ToastUtils.showToast(context, "网络异常");
                            AssetsLoadingDialog.dismiss();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        AssetsLoadingDialog.dismiss();
                    }
                });

    }


    /**
     * 展示列表数据
     *
     * @date 创建时间 2018/4/28
     * @author dingxujun
     */
    private void showAlldata(QueryCanpinBean bean) {
        QueryAdapter adapter = new QueryAdapter(context, bean.getReturnData());
        lv_store.setAdapter(adapter);

    }


}
