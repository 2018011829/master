package com.example.projecttraining.mine;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.projecttraining.R;
import com.example.projecttraining.mine.adapter.CollectionPagerAdapter;
import com.example.projecttraining.ui.NoScrollViewPager;
import com.google.android.material.tabs.TabLayout;

public class MyCollectionActivity extends AppCompatActivity {
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_mycollection);


        //为ViewPager设置Adapter
        ViewPager viewPager = setViewPagerAdapter();

        //将ViewPager和TabLayout互相绑定
        BindViewPagerAndTabLayout(viewPager);
    }


    private void BindViewPagerAndTabLayout(final ViewPager viewPager) {
        tabLayout = findViewById(R.id.mine_collection_tl);
        TabLayout.Tab tab1 = tabLayout.getTabAt(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager){
            //设置tab选中的监听器
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
            }
        });
    }


    private ViewPager setViewPagerAdapter() {
        //得到ViewPager和SectionsPagerAdapter，并为ViewPager设置Adapter
        NoScrollViewPager viewPager=findViewById(R.id.mine_collection_view_pager);
        //设置不可滑动
//        viewPager.setNoScroll(true);
        CollectionPagerAdapter collectionPagerAdapter=new CollectionPagerAdapter(getSupportFragmentManager(),1);
        viewPager.setAdapter(collectionPagerAdapter);
        return viewPager;
    }
}
