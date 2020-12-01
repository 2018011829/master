package com.example.projecttraining.home.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.PersonalInfo;
import com.example.projecttraining.mine.AddChildActivity;
import com.example.projecttraining.util.ConfigUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyFragment extends Fragment {
    private ImageView iv_headPhoto;
    private LinearLayout ll_mine_addChild;
    private View view;
    private TextView tvMineUserName;//我的昵称
    private ImageView ivHeadPhoto;//头像
    private OkHttpClient okHttpClient;//定义OKHTTPClient对象属性
    private Handler handler;//定义Handler对象属性
    private TextView tvMinePhone;//电话号码
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

        initOkHttpClient();//初始化OKHttp
        initHandler();//初始化Handler
        simpStringParamPostRequest();

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
    /**
     * 初始化OKHTTPClient对象
     */
    private void initOkHttpClient() {
        okHttpClient = new OkHttpClient();
        //使用Builder对象创建OKHTTPClient对象的方法如下：
//        okHttpClient = new OkHttpClient.Builder()
//                //可以添加网络请求参数，如网络超时连接时间等
//                .build();
    }
    /**
     * 初始化Handler对象
     */
    private void initHandler() {
//        HandlerThread handlerThread =
//                new HandlerThread("MyThread");
//        handlerThread.start();
        handler = new Handler(){//handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 1://如果服务端返回的数据是字符串
                    case 2:
                    case 3:
                        break;
                }
            }
        };
    }

    /**
     * 采用POST请求方式提交个人信息
     */
    private void simpStringParamPostRequest() {
        //2 创建Request对象
        //1) 使用RequestBody封装请求数据
        //获取待传输数据对应的MIME类型
        MediaType type = MediaType.parse("text/plain");
        //创建RequestBody对象
        PersonalInfo personalInfo = new PersonalInfo(tvMineUserName.getText().toString().trim(),"123",tvMinePhone.getText().toString().trim());
        //创建FormBody对象
        FormBody formBody =
                new FormBody.Builder()
                        .add("name", tvMineUserName.getText().toString().trim())
                        .add("photoUrl", "Android综合应用")
                        .add("personalPhone",tvMinePhone.getText().toString().trim())
                        .build();
        //2) 创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "PersonalInfoServlet")
                .post(formBody)
                .build();
        //3. 创建CALL对象
        Call call = okHttpClient.newCall(request);
        //4. 提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("lww", "请求失败");
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        });
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
        tvMinePhone = view.findViewById(R.id.tv_mine_phone);
        tvMineUserName = view.findViewById(R.id.tv_mine_userName);
        iv_headPhoto = view.findViewById(R.id.iv_headPhoto);
        ll_mine_addChild  = view.findViewById(R.id.ll_mine_addChild);
    }
}
