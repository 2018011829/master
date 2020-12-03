package com.example.projecttraining.books;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blankj.utilcode.util.BrightnessUtils;
import com.example.projecttraining.R;

public class TextSizeSettingPopupWindow extends PopupWindow {

    private View mMenuView;
    private ImageView ivChangeSmall;
    private ImageView ivChangeBig;
    private TextView tvShowSize;
    private TextView tvChangeTextStyle;
    private SeekBar changeLight; //调整系统亮度的进度条
    private int currentLight; //当前亮度


    public void setTvShowSize(int size) {

        this.tvShowSize.setText(size+"");
        this.setContentView(mMenuView);
    }

    public TextSizeSettingPopupWindow(Context context, View.OnClickListener itemsOnclick, int textSize) {
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
    private void findViews(final Context context, View.OnClickListener itemsOnclick, int textSize) {
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

        //进度条
        changeLight=mMenuView.findViewById(R.id.seekbar_change_light);
        //获取系统当前亮度
//        try {
//            currentLight=Settings.System.getInt(context.getContentResolver(),
//                    Settings.System.SCREEN_BRIGHTNESS);
//        } catch (Settings.SettingNotFoundException e) {
//            e.printStackTrace();
//        }
        currentLight=BrightnessUtils.getBrightness();
        changeLight.setProgress(currentLight);
        //设置进度条改变的监听器
        changeLight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //设置系统亮度
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //非系统签名应用，引导用户手动授权修改Settings权限
                    if (!Settings.System.canWrite(context)) {
                        Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                        intent.setData(Uri.parse("package:" + context.getPackageName()));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    } else {
                        //有了权限，具体的动作
                        Settings.System.putInt(context.getContentResolver(),
                                Settings.System.SCREEN_BRIGHTNESS, i);
                        Log.e("当前屏幕亮度", BrightnessUtils.getBrightness() +"");
                        BrightnessUtils.setBrightness((int)(255*i*0.01));
//                        tvProgress.setText("当前屏幕的亮度是：" + i + "%");
                    }
                }
                Log.e("onProgressChanged","正在拖动");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.e("onStartTrackingTouch","开始拖动");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.e("onStopTrackingTouch","停止拖动");
            }
        });
    }
}
