package com.example.projecttraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.projecttraining.home.adapters.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * MainActivity
 * @author 雨
 * @time 202011.21
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //为ViewPager设置Adapter
        ViewPager viewPager = setViewPagerAdapter();
        //将ViewPager和TabLayout互相绑定
        BindViewPagerAndTabLayout(viewPager);
    }

    /**
     * 将ViewPager和TabLayout互相绑定,并设置TabLayout的选择改变事件
     * @param viewPager
     */
    private void BindViewPagerAndTabLayout(final ViewPager viewPager) {
        TabLayout tabLayout=findViewById(R.id.tl);
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
        ViewPager viewPager=findViewById(R.id.view_pager);
        SectionsPagerAdapter sectionsPagerAdapter=new SectionsPagerAdapter(getSupportFragmentManager(),1);
        viewPager.setAdapter(sectionsPagerAdapter);
        return viewPager;
    }
}
