package com.example.projecttraining.idiom.activitys;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.R;
import com.example.projecttraining.idiom.adapter.IdiomResultAdapter;
import com.example.projecttraining.idiom.api.SearchIdiom;
import com.example.projecttraining.idiom.entity.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 2020-11-25
 * @author lrf
 */
public class SearchIdiomActivity extends AppCompatActivity {

    private List<Result> resultList;
    private IdiomResultAdapter resultAdapter;
    private String keyword;

    @BindView(R.id.tv_search_idiom) EditText etKeyword;
    @BindView(R.id.cancel_searchIdiom) Button btnCancelSearchIdiom;
    @BindView(R.id.lv_idiom_search_result) ListView lvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_idiom);

        ButterKnife.bind(this);

        initView();

    }

    //用户取消搜索
    @OnClick(R.id.cancel_searchIdiom)
    public void cancelSearch(){
        finish();
        Toast.makeText(getBaseContext(),"您取消了搜索",Toast.LENGTH_SHORT).show();
    }


    private void initView(){
        keyword = etKeyword.getText().toString().trim();
        try {
            resultList = SearchIdiom.GetIdiom(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultAdapter = new IdiomResultAdapter(this,resultList,R.layout.listview_idiom_search_result);
        lvResult.setAdapter(resultAdapter);
    }
}