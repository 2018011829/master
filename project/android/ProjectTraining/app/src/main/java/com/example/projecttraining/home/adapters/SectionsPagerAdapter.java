package com.example.projecttraining.home.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projecttraining.home.fragments.HomeFragment;
import com.example.projecttraining.home.fragments.MomentsFragment;
import com.example.projecttraining.home.fragments.MyFragment;
import com.example.projecttraining.home.fragments.RelationsFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();

            case 1:
                return new RelationsFragment();

            case 2:
                return new MomentsFragment();

            case 3:
                return new MyFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
