package com.example.projecttraining.books.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.projecttraining.R;
import com.example.projecttraining.books.entitys.Book;
import com.example.projecttraining.books.entitys.Content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MoreContentActivity extends Activity {

    private ListView allContentList;
    private ImageView ivBack;
    private Book book;
    private List<Content> contentObj; //所有的目录对象：书所在的文件名称  章节名称  开始的行数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_more_content);

        allContentList=findViewById(R.id.all_content_list);
        ivBack=findViewById(R.id.iv_back);
        Intent intent=getIntent();
        book= (Book) intent.getSerializableExtra("book");
        contentObj= (List<Content>) intent.getSerializableExtra("contentObj");
//        Log.e("contentObj",contentObj.toString());
        final ArrayList<String> data=intent.getStringArrayListExtra("contents");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MoreContentActivity.this,
                android.R.layout.simple_list_item_1,data);
        allContentList.setAdapter(adapter);

        //点击某一章节的点击事件
        allContentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //获取某一章的内容
                Content content=contentObj.get(i);
                //跳转到阅读界面
                Intent intentRead=new Intent(MoreContentActivity.this,ReadBookActivity.class);
                intentRead.putExtra("currentIndex",i);
                intentRead.putExtra("book",book);
                intentRead.putExtra("contentObj", (Serializable) contentObj);
                intentRead.putStringArrayListExtra("contents",data);
                startActivity(intentRead);
                finish();
            }
        });

        //返回箭头的点击事件
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //返回上一页
                Intent intent=new Intent(MoreContentActivity.this,BookInfoActivity.class);
                intent.putExtra("book",book);
                startActivity(intent);
                finish();
            }
        });
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //返回上一页
            Intent intent=new Intent(MoreContentActivity.this,BookInfoActivity.class);
            intent.putExtra("book",book);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}