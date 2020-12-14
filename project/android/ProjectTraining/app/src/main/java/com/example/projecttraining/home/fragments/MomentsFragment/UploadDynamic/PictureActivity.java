package com.example.projecttraining.home.fragments.MomentsFragment.UploadDynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.util.ConfigUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PictureActivity extends AppCompatActivity {

    private ImageView iv;
    private Intent intent;
    private String pic;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1://暂存照片
                    String result= (String) msg.obj;
                    if (result.equals("success")){
                        //暂存成功
                        sendPictureToServer(pic);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        intent = getIntent();
        String pictureUrl = intent.getStringExtra("pictureUrl");

        iv=findViewById(R.id.iv);
        Glide.with(PictureActivity.this)
                .load(pictureUrl)
                .into(iv);
        Log.e("lzz",pictureUrl);

        //暂时保存照片
        pursePictureToServer(pictureUrl);
    }

    //向服务端发送图片
    private void sendPictureToServer(String urlPath) {
        final String path= ConfigUtil.SERVICE_ADDRESS+"MomentsInfoServlet";
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL(path);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    OutputStream os=conn.getOutputStream();
                    InputStream inputStream=new FileInputStream(urlPath);
                    int b=-1;
                    while ((b=inputStream.read())!=-1){
                        os.write(b);
                    }
                    conn.getInputStream();
                    os.flush();
                    os.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 暂时保存照片
     * @param urlPath
     */
    private void pursePictureToServer(String urlPath) {
        new Thread(){
            @Override
            public void run() {
                String fPath=getFilesDir().getAbsolutePath()+"/pi.png";
                pic=fPath;
                try {
                    InputStream inputStream=new FileInputStream(urlPath);
                    OutputStream os=new FileOutputStream(fPath);
                    int b=-1;
                    while ((b=inputStream.read())!=-1){
                        os.write(b);
                    }
                    os.flush();
                    os.close();
                    Message msg=new Message();
                    msg.what=1;
                    msg.obj="success";
                    handler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}