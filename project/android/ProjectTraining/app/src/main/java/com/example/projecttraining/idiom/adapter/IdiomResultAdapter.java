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
import com.example.projecttraining.idiom.activitys.IdiomInfoActivity;
import com.example.projecttraining.idiom.entity.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * 2020-11-25
 * 2020-12-2
 * @author lrf
 */
public class IdiomResultAdapter extends BaseAdapter {
    private Context myContext;
    private List<Result> results = new ArrayList<>();
    private int itemLayoutRes;

    public IdiomResultAdapter(Context myContext, List<Result> results, int itemLayoutRes) {
        this.myContext = myContext;
        this.results = results;
        this.itemLayoutRes = itemLayoutRes;
    }

    @Override
    public int getCount() {//获得数据的条数
        if(null != results){
            return results.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {//获取每个item显示的数据对象
        if(null != results){
            return results.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {//获取每个item的id值
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        //convertView每个item的视图对象
        //加载item的布局文件
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(myContext);//布局填充器
            convertView = inflater.inflate(itemLayoutRes, null);
        }

        //获取item中控件的引用
        TextView tvResult = convertView.findViewById(R.id.tv_idiom_search_result);
        //设置控件内容
        tvResult.setText(results.get(i).getName());

        //设置监听器（点击某个成语，跳转到该成语的详情界面）
        tvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(myContext, IdiomInfoActivity.class);
                String name = results.get(i).getName();
                Toast.makeText(myContext,name,Toast.LENGTH_SHORT).show();
                intent.putExtra("name",name);
                myContext.startActivity(intent);
            }
        });

        return convertView;
    }
}
