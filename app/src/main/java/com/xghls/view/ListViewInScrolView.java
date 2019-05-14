package com.xghls.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by SY on 2016/6/15.
 */
public class ListViewInScrolView extends ListView {
    public ListViewInScrolView(Context context) {
        super(context);
    }

    public ListViewInScrolView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewInScrolView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //重新设置高度
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
