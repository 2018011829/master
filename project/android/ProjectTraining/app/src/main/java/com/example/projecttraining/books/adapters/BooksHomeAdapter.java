package com.example.projecttraining.books.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.books.activity.BookInfoActivity;
import com.example.projecttraining.books.activity.MoreBooksActivity;
import com.example.projecttraining.books.entitys.Book;
import com.example.projecttraining.books.tools.GlideRoundImage;
import com.example.projecttraining.util.ConfigUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class BooksHomeAdapter extends BaseAdapter {

    private Context mContext;
    private int layoutResource;//item布局文件
    private TreeMap<String, List<Book>> treeMap;//数据源
    private List<String> keys;

    public BooksHomeAdapter(Context mContext, int layoutResource, TreeMap<String, List<Book>> treeMap) {
        this.mContext = mContext;
        this.layoutResource = layoutResource;
        this.treeMap = treeMap;

        if (treeMap!=null){
            keys=new ArrayList<>();
            Set<String> treeSet=treeMap.keySet();
            Iterator<String> iterator=treeSet.iterator();
            while (iterator.hasNext()){
                keys.add(iterator.next());
            }
        }
    }

    @Override
    public int getCount() {
        if (treeMap!=null){
            return treeMap.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        List<Book> list=null;
        if (treeMap!=null){
            list=treeMap.get(keys.get(i));
        }
        return list;
    }

    @Override
    public long getItemId(int i) {
        long id=0;
        if (getItem(i)!=null){
            List<Book> list= (List<Book>) getItem(i);
            id=list.get(i).getId();
        }
        return id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            //加载item布局文件，赋值给convertView
            view = LayoutInflater.from(mContext)
                    .inflate(layoutResource, null);
            holder=new ViewHolder();
            holder.tvType=view.findViewById(R.id.tv_type);
            holder.tvGetMore=view.findViewById(R.id.tv_get_more);
            holder.ivGetMore=view.findViewById(R.id.iv_get_more);
            holder.tvName1=view.findViewById(R.id.tv_name1);
            holder.ivImg1=view.findViewById(R.id.iv_img1);
            holder.tvName2=view.findViewById(R.id.tv_name2);
            holder.ivImg2=view.findViewById(R.id.iv_img2);
            holder.tvName3=view.findViewById(R.id.tv_name3);
            holder.ivImg3=view.findViewById(R.id.iv_img3);
            holder.tvName4=view.findViewById(R.id.tv_name4);
            holder.ivImg4=view.findViewById(R.id.iv_img4);
            holder.tvName5=view.findViewById(R.id.tv_name5);
            holder.ivImg5=view.findViewById(R.id.iv_img5);
            holder.tvName6=view.findViewById(R.id.tv_name6);
            holder.ivImg6=view.findViewById(R.id.iv_img6);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        //给控件赋值
        final List<Book> listBooks= (List<Book>) getItem(i);
        holder.tvType.setText(keys.get(i));
        for (int j=0;j<listBooks.size();++j){
            if (j==0){
                holder.tvName1.setText(listBooks.get(j).getName());
                Glide.with(mContext)
                        .load(ConfigUtil.SERVICE_ADDRESS+"bookImgs/"+listBooks.get(j).getImg())
                        .placeholder(R.mipmap.loading)
                        .error(R.drawable.faliure)
                        .fallback(R.drawable.faliure)
                        .transform(new GlideRoundImage(mContext,10))
                        .into(holder.ivImg1);
            }
            if (j==1){
                holder.tvName2.setText(listBooks.get(j).getName());
                Glide.with(mContext)
                        .load(ConfigUtil.SERVICE_ADDRESS+"bookImgs/"+listBooks.get(j).getImg())
                        .placeholder(R.mipmap.loading)
                        .error(R.drawable.faliure)
                        .fallback(R.drawable.faliure)
                        .transform(new GlideRoundImage(mContext,10))
                        .into(holder.ivImg2);
            }
            if (j==2){
                holder.tvName3.setText(listBooks.get(j).getName());
                Glide.with(mContext)
                        .load(ConfigUtil.SERVICE_ADDRESS+"bookImgs/"+listBooks.get(j).getImg())
                        .placeholder(R.mipmap.loading)
                        .error(R.drawable.faliure)
                        .fallback(R.drawable.faliure)
                        .transform(new GlideRoundImage(mContext,10))
                        .into(holder.ivImg3);
            }
            if (j==3){
                holder.tvName4.setText(listBooks.get(j).getName());
                Glide.with(mContext)
                        .load(ConfigUtil.SERVICE_ADDRESS+"bookImgs/"+listBooks.get(j).getImg())
                        .placeholder(R.mipmap.loading)
                        .error(R.drawable.faliure)
                        .fallback(R.drawable.faliure)
                        .transform(new GlideRoundImage(mContext,10))
                        .into(holder.ivImg4);
            }
            if (j==4){
                holder.tvName5.setText(listBooks.get(j).getName());
                Glide.with(mContext)
                        .load(ConfigUtil.SERVICE_ADDRESS+"bookImgs/"+listBooks.get(j).getImg())
                        .placeholder(R.mipmap.loading)
                        .error(R.drawable.faliure)
                        .fallback(R.drawable.faliure)
                        .transform(new GlideRoundImage(mContext,10))
                        .into(holder.ivImg5);
            }
            if (j==5){
                Log.e("j",""+j);
                holder.tvName6.setText(listBooks.get(j).getName());
                Glide.with(mContext)
                        .load(ConfigUtil.SERVICE_ADDRESS+"bookImgs/"+listBooks.get(j).getImg())
                        .placeholder(R.mipmap.loading)
                        .error(R.drawable.faliure)
                        .fallback(R.drawable.faliure)
                        .transform(new GlideRoundImage(mContext,10))
                        .into(holder.ivImg6);
            }
        }

        final String type=holder.tvType.getText().toString();
        //更多的点击事件
        holder.tvGetMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, MoreBooksActivity.class);
                //传递类型参数
                intent.putExtra("type",type);
                intent.putExtra("grades", GradesPagerAdapter.grades);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        holder.ivGetMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, MoreBooksActivity.class);
                //传递类型参数
                intent.putExtra("type",type);
                intent.putExtra("grades", GradesPagerAdapter.grades);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        //某本书的点击事件
        holder.ivImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, BookInfoActivity.class);
                //传递类型参数
                intent.putExtra("book",listBooks.get(0));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        holder.tvName1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, BookInfoActivity.class);
                //传递类型参数
                intent.putExtra("book",listBooks.get(0));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        holder.ivImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, BookInfoActivity.class);
                //传递类型参数
                intent.putExtra("book",listBooks.get(1));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        holder.tvName2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, BookInfoActivity.class);
                //传递类型参数
                intent.putExtra("book",listBooks.get(1));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        holder.ivImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, BookInfoActivity.class);
                //传递类型参数
                intent.putExtra("book",listBooks.get(2));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        holder.tvName3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, BookInfoActivity.class);
                //传递类型参数
                intent.putExtra("book",listBooks.get(2));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        holder.ivImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, BookInfoActivity.class);
                //传递类型参数
                intent.putExtra("book",listBooks.get(3));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        holder.tvName4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, BookInfoActivity.class);
                //传递类型参数
                intent.putExtra("book",listBooks.get(3));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        holder.ivImg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, BookInfoActivity.class);
                //传递类型参数
                intent.putExtra("book",listBooks.get(4));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        holder.tvName5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, BookInfoActivity.class);
                //传递类型参数
                intent.putExtra("book",listBooks.get(4));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        holder.ivImg6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, BookInfoActivity.class);
                //传递类型参数
                intent.putExtra("book",listBooks.get(5));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        holder.tvName6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, BookInfoActivity.class);
                //传递类型参数
                intent.putExtra("book",listBooks.get(5));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    public static class ViewHolder{
        TextView tvType;
        TextView tvGetMore;
        ImageView ivGetMore;
        TextView tvName1;
        ImageView ivImg1;
        TextView tvName2;
        ImageView ivImg2;
        TextView tvName3;
        ImageView ivImg3;
        TextView tvName4;
        ImageView ivImg4;
        TextView tvName5;
        ImageView ivImg5;
        TextView tvName6;
        ImageView ivImg6;
    }
}
