package com.example.projecttraining.contact;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.projecttraining.R;
import com.example.projecttraining.util.ConfigUtil;
import com.example.projecttraining.util.ParentUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseParentUtil;
import com.hyphenate.easeui.widget.EaseContactList;
import com.hyphenate.exceptions.HyphenateException;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactFragment extends Fragment {
    List<EaseUser> easeUsers=new ArrayList<>();
    EaseContactList easeContactList;
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
                            easeUser.setNickname(parent.getNickname());
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
        easeContactList.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EaseParentUtil.toChatUserAvator=easeUsers.get(position).getAvatar();
                EaseParentUtil.currentUserAvatar=easeUsers.get(0).getAvatar();
                EaseParentUtil.toChatUserNickname=easeUsers.get(position).getNickname();
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
                    for(int i=1;i<usernames.size();i++){
                        EaseUser easeUser=new EaseUser(usernames.get(i));
                        //3.每次创建EaseUser，启动新线程向本地服务器获取昵称和头像
                        ParentUtil.getOneParent(usernames.get(i),handler);
                        easeUsers.add(easeUser);
                    }
                    //4.在Ui线程初始化EaseContactList
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            easeContactList.init(easeUsers);
                        }
                    });

                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return view;
    }
}
