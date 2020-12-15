package com.example.projecttraining.contact.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projecttraining.R;
import com.example.projecttraining.contact.activity.NewFriendsActivity;
import com.example.projecttraining.contact.dao.ContactsStatus;
import com.example.projecttraining.util.ConfigUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.GlideRoundImage;
import com.hyphenate.easeui.utils.EaseParentUtil;
import com.hyphenate.exceptions.HyphenateException;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//NewFriendAdapter
public class NewFriendAdapter extends BaseAdapter {
    private final String TAG = "NewFriendAdapter";

    private Context context;
    private List<ContactsStatus> contactsStatuses;
    private int itemLayout;
    private Handler handler;


    public NewFriendAdapter(Context context, List<ContactsStatus> contactsStatuses, int itemLayout, Handler handler) {
        this.context = context;
        this.contactsStatuses = contactsStatuses;
        this.itemLayout = itemLayout;
        this.handler=handler;

    }

    @Override
    public int getCount() {
        if (null != contactsStatuses) {
            return contactsStatuses.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (null != contactsStatuses) {
            return contactsStatuses.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(itemLayout, null);
            holder = new ViewHolder();
            holder.avatar = convertView.findViewById(R.id.avatar);
            holder.nickname = convertView.findViewById(R.id.nickname);
            holder.agree = convertView.findViewById(R.id.agree);
            holder.reject = convertView.findViewById(R.id.reject);
            holder.status = convertView.findViewById(R.id.status);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置控件内容,如果from==null,说明是我发出的邀请
        ContactsStatus contactsStatus = contactsStatuses.get(position);
        //我发出的，尚未被同意的邀请
        if (contactsStatus.getFrom() == null) {
            RequestOptions requestOptions=new RequestOptions().transform(new GlideRoundImage(context,8));
            Glide.with(context)
                    .load(ConfigUtil.SETVER_AVATAR + contactsStatus.getTo().getAvator())
                    .apply(requestOptions)
                    .into(holder.avatar);
            holder.nickname.setText(contactsStatus.getTo().getNickname());
            holder.agree.setVisibility(View.GONE);
            holder.reject.setVisibility(View.GONE);
            holder.status.setVisibility(View.VISIBLE);
            if (contactsStatus.getStatus() == 0) {
                holder.status.setText("对方处理中...");
            } else if (contactsStatus.getStatus() == 1) {
                holder.status.setText("对方已同意");
            } else if (contactsStatus.getStatus() == 2) {
                holder.status.setText("对方已拒绝");
            }
        } else {
            RequestOptions requestOptions=new RequestOptions().transform(new GlideRoundImage(context,8));
            Glide.with(context)
                    .load(ConfigUtil.SETVER_AVATAR + contactsStatus.getFrom().getAvator())
                    .apply(requestOptions)
                    .into(holder.avatar);
            holder.nickname.setText(contactsStatus.getFrom().getNickname());
            if (contactsStatus.getStatus() == 0) {
                holder.agree.setVisibility(View.VISIBLE);
                holder.reject.setVisibility(View.VISIBLE);
                holder.status.setVisibility(View.GONE);

                holder.agree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //同意好友请求,环信同意请求为同步方法，会阻塞当前线程，所以新建线程
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    EMClient.getInstance().contactManager().acceptInvitation(contactsStatus.getFrom().getPhone());
                                } catch (HyphenateException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        EaseParentUtil.isContactAddedOrDeleted = true;
                        //修改本地数据库
                        agreeInvitation(contactsStatus.getId());
                        //添加备注
                        addRemark(EMClient.getInstance().getCurrentUser(),contactsStatus.getFrom().getPhone(),EaseParentUtil.currentUserNickname,contactsStatus.getFrom().getNickname());

                    }
                });
                holder.reject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Thread(){
                            @Override
                            public void run() {
                                //拒绝好友请求
                                try {
                                    EMClient.getInstance().contactManager().declineInvitation(contactsStatus.getFrom().getPhone());
                                } catch (HyphenateException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        Log.e(TAG, "onClick:拒绝请求" + contactsStatus.getFrom().getPhone());
                        //修改本地数据库
                        rejectInvitation(contactsStatus.getId());

                    }
                });
            } else if (contactsStatus.getStatus() == 1) {
                holder.agree.setVisibility(View.INVISIBLE);
                holder.reject.setVisibility(View.INVISIBLE);
                holder.status.setVisibility(View.VISIBLE);
                holder.status.setText("您已同意");
            } else if (contactsStatus.getStatus() == 2) {
                holder.agree.setVisibility(View.INVISIBLE);
                holder.reject.setVisibility(View.INVISIBLE);
                holder.status.setVisibility(View.VISIBLE);
                holder.status.setText("您已拒绝");
            }
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView avatar;
        TextView nickname;
        Button agree;
        Button reject;
        TextView status;
    }
    private void addRemark(String currentUser, String phone, String currentUserNickname, String nickname) {
        new Thread(){
            @Override
            public void run() {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder().add("fromPhone",currentUser)
                        .add("toPhone",phone)
                        .add("fromPhoneNickname",currentUserNickname)
                        .add("toPhoneNickname",nickname).build();
                Request request=new Request.Builder().post(formBody).url(ConfigUtil.SERVICE_ADDRESS+"addRemarkServlet").build();
                Call call=okHttpClient.newCall(request);
                try {
                    Response response=call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void rejectInvitation(int id) {
        new Thread(){
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("id", id + "").build();
                Request request = new Request.Builder().post(formBody).url(ConfigUtil.SERVICE_ADDRESS + "RejectInvitationServlet").build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Looper.prepare();
                        Toast.makeText(context, "拒绝失败，请重试", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.body().string().equals("成功")) {
                            Message message = handler.obtainMessage();
                            message.what = 2;
                            handler.sendMessage(message);
                            Looper.prepare();
                            Toast.makeText(context, "拒绝成功", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(context, "拒绝失败", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                    }
                });
            }
        }.start();

    }


    private void agreeInvitation(int id) {
        new Thread(){
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("id", id + "").build();
                Request request = new Request.Builder().post(formBody).url(ConfigUtil.SERVICE_ADDRESS + "AgreeInvitationServlet").build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Looper.prepare();
                        Toast.makeText(context, "添加失败，请重试", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.body().string().equals("成功")) {
                            Message message = handler.obtainMessage();
                            message.what = 2;
                            handler.sendMessage(message);
                            Looper.prepare();
                            Toast.makeText(context, "你们已经成为好友了！", Toast.LENGTH_SHORT).show();
                            Looper.loop();

                        } else {
                            Looper.prepare();
                            Toast.makeText(context, "添加失败，请重试", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                    }
                });
            }
        }.start();

    }
}

