package com.example.projecttraining.home.fragments.MomentsFragment.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MomentsFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public MomentsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public MomentsFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
