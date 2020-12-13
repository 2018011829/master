package com.example.projecttraining.contact.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projecttraining.R;
import com.example.projecttraining.contact.ContactManager;
import com.example.projecttraining.contact.dao.ContactsStatus;
import com.example.projecttraining.util.ConfigUtil;
import com.example.projecttraining.util.ParentUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.GlideRoundImage;
import com.hyphenate.easeui.utils.EaseParentUtil;
import com.hyphenate.exceptions.HyphenateException;

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
    private List<ContactsStatus> contactsStatuses=new ArrayList<>();
    private List<ContactsStatus> lvInviteMeList = new ArrayList<>();
    private List<ContactsStatus> lvIInviteList = new ArrayList<>();
    private List<ContactsStatus> lvHistoryList = new ArrayList<>();
    private NewFriendAdapter lvInviteMeAdapter;
    private NewFriendAdapter lvIInviteAdapter;
    private NewFriendAdapter lvHistoryAdapter;
    private TextView tv1;//"我发出的邀请TextView，如果我没有发出邀请，不需要显示"
    private TextView tv2;
    private TextView tv3;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);
                    tv3.setVisibility(View.GONE);
                    lvInviteMeList.clear();
                    lvIInviteList.clear();
                    lvHistoryList.clear();
                    contactsStatuses = (List<ContactsStatus>) msg.obj;
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
                    lvInviteMeAdapter = new NewFriendAdapter(getApplicationContext(), lvInviteMeList, R.layout.contact_item_new_friend);
                    lvInviteMe.setAdapter(lvInviteMeAdapter);
                    lvIInviteAdapter = new NewFriendAdapter(getApplicationContext(), lvIInviteList, R.layout.contact_item_new_friend);
                    lvIInvite.setAdapter(lvIInviteAdapter);
                    lvHistoryAdapter = new NewFriendAdapter(getApplicationContext(), lvHistoryList, R.layout.contact_item_new_friend);
                    lvHistory.setAdapter(lvHistoryAdapter);
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
        ivReturn = findViewById(R.id.iv_return);
        tvAddFriedns = findViewById(R.id.tv_add_friends);
        lvIInvite = findViewById(R.id.lv_i_invite);
        lvInviteMe = findViewById(R.id.lv_invite_me);
        lvHistory = findViewById(R.id.lv_histroy);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
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
                //增加联系人后调用，存储当前的联系人
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


    private void getContactsStatus(String username) {
        Log.e(TAG, "getContactsStatus: 获取我的联系人状态信息");
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

    //NewFriendAdapter
    private class NewFriendAdapter extends BaseAdapter {
        private final String TAG = "NewFriendAdapter";
        private Context context;
        private List<ContactsStatus> contactsStatuses;
        private int itemLayout;
        private int flag = 0;

        public NewFriendAdapter(Context context, List<ContactsStatus> contactsStatuses, int itemLayout) {
            this.context = context;
            this.contactsStatuses = contactsStatuses;
            this.itemLayout = itemLayout;

        }

        @Override
        public int getCount() {
            if (null != contactsStatuses) {
                return contactsStatuses.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (null != contactsStatuses) {
                return contactsStatuses.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (null == convertView) {
                convertView = LayoutInflater.from(context).inflate(itemLayout, null);
                holder = new ViewHolder();
                holder.avatar = convertView.findViewById(R.id.avatar);
                holder.nickname = convertView.findViewById(R.id.nickname);
                holder.agree = convertView.findViewById(R.id.agree);
                holder.reject = convertView.findViewById(R.id.reject);
                holder.status = convertView.findViewById(R.id.status);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //设置控件内容,如果from==null,说明是我发出的邀请
            ContactsStatus contactsStatus = contactsStatuses.get(position);
            //我发出的，尚未被同意的邀请
            if (contactsStatus.getFrom() == null) {
                RequestOptions requestOptions=new RequestOptions().transform(new GlideRoundImage(context,8));
                Glide.with(context)
                        .load(ConfigUtil.SETVER_AVATAR + contactsStatus.getTo().getAvator())
                        .apply(requestOptions)
                        .into(holder.avatar);
                holder.nickname.setText(contactsStatus.getTo().getNickname());
                holder.agree.setVisibility(View.GONE);
                holder.reject.setVisibility(View.GONE);
                holder.status.setVisibility(View.VISIBLE);
                if (contactsStatus.getStatus() == 0) {
                    holder.status.setText("对方处理中...");
                } else if (contactsStatus.getStatus() == 1) {
                    holder.status.setText("对方已同意");
                } else if (contactsStatus.getStatus() == 2) {
                    holder.status.setText("对方已拒绝");
                }
            } else {
                RequestOptions requestOptions=new RequestOptions().transform(new GlideRoundImage(context,8));
                Glide.with(context)
                        .load(ConfigUtil.SETVER_AVATAR + contactsStatus.getFrom().getAvator())
                        .apply(requestOptions)
                        .into(holder.avatar);
                holder.nickname.setText(contactsStatus.getFrom().getNickname());
                if (contactsStatus.getStatus() == 0) {
                    holder.agree.setVisibility(View.VISIBLE);
                    holder.reject.setVisibility(View.VISIBLE);
                    holder.status.setVisibility(View.GONE);

                    holder.agree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //同意好友请求,环信同意请求为同步方法，会阻塞当前线程，所以新建线程
                            new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        EMClient.getInstance().contactManager().acceptInvitation(contactsStatus.getFrom().getPhone());
                                    } catch (HyphenateException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                            EaseParentUtil.isContactAddedOrDeleted = true;
                            //修改本地数据库
                            agreeInvitation(contactsStatus.getId());
                            //添加备注
                            addRemark(EMClient.getInstance().getCurrentUser(),contactsStatus.getFrom().getPhone(),EaseParentUtil.currentUserNickname,contactsStatus.getFrom().getNickname());

                        }
                    });
                    holder.reject.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new Thread(){
                                @Override
                                public void run() {
                                    //拒绝好友请求
                                    try {
                                        EMClient.getInstance().contactManager().declineInvitation(contactsStatus.getFrom().getPhone());
                                    } catch (HyphenateException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                            Log.e(TAG, "onClick:拒绝请求" + contactsStatus.getFrom().getPhone());
                            //修改本地数据库
                            rejectInvitation(contactsStatus.getId());

                        }
                    });
                } else if (contactsStatus.getStatus() == 1) {
                    holder.agree.setVisibility(View.INVISIBLE);
                    holder.reject.setVisibility(View.INVISIBLE);
                    holder.status.setVisibility(View.VISIBLE);
                    holder.status.setText("您已同意");
                } else if (contactsStatus.getStatus() == 2) {
                    holder.agree.setVisibility(View.INVISIBLE);
                    holder.reject.setVisibility(View.INVISIBLE);
                    holder.status.setVisibility(View.VISIBLE);
                    holder.status.setText("您已拒绝");
                }
            }
            return convertView;
        }

    }

    private void addRemark(String currentUser, String phone, String currentUserNickname, String nickname) {
        new Thread(){
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder().add("fromPhone",currentUser)
                        .add("toPhone",phone)
                        .add("fromPhoneNickname",currentUserNickname)
                        .add("toPhoneNickname",nickname).build();
                Request request=new Request.Builder().post(formBody).url(ConfigUtil.SERVICE_ADDRESS+"addRemarkServlet").build();
                Call call=okHttpClient.newCall(request);
                try {
                    Response response=call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void rejectInvitation(int id) {
        new Thread(){
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("id", id + "").build();
                Request request = new Request.Builder().post(formBody).url(ConfigUtil.SERVICE_ADDRESS + "RejectInvitationServlet").build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Looper.prepare();
                        Toast.makeText(NewFriendsActivity.this, "拒绝失败，请重试", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.body().string().equals("成功")) {
                            Message message = handler.obtainMessage();
                            message.what = 2;
                            handler.sendMessage(message);
                            Looper.prepare();
                            Toast.makeText(NewFriendsActivity.this, "拒绝成功", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(NewFriendsActivity.this, "拒绝失败", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                    }
                });
            }
        }.start();

    }

    static class ViewHolder {
        ImageView avatar;
        TextView nickname;
        Button agree;
        Button reject;
        TextView status;
    }

    private void agreeInvitation(int id) {
        new Thread(){
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("id", id + "").build();
                Request request = new Request.Builder().post(formBody).url(ConfigUtil.SERVICE_ADDRESS + "AgreeInvitationServlet").build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Looper.prepare();
                        Toast.makeText(NewFriendsActivity.this, "添加失败，请重试", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.body().string().equals("成功")) {
                            Message message = handler.obtainMessage();
                            message.what = 2;
                            handler.sendMessage(message);
                            Looper.prepare();
                            Toast.makeText(NewFriendsActivity.this, "你们已经成为好友了！", Toast.LENGTH_SHORT).show();
                            Looper.loop();

                        } else {
                            Looper.prepare();
                            Toast.makeText(NewFriendsActivity.this, "添加失败，请重试", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                    }
                });
            }
        }.start();

    }

}

