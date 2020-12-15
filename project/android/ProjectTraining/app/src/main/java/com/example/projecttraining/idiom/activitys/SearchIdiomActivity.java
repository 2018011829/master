package com.example.projecttraining.idiom.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MyFragment;
import com.example.projecttraining.idiom.FlowLayout;
import com.example.projecttraining.idiom.adapter.IdiomResultAdapter;
import com.example.projecttraining.idiom.entity.IdiomResult;
import com.example.projecttraining.idiom.entity.Result;
import com.example.projecttraining.util.ConfigUtil;
import com.example.projecttraining.util.IdiomJsonUtil;
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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 2020-11-25
 * 2020-12-2
 * 2020-12-6
 * 2020-12-15
 *
 * @author lrf
 */
public class SearchIdiomActivity extends AppCompatActivity {
    //定义Gson对象属性
    private Gson gson;
    private String keyword;
    private Handler myHandler;
    private IdiomResultAdapter resultAdapter;
    private List<Result> resultList = new ArrayList<>();
    private List<String> historyList = new ArrayList<>();
    private String APPKEY = "52836ab53d4cf3e9";
    private String url = "https://api.jisuapi.com/chengyu/search";

    @BindView(R.id.tv_search_idiom) EditText etKeyword;
    @BindView(R.id.idiom_delete_edit) ImageView idiomDeleteEdit;
    @BindView(R.id.cancel_searchIdiom) Button btnCancelSearchIdiom;
    @BindView(R.id.search_history) LinearLayout searchHistory;
    @BindView(R.id.tv_result_null) TextView tvResultNull;
    @BindView(R.id.lv_idiom_search_result) ListView lvResult;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    String json = (String) msg.obj;
                    if (!json.equals("没有搜索历史") && json != null) {
                        // 得到集合类型
                        Type type = new TypeToken<List<String>>(){}.getType();
                        // 反序列化
                        historyList = gson.fromJson(json, type);

                        FlowLayout flowLayout = new FlowLayout(getBaseContext());
                        flowLayout.setPadding(10, 20, 10, 10);
                        flowLayout.setVerticalSpacing(20);
                        flowLayout.setHorizontalSpacing(20);

                        TextView tvText = new TextView(SearchIdiomActivity.this);
                        tvText.setText("搜索历史：");
                        tvText.setTextSize(14);
                        searchHistory.addView(tvText);
                        searchHistory.addView(flowLayout);
                        for (int i = 0; i < historyList.size(); ++i) {
                            final TextView textView = new TextView(getBaseContext());
                            textView.setId(i);
                            textView.setTextSize(12);
                            textView.setTextColor(Color.BLACK );
                            textView.setGravity(Gravity.CENTER);
                            textView.setText(historyList.get(i));
                            textView.setPadding(10,15,10,15);
                            textView.setBackgroundResource(R.drawable.idiom_search_history_back);
                            //添加到布局文件中去
                            flowLayout.addView(textView);
                            //更新界面
                            flowLayout.invalidate();
                            textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String idiomName = textView.getText().toString();
                                    Intent intent = new Intent();
                                    intent.setClass(SearchIdiomActivity.this, IdiomInfoActivity.class);
                                    intent.putExtra("name", idiomName);
                                    SearchIdiomActivity.this.startActivity(intent);
                                    Toast.makeText(getBaseContext(), idiomName, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idiom_search);

        ButterKnife.bind(this);

        //Gson对象实例化
        initGson();

        if (!MyFragment.phoneNum.equals("") && !MyFragment.childName.equals("")) {
            getSearchHistory(ConfigUtil.SERVICE_ADDRESS + "SendSearchIdiomHistoryServlet", MyFragment.phoneNum, MyFragment.childName);
        }

        etKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // 实时监听输入框，并根据输入框中的内容即时搜索成语
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().equals("") && charSequence.toString().trim().length() != 0) {
                    keyword = charSequence.toString().trim();
                    getIdiomSearchResult(keyword);
                    idiomDeleteEdit.setImageResource(R.drawable.idiom_delete_edit);
                    idiomDeleteEdit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            etKeyword.setText("");
                        }
                    });
                    searchHistory.removeAllViews();
                } else {
                    tvResultNull.setText(null);
                    resultList = null;
                    initView();
                    idiomDeleteEdit.setImageResource(R.drawable.idiom_yincang);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                myHandler = new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        switch (msg.what) {
                            case 1:
                                String json = (String) msg.obj;
                                Log.e("lrf", json);
                                IdiomResult idiomResult = IdiomJsonUtil.convertToIdiomResult(json);
                                Log.e("lrf_search反序列化", idiomResult.toString());
                                if (idiomResult.getStatus() == 0) {
                                    tvResultNull.setTextSize(18);
                                    tvResultNull.setText("搜索结果：");
                                    resultList = idiomResult.getResult();
                                    initView();
                                } else {
                                    tvResultNull.setTextSize(18);
                                    tvResultNull.setText("暂无相关成语");
                                    resultList = null;
                                    initView();
                                }
                                break;
                        }
                    }
                };
            }
        });

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
     * 获取该用户当前孩子的搜索历史
     *
     * @param url
     * @param phoneNum
     * @param childName
     */
    private void getSearchHistory(String url, String phoneNum, String childName) {
        new Thread() {
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
                    String str = phoneNum + "&" + childName;
                    out.write(str.getBytes());
                    //获取网络输入流
                    InputStream in = conn.getInputStream();
                    //使用字符流读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    //读取字符信息
                    String result = reader.readLine();
                    Log.e("lrf_的成语搜索历史信息", result);
                    //关闭流
                    reader.close();
                    in.close();
                    out.close();
                    //通过发送message对象将数据发布出去
                    //获取Message对象
                    Message msg = mHandler.obtainMessage();
                    //设置Message对象的属性(what、obj)
                    msg.what = 0;
                    msg.obj = result;
                    //发送Message对象
                    mHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 根据用户输入的内容，搜索包含它的成语
     *
     * @param keyword
     */
    private void getIdiomSearchResult(String keyword) {
        new Thread() {
            @Override
            public void run() {
                try {
                    // 创建URL对象
                    URL urlPath = new URL(url + "?appkey=" + APPKEY + "&keyword=" + URLEncoder.encode(keyword, "utf-8"));
                    HttpURLConnection conn = (HttpURLConnection) urlPath.openConnection();
                    // 设置网络请求方式为POST
                    conn.setRequestMethod("POST");
                    // 获取网络输入流
                    InputStream in = conn.getInputStream();
                    // 使用字符流读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    // 读取字符信息
                    String json = reader.readLine();
                    Log.e("lrf_search_json", json);
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
    public void cancelSearch() {
        finish();
        Toast.makeText(getBaseContext(), "您取消了搜索", Toast.LENGTH_SHORT).show();
    }


    private void initView() {
        resultAdapter = new IdiomResultAdapter(this, resultList, R.layout.idiom_listview_search_result);
        lvResult.setAdapter(resultAdapter);
    }
}