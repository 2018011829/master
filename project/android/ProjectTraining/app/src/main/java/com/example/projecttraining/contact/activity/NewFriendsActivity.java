package com.example.projecttraining.contact.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.ChangeStatusBarColor;
import com.example.projecttraining.R;
import com.example.projecttraining.contact.adapter.NewFriendAdapter;
import com.example.projecttraining.contact.dao.ContactsStatus;
import com.example.projecttraining.util.ConfigUtil;
import com.example.projecttraining.util.ParentUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseParentUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NewFriendsActivity extends AppCompatActivity {
    private static String TAG = "NewFriendsActivity";

    private ImageView ivReturn;
    private TextView tvAddFriedns;

    private ListView lvInviteMe;
    private ListView lvIInvite;
    private ListView lvHistory;

    private List<ContactsStatus> contactsStatuses;
    private List<ContactsStatus> lvInviteMeList;
    private List<ContactsStatus> lvIInviteList;
    private List<ContactsStatus> lvHistoryList;

    private NewFriendAdapter lvInviteMeAdapter;
    private NewFriendAdapter lvIInviteAdapter;
    private NewFriendAdapter lvHistoryAdapter;

    private TextView tv1;//"我发出的邀请TextView，如果我没有发出邀请，不需要显示"
    private TextView tv2;//"我收到的邀请"TextView，如果没有收到邀请，不需要显示
    private TextView tv3;//"邀请历史"TextView，如果没有收到邀请，不需要显示

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);

                    //各项list数据清空
                    lvInviteMeList.clear();
                    lvIInviteList.clear();
                    lvHistoryList.clear();
                    contactsStatuses.clear();
                    contactsStatuses.addAll((List<ContactsStatus>) msg.obj);

                    //在各个list中添加数据
                    for (ContactsStatus contactsStatus : contactsStatuses) {
                        if (null == contactsStatus.getFrom() && 0 == contactsStatus.getStatus()) {
                            lvIInviteList.add(contactsStatus);
                            tv1.setVisibility(View.VISIBLE);
                            Log.e(TAG, "handleMessage: 将" + contactsStatus.getTo().getNickname() + "加入已发送list");
                        } else if (null == contactsStatus.getTo() && 0 == contactsStatus.getStatus()) {
                            lvInviteMeList.add(contactsStatus);
                            tv2.setVisibility(View.VISIBLE);
                        } else {
                            lvHistoryList.add(contactsStatus);
                            tv3.setVisibility(View.VISIBLE);
                        }
                    }
                    //使用adapter通知数据改变
                    lvInviteMeAdapter.notifyDataSetChanged();
                    lvIInviteAdapter.notifyDataSetChanged();
                    lvHistoryAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    //同意邀请或拒绝邀请，需要刷新页面
                    getContactsStatus(EMClient.getInstance().getCurrentUser());
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (ParentUtil.isSendInvitaion) {
            //如果是从添加好友的页面返回，并且发送了邀请
            //从服务端得到邀请我的，和我邀请的人的数据
            Log.e(TAG, "onResume: 发送了邀请更新数据");
            getContactsStatus(EMClient.getInstance().getCurrentUser());
            ParentUtil.isSendInvitaion = false;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friends);
        ChangeStatusBarColor.initSystemBar(this);

        findViews();

        //初始化各个ListView
        contactsStatuses=new ArrayList<>();
        lvInviteMeList = new ArrayList<>();
        lvIInviteList = new ArrayList<>();
        lvHistoryList = new ArrayList<>();

        //为各个ListView设置adapter
        lvInviteMeAdapter = new NewFriendAdapter(getApplicationContext(), lvInviteMeList, R.layout.contact_item_new_friend,handler);
        lvInviteMe.setAdapter(lvInviteMeAdapter);
        lvIInviteAdapter = new NewFriendAdapter(getApplicationContext(), lvIInviteList, R.layout.contact_item_new_friend,handler);
        lvIInvite.setAdapter(lvIInviteAdapter);
        lvHistoryAdapter = new NewFriendAdapter(getApplicationContext(), lvHistoryList, R.layout.contact_item_new_friend,handler);
        lvHistory.setAdapter(lvHistoryAdapter);

        //设置返回按钮和添加朋友按钮的监听器
        ivReturn.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvAddFriedns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewFriendsActivity.this, AddFriendsActivity.class));
            }
        });

        //从服务端得到邀请我的，和我邀请的人的数据
        getContactsStatus(EMClient.getInstance().getCurrentUser());

        //添加联系人监听器，监听联系人
        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {
            @Override
            public void onContactAdded(String s) {
                //增加联系人后调用，存储当前的联系人,发消息后刷新消息页面需要使用SQlite
                EaseParentUtil.isContactAddedOrDeleted = true;
                EMClient.getInstance().contactManager().aysncGetAllContactsFromServer(new EMValueCallBack<List<String>>() {
                    @Override
                    public void onSuccess(List<String> strings) {
                        ParentUtil.storeAllContacts(EMClient.getInstance().getCurrentUser(),strings,getApplicationContext(), null);
                    }
                    @Override
                    public void onError(int i, String s) {

                    }
                });
            }

            @Override
            public void onContactDeleted(String s) {
                //被好友删除时调用
            }

            @Override
            public void onContactInvited(String s, String s1) {
                //收到好友邀请回调
                //刷新页面
                getContactsStatus(EMClient.getInstance().getCurrentUser());
            }

            @Override
            public void onFriendRequestAccepted(String s) {
                //好友请求被同意后调用
                //刷新页面
                getContactsStatus(EMClient.getInstance().getCurrentUser());

            }

            @Override
            public void onFriendRequestDeclined(String s) {
                //拒绝好友请求后调用
                //刷新页面
                getContactsStatus(EMClient.getInstance().getCurrentUser());
            }
        });
    }

    private void findViews() {
        ivReturn = findViewById(R.id.iv_return);
        tvAddFriedns = findViewById(R.id.tv_add_friends);
        lvIInvite = findViewById(R.id.lv_i_invite);
        lvInviteMe = findViewById(R.id.lv_invite_me);
        lvHistory = findViewById(R.id.lv_histroy);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
    }


    //从服务端获取当前登录人的新增联系人状态信息
    private void getContactsStatus(String username) {
        new Thread(){
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("username", username).build();
                Request request = new Request.Builder()
                        .post(formBody)
                        .url(ConfigUtil.SERVICE_ADDRESS + "getContactsStatusServlet").build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(getApplicationContext(), "当前网络不稳定", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String json = response.body().string();
                        Type type = new TypeToken<List<ContactsStatus>>() {
                        }.getType();
                        List<ContactsStatus> contactsStatuses = new Gson().fromJson(json, type);
                        Log.e(TAG, "onResponse: 收到我的联系人状态信息" + contactsStatuses);
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        message.obj = contactsStatuses;
                        handler.sendMessage(message);
                    }
                });
            }
        }.start();

    }

}

