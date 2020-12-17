package com.example.projecttraining.books.activity;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.ChangeStatusBarColor;
import com.example.projecttraining.R;

public class SearchBookActivity extends AppCompatActivity {

    private EditText etSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_search_activity);
        ChangeStatusBarColor.initSystemBar(this);


    }
}
