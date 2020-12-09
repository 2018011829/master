package com.example.projecttraining.home.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projecttraining.R;
import com.example.projecttraining.mine.AddChildActivity;
import com.example.projecttraining.mine.EditorParentActivity;
import com.example.projecttraining.util.ConfigUtil;
import com.example.projecttraining.util.ParentUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseParentUtil;


public class MyFragment extends Fragment {

    public static String phoneNum=EMClient.getInstance().getCurrentUser(); //纪录当前登录的手机号，用来在收藏前进行判断
    public static String childName="小明"; //纪录当前登录的手机号下的孩子姓名，用来在收藏前进行判断、存储收藏信息
    private ImageView iv_headPhoto;
    private RelativeLayout rl_mine_addChild;
    private LinearLayout ll_mine_editorParent;
    private TextView tv_mine_useName;
    private TextView tv_mine_phone;
    private View view;
    Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==1){
                Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();
                init();
            }
        }
    };



    @Override
    public void onResume() {
        super.onResume();
        ParentUtil.storeCurrentParent(EMClient.getInstance().getCurrentUser(),handler);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my,container,false);


        //TODO 获取控件引用
        findViews();
        //TODO 设置控件内容
        init();
        //TODO 给控件添加监听器
        setClickListener();

        return view;
    }

    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.rl_mine_addChild:
                    Intent addChild = new Intent();
                    addChild.setClass(getContext(), AddChildActivity.class);
                    startActivity(addChild);
                    break;
                case R.id.ll_mine_editorParent:
                    Intent editor = new Intent();
                    editor.setClass(getContext(), EditorParentActivity.class);
                    startActivity(editor);
                    break;
            }
        }
    }

    private void setClickListener() {
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        rl_mine_addChild.setOnClickListener(myOnClickListener);
        ll_mine_editorParent.setOnClickListener(myOnClickListener);
    }
    private void init(){
        tv_mine_phone.setText(EMClient.getInstance().getCurrentUser());
        tv_mine_useName.setText(EaseParentUtil.currentUserNickname);
        //12-07得到一个设置圆角的requestOptions
        RequestOptions requestOptions=EaseParentUtil.getRoundImageTransform(getContext());
        Glide.with(getContext())
                .load(EaseParentUtil.currentUserAvatar)
                .apply(requestOptions)
                .into(iv_headPhoto);

    }

    private void findViews() {
        iv_headPhoto = view.findViewById(R.id.iv_headPhoto);
        rl_mine_addChild  = view.findViewById(R.id.rl_mine_addChild);
        ll_mine_editorParent = view.findViewById(R.id.ll_mine_editorParent);
        tv_mine_useName = view.findViewById(R.id.tv_mine_userName);
        tv_mine_phone = view.findViewById(R.id.tv_mine_phone);
    }
}
