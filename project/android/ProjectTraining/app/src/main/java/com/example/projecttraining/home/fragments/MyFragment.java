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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.mine.AddChildActivity;
import com.example.projecttraining.mine.EditorParentActivity;
import com.example.projecttraining.util.ConfigUtil;
import com.example.projecttraining.util.ParentUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseParentUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyFragment extends Fragment {
    private ImageView iv_headPhoto;
    private LinearLayout ll_mine_addChild;
    private LinearLayout ll_mine_editorParent;
    private TextView tv_mine_useName;
    private TextView tv_mine_phone;
    private View view;
    private Intent intent;
    private int tag=0;
    private String phone = "18730094411";
    private String nickName;
    private String sex;
    private String headPhoto;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    String result= (String) msg.obj;
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        nickName = jsonObject.getString("nickName");
                        phone = jsonObject.getString("phone");

                        sex = jsonObject.getString("sex");
                        headPhoto = jsonObject.getString("headphoto");

                        tv_mine_useName.setText(nickName);
                        tv_mine_phone.setText("手机号："+phone);

                        Log.i("json",sex+"");
                        Log.i("json",headPhoto+"");

                        Glide.with(MyFragment.this)
                                .load(ConfigUtil.SERVICE_ADDRESS+"headportraitimgs/"+headPhoto)
                                .circleCrop()
                                .into(iv_headPhoto);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my,container,false);

        intent = getActivity().getIntent();

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
                    Intent addChild = new Intent();
                    addChild.setClass(getContext(), AddChildActivity.class);
                    startActivity(addChild);
                    break;
                case R.id.ll_mine_editorParent:
                    Intent editor = new Intent();
                    editor.setClass(getContext(), EditorParentActivity.class);
                    editor.putExtra("head",headPhoto);
                    editor.putExtra("nickName",nickName);
                    editor.putExtra("sex",sex);
                    editor.putExtra("phone",phone);
                    startActivityForResult(editor,100);
                    break;
            }
        }
    }

    private void setClickListener() {
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        ll_mine_addChild.setOnClickListener(myOnClickListener);
        ll_mine_editorParent.setOnClickListener(myOnClickListener);
    }
    private void init(){
        tv_mine_phone.setText(EMClient.getInstance().getCurrentUser());
        tv_mine_useName.setText(EaseParentUtil.currentUserNickname);
        Glide.with(getContext()).load(EaseParentUtil.currentUserAvatar).into(iv_headPhoto);

    }
//    private void init() {
//        if(tag<=0) {
//            FormBody.Builder builder = new FormBody.Builder();
//            builder.add("phone", phone);
//            FormBody formBody = builder.build();
//            Request request = new Request.Builder()
//                    .post(formBody)
//                    .url(ConfigUtil.SERVICE_ADDRESS + "ParentMessageServlet")
//                    .build();
//            Call call = new OkHttpClient().newCall(request);
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                    Log.e("家长信息", "请求失败");
//                }
//
//                @Override
//                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                    String result = response.body().string();
//                    Log.i("json", result);
//                    Message msg = new Message();
//                    msg.what = 1;
//                    msg.obj = result;
//                    handler.sendMessage(msg);
//                }
//            });
//            tag++;
//        }else {
//            tv_mine_useName.setText(nickName);
//            tv_mine_phone.setText("手机号："+phone);
//            Glide.with(MyFragment.this)
//                    .load(ConfigUtil.SERVICE_ADDRESS+"headportraitimgs/"+headPhoto)
//                    .circleCrop()
//                    .into(iv_headPhoto);
//        }
//    }

    private void findViews() {
        iv_headPhoto = view.findViewById(R.id.iv_headPhoto);
        ll_mine_addChild  = view.findViewById(R.id.ll_mine_addChild);
        ll_mine_editorParent = view.findViewById(R.id.ll_mine_editorParent);
        tv_mine_useName = view.findViewById(R.id.tv_mine_userName);
        tv_mine_phone = view.findViewById(R.id.tv_mine_phone);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==100&&resultCode==200) {
            tag=0;
            init();
        }
    }
}
