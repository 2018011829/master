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
import com.example.projecttraining.books.tools.GlideRoundImage;
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
            holder.ivBookImg=view.findViewById(R.id.iv_book_img);
            holder.tvBookName=view.findViewById(R.id.tv_book_name);
            holder.tvAuthor=view.findViewById(R.id.tv_book_author);
            holder.tvBookType=view.findViewById(R.id.tv_book_type);
            holder.tvBookIntroduce=view.findViewById(R.id.tv_book_introduce);
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
                .transform(new GlideRoundImage(mContext,10))
                .into(holder.ivBookImg);
        holder.tvBookName.setText(book.getName());
        holder.tvBookType.setText("作者：" + book.getAuthor());
        holder.tvAuthor.setText("类型：" + book.getType());
        holder.tvBookIntroduce.setText("简介："+book.getIntroduce().substring(0,50)+"......");

        return view;
    }

    static class ViewHolder{
        ImageView ivBookImg;
        TextView tvBookName;
        TextView tvAuthor;
        TextView tvBookType;
        TextView tvBookIntroduce;
    }
}
