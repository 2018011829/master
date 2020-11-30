package com.example.projecttraining.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;
import com.example.projecttraining.books.activity.BooksHomePageActivity;
import com.example.projecttraining.idiom.activitys.IdiomActivity;

public class HomeFragment extends Fragment {

    private Button btnToIdiom;//成语专区
    private Button btnToRead;//阅读专区

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        //成语
        btnToIdiom = view.findViewById(R.id.btn_toIdiom);
        btnToIdiom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), IdiomActivity.class);
                getContext().startActivity(intent);
            }
        });

        //阅读
        btnToRead=view.findViewById(R.id.btn_toRead);
        btnToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), BooksHomePageActivity.class);
                getContext().startActivity(intent);
            }
        });

        return view;
    }
}