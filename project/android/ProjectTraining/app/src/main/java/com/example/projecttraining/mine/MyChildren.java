package com.example.projecttraining.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.R;
import com.example.projecttraining.mine.adapter.MyChildrenAdapter;
import com.example.projecttraining.mine.entity.Child;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyChildren extends AppCompatActivity {
    private ListView listView;
    private MyChildrenAdapter adapter;
    private Button btn_mine_select_ok;
    private List<Child> children = new ArrayList<>();
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    try {
                        JSONObject jChildren = new JSONObject((String) msg.obj);
                        JSONArray jArray = jChildren.getJSONArray("children");
                        for(int i= 0;i<jArray.length();i++){
                            String json = jArray.getJSONObject(i).toString();
                            Log.i("json", json);
                            Child child = new Gson().fromJson(json, Child.class);
                            children.add(child);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    initListView();
                    break;
                case 2:
                    listView.setAdapter(adapter);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_mychildren);
        children.clear();
        queryChildren();
        btn_mine_select_ok = findViewById(R.id.btn_mine_select_ok);
        btn_mine_select_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyChildren.this,"选择成功！",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    private void initListView() {
        listView = findViewById(R.id.lv_mine_child);
        adapter = new MyChildrenAdapter(this,children,handler);
        listView.setAdapter(adapter);
    }

    private void queryChildren() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", EMClient.getInstance().getCurrentUser());
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "QueryChildrenServlet")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("查询孩子信息", "请求失败");
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
}
