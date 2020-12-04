package com.example.projecttraining.books.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.projecttraining.MainActivity;
import com.example.projecttraining.R;
import com.example.projecttraining.books.adapters.BooksHomeAdapter;
import com.example.projecttraining.books.entitys.Book;
import com.example.projecttraining.books.entitys.GlideImageLoader;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BooksHomePageActivity extends Activity {

    public static BooksHomePageActivity activity;
    private ProgressBar pb; //进度条
    private Banner banner; //轮播图控件
    private ImageView ivBack;
    private ListView bookHomepageList;
    private TreeMap<String, List<Book>> treeMap;//数据源
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1://得到所有图书的Gson字符串，将字符串转换成装有图书的集合
                    String strGsonBooks= (String) msg.obj;
                    //解析Gson串
                    //集合反序列化
                    //获取集合类型
                    Type type=new TypeToken<TreeMap<String, List<Book>>>(){}.getType();
                    //将字符串转换成数据
                    treeMap=new Gson().fromJson(strGsonBooks,type);
                    Log.e("treeMap",treeMap.toString());
                    //初始化控件
                    initViews();
                    pb.setVisibility(View.INVISIBLE);
                    break;
                case 2: //显示错误信息
                    String errorInfo= (String) msg.obj;
                    Toast.makeText(BooksHomePageActivity.this,
                            errorInfo, Toast.LENGTH_SHORT).show();
                    break;
                case 3: //获取到轮播图照片的信息 展示
                    String strGsonImgs= (String) msg.obj;
                    //解析Gson串
                    //集合反序列化
                    //获取集合类型
                    Type typeList=new TypeToken<List<String>>(){}.getType();
                    List<String> imgs=new Gson().fromJson(strGsonImgs,typeList);
                    showBannerImgs(imgs);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_books_home_page);

        Intent intent=getIntent();
        activity=this;
        pb=findViewById(R.id.progressbar);
        pb.setVisibility(View.VISIBLE);
        banner=findViewById(R.id.home_page_banner);
        ivBack=findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(BooksHomePageActivity.this, MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });
        bookHomepageList=findViewById(R.id.book_homepage_list);
        //从服务端获取数据
        getDataFromServer();
        //获取轮播图要显示的图片的集合 并显示轮播图
        getBannerDatafromServer();
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        //adapter
        BooksHomeAdapter adapter=new BooksHomeAdapter(BooksHomePageActivity.this,
                R.layout.book_homepage_listitem,treeMap);
        //绑定adapter
        bookHomepageList.setAdapter(adapter);
    }

    /**
     * 将照片的信息显示在轮播图中
     * @param imgs 照片名的集合
     */
    private void showBannerImgs(List<String> imgs) {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置要显示的图片
        List<String> list=new ArrayList<>();
        for (String img:imgs){
            list.add(ConfigUtil.SERVICE_ADDRESS+"bannerHomePageImgs/"+img);
        }
        banner.setImages(list);
        //设置轮播的间隔时间
        banner.setDelayTime(2000);
        banner.start();
    }

    /**
     * 获取轮播图要显示的图片的集合
     */
    private void getBannerDatafromServer() {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient=new OkHttpClient();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS
                        + "BannerHomePageServlet")
                .build();
        //创建CALL对象
        Call call = okHttpClient.newCall(request);
        //异步方式提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("BooksHomePageActivity", "请求失败");
                Message msg = handler.obtainMessage();
                msg.what = 2;
                msg.obj = "图片信息获取失败！";
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //获取服务端返回的数据（假设是字符串）
                String result = response.body().string();
//                Log.e("BooksHomePageActivity", result);

                //通知界面信息改变
                Message msg = handler.obtainMessage();
                msg.what = 3;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        });
    }

    /**
     * 从服务端获取数据
     */
    private void getDataFromServer() {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient=new OkHttpClient();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS
                        + "BookHomePageServlet?info=签到")
                .build();
        //创建CALL对象
        Call call = okHttpClient.newCall(request);
        //异步方式提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("BooksHomePageActivity", "请求失败");
                Message msg = handler.obtainMessage();
                msg.what = 2;
                msg.obj = "书籍信息获取失败！";
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //获取服务端返回的数据（假设是字符串）
                String result = response.body().string();
//                Log.e("BooksHomePageActivity", result);

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
            //返回主页
//            Intent intent=new Intent(BooksHomePageActivity.this,MainActivity.class);
//            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}