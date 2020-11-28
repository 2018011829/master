package com.example.projecttraining.contact;

import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.projecttraining.R;
import com.example.projecttraining.util.ConfigUtil;
import com.example.projecttraining.util.ParentUtil;
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

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactFragment extends Fragment {
    private TianTianSQLiteOpenHelper tianTianSQLiteOpenHelper;
    List<EaseUser> easeUsers=new ArrayList<>();
    EaseContactList easeContactList;
    protected InputMethodManager inputMethodManager;
    //设置搜索框的相关控件
    protected ImageButton clearSearch;
    protected EditText query;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 2:
                    //从服务端得到了Parent对象
                    Parent parent=(Parent)msg.obj;
                    Log.i(TAG, "handleMessage: 得到昵称和头像 "+parent.getPhone());
                    for(EaseUser easeUser:easeUsers){
                        if(easeUser.getUsername().equals(parent.getPhone())){
                            //设置昵称和头像
                            if(parent.getPhone().equals(EMClient.getInstance().getCurrentUser())){
                                easeUser.setNickname(parent.getNickname()+"(我)");
                            }else{

                            easeUser.setNickname(parent.getNickname());
                            }
                            Log.e(TAG, "handleMessage: 昵称"+parent.getNickname());
                            EaseCommonUtils.setUserInitialLetter(easeUser);
                            easeUser.setAvatar(ConfigUtil.SETVER_AVATAR+parent.getAvator());
                            easeContactList.refresh();
                        }
                    }
                    Collections.sort(easeUsers, new Comparator<EaseUser>() {

                        @Override
                        public int compare(EaseUser o1, EaseUser o2) {
                            Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
                            return com.compare(o1.getNickname(), o2.getNickname());
                        }
                    });
                    easeContactList.refresh();
                    break;
            }
        }
    };
    private static final String TAG="ContactFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_contact, container, false);
        easeContactList=view.findViewById(R.id.ease_contact_list);
        tianTianSQLiteOpenHelper=new TianTianSQLiteOpenHelper(getContext(),"tiantian.db",null,1);
        //设置联系人列表item的点击事件，跳转到聊天页面
        easeContactList.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EaseParentUtil.toChatUserAvator=easeUsers.get(position).getAvatar();
                EaseParentUtil.toChatUserNickname=easeUsers.get(position).getNickname();
                EaseParentUtil.currentUserAvatar=easeUsers.get(0).getAvatar();
                startActivity(new Intent(getContext(),ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID,easeUsers.get(position).getUsername()));
            }
        });
        //加载所有联系人列表
        new Thread(){
            @Override
            public void run() {
                try {
                    //1.从环信服务器得到username集合
                    List<String> usernames= EMClient.getInstance().contactManager().getAllContactsFromServer();
                    usernames.add(0,EMClient.getInstance().getCurrentUser());
                    //2.得到EaseUser集合
                    easeUsers.clear();
                    for(int i=0;i<usernames.size();i++){
                        EaseUser easeUser=new EaseUser(usernames.get(i));
                        //3.每次创建EaseUser，启动新线程向本地服务器获取昵称和头像
                        ParentUtil.getOneParent(usernames.get(i),handler,tianTianSQLiteOpenHelper);
                        easeUsers.add(easeUser);
                    }
                    //4.在Ui线程初始化EaseContactList
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            easeContactList.init(easeUsers);
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
                                    easeContactList.filter(s);
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
                    });

                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        return view;
    }
    protected void hideSoftKeyboard() {
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)

                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
