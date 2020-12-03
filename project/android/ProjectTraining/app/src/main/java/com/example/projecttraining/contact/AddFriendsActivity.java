package com.example.projecttraining.contact;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.projecttraining.R;
import com.example.projecttraining.util.ParentUtil;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

import java.util.ArrayList;
import java.util.List;

public class AddFriendsActivity extends AppCompatActivity {
    private static final String TAG = "AddFriendsActivity";
    private SearchView sv;
    private ListView lvSearchResult;
    private List<Parent> parents;
    private AddFriendAdapter addFriendAdapter;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 3:
                    Log.e(TAG, "handleMessage: 搜到搜索到的用户" );
                    parents = (List<Parent>) msg.obj;
                    AddFriendAdapter addFriendAdapter = new AddFriendAdapter(getApplicationContext(), parents, R.layout.contact_item_add_friend);
                    lvSearchResult.setAdapter(addFriendAdapter);
//                    addFriendAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: " + "跳转到添加朋友页面");
        setContentView(R.layout.activity_add_friends);
        sv = findViewById(R.id.sv);
        lvSearchResult = findViewById(R.id.lv_search_result);
        parents = new ArrayList<>();
        addFriendAdapter = new AddFriendAdapter(getApplicationContext(), parents, R.layout.contact_item_add_friend);
        lvSearchResult.setAdapter(addFriendAdapter);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ParentUtil.searchParentsByPhone(query, handler);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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
    }
}
