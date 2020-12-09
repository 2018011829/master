package com.example.projecttraining.home.fragments.MomentsFragment.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.Moments;
import com.example.projecttraining.home.fragments.MomentsFragment.DynamicDetails;
import com.example.projecttraining.home.fragments.MomentsFragment.PictureGridView.PictureGridView;
import com.example.projecttraining.util.ConfigUtil;
import com.hyphenate.chat.EMClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.mob.MobSDK.getContext;

public class Frag01Adapter extends BaseAdapter {
    private Context mContext;//环境上下文
    private List<Moments> moments = new ArrayList<>();//动态列表
    private int itemLayoutRes;//
    private LinearLayout llDynamicDetails;//点击查看详情
    private OkHttpClient okHttpClient;//定义OKHTTPClient对象属性
    private Handler handler;//定义Handler对象属性
    //初始化Handler对象
    private void initHandler() {
        handler = new Handler(){//handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        };
    }
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
        ViewHolder holder = null;//图片视图管理
        if(null == view) {
            holder = new ViewHolder();//实例化图片视图
            //加载item的布局文件
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(itemLayoutRes, null);
            holder.gridview = (PictureGridView) view
                    .findViewById(R.id.gridView);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        ImageView ivHeadPortrait = view.findViewById(R.id.iv_headPortrait);//加载头像id
        TextView tvName = view.findViewById(R.id.tv_name);//加载昵称id
        TextView tvContent = view.findViewById(R.id.tv_content);//加载文案id
        llDynamicDetails = view.findViewById(R.id.ll_dynamic_details);//查看详情id
        ImageView ivComment = view.findViewById(R.id.iv_comment);
        ImageView ivGiveLike = view.findViewById(R.id.iv_give_like);//点赞图片

        tvName.setText(moments.get(i).getName());//设置昵称的值
        tvContent.setText(moments.get(i).getContent());//设置评论的值
        //设置圆形头像
        Glide.with(mContext)
                .load(moments.get(i).getHeadPortraitUrl())
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

        initOkHttpClient();//初始化okHttp对象
        initHandler();//舒适化handler
        //点击点赞图片换logo
        View finalView = view;
        ivGiveLike.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(mContext)
                        .load(R.mipmap.praise3)
                        .into(ivGiveLike);
                new Thread(){
                    @Override
                    public void run() {
                        likegivePersonAndMomentsId(finalView,i);
                    }
                }.start();
            }
        });

        List<String> pictureUrl = new ArrayList<>();//图片列表
        List<String> list = new ArrayList<>();//临时字符串列表，用来存放带引号的图片路径
        String img = String.valueOf(moments.get(i).getPictureUrl());//说说对象中的图片部分
        Log.e("moments[i].getPictureUrl()",moments.get(i).getPictureUrl());
        String imgs = img.substring(1, img.length()-2);//将图片对象去掉头部和尾部的中括号
        list = Arrays.asList(imgs.split(","));//将图片部分按逗号分隔开
        for(int j=0;j<list.size();j++){
            String str = list.get(j).substring(4,list.get(j).length()-1);//将带引号的图片路径去掉引号
            Log.e("str",str);
            pictureUrl.add(str);
        }
        for (int j=0;j<pictureUrl.size();j++){
            Log.e("pictureUrl.get(i)",pictureUrl.get(j));
        }

        //根据图片个数创建布局
        int num = pictureUrl.size();//获取当前的图片数目
        Log.e("pictureUrl", String.valueOf(pictureUrl.size()));
        Log.e("i", String.valueOf(i));
        int col = 1;//默认列数
        Log.i("tag", "num" + num);
        if (num == 1) {
            holder.gridview.setNumColumns(1);
            col = 1;
        } else if (num == 2 || num == 4) {
            holder.gridview.setNumColumns(2);
            col = 2;
        } else {
            holder.gridview.setNumColumns(3);
            col = 3;
        }

        holder.gridview.setAdapter(new MyGridViewAdapter(mContext, num, col,pictureUrl));

        holder.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                Toast.makeText(mContext, "" + position, 0).show();
            }
        });
        
        return view;
    }

    //定义Holder类
    private class ViewHolder {
        PictureGridView gridview;
    }

    //使用POST方式提交点赞人和该说说id
    private void likegivePersonAndMomentsId(View view,int i) {
        //1. OkClient对象
        //2. 创建Request请求对象（提前准备好Form表单数据封装）
        //创建FormBody对象
        String likegivePerson = getPersonalPhone();//获取点赞人的手机号
        int momentsId = moments.get(i).getId();//获取当前说说id
        Log.e("likegivePerson",likegivePerson+"and"+momentsId);
        FormBody formBody =
                new FormBody.Builder()
                        .add("likegivePerson", likegivePerson)
                        .add("momentsId", String.valueOf(momentsId))
                        .build();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "LikegivePersonAndMomentsId")
                .post(formBody)
                .build();
        //3. 创建CALL对象
        Call call = okHttpClient.newCall(request);
        //4. 提交请求并获取响应
        try {
            Response response = call.execute();
            //获取响应的字符串信息
            String result = response.body().string();

            Message msg = handler.obtainMessage();
            msg.what = 1;
            msg.obj = result;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //加载个人信息的布局文件
    private String getPersonalPhone(){
        String phone = EMClient.getInstance().getCurrentUser();
        Log.e("lzz",phone);
        return phone;
    }
    //初始化OKHTTPClient对象
    private void initOkHttpClient() {
        okHttpClient = new OkHttpClient();
    }


}
