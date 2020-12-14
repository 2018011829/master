package com.example.projecttraining.books.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projecttraining.books.fragment.BigGradesFragment;
import com.example.projecttraining.books.fragment.SmallGradesFragment;

public class GradesPagerAdapter extends FragmentPagerAdapter {
    public static String grades;

    public GradesPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SmallGradesFragment();

            case 1:
                grades="big";
                return new BigGradesFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
