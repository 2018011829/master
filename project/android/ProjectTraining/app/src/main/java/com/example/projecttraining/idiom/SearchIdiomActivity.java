package com.example.projecttraining.idiom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.projecttraining.R;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchIdiomActivity extends AppCompatActivity {

    @BindView(R.id.cancel_searchIdiom) Button btnCancelSearchIdiom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_idiom);
    }

    @OnClick
    public void cancelSearch(){
        Intent intent = new Intent();
        intent.setClass(SearchIdiomActivity.this,IdiomActivity.class);
        startActivity(intent);
        finish();
    }
}