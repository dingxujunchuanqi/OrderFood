package com.mt.dingcan.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.Window;

/**
 * 自己设计的加载进度条和对话框
 * Created by dingxujun on 2017/10/25.
 *
 * @project rt21dms_new
 */

public class AssetsLoadingDialog {

    private final Activity context;
    private final boolean finishFlag;
    private ProgressDialog dialogProgress;
    private  static ProgressDialog dialog;


    public AssetsLoadingDialog(Activity context, boolean finishFlag) {
        this.context=context;
        this.finishFlag=finishFlag;
        if (dialogProgress == null) {
            dialogProgress = new ProgressDialog(context);
            dialogProgress.setCancelable(false);
            dialogProgress.setCanceledOnTouchOutside(false);
        }
    }

    public void showLoadingDialog(String title) {
        dialogProgress.setTitle(title);
        dialogProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        if (!dialogProgress.isShowing()) {
            dialogProgress.show();
        }
        dialogProgress.setOnCancelListener(dialog1 -> {
            if (finishFlag)
            context.finish();
        });
    }

    public void dismissDialog() {
        dialogProgress.dismiss();
    }

    public void setMax(int progress) {
        dialogProgress.setMax(progress);
    }

    public void setIncremenProgress(int progress) {
        dialogProgress.incrementProgressBy(progress);
    }

    public void setProgress(int progress) {
        dialogProgress.setProgress(progress);
    }


/**自定义关闭 对话框的同时 关闭Activity
*@data 创建时间 2017/12/15
*@author dingxujun
*/
    public  static void showProgressDialog(Activity context, String message) {
        if (!context.isFinishing()) {
            dialog = new ProgressDialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(true);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            if (TextUtils.isEmpty(message)) {
                message = "加载数据中...";
            }
            dialog.setMessage(message);
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }
        }
        dialog.setOnCancelListener(dialog1 -> {
            context.finish();
        });
    }
    public static void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
