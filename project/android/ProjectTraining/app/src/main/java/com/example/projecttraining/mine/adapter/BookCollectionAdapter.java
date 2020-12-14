package com.example.projecttraining.mine.adapter;

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

public class BookCollectionAdapter extends BaseAdapter {
    private Context mContext;
    private List<Book> books;

    public BookCollectionAdapter(Context mContext, List<Book> books) {
        this.mContext = mContext;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        // 重用convertView
        if(view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.more_books_griditem, null);
            holder.iv_book_img = view.findViewById(R.id.iv_book_img);
            holder.tv_book_name = view.findViewById(R.id.tv_book_name);
            holder.tv_book_author = view.findViewById(R.id.tv_book_author);
            holder.tv_book_introduce = view.findViewById(R.id.tv_book_introduce);
            holder.tv_book_type = view.findViewById(R.id.tv_book_type);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Glide.with(mContext)
                .load(ConfigUtil.SERVICE_ADDRESS+"bookImgs/"+books.get(i).getImg())
                .into(holder.iv_book_img);
        holder.tv_book_name.setText(books.get(i).getName());
        holder.tv_book_author.setText("作者："+books.get(i).getAuthor());
        holder.tv_book_type.setText("类型："+books.get(i).getType());
        holder.tv_book_introduce.setText("简介："+books.get(i).getIntroduce());
        return view;
    }

    /**
     * 静态类，便于GC回收
     */
    public static class ViewHolder{
        ImageView iv_book_img;
        TextView tv_book_name;
        TextView tv_book_author;
        TextView tv_book_type;
        TextView tv_book_introduce;
    }
}
