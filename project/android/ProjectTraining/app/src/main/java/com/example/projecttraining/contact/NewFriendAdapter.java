//package com.example.projecttraining.contact;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.text.Layout;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.example.projecttraining.R;
//import com.example.projecttraining.books.entitys.Content;
//import com.example.projecttraining.util.ConfigUtil;
//import com.example.projecttraining.util.ParentUtil;
//import com.hyphenate.chat.EMClient;
//import com.hyphenate.exceptions.HyphenateException;
//
//import java.util.List;
//
//public class NewFriendAdapter extends BaseAdapter {
//    private final String TAG="NewFriendAdapter";
//    private Context context;
//    private List<ContactsStatus> contactsStatuses;
//    private int itemLayout;
//    private int flag=0;
//    public NewFriendAdapter(Context context,List<ContactsStatus> contactsStatuses,int itemLayout){
//        this.context=context;
//        this.contactsStatuses=contactsStatuses;
//        this.itemLayout=itemLayout;
//
//    }
//    @Override
//    public int getCount() {
//        if(null!=contactsStatuses){
//            return contactsStatuses.size();
//        }
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        if(null!=contactsStatuses){
//            return contactsStatuses.get(position);
//        }
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder=null;
//        if(null==convertView){
//            convertView= LayoutInflater.from(context).inflate(itemLayout,null);
//            holder=new ViewHolder();
//            holder.avatar=convertView.findViewById(R.id.avatar);
//            holder.nickname=convertView.findViewById(R.id.nickname);
//            holder.agree=convertView.findViewById(R.id.agree);
//            holder.reject=convertView.findViewById(R.id.reject);
//            holder.status=convertView.findViewById(R.id.status);
//            convertView.setTag(holder);
//        }else{
//            holder=(ViewHolder)convertView.getTag();
//        }
//        //设置控件内容,如果from==null,说明是我发出的邀请
//        ContactsStatus contactsStatus=contactsStatuses.get(position);
//        //我发出的，尚未被同意的邀请
//        if(contactsStatus.getFrom()==null){
//            Glide.with(context)
//                    .load(ConfigUtil.SETVER_AVATAR+contactsStatus.getTo().getAvator())
//                    .into(holder.avatar);
//            holder.nickname.setText(contactsStatus.getTo().getNickname());
//            holder.agree.setVisibility(View.GONE);
//            holder.reject.setVisibility(View.GONE);
//            holder.status.setVisibility(View.VISIBLE);
//            if(contactsStatus.getStatus()==0){
//            holder.status.setText("对方处理中...");
//            }else if(contactsStatus.getStatus()==1){
//                holder.status.setText("对方已同意");
//            }else if(contactsStatus.getStatus()==2){
//                holder.status.setText("对方已拒绝");
//            }
//        }
//        else{
//            Glide.with(context)
//                    .load(ConfigUtil.SETVER_AVATAR+contactsStatus.getFrom().getAvator())
//                    .into(holder.avatar);
//            holder.nickname.setText(contactsStatus.getFrom().getNickname());
//            if(contactsStatus.getStatus()==0){
//                holder.agree.setVisibility(View.VISIBLE);
//                holder.reject.setVisibility(View.VISIBLE);
//                holder.status.setVisibility(View.GONE);
//                holder.agree.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //同意好友请求
//                        Log.e(TAG, "onClick: 同意请求");
//                        try {
//                            EMClient.getInstance().contactManager().acceptInvitation(contactsStatus.getFrom().getPhone());
//                            ParentUtil.isContactAddedOrDeleted=true;
////                            Intent intent=new Intent();
////                            intent.setClass(context,NewFriendsActivity.class);
////                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                            context.startActivity(new Intent(context,NewFriendsActivity.class));
//
//                        } catch (HyphenateException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                holder.reject.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //拒绝好友请求
//                        try {
//                            EMClient.getInstance().contactManager().declineInvitation(contactsStatus.getFrom().getPhone());
//                        } catch (HyphenateException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }else if(contactsStatus.getStatus()==1){
//                holder.agree.setVisibility(View.INVISIBLE);
//                holder.reject.setVisibility(View.INVISIBLE);
//                holder.status.setVisibility(View.VISIBLE);
//                holder.status.setText("您已同意");
//            }else if(contactsStatus.getStatus()==2){
//                holder.agree.setVisibility(View.INVISIBLE);
//                holder.reject.setVisibility(View.INVISIBLE);
//                holder.status.setVisibility(View.VISIBLE);
//                holder.status.setText("您已拒绝");
//            }
//        }
//        return convertView;
//    }
//
//    static class ViewHolder{
//        ImageView avatar;
//        TextView nickname;
//        Button agree;
//        Button reject;
//        TextView status;
//    }
//}
