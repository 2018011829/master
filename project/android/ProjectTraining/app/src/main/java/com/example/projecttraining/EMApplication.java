package com.example.projecttraining;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;

public class EMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化EaseUI
        EMOptions options=new EMOptions();
        options.setAcceptInvitationAlways(true);
        EaseUI.getInstance().init(this,options);
        EMClient.getInstance().setDebugMode(true);
    }
}
