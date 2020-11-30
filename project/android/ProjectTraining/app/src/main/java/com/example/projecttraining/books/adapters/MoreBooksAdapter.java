package com.example.projecttraining.books.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.books.entitys.Book;
import com.example.projecttraining.util.ConfigUtil;


import java.util.List;

public class MoreBooksAdapter extends BaseAdapter {

    private Context mContext;
    private int layoutResource;
    private List<Book> list; //数据源
//    private String bookInfo;

    public MoreBooksAdapter(Context mContext, int layoutResource, List<Book> list) {
        this.mContext = mContext;
        this.layoutResource = layoutResource;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (list!=null){
            return list.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(layoutResource,null);
            holder.iv=view.findViewById(R.id.grid_item_iv);
            holder.tv=view.findViewById(R.id.grid_item_tv);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        Book book=list.get(i);
        Glide.with(mContext)
                .load(ConfigUtil.SERVICE_ADDRESS+"bookImgs/"+book.getImg())
                .placeholder(R.mipmap.loading)
                .error(R.drawable.faliure)
                .fallback(R.drawable.faliure)
                .into(holder.iv);
        holder.tv.setText(book.getName());

        return view;
    }

    static class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}
