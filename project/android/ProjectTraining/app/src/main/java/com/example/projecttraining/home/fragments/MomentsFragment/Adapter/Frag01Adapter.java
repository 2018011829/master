package com.example.projecttraining.home.fragments.MomentsFragment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.Moments;
import com.example.projecttraining.home.fragments.MomentsFragment.DynamicDetails;

import java.util.ArrayList;
import java.util.List;

import static com.mob.MobSDK.getContext;

public class Frag01Adapter extends BaseAdapter {
    private Context mContext;//环境上下文
    private List<Moments> moments = new ArrayList<>();//动态列表
    private int itemLayoutRes;//
    private ImageView ivComment;//评论按钮
    private LinearLayout llDynamicDetails;//点击查看详情


    public Frag01Adapter(Context mContext, List<Moments> moments, int itemLayoutRes) {
        this.mContext = mContext;
        this.moments = moments;
        this.itemLayoutRes = itemLayoutRes;
    }

    @Override
    public int getCount() {
        if(null != moments){
            return moments.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (null != moments){
            return moments.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(null == view) {
            //加载item的布局文件
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(itemLayoutRes, null);
        }
        ImageView ivHeadPortrait = view.findViewById(R.id.iv_headPortrait);//加载头像id
        TextView tvName = view.findViewById(R.id.tv_name);//加载昵称id
        TextView tvContent = view.findViewById(R.id.tv_content);//加载评论id
        List<String> pictureUrl = new ArrayList<>();//创建动态图片的地址列表
        llDynamicDetails = view.findViewById(R.id.ll_dynamic_details);//查看详情id
        ImageView ivComment = view.findViewById(R.id.iv_comment);

        tvName.setText(moments.get(i).getName());//设置昵称的值
        tvContent.setText(moments.get(i).getContent());//设置评论的值

        //设置圆形头像
        Glide.with(mContext)
                .load(R.drawable.photo)
                .circleCrop()
                .into(ivHeadPortrait);
        //点击文本和图片部分可以查看详情
        llDynamicDetails.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DynamicDetails.class);
                mContext.startActivity(intent);
            }
        });
        //点击评论跳转到详情
        ivComment.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DynamicDetails.class);
                mContext.startActivity(intent);
            }
        });
        return view;
    }



}
