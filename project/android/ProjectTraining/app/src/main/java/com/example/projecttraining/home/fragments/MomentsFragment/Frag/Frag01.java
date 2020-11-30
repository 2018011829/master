package com.example.projecttraining.home.fragments.MomentsFragment.Frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MomentsFragment.Adapter.Frag01Adapter;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.Moments;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class Frag01 extends Fragment {
    private List<Moments> moments = new ArrayList<>();//动态类列表
    private ListView momentsListView;  //ListView对象
    private SmartRefreshLayout srl;    //智能刷新控件对象
    private Frag01Adapter frag01Adapter;
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //为了防止第二次加载的时候重复调用这个方法的onCreateView(),重新new了一个pagedapter导致子fragment不显示，显示空白
        if (view!=null){
            ViewGroup parent = (ViewGroup) view.getParent();
            if(parent !=null){
                parent.removeView(view);
            }
            return view;
        }
        // 添加布局文件
        view=inflater.inflate(R.layout.fragment_moments_frag01, container, false);
//        initDate(view);//准备数据
//        initView(moments,view);//配置adapter
        momentsListView = view.findViewById(R.id.lv_moments_list);
        //初始化数据
        moments = initData(view);
        //实例化Adapter
        frag01Adapter = new Frag01Adapter(getContext(),moments,R.layout.fragment_moments_frag01_item);
        momentsListView.setAdapter(frag01Adapter);
        //给ListView注册监听器
        momentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Moments moment = moments.get(position);
                Toast.makeText(
                        getContext(),
                        moment.getName() + ":" + moment.getContent(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        srl = view.findViewById(R.id.srl);
        //设置Header和Footer的样式
        //创建Header样式对象
//        WaveSwipeHeader header = new WaveSwipeHeader(this);
//        srl.setRefreshHeader(header);
        //创建Footer样式
        FalsifyFooter footer = new FalsifyFooter(getContext());
        //设置Footer样式
        srl.setRefreshFooter(footer);
        //设置回弹时间
        srl.setReboundDuration(100);

        //给智能刷新控件注册下拉刷新事件监听器
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData(view);
                //通知刷新完毕
                srl.finishRefresh();
            }
        });
        //给智能刷新控件注册上拉加载更多事件监听器
        srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多数据(假设超过10条数据则加载完毕）
                if(moments.size() < 10) {
                    loadMoreData();
                    //通知加载数据完毕
                    srl.finishLoadMore();
                } else {
                    //通知没有更多数据可加载
                    srl.finishLoadMoreWithNoMoreData();
                }
            }
        });
        return view;
    }
    //准备数据
    public List<Moments> initData(View view){
        List<Moments> list = new ArrayList<>();
        moments.clear();
        List<String> pictureUrl = new ArrayList<>();
        String url1 = "321";
        pictureUrl.add(url1);
        Moments moment = new Moments("123","李仕奇","气死本仕奇了",pictureUrl);//测试数据
        moments.add(moment);

        return list;
    }
    /**
     * 模拟加载更多数据
     */
    private void loadMoreData() {
        for(int i = 0; i < 5; i++){
            Moments moment = new Moments();
            moment.setName("昵称" + (int)(Math.random()*100));
            moment.setContent("我就是这么强大" + (int)(Math.random()*100));
            moments.add(moment);
        }
        frag01Adapter.notifyDataSetChanged();
    }
    /**
     * 刷新
     */
    private void refreshData(View view) {
        moments.clear();
        moments.addAll(initData(view));//stus = initData()
        frag01Adapter.notifyDataSetChanged();
    }

    //配置adapter
    private void initView(List<Moments> moments,View view) {
        frag01Adapter = new Frag01Adapter(getContext(), moments, R.layout.fragment_moments_frag01_item);
        momentsListView = view.findViewById(R.id.lv_moments_list);
        momentsListView.setAdapter(frag01Adapter);
    }
}
