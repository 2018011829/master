package com.example.projecttraining.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


import com.example.projecttraining.contact.dao.Parent;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.easeui.tiantiansqlite.TianTianSQLiteOpenHelper;
import com.hyphenate.easeui.utils.EaseParentUtil;

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
    private static final String TAG = "ParentUtil:shenyayu";
    //创建OkHttpClient对象
    private static OkHttpClient okHttpClient = new OkHttpClient();
    //当前登录用户的头像地址
    public static String currentUserAvatar = "";
    //当前登录用户的昵称
    public static String currenUserNickname = "";
    //聊天好友的头像地址
    public static String toChatUserAvator = "";
    //当前用户的所有联系人
    public static List<String> allContacts;

    //记录是否发送了邀请
    public static boolean isSendInvitaion = false;
    //记录是否收到新的消息，若收到，需要刷新conversationFragment
    public static boolean isMessageReceived = false;
    //记录当前联系人标签的状态，用于在受到消息时显示未读消息小红点
    public static boolean isSelectedRelationTab = false;

    /**
     * 从服务端获取所有父母的集合并通过handler发送到ui线程
     *
     * @param handler
     * @return
     */
    public static void getAllparents(Handler handler) {
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "GetAllParentsServlet")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i(TAG, "onFailure: 请求所有父母信息失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                //得到集合的类型
                Type type = new TypeToken<List<Parent>>() {
                }.getType();
                List<Parent> parents = gson.fromJson(json, type);
                //通过message通知主线程
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = parents;
            }
        });
    }

    public static void getOneParent(String phone, Handler handler) {
        new Thread() {
            @Override
            public void run() {
                FormBody formBody = new FormBody.Builder().add("phone", phone).build();
                Request request = new Request.Builder().url(ConfigUtil.SERVICE_ADDRESS + "GetOneParentInfoServlet")
                        .post(formBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    String json = response.body().string();
                    Parent parent = new Gson().fromJson(json, Parent.class);
                    //发送消息
                    Message message = handler.obtainMessage();
                    message.what = 2;
                    message.obj = parent;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    public static void storeOneParent(String phone, TianTianSQLiteOpenHelper tianTianSQLiteOpenHelper) {
        new Thread() {
            @Override
            public void run() {
                FormBody formBody = new FormBody.Builder().add("phone", phone).build();
                Request request = new Request.Builder().url(ConfigUtil.SERVICE_ADDRESS + "GetOneParentInfoServlet")
                        .post(formBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    String json = response.body().string();
                    Log.e(TAG, "run: " + json);
                    Parent parent = new Gson().fromJson(json, Parent.class);
                    //存入SQLite
                    SQLiteDatabase sqLiteDatabase = tianTianSQLiteOpenHelper.getWritableDatabase();
                    Cursor cursor = sqLiteDatabase.query("parents",
                            new String[]{"id", "phone", "password", "nickname", "avatar"},
                            "id=?",
                            new String[]{parent.getId() + ""},
                            null, null, null);
                    cursor.moveToNext();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", parent.getId());
                    contentValues.put("phone", parent.getPhone());
                    contentValues.put("nickname", parent.getNickname());
                    contentValues.put("avatar", ConfigUtil.SETVER_AVATAR + parent.getAvator());
                    contentValues.put("password", parent.getPassword());
                    sqLiteDatabase.insert("parents", null, contentValues);
                    Log.e(TAG, "run: 插入SQLite成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static Parent queryAvatarAndNicknameByPhone(Context context, String phone) {
        SQLiteDatabase sqLiteDatabase = TianTianSQLiteOpenHelper.getInstance(context).getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("parents", new String[]{"avatar", "nickname"}, "phone=?", new String[]{phone}, null, null, null);
        Parent parent = new Parent();
        parent.setPhone(phone);
        if (0 != cursor.getCount()) {
            cursor.moveToNext();
            parent.setAvator(cursor.getString(0));
            parent.setNickname(cursor.getString(1));
        }
        return parent;
    }

    public static void storeCurrentParent(String currentUser, Handler handler) {
        FormBody formBody = new FormBody.Builder().add("phone", currentUser).build();
        Request request = new Request.Builder().url(ConfigUtil.SERVICE_ADDRESS + "GetOneParentInfoServlet")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try {
                    String json = response.body().string();
                    Parent parent = new Gson().fromJson(json, Parent.class);
                    EaseParentUtil.currentUserAvatar = ConfigUtil.SETVER_AVATAR + parent.getAvator();
                    Log.e(TAG, "storeCurrentParent: " + parent.getAvator());
                    EaseParentUtil.currentUserNickname = parent.getNickname();
                    if (null != handler) {
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (JsonSyntaxException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public static void searchParentsByPhone(String query, Handler handler) {
        new Thread() {
            @Override
            public void run() {
                Log.e(TAG, "run: searchParentsByPhone" + query);
                FormBody formBody = new FormBody.Builder().add("query", query).build();
                Request request = new Request.Builder().url(ConfigUtil.SERVICE_ADDRESS + "SearchParentsByPhoneServlet")
                        .post(formBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String json = response.body().string();
                        Gson gson = new Gson();
                        Log.e(TAG, "run: " + json);
                        //得到集合的类型
                        Type type = new TypeToken<List<Parent>>() {
                        }.getType();
                        List<Parent> parents = gson.fromJson(json, type);
                        Log.e(TAG, "onResponse: 得到的搜索列表" + parents);
                        //通过message通知主线程
                        Message message = handler.obtainMessage();
                        message.what = 3;
                        message.obj = parents;
                        handler.sendMessage(message);
                    }
                });
            }
        }.start();
    }

    //获取当前登录用户的饿所有好友列表，并保存到sqlite数据库
    public static void storeAllContacts(List<String> usernames, Context context, Handler handler) {
        Gson gson = new Gson();
        String json = gson.toJson(usernames);
        FormBody formBody = new FormBody.Builder().add("usernames", json).build();
        Request request = new Request.Builder().url(ConfigUtil.SERVICE_ADDRESS + "GetAllContactsServlet")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure: 获取好友信息失败");
                Looper.prepare();
                Toast.makeText(context, "当前网络不稳定", Toast.LENGTH_SHORT).show();
                Looper.loop();
                Message message = handler.obtainMessage();
                message.what = 9;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                //得到集合的类型
                if ("" != json && null != json) {
                    Type type = new TypeToken<List<Parent>>() {
                    }.getType();
//                    List<Parent> parents = gson.fromJson(json, type);
                    //解决json串不合法问题，如果服务器报404错误，则返回的不是合法错误
                    try {
                        List<Parent> parents = gson.fromJson(json, type);
                        Log.e(TAG, "onResponse: " + parents);
                        SQLiteDatabase sqLiteDatabase = TianTianSQLiteOpenHelper.getInstance(context).getWritableDatabase();
                        //存储当前用户的所有好友之前，先清除SQLite的Parent表
                        sqLiteDatabase.delete("parents", null, null);
                        for (Parent parent : parents) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("id", parent.getId());
                            contentValues.put("phone", parent.getPhone());
                            contentValues.put("nickname", parent.getNickname());
                            contentValues.put("avatar", ConfigUtil.SETVER_AVATAR + parent.getAvator());
                            contentValues.put("password", parent.getPassword());
                            sqLiteDatabase.insert("parents", null, contentValues);
                        }
                        Log.e(TAG, "onResponse:已将当前用户的好友保存到sqlite ");
                        sqLiteDatabase.close();
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        handler.sendMessage(message);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
