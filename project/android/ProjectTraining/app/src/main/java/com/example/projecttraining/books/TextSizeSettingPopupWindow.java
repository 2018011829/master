package com.example.projecttraining.books;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.projecttraining.R;

public class TextSizeSettingPopupWindow extends PopupWindow {

    private View mMenuView;
    private ImageView ivChangeSmall;
    private ImageView ivChangeBig;
    private TextView tvShowSize;
    private TextView tvChangeTextStyle;

    public TextSizeSettingPopupWindow(Context context, View.OnClickListener itemsOnclick,int textSize) {
        //加载布局 获取控件
        findViews(context,itemsOnclick,textSize);
        //设置弹出框的宽
        this.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
        //设置弹出框的高
        this.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        // 实例化一个ColorDrawable颜色为透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //设置弹出框可点击
        this.setFocusable(true);
        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int heightBottom=mMenuView.findViewById(R.id.bottom).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < heightBottom) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    /**
     * 加载布局 获取控件
     * @param context
     * @param itemsOnclick
     */
    private void findViews(Context context, View.OnClickListener itemsOnclick,int textSize) {
        this.mMenuView= LayoutInflater.from(context).inflate(R.layout.textsize_settings_popup_window,null);
        this.setContentView(mMenuView);
        //获取控件
        this.ivChangeBig=mMenuView.findViewById(R.id.iv_change_text_big);
        this.ivChangeSmall=mMenuView.findViewById(R.id.iv_change_text_small);
        this.tvShowSize=mMenuView.findViewById(R.id.tv_show_text_size);
        tvShowSize.setText(textSize+"");
        this.tvChangeTextStyle=mMenuView.findViewById(R.id.tv_change_text_style);
        ivChangeSmall.setOnClickListener(itemsOnclick);
        ivChangeBig.setOnClickListener(itemsOnclick);
        tvChangeTextStyle.setOnClickListener(itemsOnclick);
    }
}
