package com.example.projecttraining.contact;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.projecttraining.R;
import com.example.projecttraining.util.ParentUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.tiantiansqlite.TianTianSQLiteOpenHelper;
import com.hyphenate.easeui.utils.EaseParentUtil;
import com.hyphenate.easeui.widget.EaseConversationList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ConversationFragment extends Fragment {

    private static final String TAG="ConversationFragment";
    //设置搜索框的相关控件
    protected ImageButton clearSearch;
    protected EditText query;
    protected InputMethodManager inputMethodManager;
    EaseConversationList easeConversationList;

    @Override
    public void onResume() {
        super.onResume();
        easeConversationList.refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");
        View view=inflater.inflate(R.layout.fragment_conversation, container, false);
        easeConversationList=view.findViewById(R.id.ease_conversation_list);
        List<EMConversation> conversations = new ArrayList<EMConversation>();
        conversations.addAll(loadConversationList());
        easeConversationList.init(conversations);
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
                easeConversationList.filter(s);
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


        //设置联系人列表item的点击事件，跳转到聊天页面
        easeConversationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //根据conversationId从SQLite中查询昵称和头像
                Parent chatToParent=ParentUtil.queryAvatarAndNicknameByPhone(getContext(),conversations.get(position).conversationId());
                EaseParentUtil.toChatUserNickname=chatToParent.getNickname();
                EaseParentUtil.toChatUserAvator=chatToParent.getAvator();
                startActivity(new Intent(getContext(),ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID,conversations.get(position).conversationId()));
            }
        });
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        return view;
    }
    /**
     * load conversation list
     * @return
    +    */
    protected List<EMConversation> loadConversationList(){
        // get all conversations
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        List<Pair<Long, EMConversation>> sortList = new ArrayList<Pair<Long, EMConversation>>();
        /**
         * lastMsgTime will change if there is new message during sorting
         * so use synchronized to make sure timestamp of last message won't change.
         */
        synchronized (conversations) {
            for (EMConversation conversation : conversations.values()) {
                if (conversation.getAllMessages().size() != 0) {
                    sortList.add(new Pair<Long, EMConversation>(conversation.getLastMessage().getMsgTime(), conversation));
                }
            }
        }
        try {
            // Internal is TimSort algorithm, has bug
            sortConversationByLastChatTime(sortList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<EMConversation> list = new ArrayList<EMConversation>();
        for (Pair<Long, EMConversation> sortItem : sortList) {
            list.add(sortItem.second);
        }
        return list;
    }
    /**
     * sort conversations according time stamp of last message
     *
     * @param conversationList
     */
    private void sortConversationByLastChatTime(List<Pair<Long, EMConversation>> conversationList) {
        Collections.sort(conversationList, new Comparator<Pair<Long, EMConversation>>() {
            @Override
            public int compare(final Pair<Long, EMConversation> con1, final Pair<Long, EMConversation> con2) {

                if (con1.first.equals(con2.first)) {
                    return 0;
                } else if (con2.first.longValue() > con1.first.longValue()) {
                    return 1;
                } else {
                    return -1;
                }
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
}