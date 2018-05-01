package com.mt.dingcan.utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.mt.dingcan.R;
import com.mt.dingcan.entity.AllOrderBean;
import com.mt.dingcan.entity.QueryCanpinBean;
import com.mt.dingcan.entity.QueryShopCartBean;

/**
 * Created by dingxujun on 2018/4/30.
 *
 * @project dingcan
 */

public class ImageType {
    public static void setImage(Context context, QueryShopCartBean.ReturnDataBean bean, ImageView view) {
        if (bean.getVegetname() != null) {
            if (bean.getVegetname().equals("丝瓜鸡蛋")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.jidan));
            } else if (bean.getVegetname().equals("凉拌黄瓜")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.huanggua));

            } else if (bean.getVegetname().equals("凉拌西红柿2")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.xihongshi));

            } else if (bean.getVegetname().equals("花生米")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.huashengmi));

            } else if (bean.getVegetname().equals("青椒土豆丝")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.qingjiao));

            } else if (bean.getVegetname().equals("青椒鸡蛋")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.jidan));
            } else {

            }
        }
    }

    public static void setImage1(Context context, AllOrderBean.ReturnDataBean.OrderinfoBean bean, ImageView view) {
        if (bean.getVegetname() != null) {
            if (bean.getVegetname().equals("丝瓜鸡蛋")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.jidan));
            } else if (bean.getVegetname().equals("凉拌黄瓜")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.huanggua));

            } else if (bean.getVegetname().equals("凉拌西红柿2")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.xihongshi));

            } else if (bean.getVegetname().equals("花生米")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.huashengmi));

            } else if (bean.getVegetname().equals("青椒土豆丝")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.qingjiao));

            } else if (bean.getVegetname().equals("青椒鸡蛋")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.jidan));

            } else {

            }
        }
    }

    public static void setImage2(Context context, QueryCanpinBean.ReturnDataBean bean, ImageView view) {
        if (bean.getVegetname() != null) {
            if (bean.getVegetname().equals("丝瓜鸡蛋")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.jidan));
            } else if (bean.getVegetname().equals("凉拌黄瓜")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.huanggua));

            } else if (bean.getVegetname().equals("凉拌西红柿2")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.xihongshi));

            } else if (bean.getVegetname().equals("花生米")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.huashengmi));

            } else if (bean.getVegetname().equals("青椒土豆丝")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.qingjiao));

            } else if (bean.getVegetname().equals("青椒鸡蛋")) {
                view.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.jidan));

            } else {

            }

        }
    }
}
