package com.example.projecttraining.idiom.activitys;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.projecttraining.R;
import com.example.projecttraining.idiom.fragment.ContentFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;

/**
 * 2020-11-25
 * 2020-11-26
 * 2020-11-27
 * 2020-11-28
 * @author lrf
 */
public class IdiomActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {

    @BindView(R.id.search_idiom) ImageView searchIdiom;

    // 用于保存获取的成语父类型名称
    private List<String> typeList = new ArrayList<>();
    // 用于保存获取的成语父类型图片
    private List<Integer> drawableList = new ArrayList<>();
    // 用于保存界面跳转传递的json串
    private String jsonStr;
    //定义Gson对象属性
    private Gson gson;
    // 用于改变子类型集合的变量集合
    private List<String> strList = new ArrayList<>();
    // 用于保存成语父类型所对应的子类型集合
    private List<List<String>> listList = new ArrayList<>();

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ViewAnimator viewAnimator;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idiom);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        jsonStr = intent.getStringExtra("idiomType");

        //Gson对象实例化
        initGson();
        //初始化数据
        initData();

        ContentFragment contentFragment = ContentFragment.newInstance(listList.get(0));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, contentFragment)
                .commit();
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        setActionBar();
        createMenuList(typeList);
        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);

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
     * 初始化数据
     */
    private void initData() {
        //1. 得到集合的类型
        Type type = new TypeToken<LinkedHashMap<String, List<String>>>(){}.getType();
        //2. 反序列化
        LinkedHashMap<String, List<String>> idiomTypeMap = gson.fromJson(jsonStr,type);
        Log.e("lrf","反序列化之后的成语类型 ——> " + idiomTypeMap.toString());

        for(Map.Entry<String, List<String>> map : idiomTypeMap.entrySet()){
            typeList.add(map.getKey());
            listList.add(map.getValue());
        }
        Log.e("lrf","获得的父类型个数：" + typeList.size());

        // 初始化左侧菜单栏图片集合
        for(int i = 1; i <= typeList.size(); ++i){
            int drawId = getResources().getIdentifier(
                    "icn_" + i,
                    "drawable",
                    getPackageName()
            );
            drawableList.add(drawId);
        }
    }

    /**
     * 设置左侧菜单列表
     * @param strings 菜单名称集合
     */
    private void createMenuList(List<String> strings) {
        for (int i = 0; i < strings.size(); ++i) {
            SlideMenuItem menuItem = new SlideMenuItem(typeList.get(i), drawableList.get(i));
            list.add(menuItem);
        }
        Log.e("lrf","左侧菜单列表集合长度——"+list.size());
    }


    private void setActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition, String name) {
        // 根据成语父类型改变对应子类型
        for(int i = 0; i < typeList.size(); ++i){
            if(name.equals(typeList.get(i))){
                this.strList = listList.get(i);
            }
        }

        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        //这里定义圆形揭露动画
        Animator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
        //在动画执行前将之前获得的fragment区域的截图覆盖到真正的fragment之上
        findViewById(R.id.content_overlay).setBackground(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        //在动画执行中更换真正的新的fragment
        ContentFragment contentFragment = ContentFragment.newInstance(this.strList);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
        return contentFragment;
    }

    /**
     * 判断是哪一个菜单被点击了
     * @param slideMenuItem
     * @param screenShotable
     * @param position
     * @return
     */
    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        System.out.println(slideMenuItem.getName());
        return replaceFragment(screenShotable, position, slideMenuItem.getName());
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();
    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }

    // 点击搜索框，跳转到搜索成语的界面
    @OnClick(R.id.search_idiom)
    public void clickToSearch(){
        Intent intent = new Intent();
        intent.setClass(this, SearchIdiomActivity.class);
        startActivity(intent);
    }

}