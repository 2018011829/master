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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.projecttraining.R;
import com.example.projecttraining.books.adapters.MoreBooksAdapter;
import com.example.projecttraining.books.entitys.Book;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MoreBooksActivity extends Activity {

    private String grades;
    private int currentListSize=0;
    private ProgressBar pb; //进度条
    private SmartRefreshLayout smartRefreshLayout; //刷新
    private ImageView ivBack;
    private TextView tvTitleType;
    private TextView tvType;
    private ListView listMoreBooks;
    private List<Book> list; //数据源
    private List<Book> temp; //刷新
    private MoreBooksAdapter adapter; //适配器 用于刷新
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1://得到所有图书的Gson字符串，将字符串转换成装有图书的集合
                    String strGsonBooks= (String) msg.obj;
                    //解析Gson串
                    //集合反序列化
                    //获取集合类型
                    if (!strGsonBooks.equals("没有更多书籍了")){
                        Type type=new TypeToken<List<Book>>(){}.getType();
                        //将字符串转换成数据
                        if (list==null){
                            list=new Gson().fromJson(strGsonBooks,type);
                            //初始化控件
                            initViews();
                            pb.setVisibility(View.INVISIBLE);
                        }else {
                            temp=new Gson().fromJson(strGsonBooks,type);
                            list.addAll(temp);
                            adapter.notifyDataSetChanged();
                            //通知加载数据完毕
                            smartRefreshLayout.finishLoadMore();
                        }
                        currentListSize=list.size();
                        Log.e("type",list.toString());
                    }else {
                        //通知没有更多数据可加载
                        smartRefreshLayout.finishLoadMoreWithNoMoreData();
                        Toast.makeText(MoreBooksActivity.this,
                                "没有更多书籍了！",
                                Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_more_books);

        pb=findViewById(R.id.progressbar);
        smartRefreshLayout=findViewById(R.id.srl);
        pb.setVisibility(View.VISIBLE);
        tvTitleType=findViewById(R.id.tv_title_type);
        ivBack=findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(MoreBooksActivity.this, BooksHomePageActivity.class);
//                startActivity(intent);
                finish();
            }
        });
//        tvType=findViewById(R.id.tv_type);
        listMoreBooks=findViewById(R.id.list_more_books);
        //获取参数
        Intent intent=getIntent();
        String type=intent.getStringExtra("type");
        grades=intent.getStringExtra("grades");
        tvTitleType.setText(type);
//        tvType.setText(type);
        //连接服务器获取该类型的所有图书
        getDataFromServer(type,grades);

        //给智能刷新控件注册上拉加载更多事件监听器
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多数据(假设超过10条数据则加载完毕）
                getDataFromServer(type,grades);
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        adapter=new MoreBooksAdapter(getApplicationContext(),
                R.layout.more_books_griditem,list);
        //设置adapter
        listMoreBooks.setAdapter(adapter);
        //设置点击事件
        listMoreBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book=list.get(i);
                Intent intent=new Intent(MoreBooksActivity.this, BookInfoActivity.class);
                //传递类型参数
                intent.putExtra("book",book);
                intent.putExtra("grades",grades);
                //标记是从更多书的界面跳转走的
                intent.putExtra("flag","flag");
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 连接服务器获取该类型、该年级的所有图书
     */
    private void getDataFromServer(String type,String grades) {        //创建OkHttpClient对象
        OkHttpClient okHttpClient=new OkHttpClient();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS
                        + "MoreBooksServlet?type="+type+"&grades="+grades+"&currentListSize="+currentListSize)
                .build();
        //创建CALL对象
        Call call = okHttpClient.newCall(request);
        //异步方式提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("MoreBooksActivity", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //获取服务端返回的数据（假设是字符串）
                String result = response.body().string();
//                Log.e("MoreBooksActivity", result);

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
            //返回分类页
//            Intent intent=new Intent(MoreBooksActivity.this, BooksHomePageActivity.class);
//            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}