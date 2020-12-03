package com.example.projecttraining.books.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.projecttraining.R;
import com.example.projecttraining.books.SettingsPopupWindow;
import com.example.projecttraining.books.TextSizeSettingPopupWindow;
import com.example.projecttraining.books.entitys.Book;
import com.example.projecttraining.books.entitys.Content;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReadBookActivity extends Activity {

    private int currentTextSize; //当前字体大小
    private int changedY;
    private String flag; //标记从图书详情也跳转来
    private ScrollView scrollView;
    private ProgressBar pb; //进度条
    private TextView textContent;
    private int currentIndex; //当前章节下标
    private Content currentContent; //当前章节
    private Content nextContent; //下一章
    private Book book;
    private List<Content> contentObj; //所有的目录对象：书所在的文件名称  章节名称  开始的行数
    private ArrayList<String> data;
    private SettingsPopupWindow settingsPopupWindow; //全部设置的弹出框
    private TextSizeSettingPopupWindow textSizeSettingPopupWindow; //字体设置弹出框
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1://得到图书的文章内容
                    String strBookText = (String) msg.obj;
                    textContent.setText("         " + strBookText);
                    pb.setVisibility(View.INVISIBLE);

                    break;
                case 2: //显示错误信息
                    String errorInfo = (String) msg.obj;
                    Toast.makeText(ReadBookActivity.this,
                            errorInfo, Toast.LENGTH_SHORT).show();
                    break;
                case 3://下载
                    Toast.makeText(ReadBookActivity.this,
                            "下载成功！",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_read_book);

        scrollView = findViewById(R.id.scrollView);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                //改变后的x 改编后的y
                changedY = i;
                Log.e("改变后的y", "" + changedY);
            }
        });
        pb = findViewById(R.id.progressbar);
        pb.setVisibility(View.VISIBLE);
        textContent = findViewById(R.id.text_content);

        //获取信息
        getExtrasFromIntent();

        //如果是从章节界面跳转过来 获取对应章节内容
        if (book != null && contentObj != null && data != null && currentContent != null && nextContent != null) {
            //从服务器获取要读的书的对应章节内容
            getBookTextFromServer();
        } else {//如果是从书籍详情信息界面跳转过来 获取全本书的内容
            //读取服务端本书的所有内容
            Log.e("读取整本书的内容","开始");
            getWholeBookTextFromServer();
        }

        //TextView的点击事件  上一章  下一章  菜单设置
        textContent.setOnTouchListener(new TouchListener());
    }

    /**
     * 获取信息
     */
    private void getExtrasFromIntent() {
        Intent intent = getIntent();
        flag = intent.getStringExtra("activity");
        book = (Book) intent.getSerializableExtra("book");
        contentObj = (List<Content>) intent.getSerializableExtra("contentObj");
        data = intent.getStringArrayListExtra("contents");

        //获取要读的当前页的信息
        currentIndex = intent.getIntExtra("currentIndex", 0);
        currentContent = contentObj.get(currentIndex);
        if (currentIndex < contentObj.size() - 1) {
            nextContent = contentObj.get(currentIndex + 1);
        } else {
            nextContent = contentObj.get(currentIndex);
        }

    }

    private class TouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //获取点击的位置的坐标
                int y = (int) motionEvent.getY();
                int x = (int) motionEvent.getX();
                Log.e("x", "" + x);
                Log.e("y", "" + y);
                //获取屏幕高度、宽度
                WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                int screenWidth = windowManager.getDefaultDisplay().getWidth();
                int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
                //屏幕中间的高度
                int middenHeight = screenHeight / 2;
                //屏幕中间的宽度
                int middenWidth = screenWidth / 2;
                //点击屏幕中间
                if (middenWidth - 200 <= x && middenWidth + 200 >= x) {
                    settingsPopupWindow = new SettingsPopupWindow(getApplicationContext(),
                            itemClickListener);
                    settingsPopupWindow.showAtLocation(ReadBookActivity.this.findViewById(R.id.main),
                            Gravity.NO_GRAVITY, 0, 0);
                }
                if (0 <= x && 200 >= x) {
                    //上一章
                    if (currentIndex == 0) {
                        Toast.makeText(ReadBookActivity.this,
                                "亲，已经到头了！",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        currentIndex = currentIndex - 1;
                        currentContent = contentObj.get(currentIndex);
                        nextContent = contentObj.get(currentIndex + 1);
                        //从服务器获取上一章的内容
                        getBookTextFromServer();
                    }
                }
                if (screenWidth - 200 <= x && screenWidth >= x) {
                    //下一章
                    if (currentIndex == contentObj.size() - 1) {
                        nextContent = contentObj.get(currentIndex);
                        Toast.makeText(ReadBookActivity.this,
                                "亲，已经到最后一章了！",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        currentIndex = currentIndex + 1;
                        Log.e("currentIndex", "" + currentIndex);
                        currentContent = contentObj.get(currentIndex);
                        if (currentIndex == contentObj.size() - 1) {
                            nextContent = contentObj.get(currentIndex);
                        }else {
                            nextContent = contentObj.get(currentIndex+1);
                        }
                    }
                    //从服务器获取下一章的内容
                    getBookTextFromServer();
                }
                return true;
            }
            return false;
        }
    }

    /**
     * 点击事件的监听器
     */
    private View.OnClickListener itemClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_back://点击返回
                    //返回上一页
                    if (contentObj != null && flag == null) {
                        Intent intent = new Intent(ReadBookActivity.this, MoreContentActivity.class);
                        intent.putExtra("book", book);
                        intent.putExtra("contentObj", (Serializable) contentObj);
                        intent.putStringArrayListExtra("contents", data);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(ReadBookActivity.this, BookInfoActivity.class);
                        intent.putExtra("book", book);
                        startActivity(intent);
                    }
                    finish();
                    break;
                case R.id.iv_search://点击搜索
                    Toast.makeText(ReadBookActivity.this,
                            "点击搜索",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_listen://点击听
                    Toast.makeText(ReadBookActivity.this,
                            "点击听",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.before_text://点击上一章
                    if (currentIndex == 0) {
                        Toast.makeText(ReadBookActivity.this,
                                "亲，已经到头了！",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        currentIndex = currentIndex - 1;
                        currentContent = contentObj.get(currentIndex);
                        nextContent = contentObj.get(currentIndex + 1);
                        //从服务器获取上一章的内容
                        getBookTextFromServer();
                    }
                    break;
                case R.id.next_text://点击下一章
                    if (currentIndex == contentObj.size() - 1) {
                        nextContent = contentObj.get(currentIndex);
                        Toast.makeText(ReadBookActivity.this,
                                "亲，已经到最后一章了！",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        currentIndex = currentIndex + 1;
                        Log.e("currentIndex", "" + currentIndex);
                        currentContent = contentObj.get(currentIndex);
                        if (currentIndex == contentObj.size() - 1) {
                            nextContent = contentObj.get(currentIndex);
                        }else {
                            nextContent = contentObj.get(currentIndex+1);
                        }
                    }
                    //从服务器获取下一章的内容
                    getBookTextFromServer();
                    break;
                case R.id.book_contents_list://点击目录
                    Toast.makeText(ReadBookActivity.this,
                            "点击目录",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.book_text_size://点击字体大小
                    //获取当前字体大小
                    currentTextSize=20;
                    if (settingsPopupWindow.isShowing()) {
                        settingsPopupWindow.dismiss();
                        changeCurrentTextSize(currentTextSize);
                    }
                    break;
                case R.id.book_night_model://点击夜间模式/白天模式
                    //获取当前字体颜色，如果为白色则为夜间模式，改变其状态为白天
                    if (textContent.getCurrentTextColor()==getResources().getColor(android.R.color.white)){
                        //改为白天模式
                        //改变字体颜色
                        textContent.setTextColor(getResources().getColor(android.R.color.black));
                        //改变背景颜色
                        scrollView.setBackgroundColor(getResources().getColor(android.R.color.white));
                        //改变图片显示为太阳
                        changeModelImage(R.mipmap.baitian,"白天模式");
                    }else {
                        //改变字体颜色
                        textContent.setTextColor(getResources().getColor(android.R.color.white));
                        //改变背景颜色
                        scrollView.setBackgroundColor(getResources().getColor(android.R.color.black));
                        //改变图片显示为太阳
                        changeModelImage(R.mipmap.nightmodel,"夜间模式");
                    }

                    break;
                case R.id.book_download://点击下载
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                Message msg = new Message();
                                msg.what = 3;
                                msg.obj = 1;
                                handler.sendMessage(msg);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    break;
                case R.id.iv_change_text_small://点击缩小字体
                    if (currentTextSize>10){
                        currentTextSize=currentTextSize-1;
                        textContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, currentTextSize);
                        changeCurrentTextSize(currentTextSize);
                    }else {
                        Toast.makeText(ReadBookActivity.this,
                                "当前字体大小已为最小！",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.iv_change_text_big://点击加大字体
                    if (currentTextSize<35){
                        currentTextSize=currentTextSize+1;
                        textContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, currentTextSize);
                        changeCurrentTextSize(currentTextSize);
                    }else {
                        Toast.makeText(ReadBookActivity.this,
                                "当前字体大小已为最大！",
                                Toast.LENGTH_SHORT).show();
                    }

                    break;
                case R.id.tv_change_text_style://点击改变字体样式
                    Toast.makeText(ReadBookActivity.this,
                            "点击改变字体样式",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    /**
     * 模式：改变控件中的图片
     * @param imgRes 图片资源
     * @param text 显示文字
     */
    private void changeModelImage(int imgRes,String text) {
        if (settingsPopupWindow==null){
            settingsPopupWindow = new SettingsPopupWindow(ReadBookActivity.this,itemClickListener);
            settingsPopupWindow.showAtLocation(ReadBookActivity.this.findViewById(R.id.main),
                    Gravity.NO_GRAVITY, 0, 0);
        }else {
            settingsPopupWindow.setModel(imgRes,text);
            settingsPopupWindow.showAtLocation(ReadBookActivity.this.findViewById(R.id.main),
                    Gravity.NO_GRAVITY, 0, 0);
        }
    }

    /**
     * 改变阅读书籍时的字体大小
     */
    private void changeCurrentTextSize(int size) {
        if (textSizeSettingPopupWindow==null){
            textSizeSettingPopupWindow = new TextSizeSettingPopupWindow(ReadBookActivity.this,
                    itemClickListener, size);
            textSizeSettingPopupWindow.showAtLocation(ReadBookActivity.this.findViewById(R.id.main),
                    Gravity.NO_GRAVITY, 0, 0);
        }else {
            textSizeSettingPopupWindow.setTvShowSize(size);
            textSizeSettingPopupWindow.showAtLocation(ReadBookActivity.this.findViewById(R.id.main),
                    Gravity.NO_GRAVITY, 0, 0);
        }
    }

    /**
     * 读取服务端本书的所有内容
     */
    private void getWholeBookTextFromServer() {
        //获取书籍对应的文件名
        String bookFileName = book.getContent();
        //拼接文件路径
        String bookFilePath = ConfigUtil.SERVICE_ADDRESS + "books/" + bookFileName;
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建请求对象
        Request request = new Request.Builder()
                .url(bookFilePath)
                .build();
        //创建CALL对象
        Call call = okHttpClient.newCall(request);
        //异步方式提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("ReadBookActivity", "请求失败");
                Message msg = handler.obtainMessage();
                msg.what = 2;
                msg.obj = "书本内容获取失败！";
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //获取服务端返回的数据（假设是字符串）
                String result = response.body().string();
//                Log.e("ReadBookActivity", result);

                //通知界面信息改变
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        });
    }

    /**
     * 从服务器获取要读的章节的书的内容，并显示在空间中
     */
    private void getBookTextFromServer() {
        //将章节对象转成gson串
        String info1 = new Gson().toJson(currentContent);
        String info2 = new Gson().toJson(nextContent);
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS
                        + "ReadBookServlet?currentContent=" + info1 + "&nextContent=" + info2)
                .build();
        //创建CALL对象
        Call call = okHttpClient.newCall(request);
        //异步方式提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("ReadBookActivity", "请求失败");
                Message msg = handler.obtainMessage();
                msg.what = 2;
                msg.obj = "章节内容获取失败！";
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //获取服务端返回的数据（假设是字符串）
                String result = response.body().string();
//                Log.e("ReadBookActivity", result);

                //通知界面信息改变
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        });
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //返回上一页
            if (contentObj != null && flag == null) {
                Intent intent = new Intent(ReadBookActivity.this, MoreContentActivity.class);
                intent.putExtra("book", book);
                intent.putExtra("contentObj", (Serializable) contentObj);
                intent.putStringArrayListExtra("contents", data);
                startActivity(intent);
            } else {
                Intent intent = new Intent(ReadBookActivity.this, BookInfoActivity.class);
                intent.putExtra("book", book);
                startActivity(intent);
            }
            finish();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}