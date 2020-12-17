package com.example.projecttraining.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.ChangeStatusBarColor;
import com.example.projecttraining.R;
import com.example.projecttraining.login.LoginByPasswordActivity;
import com.hyphenate.chat.EMClient;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_logout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_setting);
        ChangeStatusBarColor.initSystemBar(this);

        findView();
        setListener();
    }

    private void setListener() {
        btn_logout.setOnClickListener(this);
    }

    private void findView() {
        btn_logout = findViewById(R.id.btn_mine_logout);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_mine_logout:
                EMClient.getInstance().logout(true);
                //跳转登录到饿activity之前，清空所有的活动栈，否则一直返回会返回到上一个用户的登录状态
                startActivity(new Intent(SettingActivity.this, LoginByPasswordActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;
        }
    }
}
