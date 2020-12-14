package com.hyphenate.easeui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.tiantiansqlite.TianTianSQLiteOpenHelper;
import com.hyphenate.easeui.utils.EaseParentUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EditRemarkActivity extends AppCompatActivity {
    private final String TAG="EditRemarkActivity";
    private String username;
    private EditText etRemark;
    private Intent intent;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==0){
                Log.e(TAG, "handleMessage:"+msg.what);
                //结束
                EaseParentUtil.isContactAddedOrDeleted=true;
                //将本地数据库的备注修改,返回消息界面时，该联系人的历史消息备注也可以被更改
                TianTianSQLiteOpenHelper tianTianSQLiteOpenHelper=TianTianSQLiteOpenHelper.getInstance(getApplicationContext());
                SQLiteDatabase sqLiteDatabase=tianTianSQLiteOpenHelper.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                contentValues.put("remark",etRemark.getText().toString());
                sqLiteDatabase.update("parentInfos",contentValues,"phone="+username,null);
                sqLiteDatabase.close();
                //结束当前页面
                intent.putExtra("remark",etRemark.getText().toString());
                setResult(1,intent);
                finish();
                Toast.makeText(EditRemarkActivity.this, "修改成功", Toast.LENGTH_SHORT).show();

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_remark);
        intent=getIntent();
        username=intent.getStringExtra("username");
        String currentRemark=intent.getStringExtra("currentRemark");
        Log.e(TAG, "onCreate: "+username+currentRemark);
        etRemark=findViewById(R.id.et_remark);
        etRemark.setText(currentRemark);
        findViewById(R.id.iv_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //向服务端发消息，改变数据库
                changeRemark(username,etRemark.getText().toString().trim(),handler);
            }
        });
    }

    private void changeRemark(String username, String remark, final Handler handler){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody formBody=new FormBody.Builder()
                .add("fromPhone", EMClient.getInstance().getCurrentUser())
                .add("toPhone",username)
                .add("remark",remark).build();
        Request request=new Request.Builder().post(formBody).url(EaseParentUtil.SERVICE_ADDRESS+"ChangeRemarkServlet").build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.body().string().equals("success")){
                Message message=handler.obtainMessage();
                message.what=0;
                handler.sendMessage(message);
                }else{
                    Looper.prepare();
                    Toast.makeText(EditRemarkActivity.this, "修改失败，请重试", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        });
    }
}
