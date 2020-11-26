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
import com.example.projecttraining.login.LoginByPasswordActivity;
import com.hyphenate.chat.EMClient;

public class MyFragment extends Fragment implements View.OnClickListener {
    private Button btnLogout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my,container,false);

        //TODO 获取控件引用
        btnLogout=view.findViewById(R.id.btn_logout);
        //TODO 设置控件内容
        btnLogout.setOnClickListener(this);

        return view;
    }

    /**
     * 退出登录的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_logout:
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                EMClient.getInstance().logout(true);
                                startActivity(new Intent(getContext(), LoginByPasswordActivity.class));
                            }
                        }
                ).start();

                break;
        }
    }
}
