package com.example.projecttraining.contact.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.projecttraining.R;
import com.example.projecttraining.contact.dao.Parent;
import com.example.projecttraining.contact.adapter.AddFriendAdapter;
import com.example.projecttraining.util.ParentUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

public class AddFriendsActivity extends AppCompatActivity {
    private static final String TAG = "AddFriendsActivity";
    private SearchView sv;
    private ListView lvSearchResult;
    private List<Parent> parents;
    private AddFriendAdapter addFriendAdapter;
    protected ImageButton clearSearch;
    protected EditText query;
    protected InputMethodManager inputMethodManager;
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
        setContentView(R.layout.activity_add_friends);
        lvSearchResult = findViewById(R.id.lv_search_result);
        parents = new ArrayList<>();
        addFriendAdapter = new AddFriendAdapter(getApplicationContext(), parents, R.layout.contact_item_add_friend);
        lvSearchResult.setAdapter(addFriendAdapter);
        //从服务端获取全部好友,如果当前搜索到的用户已经是我的好友，则显示“已在通讯录”
        new Thread(){
            @Override
            public void run() {
                try {
                    ParentUtil.allContacts=EMClient.getInstance().contactManager().getAllContactsFromServer();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        //获取搜索框相关控件
        query = (EditText)findViewById(R.id.query);
        clearSearch = (ImageButton)findViewById(R.id.search_clear);
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //注册搜索框搜索事件
        query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    clearSearch.setVisibility(View.VISIBLE);
                } else {
                    clearSearch.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                ParentUtil.searchParentsByPhone(s.toString(),handler);
            }
        });
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.getText().clear();
                hideSoftKeyboard();
            }
        });
    }
    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)

                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
