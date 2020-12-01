package com.example.projecttraining.idiom.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projecttraining.idiom.fragment.IdiomAllusionFragment;
import com.example.projecttraining.idiom.fragment.IdiomExampleSentenceFragment;
import com.example.projecttraining.idiom.fragment.IdiomMeanFragment;
import com.example.projecttraining.idiom.fragment.IdiomNearAntonymsFragment;

/**
 * 2020-11-30
 *
 * @author lrf
 */
public class IdiomInfoAdapter extends FragmentPagerAdapter {
    public IdiomInfoAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new IdiomMeanFragment();
            case 1:
                return new IdiomNearAntonymsFragment();
            case 2:
                return new IdiomAllusionFragment();
            case 3:
                return new IdiomExampleSentenceFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
