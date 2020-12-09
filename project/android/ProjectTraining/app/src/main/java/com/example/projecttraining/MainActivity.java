package com.example.projecttraining;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.projecttraining.home.adapters.SectionsPagerAdapter;

import com.example.projecttraining.util.AppConstantsUtil;
import com.example.projecttraining.util.ParentUtil;
import com.example.projecttraining.util.SpUtils;

import com.example.projecttraining.ui.NoScrollViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * MainActivity
 * @author 雨
 * @time 202011.21
 */

public class MainActivity extends AppCompatActivity {
    private final String TAG="MainActivity";
    private TabItem ti1;
    private View ti2;
    private TabItem ti3;
    private TabItem ti4;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * 引导页代码
         * 哪个页面是默认显示页面就把这段代码添加到哪里
         * */
        // 判断是否是第一次开启应用
        boolean isFirstOpen = SpUtils.getBoolean(this, AppConstantsUtil.FIRST_OPEN,false);
        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstOpen) {
            Intent intent = new Intent(MainActivity.this, IntroAppActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        //为ViewPager设置Adapter
        ViewPager viewPager = setViewPagerAdapter();

        //将ViewPager和TabLayout互相绑定
        BindViewPagerAndTabLayout(viewPager);

        //设置受到消息监听器，提示有消息未读
        EMClient.getInstance().chatManager().addMessageListener(new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> list) {
                //受到消息，如果当前不是在聊天界面，需要在MainActivity有一个提醒，联系人的图标应有一个小红点
                new Thread(){
                    @Override
                    public void run() {
                        TabLayout.Tab tab1=tabLayout.getTabAt(1);
                        if(EMClient.getInstance().chatManager().getUnreadMessageCount() >= 0){
                            tab1.setIcon(R.mipmap.relations_black_noreaded_message);
                        }
                    }
                }.start();

            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageRead(List<EMMessage> list) {

            }

            @Override
            public void onMessageDelivered(List<EMMessage> list) {

            }

            @Override
            public void onMessageRecalled(List<EMMessage> list) {

            }

            @Override
            public void onMessageChanged(EMMessage emMessage, Object o) {

            }
        });


    }

    /**
     * 将ViewPager和TabLayout互相绑定,并设置TabLayout的选择改变事件
     * @param viewPager
     */
    private void BindViewPagerAndTabLayout(final ViewPager viewPager) {
       tabLayout=findViewById(R.id.tl);
       TabLayout.Tab tab1 =tabLayout.getTabAt(1);
       new Thread(){
           @Override
           public void run() {
               if(EMClient.getInstance().chatManager().getUnreadMessageCount()==0){
                   tab1.setIcon(R.mipmap.relations_black);
               }else{
                   tab1.setIcon(R.mipmap.relations_black_noreaded_message);
               }
           }
       }.start();
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager){
            //设置tab选中的监听器
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                switch (tab.getPosition()){
                    case 0:
                        tab.setIcon(R.mipmap.home_green);
                        break;
                    case 1:
                        tab.setIcon(R.mipmap.relations_green);
                        ParentUtil.isSelectedRelationTab=true;
                        break;
                    case 2:
                        tab.setIcon(R.mipmap.moments_green);
                        break;
                    case 3:
                        tab.setIcon(R.mipmap.my_green);
                        break;
                }
            }
            //设置tab未选中的监听器
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tab.setIcon(R.mipmap.home_black);
                        break;
                    case 1:
                        tab.setIcon(R.mipmap.relations_black);
                        break;
                    case 2:
                        tab.setIcon(R.mipmap.moments_black);
                        break;
                    case 3:
                        tab.setIcon(R.mipmap.my_black);
                        break;
                }
            }
        });
    }
    /**
     * 得到ViewPager引用，并这是Adapter
     * @return ViewPager
     */
    private ViewPager setViewPagerAdapter() {
        //得到ViewPager和SectionsPagerAdapter，并为ViewPager设置Adapter
        NoScrollViewPager viewPager=findViewById(R.id.view_pager);
        //设置不可滑动
        viewPager.setNoScroll(true);
        SectionsPagerAdapter sectionsPagerAdapter=new SectionsPagerAdapter(getSupportFragmentManager(),1);
        viewPager.setAdapter(sectionsPagerAdapter);
        return viewPager;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }
}
