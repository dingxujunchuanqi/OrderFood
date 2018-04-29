package com.mt.dingcan.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yinl01 on 2017/6/5.
 */

public class ToastUtils {

    protected static Toast toast = null;
    private static String oldMsg;
    private static long oneTime = 0;
    private static long twoTime = 0;

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * @param context context
     * @param msg     提示信息
     */
    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (msg.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = msg;
                toast.setText(msg);
                toast.show();
            }
        }
        oneTime = twoTime;
    }

    /**
     * @param context context
     * @param resId   提示信息的资源id
     */
    public static void showToast(Context context, int resId) {
        showToast(context, context.getString(resId));
    }
}
