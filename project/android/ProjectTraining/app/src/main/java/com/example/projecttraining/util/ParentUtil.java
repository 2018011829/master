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
import com.example.projecttraining.contact.dao.ParentInfo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMClient;
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
    private static final String TAG = "ParentUtil";
    //创建OkHttpClient对象
    private static OkHttpClient okHttpClient = new OkHttpClient();
    //当前用户的所有联系人
    public static List<String> allContacts;
    //记录是否发送了邀请
    public static boolean isSendInvitaion = false;
    //记录是否收到新的消息，若收到，需要刷新conversationFragment
    public static boolean isMessageReceived = false;
    //记录当前联系人标签的状态，用于在受到消息时显示未读消息小红点
    public static boolean isSelectedRelationTab = false;

    /**
     * 从本地数据库查询当前聊天对象的相关信息，并存储到EaseParentUtil的相关属性中
     * @param context
     * @param phone
     */

    public static void setToChatParentInfo(Context context, String phone) {
        SQLiteDatabase sqLiteDatabase = TianTianSQLiteOpenHelper.getInstance(context).getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("parentInfos", new String[]{"avatar", "remark","nickname"}, "phone=?", new String[]{phone}, null, null, null);
        ParentInfo parentInfo = new ParentInfo();
        parentInfo.setPhone(phone);
        if (0 != cursor.getCount()) {
            cursor.moveToNext();
            parentInfo.setAvatar(cursor.getString(0));
            parentInfo.setRemark(cursor.getString(1));
            parentInfo.setNickname(cursor.getString(2));
        }
        sqLiteDatabase.close();
        EaseParentUtil.toChatUserRemark=parentInfo.getRemark();
        EaseParentUtil.toChatUserAvator=parentInfo.getAvatar();
        EaseParentUtil.tochatUserNickname=parentInfo.getNickname();
    }

    /**
     * 存储当前登录用户的信息
     * @param currentUser 手机号码
     * @param handler
     */

    public static void storeCurrentParent(String currentUser, Handler handler) {
        new Thread(){
            @Override
            public void run() {
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
        }.start();

    }

    /**
     * 根据手机号在服务端查询相关用户，供添加朋友使用
     * @param query 查询到手机号码
     * @param handler
     */

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
                        //得到集合的类型
                        Type type = new TypeToken<List<Parent>>() {
                        }.getType();
                        List<Parent> parents = gson.fromJson(json, type);
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

    /**
     * 获取当前登录用户的饿所有好友列表，并保存到sqlite数据库
     * @param currentUsername 当前登录用户的手机号码
     * @param usernames 好友的手机号码集合
     * @param context
     * @param handler
     */

    public static void storeAllContacts(String currentUsername,List<String> usernames, Context context, Handler handler) {
        Gson gson = new Gson();
        String json = gson.toJson(usernames);
        FormBody formBody = new FormBody.Builder().add("usernames", json).add("currentUsername",currentUsername).build();
        Request request = new Request.Builder().url(ConfigUtil.SERVICE_ADDRESS + "GetAllContactsServlet")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Message message = handler.obtainMessage();
                message.what = 9;
                handler.sendMessage(message);
                Looper.prepare();
                Toast.makeText(context, "当前网络不稳定", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                //得到集合的类型
                if ("" != json && null != json) {
                    Type type = new TypeToken<List<ParentInfo>>() {
                    }.getType();
                    //解决json串不合法问题，如果服务器报404错误，则返回的不是合法错误
                    try {
                        List<ParentInfo> parentInfos = gson.fromJson(json, type);
                        SQLiteDatabase sqLiteDatabase = TianTianSQLiteOpenHelper.getInstance(context).getWritableDatabase();
                        //存储当前用户的所有好友之前，先清除SQLite的Parent表
                        sqLiteDatabase.delete("parentInfos", null, null);
                        for (ParentInfo parentInfo : parentInfos) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("phone",parentInfo.getPhone());
                            contentValues.put("nickname",parentInfo.getNickname());
                            contentValues.put("avatar",ConfigUtil.SETVER_AVATAR+parentInfo.getAvatar());
                            contentValues.put("remark",parentInfo.getRemark());
                            sqLiteDatabase.insert("parentInfos", null, contentValues);
                        }
                        sqLiteDatabase.close();
                        if(null!=handler) {
                            Message message = handler.obtainMessage();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    /**
     * 获取当前登录用户的饿所有好友列表
     * @param usernames
     * @param handler
     * @param context
     */
    public static void getAllContacts(List<String> usernames, Handler handler, Context context) {
        Gson gson = new Gson();
        String json = gson.toJson(usernames);
        Log.e(TAG, "storeAllContacts: " + json);
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder().add("usernames", json).add("currentUsername", EMClient.getInstance().getCurrentUser()).build();
        Request request = new Request.Builder().url(ConfigUtil.SERVICE_ADDRESS + "GetAllContactsServlet")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Looper.prepare();
                Toast.makeText(context, "当前网络不稳定", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                //得到集合的类型
                if ("" != json && null != json) {
                    Type type = new TypeToken<List<ParentInfo>>() {
                    }.getType();
                    Log.e(TAG, "onResponse: " + json);
                    try {
                        List<ParentInfo> parentInfos = gson.fromJson(json, type);
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        message.obj = parentInfos;
                        handler.sendMessage(message);
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}
