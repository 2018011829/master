package com.example.projecttraining.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MyFragment;
import com.example.projecttraining.idiom.FlowLayout;
import com.example.projecttraining.idiom.activitys.IdiomInfoActivity;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMClient;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IdiomFragment extends Fragment {
    private View view;
    private Gson gson;
    private LinearLayout idiomSaved;
    private List<String> idiomList = new ArrayList<>();
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    String json = (String) msg.obj;
                    // 得到集合类型
                    Type type = new TypeToken<List<String>>(){}.getType();
                    // 反序列化
                    idiomList = gson.fromJson(json,type);
                    Log.e("lrf_收藏的成语",idiomList.toString());

                    if(!idiomList.isEmpty()){
                        FlowLayout flowLayout = new FlowLayout(getContext());
                        flowLayout.setPadding(15,20,15,20);
                        flowLayout.setVerticalSpacing(40);
                        flowLayout.setHorizontalSpacing(30);
                        idiomSaved.addView(flowLayout);
                        for(int i = 0; i < idiomList.size(); ++i){
                            final Button button = new Button(getContext());
                            button.setId(i);
                            button.setTextSize(17);
                            button.setText(idiomList.get(i));
                            button.setBackgroundResource(R.drawable.black_border);
                            button.setPadding(20,10,20,10);
                            //添加到布局文件中去
                            flowLayout.addView(button);
                            //更新界面
                            flowLayout.invalidate();
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String idiomName = button.getText().toString();
                                    Intent intent = new Intent();
                                    intent.setClass(getContext(), IdiomInfoActivity.class);
                                    intent.putExtra("name",idiomName);
                                    getContext().startActivity(intent);
                                    Toast.makeText(getContext(),idiomName,Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }else {
                        ImageView imageView = new ImageView(getContext());
                        imageView.setImageResource(R.drawable.mine_collection_null);
                        idiomSaved.addView(imageView);
                    }
                    break;
            }
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        idiomList.clear();
        idiomSaved.removeAllViews();
        //从服务端获取收藏的成语信息
        searchSaveIdiomByInfo();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_mine_collectionidiom,container,false);
        idiomSaved = view.findViewById(R.id.idiom_saved);
        //Gson对象实例化
        initGson();
        return view;
    }

    private void searchSaveIdiomByInfo() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", EMClient.getInstance().getCurrentUser());
        builder.add("cname", MyFragment.childName);
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "SearchSaveIdiomByInfoServlet")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("查询收藏成语信息", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = new Message();
                message.obj = result;
                message.what = 1;
                handler.sendMessage(message);
                Log.i("result", result);
            }
        });
    }

    private void initGson() {
        //允许配置参数的Gson对象初始化
        gson = new GsonBuilder()//创建GsonBuilder对象
                .setPrettyPrinting()//格式化输出
                .serializeNulls()//允许输出Null值属性
                .create();//创建Gson对象
    }
}
