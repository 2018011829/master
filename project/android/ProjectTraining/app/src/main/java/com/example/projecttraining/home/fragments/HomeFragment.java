package com.example.projecttraining.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;
import com.example.projecttraining.books.activity.BooksHomePageActivity;
import com.example.projecttraining.home.MyImageLoader;
import com.example.projecttraining.idiom.activitys.IdiomActivity;
import com.example.projecttraining.util.ConfigUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private LinearLayout linearToIdiom;//成语专区
    private LinearLayout linearToRead;//阅读专区
    private Handler myHandler;
    private Banner mBanner;
    private List<Integer> imgPath = new ArrayList<>();
    private MyImageLoader myImageLoader = new MyImageLoader();
    private View view;
    private int tag = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);
        initViews();
        initData();

        /**
         * 成语
         * 2020-11-25
         * 2020-11-28
         * @author lrf
         */
        linearToIdiom = view.findViewById(R.id.linear_to_idiom);
        linearToIdiom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIdiomTypes(ConfigUtil.SERVICE_ADDRESS+"SendClassifyIdiomServlet");
                Log.e("lrf","点击了跳转按钮");
                myHandler = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        switch (msg.what){
                            case 1:
                                String str = (String) msg.obj;
                                Intent intent = new Intent();
                                intent.setClass(getContext(), IdiomActivity.class);
                                intent.putExtra("idiomType",str);
                                getContext().startActivity(intent);
                                break;
                        }
                    }
                };
            }
        });

        //阅读
        linearToRead=view.findViewById(R.id.linear_to_read);
        linearToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), BooksHomePageActivity.class);
                getContext().startActivity(intent);
            }
        });
        return view;
    }

    private void initData() {
        if(tag<=0) {
            imgPath.add(R.drawable.banner0);
            imgPath.add(R.drawable.banner1);
            imgPath.add(R.drawable.banner2);
            imgPath.add(R.drawable.banner3);
            tag++;
        }

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImages(imgPath)
                .setBannerAnimation(Transformer.Default)
                .setImageLoader(myImageLoader)
                .setIndicatorGravity(BannerConfig.CENTER)
                .isAutoPlay(true)
                .setDelayTime(2000)
                .start();

    }

    private void initViews() {
        mBanner = view.findViewById(R.id.home_banner);
    }

    /**
     * 获取成语类型
     * 2020-11-28
     * @author lrf
     */
    private void getIdiomTypes(String url) {
        new Thread(){
            @Override
            public void run() {
                try {
                    //创建URL对象
                    URL urlPath = new URL(url);
                    //通过URL对象获取网络输入流
                    InputStream in = urlPath.openStream();
                    //使用字符流读取
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(in, "utf-8"));
                    //读取字符信息
                    String result = reader.readLine();
                    Log.e("lrf",result);
                    //关闭流
                    reader.close();
                    in.close();
                    //通过发送message对象将数据发布出去
                    //获取Message对象
                    Message msg = myHandler.obtainMessage();
                    //设置Message对象的属性(what、obj)
                    msg.what = 1;
                    msg.obj = result;
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
}
