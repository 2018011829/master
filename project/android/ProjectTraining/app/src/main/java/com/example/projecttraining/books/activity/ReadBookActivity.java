package com.example.projecttraining.books.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.example.projecttraining.R;
import com.example.projecttraining.books.SettingsPopupWindow;
import com.example.projecttraining.books.TextSizeSettingPopupWindow;
import com.example.projecttraining.books.entitys.Book;
import com.example.projecttraining.books.entitys.Content;
import com.example.projecttraining.idiom.read.control.InitConfig;
import com.example.projecttraining.idiom.read.listener.UiMessageListener;
import com.example.projecttraining.idiom.read.util.Auth;
import com.example.projecttraining.idiom.read.util.AutoCheck;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.projecttraining.idiom.read.util.IOfflineResourceConst.DEFAULT_SDK_TTS_MODE;
import static com.example.projecttraining.idiom.read.util.IOfflineResourceConst.PARAM_SN_NAME;
import static com.example.projecttraining.idiom.read.util.IOfflineResourceConst.TEXT_MODEL;
import static com.example.projecttraining.idiom.read.util.IOfflineResourceConst.VOICE_MALE_MODEL;

public class ReadBookActivity extends Activity {

    private boolean stopThread=false;
    private int i=0;
    private List<String> textList; //保存分割出来的文字，用于语音合成
    private MyAdapter adapter;
    public static int currentReadStatus= R.mipmap.listen_white; //当前听的状态：正在播放、暂停
    public static String currentReadText="还未开始播放!"; //当前听到的话
    private static int currentReadTextIndex=0;
    private int currentTextColor; //当前字体的颜色
    public static int currentTextSize=20; //当前字体大小
    public static String currentModelText="夜间模式"; //当前模式名称
    public static int currentModelImg= R.mipmap.nightmodel; //当前模式图片
    private int currentIndex; //当前章节下标
    private LinearLayout linearWholeBook; //整个界面
    private LinearLayout linearDrawerBookContents; //侧滑出来的整个目录界面
    private ListView drawerListContents; //侧滑目录
    private TextView tvMuLu; //“目录”所在的 textview
    private String flag; //标记从图书详情也跳转来
    private String flag1; //标记开始全文阅读
    private ProgressBar pb; //进度条
    private TextView textContent;
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
                    //将一大段文字进行字符串分隔，用于语音合成
                    String[] textArr=strBookText.split("\\r\\n|\\n|\\r|。|!");
                    //将数组转换成集合
                    textList=new ArrayList<>();
                    textList.addAll(Arrays.asList(textArr));

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
                case 5://获取当前读的文字
                    String text = (String) msg.obj;
                    changeModelImage();
                    break;
            }
        }
    };
    private MyThread myThread=new MyThread();

    private class MyThread extends Thread{
        @Override
        public void run() {
            while (!stopThread && currentReadTextIndex<textList.size()){
                speak(textList.get(currentReadTextIndex));
                Message message=new Message();
                message.what=5;
                message.obj=currentReadText;
                handler.sendMessage(message);
                currentReadTextIndex=currentReadTextIndex+1;
//                Log.e("当前下标",currentReadTextIndex+"");
            }
        }
    }

    // ================== 百度语音合成部分,精简版初始化参数设置开始 ==========================
    /**
     * 发布时请替换成自己申请的appId appKey 和 secretKey。注意如果需要离线合成功能,请在您申请的应用中填写包名。
     * 本demo的包名是com.baidu.tts.sample，定义在build.gradle中。
     */
    protected String appId;

    protected String appKey;

    protected String secretKey;

    protected String sn; // 纯离线合成SDK授权码；离在线合成SDK没有此参数

    // TtsMode.MIX; 离在线融合，在线优先； TtsMode.ONLINE 纯在线； 没有纯离线
    private TtsMode ttsMode = DEFAULT_SDK_TTS_MODE;

    private boolean isOnlineSDK = TtsMode.ONLINE.equals(DEFAULT_SDK_TTS_MODE);
    // ================ 纯离线sdk或者选择TtsMode.ONLINE  以下参数无用;
    private static final String TEMP_DIR = "/sdcard/baiduTTS"; // 重要！请手动将assets目录下的3个dat 文件复制到该目录

    // 请确保该PATH下有这个文件
    private static final String TEXT_FILENAME = TEMP_DIR + "/" + TEXT_MODEL;

    // 请确保该PATH下有这个文件 ，m15是离线男声
    private static final String MODEL_FILENAME = TEMP_DIR + "/" + VOICE_MALE_MODEL;

    // ===============初始化参数设置完毕，更多合成参数请至getParams()方法中设置 =================

    protected SpeechSynthesizer mSpeechSynthesizer;

    protected Handler mainHandler=new Handler(Looper.getMainLooper()) {
        /*
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.obj != null) {
                Log.e("lg",msg.obj.toString());
            }
        }

    };;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 百度语音合成部分
        appId = Auth.getInstance(this).getAppId();
        appKey = Auth.getInstance(this).getAppKey();
        secretKey = Auth.getInstance(this).getSecretKey();
        sn = Auth.getInstance(this).getSn(); // 纯离线合成必须有此参数；离在线合成SDK没有此参数

        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_read_book);

        currentTextColor=getResources().getColor(android.R.color.black,null);
        linearWholeBook=findViewById(R.id.whole_read_book);
        linearDrawerBookContents=findViewById(R.id.linear_drawer_book_contents);
        tvMuLu=findViewById(R.id.tv_mulu);
        drawerListContents=findViewById(R.id.drawer_book_contents);
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

        // 百度语音合成部分

        initPermission();
        initTTs();

    }

    /**
     * 获取信息
     */
    private void getExtrasFromIntent() {
        Intent intent = getIntent();
        flag = intent.getStringExtra("activity");
        flag1=intent.getStringExtra("flag");
        book = (Book) intent.getSerializableExtra("book");
        contentObj = (List<Content>) intent.getSerializableExtra("contentObj");
        data = intent.getStringArrayListExtra("contents");
        //获取要读的当前页的信息
        currentIndex = intent.getIntExtra("currentIndex", 0);

        //滑动出来的章节列表
        if (data!=null){
//            Toast.makeText(ReadBookActivity.this,
//                    "data不为空",
//                    Toast.LENGTH_SHORT).show();
            adapter=new MyAdapter();
            drawerListContents.setAdapter(adapter);
            adapter.setCurrentItem(currentIndex);
            adapter.setClick(true);
            adapter.notifyDataSetChanged();
            drawerListContents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    currentIndex=i;
                    currentContent = contentObj.get(currentIndex);
                    if (currentIndex < contentObj.size() - 1) {
                        nextContent = contentObj.get(currentIndex + 1);
                    } else {
                        nextContent = contentObj.get(currentIndex);
                    }
                    //从服务器获取要读的书的对应章节内容
                    getBookTextFromServer();
                    adapter.setCurrentItem(i);
                    adapter.setClick(true);
                    adapter.notifyDataSetChanged();
                }
            });
        }
        if (currentIndex!=0){
            currentContent = contentObj.get(currentIndex);
            if (currentIndex < contentObj.size() - 1) {
                nextContent = contentObj.get(currentIndex + 1);
            } else {
                nextContent = contentObj.get(currentIndex);
            }
        }else {
            Log.e("ReadBookActivity","开始读取整本书");
        }

    }

    class MyAdapter extends BaseAdapter{

        private TextView textView;
        private int mCurrentItem=0;//选中项的下标
        private boolean isClick = false;//判断是否选中

        public void setCurrentItem(int currentItem) {
            this.mCurrentItem = currentItem;
        }

        public void setClick(boolean click) {
            this.isClick = click;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null){
                view= LayoutInflater.from(ReadBookActivity.this).inflate(R.layout.book_about_content_item,null);
            }
            textView=view.findViewById(R.id.book_contents_list_item_text);
            textView.setText(data.get(i));

            //判断初始进来选中的下标==当前的下标 如果i=0时设置背景、文字颜色、以及左侧的竖直tab
            if (mCurrentItem == i) {
                textView.setTextColor(getColor(android.R.color.holo_red_dark));
            } else {
                textView.setTextColor(currentTextColor);
            }
            textView.setTextSize(currentTextSize);

            return view;
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
                    if (flag1!=null){
                        Toast.makeText(ReadBookActivity.this,
                                "亲，您现在是全文阅读哦！",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        //上一章
                        mSpeechSynthesizer.stop();
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

                            adapter.setCurrentItem(currentIndex);
                            adapter.setClick(true);
                            adapter.notifyDataSetChanged();

                            stopThread=true;
                            currentReadStatus= R.mipmap.listen_white;
                            currentReadTextIndex=0;
                            Toast.makeText(ReadBookActivity.this,
                                    "暂停播放:"+currentReadTextIndex,
                                    Toast.LENGTH_SHORT).show();
                            if (myThread.isAlive()){
                                myThread.interrupt();
                            }

                            myThread=new MyThread();
                            stopThread=false;
                            changeModelImage();
                        }
                    }

                }
                if (screenWidth - 200 <= x && screenWidth >= x) {
                    if (flag1!=null){
                        Toast.makeText(ReadBookActivity.this,
                                "亲，您现在是全文阅读哦！",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        //下一章
                        mSpeechSynthesizer.stop();
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

                        adapter.setCurrentItem(currentIndex);
                        adapter.setClick(true);
                        adapter.notifyDataSetChanged();

                        stopThread=true;
                        currentReadStatus= R.mipmap.listen_white;
                        currentReadTextIndex=0;
                        Toast.makeText(ReadBookActivity.this,
                                "暂停播放:"+currentReadTextIndex,
                                Toast.LENGTH_SHORT).show();
                        if (myThread.isAlive()){
                            myThread.interrupt();
                        }

                        myThread=new MyThread();
                        stopThread=false;
                        changeModelImage();
                    }

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
                case R.id.before_text://点击上一章
                    if (flag1!=null){
                        Toast.makeText(ReadBookActivity.this,
                                "亲，您现在是全文阅读哦！",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        if (currentIndex == 0) {
                            Toast.makeText(ReadBookActivity.this,
                                    "亲，已经到头了！",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            mSpeechSynthesizer.stop();
                            currentIndex = currentIndex - 1;
                            currentContent = contentObj.get(currentIndex);
                            nextContent = contentObj.get(currentIndex + 1);
                            //从服务器获取上一章的内容
                            getBookTextFromServer();
                            adapter.setCurrentItem(currentIndex);
                            adapter.setClick(true);
                            adapter.notifyDataSetChanged();
                            stopThread=true;
                            currentReadStatus= R.mipmap.listen_white;
                            currentReadTextIndex=0;
                            Toast.makeText(ReadBookActivity.this,
                                    "暂停播放:"+currentReadTextIndex,
                                    Toast.LENGTH_SHORT).show();
                            if (myThread.isAlive()){
                                myThread.interrupt();
                            }

                            myThread=new MyThread();
                            stopThread=false;
                            changeModelImage();
                        }
                    }

                    break;
                case R.id.next_text://点击下一章
                    mSpeechSynthesizer.stop();
                    if (flag1!=null){
                        Toast.makeText(ReadBookActivity.this,
                                "亲，您现在是全文阅读哦！",
                                Toast.LENGTH_SHORT).show();
                    }else {
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
                        adapter.setCurrentItem(currentIndex);
                        adapter.setClick(true);
                        adapter.notifyDataSetChanged();
                        stopThread=true;

                        currentReadStatus= R.mipmap.listen_white;
                        currentReadTextIndex=0;
                        Toast.makeText(ReadBookActivity.this,
                                "暂停播放:"+currentReadTextIndex,
                                Toast.LENGTH_SHORT).show();
                        if (myThread.isAlive()){
                            myThread.interrupt();
                        }
                        myThread=new MyThread();
                        stopThread=false;
                        changeModelImage();

                    }

                    break;
                case R.id.book_text_size://点击字体大小
                    //获取当前字体大小
                    if (settingsPopupWindow.isShowing()) {
                        settingsPopupWindow.dismiss();
                        changeCurrentTextSize();
                    }
                    break;
                case R.id.book_night_model://点击夜间模式/白天模式
                    //获取当前字体颜色，如果为白色则为夜间模式，改变其状态为白天
                    if (textContent.getCurrentTextColor()==getResources().getColor(android.R.color.white,null)){
                        //改为白天模式
                        //改变字体颜色
                        currentTextColor=getResources().getColor(android.R.color.black,null);
                        textContent.setTextColor(getResources().getColor(android.R.color.black,null));
                        tvMuLu.setTextColor(getResources().getColor(android.R.color.black,null));
                        //改变背景颜色
                        linearWholeBook.setBackgroundColor(getResources().getColor(android.R.color.white,null));
                        linearDrawerBookContents.setBackgroundColor(getResources().getColor(android.R.color.white,null));

                        adapter.setCurrentItem(currentIndex);
                        adapter.setClick(true);
                        adapter.notifyDataSetChanged();
                        //改变图片显示为月亮
                        currentModelImg= R.mipmap.nightmodel;
                        currentModelText="夜间模式";
                        changeModelImage();
                    }else {
                        //改变字体颜色
                        currentTextColor=getResources().getColor(android.R.color.white,null);
                        textContent.setTextColor(getResources().getColor(android.R.color.white,null));
                        tvMuLu.setTextColor(getResources().getColor(android.R.color.white,null));
                        //改变背景颜色
                        linearWholeBook.setBackgroundColor(getResources().getColor(android.R.color.black,null));
                        linearDrawerBookContents.setBackgroundColor(getResources().getColor(android.R.color.black,null));

                        adapter.setCurrentItem(currentIndex);
                        adapter.setClick(true);
                        adapter.notifyDataSetChanged();
                        //改变图片显示为太阳
                        currentModelText="白天模式";
                        currentModelImg= R.mipmap.baitian;
                        changeModelImage();
                    }

                    break;
                case R.id.iv_change_text_small://点击缩小字体
                    if (currentTextSize>10){
                        currentTextSize=currentTextSize-1;
                        textContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, currentTextSize);
                        changeCurrentTextSize();
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
                        changeCurrentTextSize();
                    }else {
                        Toast.makeText(ReadBookActivity.this,
                                "当前字体大小已为最大！",
                                Toast.LENGTH_SHORT).show();
                    }

                    break;
                case R.id.iv_search://点击搜索
                    Toast.makeText(ReadBookActivity.this,
                            "点击搜索",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.book_contents_list://点击目录
                    Toast.makeText(ReadBookActivity.this,
                            "点击目录",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_change_text_style://点击改变字体样式
                    Toast.makeText(ReadBookActivity.this,
                            "点击改变字体样式",
                            Toast.LENGTH_SHORT).show();
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
                case R.id.iv_listen: //点击听读书
                    if (currentReadStatus== R.mipmap.listen_white){
                        if (!stopThread){
//                            myThread=new MyThread();
                            myThread.start();
                        }else {
                            mSpeechSynthesizer.resume();
                        }

                        currentReadStatus= R.mipmap.zhengzaitingshu;
                        Toast.makeText(ReadBookActivity.this,
                                "开始播放",
                                Toast.LENGTH_SHORT).show();
                        changeModelImage();
                    }
                    else {
                        stopThread=true;
                        currentReadStatus= R.mipmap.listen_white;
                        Toast.makeText(ReadBookActivity.this,
                                "暂停播放:"+currentReadTextIndex,
                                Toast.LENGTH_SHORT).show();
                        if (myThread.isAlive()){
                            myThread.interrupt();
                        }
                        mSpeechSynthesizer.pause();
                        changeModelImage();
                    }

                    break;

            }
        }
    };

    /**
     * 模式：改变控件中的图片
     */
    private void changeModelImage() {
        if (settingsPopupWindow==null){
            settingsPopupWindow = new SettingsPopupWindow(ReadBookActivity.this,itemClickListener);
            settingsPopupWindow.showAtLocation(ReadBookActivity.this.findViewById(R.id.main),
                    Gravity.NO_GRAVITY, 0, 0);
        }else {
            settingsPopupWindow.setModel();
            settingsPopupWindow.setCurrentRead();
            settingsPopupWindow.showAtLocation(ReadBookActivity.this.findViewById(R.id.main),
                    Gravity.NO_GRAVITY, 0, 0);
        }
    }

    /**
     * 改变阅读书籍时的字体大小
     */
    private void changeCurrentTextSize() {
        if (textSizeSettingPopupWindow==null){
            textSizeSettingPopupWindow = new TextSizeSettingPopupWindow(ReadBookActivity.this,
                    itemClickListener);
            textSizeSettingPopupWindow.showAtLocation(ReadBookActivity.this.findViewById(R.id.main),
                    Gravity.NO_GRAVITY, 0, 0);
        }else {
            textSizeSettingPopupWindow.setTvShowSize();
            textSizeSettingPopupWindow.showAtLocation(ReadBookActivity.this.findViewById(R.id.main),
                    Gravity.NO_GRAVITY, 0, 0);
        }
        adapter.setCurrentItem(currentIndex);
        adapter.setClick(true);
        adapter.notifyDataSetChanged();
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

    // 百度语音合成部分
    /**
     * 注意此处为了说明流程，故意在UI线程中调用。
     * 实际集成中，该方法一定在新线程中调用，并且该线程不能结束。具体可以参考NonBlockSyntherizer的写法
     */
    private void initTTs() {
        LoggerProxy.printable(true); // 日志打印在logcat中
        boolean isSuccess;
        if (!isOnlineSDK) {
            // 检查2个离线资源是否可读
            isSuccess = checkOfflineResources();
            if (!isSuccess) {
                return;
            } else {
                Log.e("lrf","离线资源存在并且可读, 目录：" + TEMP_DIR);
            }
        }
        // 日志更新在UI中，可以换成MessageListener，在logcat中查看日志
        SpeechSynthesizerListener listener = new UiMessageListener(mainHandler);

        // 1. 获取实例
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(this);

        // 2. 设置listener
        mSpeechSynthesizer.setSpeechSynthesizerListener(listener);

        // 3. 设置appId，appKey.secretKey
        int result = mSpeechSynthesizer.setAppId(appId);
        checkResult(result, "setAppId");
        result = mSpeechSynthesizer.setApiKey(appKey, secretKey);
        checkResult(result, "setApiKey");

        // 4. 如果是纯离线SDK需要离线功能的话
        if (!isOnlineSDK) {
            // 文本模型文件路径 (离线引擎使用)， 注意TEXT_FILENAME必须存在并且可读
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, TEXT_FILENAME);
            // 声学模型文件路径 (离线引擎使用)， 注意TEXT_FILENAME必须存在并且可读
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, MODEL_FILENAME);

            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
            // 该参数设置为TtsMode.MIX生效。
            // MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
            // MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
            // MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
            // MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线

        }

        // 5. 以下setParam 参数选填。不填写则默认值生效
        // 设置在线发声音人： 0 普通女声（默认） 1 普通男声  3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        // 设置合成的音量，0-15 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "9");
        // 设置合成的语速，0-15 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "4");
        // 设置合成的语调，0-15 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "5");

        // mSpeechSynthesizer.setAudioStreamType(AudioManager.MODE_IN_CALL); // 调整音频输出

        if (sn != null) {
            // 纯离线sdk这个参数必填；离在线sdk没有此参数
            mSpeechSynthesizer.setParam(PARAM_SN_NAME, sn);
        }

        // x. 额外 ： 自动so文件是否复制正确及上面设置的参数
        Map<String, String> params = new HashMap<>();
        // 复制下上面的 mSpeechSynthesizer.setParam参数
        // 上线时请删除AutoCheck的调用
        if (!isOnlineSDK) {
            params.put(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, TEXT_FILENAME);
            params.put(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, MODEL_FILENAME);
        }

        // 检测参数，通过一次后可以去除，出问题再打开debug
        InitConfig initConfig = new InitConfig(appId, appKey, secretKey, ttsMode, params, listener);
        AutoCheck.getInstance(getApplicationContext()).check(initConfig, new Handler() {
            @Override
            /**
             * 开新线程检查，成功后回调
             */
            public void handleMessage(Message msg) {
                if (msg.what == 100) {
                    AutoCheck autoCheck = (AutoCheck) msg.obj;
                    synchronized (autoCheck) {
                        String message = autoCheck.obtainDebugMessage();
                        Log.e("lrf",message);
                    }
                }
            }

        });

        // 6. 初始化
        result = mSpeechSynthesizer.initTts(ttsMode);
        checkResult(result, "initTts");

    }

    /**
     * 在线SDK不需要调用，纯离线SDK会检查资源文件
     *
     * 检查 TEXT_FILENAME, MODEL_FILENAME 这2个文件是否存在，不存在请自行从assets目录里手动复制
     *
     * @return 检测是否成功
     */
    private boolean checkOfflineResources() {
        String[] filenames = {TEXT_FILENAME, MODEL_FILENAME};
        for (String path : filenames) {
            File f = new File(path);
            if (!f.canRead()) {
                Log.e("lg","[ERROR] 文件不存在或者不可读取，请从demo的assets目录复制同名文件到："
                        + f.getAbsolutePath());
                Log.e("lg","[ERROR] 初始化失败！！！");
                return false;
            }
        }
        return true;
    }

    private void speak(String text) {
        /* 以下参数每次合成时都可以修改
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
         *  设置在线发声音人： 0 普通女声（默认） 1 普通男声  3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "5"); 设置合成的音量，0-9 ，默认 5
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "5"); 设置合成的语速，0-9 ，默认 5
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "5"); 设置合成的语调，0-9 ，默认 5
         */

        if (mSpeechSynthesizer == null) {
            Log.e("lg","[ERROR], 初始化失败");
            return;
        }
        int result = mSpeechSynthesizer.speak(text);
        Log.e("lg","合成并播放 按钮已经点击");
        checkResult(result, "speak");
    }

    @Override
    protected void onDestroy() {
        if (mSpeechSynthesizer != null) {
            mSpeechSynthesizer.stop();
            mSpeechSynthesizer.release();
            mSpeechSynthesizer = null;
            Log.e("lg","释放资源成功");
        }
        super.onDestroy();
    }

    private void checkResult(int result, String method) {
        if (result != 0) {
            Log.e("lg","error code :" + result + " method:" + method);
        }
    }

    //  下面是android 6.0以上的动态授权

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.
                Log.e("lg","没有权限");
            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }
}