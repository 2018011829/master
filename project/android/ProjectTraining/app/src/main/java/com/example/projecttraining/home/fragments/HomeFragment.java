package com.example.projecttraining.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;
import com.example.projecttraining.books.activity.BookInfoActivity;
import com.example.projecttraining.books.activity.BooksHomePageActivity;
import com.example.projecttraining.books.entitys.Book;
import com.example.projecttraining.home.MyImageLoader;
import com.example.projecttraining.idiom.activitys.IdiomActivity;
import com.example.projecttraining.idiom.activitys.IdiomInfoActivity;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private LinearLayout linearToIdiom;//成语专区
    private LinearLayout linearToRead;//阅读专区
    private LinearLayout linearGuess1;
    private LinearLayout linearGuess2;
    private LinearLayout linearGuess3;
    private LinearLayout linearGuess4;
    private LinearLayout linearGuess5;
    private Handler myHandler;
    private Banner mBanner;
    private LinearLayout changeRecommend;
    private List<Integer> imgPath = new ArrayList<>();
    private MyImageLoader myImageLoader = new MyImageLoader();
    private View view;
    private int tag = 0;

    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1://获取书籍Book
                    String bookGsonStr= (String) msg.obj;
                    if (!bookGsonStr.equals("图书不存在！")){
                        Book book= new Gson().fromJson(bookGsonStr,Book.class);
                        //跳转到详情界面
                        Intent intent=new Intent(getContext(), BookInfoActivity.class);
                        intent.putExtra("book",book);
                        intent.putExtra("HomeFragment","HomeFragment");
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(),
                                "图书已下架！",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    Toast.makeText(getContext(),
                            "网络错误",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

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
                Toast.makeText(getContext(),"正在获取相关数据...",Toast.LENGTH_SHORT).show();
                getIdiomTypes(ConfigUtil.SERVICE_ADDRESS+"SendClassifyIdiomServlet");
                myHandler = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        switch (msg.what){
                            case 0:
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
            imgPath.add(R.drawable.banner3);
            imgPath.add(R.drawable.banner4);
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
        changeRecommend = view.findViewById(R.id.change);
        mBanner = view.findViewById(R.id.home_banner);
        linearGuess1=view.findViewById(R.id.linear_guess1);
        linearGuess2=view.findViewById(R.id.linear_guess2);
        linearGuess3=view.findViewById(R.id.linear_guess3);
        linearGuess4=view.findViewById(R.id.linear_guess4);
        linearGuess5=view.findViewById(R.id.linear_guess5);
        linearGuess1.setOnClickListener(this);
        linearGuess2.setOnClickListener(this);
        linearGuess3.setOnClickListener(this);
        linearGuess4.setOnClickListener(this);
        linearGuess5.setOnClickListener(this);
        changeRecommend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change:
                Toast.makeText(getContext(),"没有更多推荐了哟",Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_guess1://安徒生童话
                getBookByNameFromServer("安徒生童话");
                break;
            case R.id.linear_guess2://窗边的小豆豆
                getBookByNameFromServer("窗边的小豆豆");
                break;
            case R.id.linear_guess3://动物庄园
                getBookByNameFromServer("动物庄园");
                break;
            case R.id.linear_guess4://王尔德童话
                getBookByNameFromServer("王尔德童话");
                break;
            case R.id.linear_guess5://一千零一夜
                getBookByNameFromServer("一千零一夜");
                break;
        }
    }

    /**
     * 根据名字获取书籍
     * @param bookName
     */
    private void getBookByNameFromServer(String bookName) {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS
                        + "GetOneBookByNameServlet?bookName=" + bookName)
                .build();
        //创建CALL对象
        Call call = okHttpClient.newCall(request);
        //异步方式提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("BookInfoActivity", "请求失败");
                Message msg = handler.obtainMessage();
                msg.what = 2;
                msg.obj = "网络错误";
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //获取服务端返回的数据（假设是字符串）
                String result = response.body().string();
//                Log.e("BookInfoActivity", result);

                //通知界面信息改变
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        });
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
                    msg.what = 0;
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
