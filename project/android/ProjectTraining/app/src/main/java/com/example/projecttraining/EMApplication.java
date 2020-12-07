package com.example.projecttraining;

import android.app.Application;
import android.os.Looper;
import android.util.Log;

import com.example.projecttraining.contact.ContactManager;
import com.example.projecttraining.contact.Parent;
import com.example.projecttraining.util.ParentUtil;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class EMApplication extends Application {
    private final String TAG="EMApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化EaseUI
        EMOptions options=new EMOptions();
        options.setAcceptInvitationAlways(false);
        EaseUI.getInstance().init(this,options);
        EMClient.getInstance().setDebugMode(true);

        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {
            @Override
            public void onContactAdded(String s) {

            }

            @Override
            public void onContactDeleted(String s) {

            }

            @Override
            public void onContactInvited(String s, String s1) {
                //收到好友申请
                Log.e(TAG, "onContactInvited: 收到好友申请"+s);
                String currentUser=EMClient.getInstance().getCurrentUser();
                List<String> newFriendsList=ContactManager.newFriends.get(currentUser);
                if(newFriendsList==null){
                    newFriendsList=new ArrayList<>();
                }
                if(!newFriendsList.contains(s)){
                    newFriendsList.add(s);
                }
                ContactManager.newFriends.put(currentUser,newFriendsList);
                Log.e(TAG, "onContactInvited: "+newFriendsList );
            }

            @Override
            public void onFriendRequestAccepted(String s) {
                ParentUtil.isContactAddedOrDeleted=true;
            }

            @Override
            public void onFriendRequestDeclined(String s) {

            }
        });
        //注册消息监听
        EMClient.getInstance().chatManager().addMessageListener(new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> list) {
                ParentUtil.isMessageReceived=true;
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> list) {

            }

            @Override
            public void onMessageRead(List<EMMessage> list) {

            }

            @Override
            public void onMessageDelivered(List<EMMessage> list) {

            }

            @Override
            public void onMessageRecalled(List<EMMessage> list) {

            }

            @Override
            public void onMessageChanged(EMMessage emMessage, Object o) {

            }
        });
    }
}
