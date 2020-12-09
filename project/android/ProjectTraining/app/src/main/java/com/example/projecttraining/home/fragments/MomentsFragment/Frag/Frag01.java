package com.example.projecttraining.home.fragments.MomentsFragment.Frag;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MomentsFragment.Adapter.Frag01Adapter;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.Moments;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Frag01 extends Fragment {
    private List<Moments> moments = new ArrayList<>();//动态类列表
    private ListView momentsListView;  //ListView对象
    private SmartRefreshLayout srl;    //智能刷新控件对象
    private Frag01Adapter frag01Adapter;
    private View view;
    private List<String> pictureUrl = new ArrayList<>();//图片网络路径列表
    private OkHttpClient okHttpClient;//定义OKHTTPClient对象属性
    private Handler handler;//定义Handler对象属性
    private String content=null;//定义一个说说文案对象
    // 定义Gson对象属性
    private Gson gson;
    //初始化Handler对象
    private void initHandler(View view) {
        handler = new Handler(){//handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 3:
                        //获取图片资源路径
                        String imgUrl = (String) msg.obj;//接收到的是一个说说对象

                        String json = imgUrl;
                        //反序列化
                        Moments[] moments = gson.fromJson(json, Moments[].class);//说说对象反序列化

                        initView(Arrays.asList(moments),view);//准备adapter
                        break;
                }
            }
        };
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //为了防止第二次加载的时候重复调用这个方法的onCreateView(),重新new了一个pagedapter导致子fragment不显示，显示空白
        if (view!=null){
            ViewGroup parent = (ViewGroup) view.getParent();
            if(parent !=null){
                parent.removeView(view);
            }
            return view;
        }
        // 添加布局文件
        view=inflater.inflate(R.layout.fragment_moments_frag01, container, false);

        initGson();//初始化gson对象
        initHandler(view);//初始化handler对象
        initOkHttpClient();//初始化okHttp对象
        new Thread(){//创建线程发送请求说说数据的命令
            @Override
            public void run() {
                downLoadImgNameFromServerRequest();
            }
        }.start();

//        initDate(view);//准备数据
//        initView(moments,view);//配置adapter
        momentsListView = view.findViewById(R.id.lv_moments_list);
        //初始化数据
        moments = initData(view);
        //实例化Adapter
        frag01Adapter = new Frag01Adapter(getContext(),moments, R.layout.fragment_moments_frag01_item);
        momentsListView.setAdapter(frag01Adapter);
        //给ListView注册监听器
        momentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Moments moment = moments.get(position);
                Toast.makeText(
                        getContext(),
                        moment.getName() + ":" + moment.getContent(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        srl = view.findViewById(R.id.srl);
        //设置Header和Footer的样式
        //创建Header样式对象
//        WaveSwipeHeader header = new WaveSwipeHeader(this);
//        srl.setRefreshHeader(header);
        //创建Footer样式
        FalsifyFooter footer = new FalsifyFooter(getContext());
        //设置Footer样式
        srl.setRefreshFooter(footer);
        //设置回弹时间
        srl.setReboundDuration(100);
        //给智能刷新控件注册下拉刷新事件监听器
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData(view);
                //通知刷新完毕
                srl.finishRefresh();
                new Thread(){//创建线程发送请求说说数据的命令
                    @Override
                    public void run() {
                        downLoadImgNameFromServerRequest();
                    }
                }.start();
            }
        });
        //给智能刷新控件注册上拉加载更多事件监听器
        srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多数据(假设超过10条数据则加载完毕）
                if(moments.size() < 10) {
                    loadMoreData();
                    //通知加载数据完毕
                    srl.finishLoadMore();
                } else {
                    //通知没有更多数据可加载
                    srl.finishLoadMoreWithNoMoreData();
                }
            }
        });
        return view;
    }
    /**
     * 初始化Gson对象
     */
    private void initGson() {
//       gson = new Gson();
        // 允许配置参数的Gson对象初始化
        gson = new GsonBuilder()// 创建GsonBuilder对象
                .setPrettyPrinting()// 格式化输出
                .serializeNulls()// 允许输出Null值属性
                .setDateFormat("YY:MM:dd")// 日期格式化
                .create();// 创建Gson对象
    }
    /**
     * 初始化OKHTTPClient对象
     */
    private void initOkHttpClient() {
        okHttpClient = new OkHttpClient();
    }

    /**
     * 从服务端获取图片资源的网络路径
     */
    private void downLoadImgNameFromServerRequest() {
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "DownPictureServlet")
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String img = response.body().string();
            Message msg = handler.obtainMessage();
            msg.what = 3;
            msg.obj = img;
            handler.sendMessage(msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    //准备数据
    public List<Moments> initData(View view){
        List<Moments> list = new ArrayList<>();
        moments.clear();
        List<String> pictureUrl = new ArrayList<>();
        String url1 = "321";
        pictureUrl.add(url1);
        Moments moment = new Moments("123","李仕奇","气死本仕奇了","123");//测试数据
        moments.add(moment);
        return list;
    }
    /**
     * 模拟加载更多数据
     */
    private void loadMoreData() {
        for(int i = 0; i < 5; i++){
            Moments moment = new Moments();
            moment.setName("昵称" + (int)(Math.random()*100));
            moment.setContent("我就是这么强大" + (int)(Math.random()*100));
            moments.add(moment);
        }
        frag01Adapter.notifyDataSetChanged();
    }
    /**
     * 刷新
     */
    private void refreshData(View view) {
        moments.clear();
        moments.addAll(initData(view));//stus = initData()
        frag01Adapter.notifyDataSetChanged();
    }

    //配置adapter
    private void initView(List<Moments> moments,View view) {
        frag01Adapter = new Frag01Adapter(getContext(), moments, R.layout.fragment_moments_frag01_item);
        momentsListView = view.findViewById(R.id.lv_moments_list);
        momentsListView.setAdapter(frag01Adapter);
    }
}
