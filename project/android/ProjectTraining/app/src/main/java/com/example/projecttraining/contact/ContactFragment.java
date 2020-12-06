package com.example.projecttraining.contact;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.projecttraining.R;
import com.example.projecttraining.util.ConfigUtil;
import com.example.projecttraining.util.ParentUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.tiantiansqlite.TianTianSQLiteOpenHelper;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseParentUtil;
import com.hyphenate.easeui.widget.EaseContactList;
import com.hyphenate.easeui.widget.EaseConversationList;
import com.hyphenate.exceptions.HyphenateException;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ContactFragment extends Fragment {
    private static final String TAG="ContactFragment";
    private RelativeLayout newFriends;
    List<EaseUser> easeUsers;
    List<EaseUser> copyEaseUsers=new ArrayList<>();
    EaseContactList easeContactList;
    protected InputMethodManager inputMethodManager;
    private boolean isInited=false;
    //设置搜索框的相关控件
    protected ImageButton clearSearch;
    protected EditText query;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //从服务器得到了所有联系人,初始化联系人界面
            if(msg.what==1){
                easeUsers.clear();
                List<Parent> parents= (List<Parent>) msg.obj;
                for(Parent parent:parents){
                    EaseUser easeUser=new EaseUser(parent.getPhone());
                    easeUser.setAvatar(ConfigUtil.SETVER_AVATAR+parent.getAvator());
                    easeUser.setNickname(parent.getNickname());
                    easeUsers.add(easeUser);
                }
                //如果是从onCreateView方法获取联系人
                if(!isInited){
                easeContactList.init(easeUsers);
                }else{
                    //如果是从其他activity恢复到MainActivity并且发生了联系人的增加，则刷新
                    easeContactList.refresh();
                }
                //搜索时，需要清空Easeusers,使用copyEaseUser保存所有数据
                copyEaseUsers.clear();
                copyEaseUsers.addAll(easeUsers);
            }
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        if(ParentUtil.isContactAddedOrDeleted) {
            new Thread(){
                @Override
                public void run() {
                    //初始化联系人列表
                    try {
                        List<String> usernames=EMClient.getInstance().contactManager().getAllContactsFromServer();
                        getAllContacts(usernames);
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            //如果添加了联系人，则需要重新获取所有的练习人列表
            ParentUtil.isContactAddedOrDeleted=false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_contact, container, false);
        easeContactList=view.findViewById(R.id.ease_contact_list);
        newFriends=view.findViewById(R.id.new_friends);
        easeUsers=new ArrayList<EaseUser>();
        new Thread(){
            @Override
            public void run() {
                //初始化联系人列表
                try {
                    List<String> usernames=EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Log.e(TAG, "onCreateView: "+usernames);
                    getAllContacts(usernames);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //设置联系人列表item的点击事件，跳转到聊天页面
        easeContactList.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EaseParentUtil.toChatUserAvator=easeUsers.get(position).getAvatar();
                EaseParentUtil.toChatUserNickname=easeUsers.get(position).getNickname();
                startActivity(new Intent(getContext(),ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID,easeUsers.get(position).getUsername()));
            }
        });
        newFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),NewFriendsActivity.class));
            }
        });
        //为搜索框设置点击搜索事件监听器
        setOnSearchListener(view);
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        return view;
    }

    private void setOnSearchListener(View view) {
        //获取搜索框相关控件
        query = (EditText) view.findViewById(R.id.query);
        clearSearch = (ImageButton) view.findViewById(R.id.search_clear);
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.getText().clear();
                hideSoftKeyboard();
            }
        });
        //设置搜索框的搜索事件
        query.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                easeUsers.clear();
                easeUsers.addAll(copyEaseUsers);
                for(EaseUser easeUser:copyEaseUsers){
                    if(!easeUser.getNickname().contains(s)){
                        easeUsers.remove(easeUser);
                    }
                    easeContactList.refresh();
                }
                if (s.length() > 0) {
                    clearSearch.setVisibility(View.VISIBLE);
                } else {
                    clearSearch.setVisibility(View.INVISIBLE);

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
    }

    protected void hideSoftKeyboard() {
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)

                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private  void getAllContacts(List<String> usernames) {
        Gson gson = new Gson();
        String json = gson.toJson(usernames);
        Log.e(TAG, "storeAllContacts: "+json );
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder().add("usernames", json).build();
        Request request = new Request.Builder().url(ConfigUtil.SERVICE_ADDRESS + "GetAllContactsServlet")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure: 获取好友信息失败" );
                Looper.prepare();
                Toast.makeText(getContext(), "当前网络不稳定", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                //得到集合的类型
                if ("" != json && null != json) {
                    Type type = new TypeToken<List<Parent>>() {}.getType();
                    Log.e(TAG, "onResponse: "+json);
                    List<Parent> parents = gson.fromJson(json, type);
                    Message message=handler.obtainMessage();
                    message.what=1;
                    message.obj=parents;
                    handler.sendMessage(message);
                }
            }
        });
    }
}
