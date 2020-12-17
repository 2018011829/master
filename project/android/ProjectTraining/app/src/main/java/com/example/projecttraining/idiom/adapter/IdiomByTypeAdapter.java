package com.example.projecttraining.idiom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projecttraining.R;
import com.example.projecttraining.idiom.activitys.IdiomInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 2020-11-28
 * @author lrf
 */
public class IdiomByTypeAdapter extends BaseAdapter {
    private Context myContext;
    private List<String> idioms = new ArrayList<>();
    private int itemLayout;

    public IdiomByTypeAdapter(Context myContext, List<String> idioms, int itemLayout) {
        this.myContext = myContext;
        this.idioms = idioms;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() { // 获得数据的条数
        if(null != idioms){
            return idioms.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) { // 获取每个item显示的数据对象
        return null;
    }

    @Override
    public long getItemId(int i) { //获取每个item的id值
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //view每个item的视图对象
        //加载item的布局文件
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(myContext);//布局填充器
            view = inflater.inflate(itemLayout, null);
        }

        // 获取控件引用
        LinearLayout linearIdiom = view.findViewById(R.id.linear_idiom);
        TextView tvIdiomByType = view.findViewById(R.id.tv_idiom_by_type);

        // 设置控件内容
        tvIdiomByType.setText(idioms.get(i));

        // 设置监听器（点击某个成语，跳转到该成语的详情界面）
        linearIdiom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(myContext, IdiomInfoActivity.class);
                intent.putExtra("name",idioms.get(i));
                myContext.startActivity(intent);
            }
        });

        return view;
    }
}
