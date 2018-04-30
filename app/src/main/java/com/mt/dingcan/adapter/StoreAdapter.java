package com.mt.dingcan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.mt.dingcan.R;
import com.mt.dingcan.entity.AddShopCartbean;
import com.mt.dingcan.entity.QueryShopCartBean;
import com.mt.dingcan.httpnet.HttpNetApi;
import com.mt.dingcan.utils.ImageType;
import com.mt.dingcan.utils.ToastUtils;

import java.util.List;

import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/27.
 */

public class StoreAdapter extends BaseAdapter {
    public List<QueryShopCartBean.ReturnDataBean> list;
    private Context context;

    public StoreAdapter(Context context, List<QueryShopCartBean.ReturnDataBean> list) {
        this.context = context;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_shop, null);
            holder = new MyHolder();
            holder.tv_name = view.findViewById(R.id.tv_name);
            holder.tv_type = view.findViewById(R.id.tv_type);
            holder.tv_shop = view.findViewById(R.id.tv_shop);
            holder.tv_price = view.findViewById(R.id.tv_price);
            holder.add_layout = view.findViewById(R.id.add_layout);
            holder.delete_shop = view.findViewById(R.id.delete_shop);
            holder.iv_pic = view.findViewById(R.id.iv_pic);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        holder.add_layout.setVisibility(View.GONE);
        holder.delete_shop.setVisibility(View.VISIBLE);
        holder.tv_name.setText(list.get(i).getVegetname());
        holder.tv_shop.setText(list.get(i).getShopname());
        holder.tv_price.setText(list.get(i).getPrice() + "¥");
        holder.tv_name.setText(list.get(i).getVegetname());
        holder.tv_type.setText(list.get(i).getNum() + "");

        ImageType.setImage(context, list.get(i), holder.iv_pic);


        MyHolder finalHolder1 = holder;
        holder.delete_shop.setOnClickListener(v -> {
            QueryShopCartBean.ReturnDataBean itemBean = list.get(i);
            int num = itemBean.getNum();
            if (num > 0) {
                MyHolder finalHolder = finalHolder1;
                OkGo.post(HttpNetApi.deleShopCart).
                        params("id", itemBean.getId())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                //  int mumber = itemBean.getNum();
                                //  mumber = mumber - 1;
                                Gson gson = new Gson();
                                AddShopCartbean bean = gson.fromJson(s, AddShopCartbean.class);
                                if (bean.getReturnCode().equals("1")) {
                                    // itemBean.setNum(mumber);
                                    ToastUtils.showToast(context, "删除成功");
                                    EventBus.getDefault().post("delete");
                                    //   finalHolder.tv_type.setText("" + itemBean.getNum());
                                    //   notifyDataSetChanged();
                                }
                            }
                        });
            } else {
                ToastUtils.showToast(context, "商品数量已经是0了");
            }
        });


        return view;
    }

    public class MyHolder {
        private TextView tv_name;
        private TextView tv_shop;
        private TextView tv_type;
        private TextView delete_shop;
        private TextView tv_price;
        private LinearLayout add_layout;
        private ImageView iv_pic;
    }
}
