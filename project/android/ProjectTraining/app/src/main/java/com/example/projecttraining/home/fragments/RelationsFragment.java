package com.example.projecttraining.home.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.contact.adapter.ContactSectionPagerAdapter;
import com.example.projecttraining.util.ParentUtil;
import com.google.android.material.tabs.TabLayout;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseParentUtil;

/**
 * 联系人的fragment
 * @author 雨
 * @date 2020.11.21
 */
public class RelationsFragment extends Fragment {

    private ImageView photo;
    private TextView titleName;
    public static final String TAG="RelationsFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_relation,container,false);
        photo = view.findViewById(R.id.photo);
        titleName = view.findViewById(R.id.top_title_name);
        Glide.with(getContext())
                .load(EaseParentUtil.currentUserAvatar)
                .circleCrop()
                .into(photo);
        titleName.setText("联 系 人");
        ViewPager viewPager=setViewPagerAdapter(view);
        BindViewPagerAndTabLayout(viewPager,view);
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
        viewPager.setAdapter(contactSectionPagerAdapter);
        return viewPager;
    }
}

