package com.example.projecttraining.mine.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projecttraining.mine.fragment.BookFragment;
import com.example.projecttraining.mine.fragment.IdiomFragment;

public class CollectionPagerAdapter extends FragmentPagerAdapter {

    public CollectionPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BookFragment();

            case 1:
                return new IdiomFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
