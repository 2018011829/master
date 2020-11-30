package com.example.projecttraining.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class DoNotSlideUtil extends ViewPager {
    private boolean isCanScroll = true;

    public DoNotSlideUtil(Context context) {
        super(context);
    }

    public DoNotSlideUtil(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置其是否能滑动换页
     * @param isCanScroll false 不能换页， true 可以滑动换页
     */
    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onTouchEvent(ev);

    }
}