package com.example.projecttraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.projecttraining.login.LoginByPasswordActivity;
import com.example.projecttraining.util.ParentUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.tiantiansqlite.TianTianSQLiteOpenHelper;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private TianTianSQLiteOpenHelper tianTianSQLiteOpenHelper;
    private static final int sleepTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    protected void onStart() {

        super.onStart();
        tianTianSQLiteOpenHelper=TianTianSQLiteOpenHelper.getInstance(getApplicationContext());
        new Thread(new Runnable() {
            public void run() {
                if (EMClient.getInstance().isLoggedInBefore()) {
                    // auto login mode, make sure all group and conversation is loaed before enter the main screen
                    long start = System.currentTimeMillis();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    EMClient.getInstance().groupManager().loadAllGroups();
                    //加载所有好友信息
                    try {
                        List<String> usernames=EMClient.getInstance().contactManager().getAllContactsFromServer();
                        for(String username:usernames){
                            ParentUtil.storeOneParent(username,tianTianSQLiteOpenHelper);
                        }
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                    //存储当前用户的昵称和头像
                    ParentUtil.storeCurrentParent(EMClient.getInstance().getCurrentUser(),null);
                    long costTime = System.currentTimeMillis() - start;
                    //wait
                    if (sleepTime - costTime > 0) {
                        try {
                            Thread.sleep(sleepTime - costTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //如果之前登陆过，直接跳转到MainActivity
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }else {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                    }
                    //如果之前没有登录，跳转到登录页面
                    startActivity(new Intent(SplashActivity.this, LoginByPasswordActivity.class));
                    finish();
                }
            }
        }).start();
    }

}
