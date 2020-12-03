package com.example.projecttraining.home.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projecttraining.R;
import com.example.projecttraining.contact.ContactSectionPagerAdapter;
import com.example.projecttraining.home.adapters.SectionsPagerAdapter;
import com.example.projecttraining.ui.NoScrollViewPager;
import com.google.android.material.tabs.TabLayout;
import com.mob.tools.gui.ViewPagerAdapter;

/**
 * 联系人的fragment
 * @author 雨
 * @date 2020.11.21
 */
public class RelationsFragment extends Fragment {
    public static final String TAG="RelationsFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_relation,container,false);
        ViewPager viewPager=setViewPagerAdapter(view);
        Log.e(TAG, "onCreateView: 绑定Adapter" );
        BindViewPagerAndTabLayout(viewPager,view);
        Log.e(TAG, "onCreateView: 设置Tablayout" );
        return view;
    }

    /**
     * 将ViewPager和TabLayout互相绑定,并设置TabLayout的选择改变事件
     * @param viewPager
     */
    private void BindViewPagerAndTabLayout(final ViewPager viewPager,View view) {
        TabLayout tabLayout=view.findViewById(R.id.tl);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }
    /**
     * 得到ViewPager引用，并这是Adapter
     * @return ViewPager
     */
    private ViewPager setViewPagerAdapter(View view) {
        //得到ViewPager和SectionsPagerAdapter，并为ViewPager设置Adapter
        ViewPager viewPager=view.findViewById(R.id.view_pager);
        ContactSectionPagerAdapter contactSectionPagerAdapter=new ContactSectionPagerAdapter(getChildFragmentManager(),1);
        Log.e(TAG, "setViewPagerAdapter: 设置adapter");
        viewPager.setAdapter(contactSectionPagerAdapter);
        return viewPager;
    }
}

