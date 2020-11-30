package com.example.projecttraining.idiom.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
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
public class IdiomActivity extends AppCompatActivity {

    @BindView(R.id.btn_menuMore) Button btnMenuMore;
    @BindView(R.id.search_idiom) LinearLayout searchIdiom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idiom);

        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_menuMore)
    public void clickForMore() {
        Toast.makeText(this,"点击了更多菜单",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.search_idiom)
    public void clickToSearch(){
        Intent intent = new Intent();
        intent.setClass(this,SearchIdiomActivity.class);
        startActivity(intent);
    }
}