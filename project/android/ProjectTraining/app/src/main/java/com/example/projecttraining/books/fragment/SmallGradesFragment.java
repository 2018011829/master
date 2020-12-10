package com.example.projecttraining.books.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;
import com.example.projecttraining.books.adapters.BooksHomeAdapter;
import com.example.projecttraining.books.entitys.Book;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SmallGradesFragment extends Fragment {

    private View view;
    private ProgressBar pb; //进度条
    private ListView bookHomepageList;
    private TreeMap<String, List<Book>> treeMap;//数据源
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1://得到所有图书的Gson字符串，将字符串转换成装有图书的集合
                    String strGsonBooks= (String) msg.obj;
                    //解析Gson串
                    //集合反序列化
                    //获取集合类型
                    Type type=new TypeToken<TreeMap<String, List<Book>>>(){}.getType();
                    //将字符串转换成数据
                    treeMap=new Gson().fromJson(strGsonBooks,type);
                    Log.e("treeMap",treeMap.toString());
                    //初始化控件
                    initViews();
                    pb.setVisibility(View.INVISIBLE);
                    break;
                case 2: //显示错误信息
                    String errorInfo= (String) msg.obj;
                    Toast.makeText(view.getContext(),
                            errorInfo, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.book_small_grades_fragment,container,false);
        pb=view.findViewById(R.id.progressbar);
        pb.setVisibility(View.VISIBLE);
        bookHomepageList=view.findViewById(R.id.book_homepage_list);
        //从服务端获取数据
        getDataFromServer();

        return view;
    }


    /**
     * 初始化控件
     */
    private void initViews() {
        //adapter
        BooksHomeAdapter adapter=new BooksHomeAdapter(view.getContext(),
                R.layout.book_homepage_listitem,treeMap);
        //绑定adapter
        bookHomepageList.setAdapter(adapter);
    }

    /**
     * 从服务端获取数据
     */
    private void getDataFromServer() {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient=new OkHttpClient();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS
                        + "BookHomePageServlet?info=small")
                .build();
        //创建CALL对象
        Call call = okHttpClient.newCall(request);
        //异步方式提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("BooksHomePageActivity", "请求失败");
                Message msg = handler.obtainMessage();
                msg.what = 2;
                msg.obj = "书籍信息获取失败！";
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //获取服务端返回的数据（假设是字符串）
                String result = response.body().string();
//                Log.e("BooksHomePageActivity", result);

                //通知界面信息改变
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        });
    }

}
