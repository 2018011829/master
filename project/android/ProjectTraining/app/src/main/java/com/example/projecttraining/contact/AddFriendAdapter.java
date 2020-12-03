package com.example.projecttraining.contact;

import android.content.Context;
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
import com.example.projecttraining.util.ConfigUtil;
import com.example.projecttraining.util.ParentUtil;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import org.w3c.dom.Text;

import java.util.List;

import static com.example.projecttraining.R2.id.add;

public class AddFriendAdapter extends BaseAdapter {
    private static final String TAG="AddFriendAdapter";
    private Context context;
    private List<Parent> parents;
    private int itemLayout;
    public AddFriendAdapter(Context context,List<Parent> parents,int itemLayout){
        this.context=context;
        this.parents=parents;
        this.itemLayout=itemLayout;
    }
    @Override
    public int getCount() {
        if(null!=parents) return parents.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(null!=parents) return parents.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e(TAG, "getView: " );
        ViewHolder holder=null;
        if(null==convertView){
            convertView= LayoutInflater.from(context).inflate(itemLayout,null);
            holder=new ViewHolder();
            holder.avatar=convertView.findViewById(R.id.avatar);
            holder.nickname=convertView.findViewById(R.id.nickname);
            holder.add=convertView.findViewById(R.id.add);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        //设置控件内容
        Glide.with(context).load(ConfigUtil.SETVER_AVATAR+parents.get(position).getAvator()).into(holder.avatar);
        holder.nickname.setText(parents.get(position).getNickname());
        if(ParentUtil.allContacts.contains(parents.get(position).getPhone())){
            holder.add.setText("已在通讯录");
            holder.add.setClickable(false);
        }
        //设置“添加”按钮的监听器，发送添加好友申请
        ViewHolder finalHolder = holder;
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().contactManager().addContact(parents.get(position).getPhone(),null);
                            Log.e(TAG, "run: 好友请求发送成功"+parents.get(position).getPhone());
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                finalHolder.add.setClickable(false);
                finalHolder.add.setText("已发送");
            }
        });
        return convertView;
    }
    static class ViewHolder{
        ImageView avatar;
        TextView nickname;
        Button add;
    }
}
