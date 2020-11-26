package com.example.projecttraining.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.example.projecttraining.contact.Parent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ParentUtil {
    private static final String TAG="ParentUtil:shenyayu";
    //创建OkHttpClient对象
    private static OkHttpClient okHttpClient=new OkHttpClient();


    /**
     * 从服务端获取所有父母的集合并通过handler发送到ui线程
     * @param handler
     * @return
     */
    public static void getAllparents(Handler handler){
        Request request=new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS+"GetAllParentsServlet")
                .build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i(TAG, "onFailure: 请求所有父母信息失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json=response.body().string();
                Gson gson=new Gson();
                //得到集合的类型
                Type type=new TypeToken<List<Parent>>(){}.getType();
                List<Parent> parents=gson.fromJson(json,type);
                //通过message通知主线程
                Message message=handler.obtainMessage();
                message.what=1;
                message.obj=parents;
            }
        });
    }
    public static void getOneParent(String phone,Handler handler){
        FormBody formBody=new FormBody.Builder().add("phone",phone).build();
        Request request=new Request.Builder().url(ConfigUtil.SERVICE_ADDRESS+"GetOneParentInfoServlet")
                .post(formBody)
                .build();
        Call call=okHttpClient.newCall(request);
        try {
            Response response=call.execute();
            String json=response.body().string();
            Parent parent=new Gson().fromJson(json,Parent.class);
            Message message=handler.obtainMessage();
            message.what=2;
            message.obj=parent;
            handler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
