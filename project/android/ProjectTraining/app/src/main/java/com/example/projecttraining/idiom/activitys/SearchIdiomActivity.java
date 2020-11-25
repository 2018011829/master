package com.example.projecttraining.idiom.activitys;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 2020-11-25
 * @author lrf
 */
public class SearchIdiomActivity extends AppCompatActivity {

    @BindView(R.id.cancel_searchIdiom) Button btnCancelSearchIdiom;
    @BindView(R.id.lv_idiom_search_result) ListView searchResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_idiom);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.cancel_searchIdiom)
    public void cancelSearch(){
        finish();
        Toast.makeText(getBaseContext(),"您取消了搜索",Toast.LENGTH_SHORT).show();
    }
}