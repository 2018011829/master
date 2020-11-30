package com.example.projecttraining.home.fragments.MomentsFragment.Frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;


public class Frag03 extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 添加布局文件
        View view=inflater.inflate(R.layout.fragment_moments_frag03, container, false);

        return view;
    }
}
