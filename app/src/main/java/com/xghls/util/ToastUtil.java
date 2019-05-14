package com.xghls.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 显示toast
 */
public class ToastUtil {

    private ToastUtil() {
        throw new NullPointerException();
    }

    private static Toast mToast;

    /**
     * 适用于快速替换toast内容而不用等上一个Toast显示完毕再弹出
     */
    public static void showToast(Context context, CharSequence text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
//            mToast.setGravity(Gravity.CENTER, 0, 0);
        }
        mToast.show();
    }
    /**
     * 短时间显示toast
     */
    public static void showShortToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }
    /**
     * 长时间显示toast
     * @param context
     * @param text
     */
    public static void showLongToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_LONG);
    }
}