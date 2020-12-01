package com.example.projecttraining.idiom.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.projecttraining.R;
import com.example.projecttraining.idiom.adapter.IdiomInfoAdapter;
import com.example.projecttraining.idiom.entity.IdiomInfo;
import com.example.projecttraining.idiom.entity.IdiomInfoResult;
import com.example.projecttraining.util.IdiomJsonUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 2020-11-26
 * 2020-11-28
 * 2020-11-30
 *
 * @author lrf
 */
public class IdiomInfoActivity extends AppCompatActivity {

    private String idiomName;
    private String APPKEY = "a9b630b59585bbd480cddd11fc7de952";
    private String url = "http://v.juhe.cn/chengyu/query";
//    private String APPKEY = "52836ab53d4cf3e9";
//    private String url = "https://api.jisuapi.com/chengyu/detail";

    @BindView(R.id.tv_idiom_name) TextView tvIdiomName;
    @BindView(R.id.idiom_info_tab)
    TabLayout tabLayout;
    @BindView(R.id.idiom_view_pager) ViewPager viewPager;

    private Handler myHandler;
    //定义Gson对象属性
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idiom_info);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        idiomName = intent.getStringExtra("name");

        getIdiomInfoFromAPI(idiomName);

        tvIdiomName.setText(idiomName);
        //Gson对象实例化
        initGson();

        //为ViewPager设置Adapter
        ViewPager viewPager = setViewPagerAdapter();
        //将ViewPager和TabLayout互相绑定,并设置TabLayout的选择改变事件
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        myHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 1:
                        String json = (String) msg.obj;
                        Log.e("lrf_json",json);
                        IdiomInfo idiomInfo = IdiomJsonUtil.convertToIdiomInfo(json);
                        Log.e("lrf_反序列化",idiomInfo.toString());
                        IdiomInfoResult idiomInfoResult = idiomInfo.getIdiomInfoResult();

                        break;
                }
            }
        };

    }

    /**
     * 实例化Gson对象
     */
    private void initGson() {
        //允许配置参数的Gson对象初始化
        gson = new GsonBuilder()//创建GsonBuilder对象
                .setPrettyPrinting()//格式化输出
                .serializeNulls()//允许输出Null值属性
                .create();//创建Gson对象
    }

    /**
     * 调用成语API接口，查询某成语的详细信息
     * @param word
     * @return
     */
    private void getIdiomInfoFromAPI(String word) {
        new Thread(){
            @Override
            public void run() {
                try {
                    // 创建URL对象
                    URL urlPath = new URL(url + "?word="+ URLEncoder.encode(word, "utf-8")+"&dtype=&key="+APPKEY);
//                    URL urlPath = new URL(url+"?appkey=" + APPKEY + "&chengyu="+ URLEncoder.encode(word, "utf-8"));
                    HttpURLConnection conn = (HttpURLConnection) urlPath.openConnection();
                    // 设置网络请求方式为POST
                    conn.setRequestMethod("POST");
                    // 获取网络输入流
                    InputStream in = conn.getInputStream();
                    // 使用字符流读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
                    // 读取字符信息
                    String json = reader.readLine();
                    Log.e("lrf",json);
                    // 关闭流
                    reader.close();
                    in.close();
                    //通过发送message对象将数据发布出去
                    //获取Message对象
                    Message msg = myHandler.obtainMessage();
                    //设置Message对象的属性(what、obj)
                    msg.what = 1;
                    msg.obj = json;
                    //发送Message对象
                    myHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 得到ViewPager引用，并设置Adapter
     *
     * @return ViewPager
     */
    private ViewPager setViewPagerAdapter() {
        //得到ViewPager和IdiomInfoAdapter，并为ViewPager设置Adapter
        IdiomInfoAdapter idiomInfoAdapter = new IdiomInfoAdapter(getSupportFragmentManager(), 1);
        viewPager.setAdapter(idiomInfoAdapter);
        return viewPager;
    }
}