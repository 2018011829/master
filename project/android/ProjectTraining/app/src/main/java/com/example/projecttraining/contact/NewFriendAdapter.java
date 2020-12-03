package com.example.projecttraining.contact;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.books.entitys.Content;
import com.example.projecttraining.util.ConfigUtil;
import com.example.projecttraining.util.ParentUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

public class NewFriendAdapter extends BaseAdapter {
    private final String TAG="NewFriendAdapter";
    private Context context;
    private List<Parent> parents;
    private int itemLayout;
    private int flag=0;
    public NewFriendAdapter(Context context,List<Parent> parents,int itemLayout){
        this.context=context;
        this.parents=parents;
        this.itemLayout=itemLayout;

    }
    @Override
    public int getCount() {
        if(null!=parents){
            return parents.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(null!=parents){
            return parents.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(null==convertView){
            convertView= LayoutInflater.from(context).inflate(itemLayout,null);
            holder=new ViewHolder();
            holder.avatar=convertView.findViewById(R.id.avatar);
            holder.nickname=convertView.findViewById(R.id.nickname);
            holder.agree=convertView.findViewById(R.id.agree);
            holder.reject=convertView.findViewById(R.id.reject);
            holder.status=convertView.findViewById(R.id.status);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        //设置控件内容
        Glide.with(context).load(ConfigUtil.SETVER_AVATAR+parents.get(position).getAvator()).into(holder.avatar);
        holder.nickname.setText(parents.get(position).getNickname());
        holder.agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //同意好友请求
                Log.e(TAG, "onClick: 同意请求");
                try {
                    EMClient.getInstance().contactManager().acceptInvitation(parents.get(position).getPhone());
                    flag=1;
                    ParentUtil.isContactAddedOrDeleted=true;
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拒绝好友请求
                try {
                    EMClient.getInstance().contactManager().declineInvitation(parents.get(position).getPhone());
                   flag=2;
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        });
        if(flag!=0){
            holder.status.setVisibility(View.VISIBLE);
            holder.agree.setVisibility(View.GONE);
            holder.reject.setVisibility(View.GONE);
        }
        if (flag == 1) {
            holder.status.setText("已同意");
        }
        if(flag==2){
            holder.status.setText("已拒绝");
        }
        return convertView;
    }

    static class ViewHolder{
        ImageView avatar;
        TextView nickname;
        Button agree;
        Button reject;
        TextView status;
    }
}
