package com.example.projecttraining.idiom.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.R;
import com.example.projecttraining.idiom.adapter.IdiomResultAdapter;
import com.example.projecttraining.idiom.entity.IdiomResult;
import com.example.projecttraining.idiom.entity.Result;
import com.example.projecttraining.util.IdiomJsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 2020-11-25
 * 2020-12-2
 * @author lrf
 */
public class SearchIdiomActivity extends AppCompatActivity {

    private List<Result> resultList = new ArrayList<>();
    private IdiomResultAdapter resultAdapter;
    private String keyword;
    private Handler myHandler;
    private String APPKEY = "52836ab53d4cf3e9";
    private String url = "https://api.jisuapi.com/chengyu/search";

    @BindView(R.id.tv_search_idiom) EditText etKeyword;
    @BindView(R.id.cancel_searchIdiom) Button btnCancelSearchIdiom;
    @BindView(R.id.lv_idiom_search_result) ListView lvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idiom_search);

        ButterKnife.bind(this);
        etKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // 实时监听输入框，并根据输入框中的内容即时搜索成语
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().equals("") && charSequence.toString().trim().length() != 0){
                    keyword = charSequence.toString().trim();
                    getIdiomSearchResult(keyword);
<<<<<<< HEAD
=======
                }else {
                    resultList = null;
                    initView();
>>>>>>> f6a91be86ac7c1a0bd45e5f5730da673a1d41b5e
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                myHandler = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        switch (msg.what){
                            case 1:
                                String json = (String) msg.obj;
                                Log.e("lrf",json);
                                IdiomResult idiomResult = IdiomJsonUtil.convertToIdiomResult(json);
                                Log.e("lrf_search反序列化",idiomResult.toString());
                                resultList = idiomResult.getResult();
                                initView();
                                break;
<<<<<<< HEAD
//                            default:
//                                resultList = null;
//                                initView();
//                                break;
=======
>>>>>>> f6a91be86ac7c1a0bd45e5f5730da673a1d41b5e
                        }
                    }
                };
            }
        });

    }

    private void getIdiomSearchResult(String keyword) {
        new Thread(){
            @Override
            public void run() {
                try {
                    // 创建URL对象
                    URL urlPath = new URL(url + "?appkey="+APPKEY+"&keyword="+URLEncoder.encode(keyword, "utf-8"));
                    HttpURLConnection conn = (HttpURLConnection) urlPath.openConnection();
                    // 设置网络请求方式为POST
                    conn.setRequestMethod("POST");
                    // 获取网络输入流
                    InputStream in = conn.getInputStream();
                    // 使用字符流读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
                    // 读取字符信息
                    String json = reader.readLine();
                    Log.e("lrf_search_json",json);
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

    //用户取消搜索
    @OnClick(R.id.cancel_searchIdiom)
    public void cancelSearch(){
        finish();
        Toast.makeText(getBaseContext(),"您取消了搜索",Toast.LENGTH_SHORT).show();
    }


    private void initView(){
        resultAdapter = new IdiomResultAdapter(this,resultList, R.layout.idiom_listview_search_result);
        lvResult.setAdapter(resultAdapter);
    }
}