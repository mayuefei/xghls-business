package com.xghls.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.xghls.R;


/**
 * Created by myf on 2017/8/11.
 */

public class MyProgressDialog extends AlertDialog {
    private TextView mTextView;
    private CountDownTimer mCountDownTimer;
    // TODO: 2017/9/15 修改时间为30000L
    private final static long COUNT_DOWN_TIME_TOTAL = 30000L;
    //    private final static long COUNT_DOWN_TIME_TOTAL = 10000L;
    private final static long COUNT_DOWN_TIME_TICK = 1000L;
    // 创建的时间
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long time = 0;
    private TextView mCountDown;

    public MyProgressDialog(Context context) {
        super(context);
    }
    public MyProgressDialog(Context context, int theme) {
        super(context, theme);
    }
    public MyProgressDialog(Context context, int theme ,boolean isBackDismiss) {
        super(context,theme);
        // 点击提示框外面是否取消提示框
        setCanceledOnTouchOutside(false);
        // 点击返回键是否取消提示框
        setCancelable(isBackDismiss);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);// Dialog界面布局
        mCountDown = (TextView) findViewById(R.id.count_down);
        mCountDownTimer = new CountDownTimer(COUNT_DOWN_TIME_TOTAL, COUNT_DOWN_TIME_TICK) {
            public void onTick(long millisUntilFinished) {// 定时执行的任务
                mCountDown.setText(String.format(getContext().getString(R.string.format_retry_send), millisUntilFinished / COUNT_DOWN_TIME_TICK));
            }

            public void onFinish() {
                dismiss();
            }
        };
        // 定时器启动任务
        mCountDownTimer.start();
    }
}
