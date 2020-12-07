package com.example.projecttraining.idiom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projecttraining.R;
import com.example.projecttraining.idiom.activitys.IdiomByTypeActivity;
import com.example.projecttraining.idiom.activitys.IdiomInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 2020-12-7
 * @author lrf
 */
public class IdiomByTypeNameAdapter extends BaseAdapter {
    private Context myContext;
    private List<String> idioms = new ArrayList<>();
    private int itemLayout;

    public IdiomByTypeNameAdapter(Context myContext, List<String> idioms, int itemLayout) {
        this.myContext = myContext;
        this.idioms = idioms;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() { // 获得数据的条数
        if (null != idioms) {
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
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(myContext);//布局填充器
            view = inflater.inflate(itemLayout, null);
        }

        // 获取控件引用
        TextView tvIdiomByType = view.findViewById(R.id.tv_idiom_by_type);

        // 设置控件内容
        tvIdiomByType.setText(idioms.get(i));

        // 设置监听器（点击某个成语类型，跳转到该成语类型的界面）
        tvIdiomByType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(myContext, IdiomByTypeActivity.class);
                intent.putExtra("idiomType", idioms.get(i));
                myContext.startActivity(intent);
                Toast.makeText(myContext, idioms.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
