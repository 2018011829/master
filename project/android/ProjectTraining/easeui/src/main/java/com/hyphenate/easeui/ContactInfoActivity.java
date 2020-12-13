package com.hyphenate.easeui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.easeui.utils.EaseParentUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ContactInfoActivity extends AppCompatActivity {
    private ImageView ivAvatar;
    private TextView tvNickname;
    private TextView tvUsername;
    private TextView tvUserRemark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        String avatarPath = EaseParentUtil.toChatUserAvator;
        String nickname = EaseParentUtil.tochatUserNickname;
        final String remark = EaseParentUtil.toChatUserRemark;
        ivAvatar = findViewById(R.id.iv_avatar);
        tvNickname = findViewById(R.id.tv_nickname);
        tvUsername = findViewById(R.id.tv_username);
        tvUserRemark = findViewById(R.id.tv_remark);

        RequestOptions requestOptions = new RequestOptions().transform(new GlideRoundImage(getApplicationContext(), 8));
        Glide.with(this).load(avatarPath).apply(requestOptions).into(ivAvatar);
        tvNickname.setText(nickname);
        tvUsername.setText(username);
        tvUserRemark.setText(remark);
        //设置监听事件
        //设置备注的监听事件
        findViewById(R.id.rl_remark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditRemarkActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("currentRemark", remark);
                startActivityForResult(intent, 0);
            }
        });
        //发消息的点击事件
        findViewById(R.id.rl_send_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //删除联系人的点击事件
        findViewById(R.id.rl_delete_contact).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDeleteContactDialog(username);

            }
        });
        //返回按钮的点击事件
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //电话号码的点击事件
        tvUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 显示是否拨打改电话号码的对话框，跳转到拨号页面
            }
        });
    }

    private void showDeleteContactDialog(final String username) {
        //方法链调用
        new AlertDialog.Builder(ContactInfoActivity.this).setTitle("温馨提示")
                .setMessage("确定要删除这个联系人吗？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //执行删除联系人操作
                        EMClient.getInstance().contactManager().aysncDeleteContact(username, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                EaseParentUtil.isContactAddedOrDeleted = true;
                                deleteRemark(EMClient.getInstance().getCurrentUser(), username);
                                //删除成功，返回聊天界面，聊天界面收到返回消息直接关闭
                                setResult(99, null);
                                finish();
                            }

                            @Override
                            public void onError(int i, String s) {
                                Looper.prepare();
                                Toast.makeText(ContactInfoActivity.this, "删除联系人失败，请重试！", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });

                    }
                }).create().show();
    }

    private void deleteRemark(final String currentUser, final String username) {
        new Thread() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("fromPhone", currentUser)
                        .add("toPhone", username).build();
                Request request = new Request.Builder().url(EaseParentUtil.SERVICE_ADDRESS + "DeleteRemarkServlet").post(formBody).build();
                Call call = okHttpClient.newCall(request);
                try {
                    call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0&&resultCode==1){
            tvUserRemark.setText(data.getStringExtra("remark"));
            EaseParentUtil.toChatUserRemark=data.getStringExtra("remark");
        }
    }
}