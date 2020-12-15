package com.example.projecttraining.idiom.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MyFragment;
import com.example.projecttraining.idiom.activitys.IdiomInfoActivity;
import com.example.projecttraining.idiom.entity.Result;
import com.example.projecttraining.util.ConfigUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 2020-11-25
 * 2020-12-2
 * 2020-12-15
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
        RelativeLayout relativeSearch = convertView.findViewById(R.id.relative_search);
        TextView tvResult = convertView.findViewById(R.id.tv_idiom_search_result);
        //设置控件内容
        tvResult.setText(results.get(i).getName());

        //设置监听器（点击某个成语，跳转到该成语的详情界面）
        relativeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = results.get(i).getName();
                if(!MyFragment.childName.equals("") && !MyFragment.phoneNum.equals("")){
                    saveSearchIdiomHistory(ConfigUtil.SERVICE_ADDRESS + "SaveSearchIdiomHistoryServlet",name, MyFragment.phoneNum,MyFragment.childName);
                }
                Intent intent = new Intent();
                intent.setClass(myContext, IdiomInfoActivity.class);
                intent.putExtra("name",name);
                myContext.startActivity(intent);
            }
        });

        return convertView;
    }

    /**
     * 向客户端发送客户搜索的成语，生成搜索历史
     * @param url
     * @param name
     * @param phone
     * @param childName
     */
    private void saveSearchIdiomHistory(String url, String name,String phone,String childName) {
        new Thread(){
            @Override
            public void run() {
                try {
                    //创建URL对象
                    URL urlPath = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) urlPath.openConnection();
                    //设置网络请求方式为POST
                    conn.setRequestMethod("POST");
                    //获取网络输出流
                    OutputStream out = conn.getOutputStream();
                    String str = phone + "&" + childName + "&" + name;
                    Log.e("lrf_生成搜索历史相关信息",str);
                    out.write(str.getBytes());
                    //必须要获取网络输入流，保证客户端和服务端建立连接
                    conn.getInputStream();
                    //关闭流
                    out.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
