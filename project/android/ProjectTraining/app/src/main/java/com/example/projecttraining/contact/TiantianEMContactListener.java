package com.example.projecttraining.contact;

import android.util.Log;

import com.example.projecttraining.util.ParentUtil;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

import java.util.ArrayList;
import java.util.List;

public class TiantianEMContactListener implements EMContactListener {
    private int where =0;
    private  static String TAG="TiantianEMContactListener";
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
        String currentUser= EMClient.getInstance().getCurrentUser();
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
}
