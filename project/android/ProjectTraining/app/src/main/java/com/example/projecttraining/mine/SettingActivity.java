package com.example.projecttraining.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.R;
import com.example.projecttraining.login.LoginByPasswordActivity;
import com.hyphenate.chat.EMClient;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_logout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_setting);

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
                startActivity(new Intent(SettingActivity.this, LoginByPasswordActivity.class));
                break;
        }
    }
}
