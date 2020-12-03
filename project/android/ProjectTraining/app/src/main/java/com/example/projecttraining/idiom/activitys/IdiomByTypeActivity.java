package com.example.projecttraining.idiom.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.R;
import com.example.projecttraining.idiom.adapter.IdiomByTypeAdapter;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 2020-11-28
 * 2020-12-2
 * @author lrf
 */
public class IdiomByTypeActivity extends AppCompatActivity {

    @BindView(R.id.linear_forward) LinearLayout linearForward;
    @BindView(R.id.tv_idiom_type_name) TextView tvIdiomTypeName;
    @BindView(R.id.grid_idiom_by_type) GridView gVIdiomByType;

    private String idiomType;
    private List<String> idiomList;
    private IdiomByTypeAdapter idiomByTypeAdapter;
    private Handler myHandler;
    //定义Gson对象属性
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idiom_by_type);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        idiomType = intent.getStringExtra("idiomType");

        //Gson对象实例化
        initGson();

        getIdiomByType(ConfigUtil.SERVICE_ADDRESS + "SendIdiomByTypeServlet",idiomType);

        myHandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 1:
                        String json = (String) msg.obj;
                        // 得到集合类型
                        Type type = new TypeToken<List<String>>(){}.getType();
                        // 反序列化
                        idiomList = gson.fromJson(json,type);
                        Log.e("lrf",idiomList.toString());
                        initView();
                        break;
                }
            }
        };

    }

    @OnClick(R.id.linear_forward)
    public void clickToForward(){
        finish();
    }

    private void initView(){
        idiomByTypeAdapter = new IdiomByTypeAdapter(this,idiomList, R.layout.idiom_gridview_by_type);
        gVIdiomByType.setAdapter(idiomByTypeAdapter);
        tvIdiomTypeName.setText(idiomType);
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
     * 向服务器请求用户点击的某类型的成语信息
     * @param url
     * @param idiomType
     */
    private void getIdiomByType(String url,String idiomType) {
        new Thread(){
            @Override
            public void run() {
                try {
                    //创建URL对象
                    URL urlPath = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) urlPath.openConnection();
                    //设置网络请求方式为POST
                    conn.setRequestMethod("POST");
                    //获取网络输出流
                    OutputStream out = conn.getOutputStream();
                    out.write(idiomType.getBytes());
                    //获取网络输入流
                    InputStream in = conn.getInputStream();
                    //使用字符流读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
                    //读取字符信息
                    String json = reader.readLine();
                    //关闭流
                    reader.close();
                    in.close();
                    out.close();
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

}