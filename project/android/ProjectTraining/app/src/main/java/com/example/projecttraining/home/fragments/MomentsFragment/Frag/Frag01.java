package com.example.projecttraining.home.fragments.MomentsFragment.Frag;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MomentsFragment.Adapter.Frag01Adapter;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.Attention;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.Moments;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyphenate.chat.EMClient;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
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
    // 定义Gson对象属性
    private Gson gson;
    //初始化Handler对象
    private void initHandler(View view) {
        handler = new Handler(){//handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {

                switch (msg.what){
                    case 1:
                        //获取图片资源路径
                        String json = (String) msg.obj;//接收到的是一个说说对象
                        if(!json.equals("")){
                            //反序列化
                            Moments[] moments = gson.fromJson(json, Moments[].class);//说说对象反序列化

                            String att = moments[0].getAttentionList();
                            Attention[] attentions = gson.fromJson(att,Attention[].class);
                            Log.e("att",att);

                            initView(Arrays.asList(moments),view, Arrays.asList(attentions));//准备adapter
                        }
                        break;

                    case 2:
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
        momentsListView = view.findViewById(R.id.lv_moments_list);
        srl = view.findViewById(R.id.srl);
        //设置回弹时间
        srl.setReboundDuration(100);
        //给智能刷新控件注册下拉刷新事件监听器
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData(view);
                new Thread(){//创建线程发送请求说说数据的命令
                    @Override
                    public void run() {
                        downLoadImgNameFromServerRequest();
                    }
                }.start();
                //通知刷新完毕
                srl.finishRefresh();
            }
        });
        return view;
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.e("1", "onStop: ");
    }

    //刷新
    private void refreshData(View view) {
        moments.clear();
        frag01Adapter.notifyDataSetChanged();
    }
    //初始化Gson对象
    private void initGson() {
        gson = new GsonBuilder()// 创建GsonBuilder对象
                .setPrettyPrinting()// 格式化输出
                .serializeNulls()// 允许输出Null值属性
                .setDateFormat("YY:MM:dd")// 日期格式化
                .create();// 创建Gson对象
    }
    //初始化OKHTTPClient对象
    private void initOkHttpClient() {
        okHttpClient = new OkHttpClient();
    }

    //从服务端获取图片资源的网络路径
    private void downLoadImgNameFromServerRequest() {
        //2 创建Request对象
        //1) 使用RequestBody封装请求数据
        //获取待传输数据对应的MIME类型
        MediaType type = MediaType.parse("text/plain");
        //创建FormBody对象
        FormBody formBody =
                new FormBody.Builder()
                        .add("PersonPhone",getPersonalPhone())
                        .build();
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "DownPictureServlet")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String img = response.body().string();
            Message msg = handler.obtainMessage();
            msg.what = 1;
            msg.obj = img;
            handler.sendMessage(msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //从服务端获取关注列表
    private void attentionInfo() {
        Log.e("调用","调用");
        //2 创建Request对象
        //1) 使用RequestBody封装请求数据
        //获取待传输数据对应的MIME类型
        MediaType type = MediaType.parse("text/plain");
        //创建FormBody对象
        FormBody formBody =
                new FormBody.Builder()
                        .add("personPhone",getPersonalPhone())
                        .build();
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "AttentionServlet")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String img = response.body().string();
            Message msg = handler.obtainMessage();
            msg.what = 2;
            msg.obj = img;
            handler.sendMessage(msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //获取当前用户手机号
    private String getPersonalPhone(){
        String phone = EMClient.getInstance().getCurrentUser();
        return phone;
    }

    //配置adapter
    private void initView(List<Moments> moments, View view, List<Attention> attentions) {
        frag01Adapter = new Frag01Adapter(getContext(), moments, R.layout.fragment_moments_frag01_item,attentions);
        momentsListView = view.findViewById(R.id.lv_moments_list);
        momentsListView.setAdapter(frag01Adapter);
    }
}
