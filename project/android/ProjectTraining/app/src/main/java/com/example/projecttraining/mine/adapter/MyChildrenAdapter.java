package com.example.projecttraining.mine.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MyFragment;
import com.example.projecttraining.mine.entity.Child;

import java.util.List;

public class MyChildrenAdapter extends BaseAdapter {
    private Context mContext;
    private List<Child> children;
    private Handler handler;

    public MyChildrenAdapter(Context mContext, List<Child> children, Handler handler) {
        this.mContext = mContext;
        this.children = children;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return children.size();
    }

    @Override
    public Object getItem(int i) {
        return children.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        // 重用convertView
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_mine_mychildren_item, null);
            holder.tv_child_name =  convertView.findViewById(R.id.tv_mine_child_name);
            holder.tv_child_grade = convertView.findViewById(R.id.tv_mine_child_grade);
            holder.tv_child_sex = convertView.findViewById(R.id.tv_mine_child_sex);
            holder.iv_child_select = convertView.findViewById(R.id.iv_mine_child_select);
            holder.ll_child_select = convertView.findViewById(R.id.ll_mine_child_select);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        // 适配数据
        holder.tv_child_name.setText(children.get(i).getName());
        holder.tv_child_grade.setText(children.get(i).getGrade());
        holder.tv_child_sex.setText(children.get(i).getSex());
        if(MyFragment.childName.equals(children.get(i).getName())){
            holder.iv_child_select.setImageDrawable(mContext.getResources().getDrawable(R.drawable.mine_select,null));
        }

        holder.ll_child_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.iv_child_select.setImageDrawable(mContext.getResources().getDrawable(R.drawable.mine_select,null));
                MyFragment.childName = children.get(i).getName();
                MyFragment.childNumber = i;
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
        });

        return convertView;
    }



    /**
     * 静态类，便于GC回收
     */
    public static class ViewHolder{
        TextView tv_child_name;
        TextView tv_child_grade;
        TextView tv_child_sex;
        ImageView iv_child_select;
        LinearLayout ll_child_select;
    }

}
