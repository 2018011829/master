package com.example.projecttraining.contact;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.R;
import com.example.projecttraining.util.ParentUtil;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

import java.util.ArrayList;
import java.util.List;


public class NewFriendsActivity extends AppCompatActivity {
    private ImageView ivReturn;
    private TextView tvAddFriedns;
    private ListView lvNewFriends;
    private List<Parent> parents;
    NewFriendAdapter newFriendAdapter;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 2:
                    Parent parent=(Parent) msg.obj;
                    parents.add(parent);
                    newFriendAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friends);
        ivReturn = findViewById(R.id.iv_return);
        tvAddFriedns = findViewById(R.id.tv_add_friends);
        lvNewFriends = findViewById(R.id.lv_new_friends);
        parents=new ArrayList<>();
        newFriendAdapter=new NewFriendAdapter(getApplicationContext(),parents,R.layout.contact_item_new_friend);
        lvNewFriends.setAdapter(newFriendAdapter);
        List<String> newFriendsList= ContactManager.newFriends.get(EMClient.getInstance().getCurrentUser());
        if(newFriendsList!=null){
            for(String phone:newFriendsList){
                ParentUtil.getOneParent(phone,handler);
            }
        }else{
            findViewById(R.id.tv_no_friends).setVisibility(View.VISIBLE);
        }
        ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvAddFriedns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddFriendsActivity.class));
            }
        });
    }
}
