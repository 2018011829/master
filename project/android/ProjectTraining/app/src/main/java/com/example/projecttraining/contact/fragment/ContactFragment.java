package com.example.projecttraining.contact.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.contact.activity.ChatActivity;
import com.hyphenate.easeui.ContactInfoActivity;
import com.hyphenate.easeui.EditRemarkActivity;
import com.example.projecttraining.contact.activity.NewFriendsActivity;
import com.example.projecttraining.contact.dao.ParentInfo;
import com.example.projecttraining.util.ConfigUtil;
import com.example.projecttraining.util.ParentUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.GlideRoundImage;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseParentUtil;
import com.hyphenate.easeui.widget.EaseContactList;
import com.hyphenate.exceptions.HyphenateException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ContactFragment extends Fragment {
    private static final String TAG = "ContactFragment";
    private RelativeLayout newFriends;
    private ImageView iv;
    List<EaseUser> easeUsers;
    List<EaseUser> copyEaseUsers = new ArrayList<>();
    EaseContactList easeContactList;
    protected InputMethodManager inputMethodManager;
    //设置搜索框的相关控件
    protected ImageButton clearSearch;
    protected EditText query;
    //当网络不稳定时显示加载图片
    private ImageView waitingForInternet;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //从服务器得到了所有联系人,初始化联系人界面
            if (msg.what == 1) {
                waitingForInternet.setVisibility(View.GONE);
                easeUsers.clear();
                List<ParentInfo> parentInfos = (List<ParentInfo>) msg.obj;
                for (ParentInfo parentInfo : parentInfos) {
                    EaseUser easeUser = new EaseUser(parentInfo.getPhone());
                    easeUser.setAvatar(ConfigUtil.SETVER_AVATAR + parentInfo.getAvatar());
                    easeUser.setNickname(parentInfo.getRemark());
                    easeUsers.add(easeUser);
                }
                //刷新控件
                easeContactList.refresh();
                //搜索时，需要清空Easeusers,使用copyEaseUser保存所有数据
                copyEaseUsers.clear();
                copyEaseUsers.addAll(easeUsers);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        initViews(view);

        setContactListClickListener();

        newFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NewFriendsActivity.class));
            }
        });
        //为搜索框设置点击搜索事件监听器
        setOnSearchListener(view);
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);


        //设置联系人状态改变监听器
        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {
            @Override
            public void onContactAdded(String s) {

            }

            @Override
            public void onContactDeleted(String s) {

            }

            @Override
            public void onContactInvited(String s, String s1) {

            }

            @Override
            public void onFriendRequestAccepted(String s) {
                //好友请求被同意，刷新联系人列表
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                            ParentUtil.getAllContacts(usernames, handler, getContext());
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }

            @Override
            public void onFriendRequestDeclined(String s) {
                //好友请求被拒绝
            }
        });
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initViews(View view) {
        easeContactList = view.findViewById(R.id.ease_contact_list);
        newFriends = view.findViewById(R.id.new_friends);
        iv = view.findViewById(R.id.iv);
        Glide.with(this)
                .load(R.drawable.newfriend)
                .transform(new GlideRoundImage(getContext(), 8))
                .into(iv);
        waitingForInternet = view.findViewById(R.id.iv_waiting_for_internet);
        Glide.with(getContext())
                .asGif()
                .load(R.drawable.waiting_for_internet)
                .into(waitingForInternet);
        easeUsers = new ArrayList<EaseUser>();
        easeContactList.init(easeUsers);
        new Thread() {
            @Override
            public void run() {
                //初始化联系人列表
                try {
                    List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    ParentUtil.getAllContacts(usernames, handler, getContext());
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void setContactListClickListener() {
        //设置联系人列表item的点击事件，跳转到聊天页面
        easeContactList.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //根据conversationId从SQLite中查询,并设置当前聊天对象的昵称，备注和头像
                ParentUtil.setToChatParentInfo(getContext(), easeUsers.get(position).getUsername());
                startActivity(new Intent(getContext(), ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, easeUsers.get(position).getUsername()));
            }
        });


        //设置联系人列表item的点击事件，跳转到聊天页面
        easeContactList.getListView().setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                Log.e(TAG, "onCreateContextMenu: ");
                menu.add(0, 0, 0, "修改备注");
                menu.add(0, 1, 0, "删除该联系人");
                menu.add(0, 2, 0, "查看个人信息");
            }
        });
    }


    private void setOnSearchListener(View view) {
        Log.e(TAG, "setOnSearchListener: ");
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
                for (EaseUser easeUser : copyEaseUsers) {
                    if (!easeUser.getNickname().contains(s)) {
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


    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " + EaseParentUtil.isContactAddedOrDeleted);
        //如果添加了联系人，则需要重新获取所有的练习人列表
        if (EaseParentUtil.isContactAddedOrDeleted == true) {
            new Thread() {
                @Override
                public void run() {
                    //初始化联系人列表
                    try {
                        List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                        ParentUtil.getAllContacts(usernames, handler, getContext());
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            EaseParentUtil.isContactAddedOrDeleted = false;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: ");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = menuInfo.position;
        switch (item.getItemId()) {
            case 0:
                Intent intent = new Intent(getContext(), EditRemarkActivity.class);
                intent.putExtra("username", easeUsers.get(position).getUsername());
                intent.putExtra("currentRemark", easeUsers.get(position).getNickname());
                startActivity(intent);
                break;
            case 1:
                showDeleteContactDialog(position);
                break;
            case 2:
                ParentUtil.setToChatParentInfo(getContext(), easeUsers.get(position).getUsername());
                startActivity(new Intent(getContext(), ContactInfoActivity.class).putExtra("username", easeUsers.get(position).getUsername()));
                break;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * 显示删除联系人的dialog
     *
     * @param position
     */
    private void showDeleteContactDialog(int position) {
        //方法链调用
        new AlertDialog.Builder(getContext()).setTitle("温馨提示")
                .setMessage("确定要删除这个联系人吗？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //执行删除联系人操作
                        EMClient.getInstance().contactManager().aysncDeleteContact(easeUsers.get(position).getUsername(), new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                //在本地数据库中删除彼此的备注信息
                                deleteRemark(EMClient.getInstance().getCurrentUser(), easeUsers.get(position).getUsername());
                                //更新列表
                                easeUsers.remove(position);
                                easeContactList.refresh();
                            }

                            @Override
                            public void onError(int i, String s) {
                                Looper.prepare();
                                Toast.makeText(getContext(), "删除联系人失败，请重试！", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });

                    }
                }).create().show();
    }

    private void deleteRemark(String currentUser, String username) {
        new Thread() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("fromPhone", currentUser)
                        .add("toPhone", username).build();
                Request request = new Request.Builder().url(ConfigUtil.SERVICE_ADDRESS + "DeleteRemarkServlet").post(formBody).build();
                Call call = okHttpClient.newCall(request);
                try {
                    call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
