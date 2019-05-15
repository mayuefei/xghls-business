package com.xghls.util;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xghls.R;
import com.xghls.listener.HintOneListener;
import com.xghls.listener.HintTwoSelectListener;
import com.xghls.view.MyProgressDialog;


/**
 * Created by myf on 2017/9/20.
 */

public class DialogUtil {
    private static MyProgressDialog loadingDialog;

    /**
     * 弹出双选提示
     */
    public static void showHintTwo(Context context, String text, final HintTwoSelectListener listener) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.alert_dialog_two, null);
        final Dialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.getWindow().setContentView(layout);
        TextView textView = (TextView) layout.findViewById(R.id.tv_tishi);
        textView.setText(text);
        Button btnOK = (Button) layout.findViewById(R.id.dialog_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.makeSure();
                dialog.dismiss();
            }
        });
        Button btnCancel = (Button) layout.findViewById(R.id.dialog_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.cancel();
                dialog.dismiss();
            }
        });
    }

    /**
     * 弹框单选提示
     */
    public static void showHintOne(Context context, String text) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.alert_dialog_one, null);
        final Dialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().setContentView(layout);
        TextView textView = (TextView) layout.findViewById(R.id.text_view);
        textView.setText(text);
        Button btnOK = (Button) layout.findViewById(R.id.dialog_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 显示正在加载数据dialog
     *
     * @param context
     * @param isBackDismiss
     */
    public static void showLoadingDialog(Context context, boolean isBackDismiss) {
        if (loadingDialog == null) {
            loadingDialog = new MyProgressDialog(context, R.style.loading_dialog, isBackDismiss);
        } else {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
            loadingDialog.show();
        }
    }

    /**
     * 关闭正在加载dialog
     */
    public static void closeLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 单项提示框
     *
     * @param context  context
     * @param text     提示内容
     * @param listener 点击确定监听
     */
    public void showHintOne(Context context, String text, final HintOneListener listener) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.alert_dialog_one, null);
        final Dialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().setContentView(layout);
        TextView textView = (TextView) layout.findViewById(R.id.text_view);
        textView.setText(text);
        Button btnOK = (Button) layout.findViewById(R.id.dialog_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.makeSure();
                dialog.dismiss();
            }
        });
    }
}
