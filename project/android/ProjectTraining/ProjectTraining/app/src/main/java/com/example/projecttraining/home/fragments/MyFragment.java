package com.example.projecttraining.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.mine.AddChildActivity;

public class MyFragment extends Fragment {
    private ImageView iv_headPhoto;
    private LinearLayout ll_mine_addChild;
    private View view;
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
                case R.id.ll_mine_addChild:
                    Intent intent = new Intent();
                    intent.setClass(getContext(), AddChildActivity.class);
                    startActivity(intent);
            }
        }
    }

    private void setClickListener() {
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        ll_mine_addChild.setOnClickListener(myOnClickListener);
    }
    private void init() {
        Glide.with(this)
                .load(R.mipmap.mine_head_photo)
                .circleCrop()
                .into(iv_headPhoto);
    }

    private void findViews() {
        iv_headPhoto = view.findViewById(R.id.iv_headPhoto);
        ll_mine_addChild  = view.findViewById(R.id.ll_mine_addChild);
    }
}
