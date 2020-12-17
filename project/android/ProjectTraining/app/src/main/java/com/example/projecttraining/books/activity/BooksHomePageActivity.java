package com.example.projecttraining.books.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.projecttraining.ChangeStatusBarColor;
import com.example.projecttraining.MainActivity;
import com.example.projecttraining.R;
import com.example.projecttraining.books.adapters.GradesPagerAdapter;
import com.example.projecttraining.ui.NoScrollViewPager;
import com.example.projecttraining.util.ConfigUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class BooksHomePageActivity extends AppCompatActivity {

    public static BooksHomePageActivity activity;

    private ImageView ivBack;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 2: //显示错误信息
                    String errorInfo= (String) msg.obj;
                    Toast.makeText(BooksHomePageActivity.this,
                            errorInfo, Toast.LENGTH_SHORT).show();
                    break;
                case 3: //获取到轮播图照片的信息 展示
                    String strGsonImgs= (String) msg.obj;
                    //解析Gson串
                    //集合反序列化
                    //获取集合类型
                    Type typeList=new TypeToken<List<String>>(){}.getType();
                    List<String> imgs=new Gson().fromJson(strGsonImgs,typeList);
//                    showBannerImgs(imgs);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_home_page);
        ChangeStatusBarColor.initSystemBar(this);

        //为ViewPager设置Adapter
        ViewPager viewPager = setViewPagerAdapter();

        //将ViewPager和TabLayout互相绑定
        BindViewPagerAndTabLayout(viewPager);

        activity=this;
        ivBack=findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BooksHomePageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 将照片的信息显示在轮播图中
     * @param imgs 照片名的集合
     */
    private void showBannerImgs(List<String> imgs) {
        //设置图片加载器
        //设置要显示的图片
        List<String> list=new ArrayList<>();
        for (String img:imgs){
            list.add(ConfigUtil.SERVICE_ADDRESS+"bannerHomePageImgs/"+img);
        }
    }

    /**
     * 将ViewPager和TabLayout互相绑定,并设置TabLayout的选择改变事件
     * @param viewPager
     */
    private void BindViewPagerAndTabLayout(final ViewPager viewPager) {
        TabLayout tabLayout=findViewById(R.id.tl);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }


    /**
     * 得到ViewPager引用，并这是Adapter
     * @return ViewPager
     */
    private ViewPager setViewPagerAdapter() {
        //得到ViewPager和GradesPagerAdapter，并为ViewPager设置Adapter
        NoScrollViewPager viewPager=findViewById(R.id.view_pager);
        //设置不可滑动
        viewPager.setNoScroll(false);
        GradesPagerAdapter gradesPagerAdapter=new GradesPagerAdapter(getSupportFragmentManager(),1);
        viewPager.setAdapter(gradesPagerAdapter);

        return viewPager;
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //返回主页
            Intent intent=new Intent(BooksHomePageActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}