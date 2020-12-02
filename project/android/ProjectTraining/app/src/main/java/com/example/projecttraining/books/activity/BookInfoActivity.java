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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.books.entitys.Book;
import com.example.projecttraining.books.entitys.Content;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookInfoActivity extends Activity implements View.OnClickListener {

    private ProgressBar progressbar; //进度条
    private TextView tvGetMoreContents;
    private ImageView ivBack;
    private TextView tvTitleName;
    private ImageView ivShouCang;
    private ImageView ivShare;
    private ImageView ivBookImg;
    private TextView tvBookName;
    private TextView tvAuthor;
    private TextView tvBookType;
    private TextView tvBookIntroduce;
    private ListView bookList;
    private Button btnAddBookshelf;
    private Button btnStartRead;
    private String type;
    private Book book;
    private List<Content> contents;
    private List<String> data;
    private String flag; //标记是从更多书的界面跳转来的 MoreBooksActivity
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1://得到图书所有章节列表的Gson字符串，将字符串转换成装有集合
                    String strGsonBooks= (String) msg.obj;
                    //解析Gson串
                    //集合反序列化
                    //获取集合类型
                    Type type=new TypeToken<List<Content>>(){}.getType();
                    //将字符串转换成数据
                    contents=new Gson().fromJson(strGsonBooks,type);
                    Log.e("contents",contents.toString());
                    //初始化控件
                    initViews();
                    progressbar.setVisibility(View.INVISIBLE);
                    break;
                case 2: //显示错误信息
                    String errorInfo= (String) msg.obj;
                    Toast.makeText(BookInfoActivity.this,
                            errorInfo, Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏以及状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_book_info);

        findViews();
        //获取intent中的数据，并进行解析显示在控件中
        Intent intent=getIntent();
        book= (Book) intent.getSerializableExtra("book");
        flag=intent.getStringExtra("flag");
        Log.e("book",book.toString());
        showInfoToWidget();

        //获取文章的章节列表，并显示在Listview中
        getArticleFromServer(book.getContent());
    }

    /**
     * 初始化目录列表
     */
    private void initViews() {
        //准备数据源
        data=new ArrayList<>();
        List<String> list=new ArrayList<>();
        int i=0;
        for (Content c:contents){
            data.add(c.getContentName());
            if (i<=3){
                list.add(c.getContentName());
            }
            i++;
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(BookInfoActivity.this,android.R.layout.simple_list_item_1,list);
        bookList.setAdapter(adapter);
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentRead=new Intent(BookInfoActivity.this,ReadBookActivity.class);
                intentRead.putExtra("currentIndex",i);
                intentRead.putExtra("contentObj", (Serializable) contents);
                intentRead.putStringArrayListExtra("contents", (ArrayList<String>) data);
                intentRead.putExtra("book",book);
                intentRead.putExtra("activity","BookInfoActivity");
                startActivity(intentRead);
                finish();
            }
        });
    }

    /**
     * 获取文章的章节列表，并显示在Listview中
     * @param content 要获取的文章的文件名
     */
    private void getArticleFromServer(String content) {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient=new OkHttpClient();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS
                        + "BookContentsServlet?content="+content)
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
                msg.obj = "目录信息获取失败！";
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
     * 获取intent中的数据，并进行解析显示在控件中
     */
    private void showInfoToWidget() {
        tvTitleName.setText(book.getName());
        Glide.with(getApplicationContext())
                .load(ConfigUtil.SERVICE_ADDRESS+"bookImgs/"+book.getImg())
                .placeholder(R.mipmap.loading)
                .error(R.drawable.faliure)
                .fallback(R.drawable.faliure)
                .into(ivBookImg);
        tvBookName.setText(book.getName());
        tvAuthor.setText("作者："+book.getAuthor());
        tvBookType.setText("类型："+book.getType());
        tvBookIntroduce.setText(book.getIntroduce());
        type=book.getType();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back://点击返回
                if (flag!=null){
                    Intent intent=new Intent(BookInfoActivity.this,MoreBooksActivity.class);
                    intent.putExtra("type",type);
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(BookInfoActivity.this,BooksHomePageActivity.class);
                    startActivity(intent);
                }
                finish();
                break;
            case R.id.tv_get_more_content://点击显示更多目录
                Intent intentContent=new Intent(BookInfoActivity.this,MoreContentActivity.class);
                intentContent.putExtra("contentObj",(Serializable) contents);
                intentContent.putStringArrayListExtra("contents", (ArrayList<String>) data);
                intentContent.putExtra("book",book);
                startActivity(intentContent);
                finish();
                break;
            case R.id.iv_shou_cang://点击收藏
                break;
            case R.id.iv_share://点击分享
                break;
            case R.id.add_bookshelf://点击加入书架
                break;
            case R.id.start_read://点击开始阅读
                //获取整本书的内容
                Intent intentText=new Intent(BookInfoActivity.this,ReadBookActivity.class);
                intentText.putExtra("book",book);
                startActivity(intentText);
                finish();
                break;
        }
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //返回上一页
            if (flag!=null){
                Intent intent=new Intent(BookInfoActivity.this,MoreBooksActivity.class);
                intent.putExtra("type",type);
                startActivity(intent);
            }else {
                Intent intent=new Intent(BookInfoActivity.this,BooksHomePageActivity.class);
                startActivity(intent);
            }

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void findViews() {
        progressbar=findViewById(R.id.progressbar);
        progressbar.setVisibility(View.VISIBLE);
        tvGetMoreContents=findViewById(R.id.tv_get_more_content);
        ivBack=findViewById(R.id.iv_back);
        tvTitleName=findViewById(R.id.tv_title_name);
        ivShouCang=findViewById(R.id.iv_shou_cang);
        ivShare=findViewById(R.id.iv_share);
        ivBookImg=findViewById(R.id.iv_book_img);
        tvBookName=findViewById(R.id.tv_book_name);
        tvAuthor=findViewById(R.id.tv_book_author);
        tvBookType=findViewById(R.id.tv_book_type);
        tvBookIntroduce=findViewById(R.id.tv_book_introduce);
        bookList=findViewById(R.id.book_contents_list);
        btnAddBookshelf=findViewById(R.id.add_bookshelf);
        btnStartRead=findViewById(R.id.start_read);
        tvGetMoreContents.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivShouCang.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        btnAddBookshelf.setOnClickListener(this);
        btnStartRead.setOnClickListener(this);
    }
}