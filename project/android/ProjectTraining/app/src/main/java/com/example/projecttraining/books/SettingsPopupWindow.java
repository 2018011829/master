package com.example.projecttraining.books;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.projecttraining.R;
import com.example.projecttraining.books.activity.ReadBookActivity;

public class SettingsPopupWindow extends PopupWindow {

    private View mMenuView;
    private ImageView ivBack;
    private ImageView ivListen;
    private TextView tvBeforeContent;
    private TextView tvNextContent;
    private LinearLayout linearContents;
    private LinearLayout linearTextSize;
    private LinearLayout linearNightModel;
    private ImageView ivNightModel;
    private TextView tvNightModel;
    private LinearLayout linearDownload;


    public void setCurrentRead(){
        ivListen.setImageResource(ReadBookActivity.currentReadStatus);
        this.setContentView(mMenuView);
    }

    public void setModel(){
        ivNightModel.setImageResource(ReadBookActivity.currentModelImg);
        tvNightModel.setText(ReadBookActivity.currentModelText);
        this.setContentView(mMenuView);
    }

    public SettingsPopupWindow(Context context, View.OnClickListener itemsOnclick) {
        //加载布局文件
        findViews(context,itemsOnclick);
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

                int heightTop = mMenuView.findViewById(R.id.top).getBottom();
                int heightBottom=mMenuView.findViewById(R.id.bottom).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < heightBottom && y>heightTop) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    private void findViews(Context context, View.OnClickListener itemsOnclick) {
        this.mMenuView= LayoutInflater.from(context).inflate(R.layout.settings_popup_window,null);
        this.setContentView(mMenuView);
        //获取控件
        this.ivBack=mMenuView.findViewById(R.id.iv_back);
        ivBack.setOnClickListener(itemsOnclick);
        this.ivListen=mMenuView.findViewById(R.id.iv_listen);
        ivListen.setOnClickListener(itemsOnclick);
        this.tvBeforeContent=mMenuView.findViewById(R.id.before_text);
        tvBeforeContent.setOnClickListener(itemsOnclick);
        this.tvNextContent=mMenuView.findViewById(R.id.next_text);
        tvNextContent.setOnClickListener(itemsOnclick);
        this.linearContents=mMenuView.findViewById(R.id.book_contents_list);
        linearContents.setOnClickListener(itemsOnclick);
        this.linearTextSize=mMenuView.findViewById(R.id.book_text_size);
        linearTextSize.setOnClickListener(itemsOnclick);
        this.linearNightModel=mMenuView.findViewById(R.id.book_night_model);
        linearNightModel.setOnClickListener(itemsOnclick);
        this.ivNightModel=mMenuView.findViewById(R.id.iv_night_model);
        this.tvNightModel=mMenuView.findViewById(R.id.tv_night_model);
        this.ivNightModel.setImageResource(ReadBookActivity.currentModelImg);
        this.tvNightModel.setText(ReadBookActivity.currentModelText);
        this.linearDownload=mMenuView.findViewById(R.id.book_download);
        linearDownload.setOnClickListener(itemsOnclick);
        ivListen.setImageResource(ReadBookActivity.currentReadStatus);
    }
}
