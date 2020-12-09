package com.example.projecttraining.home.fragments.MomentsFragment.UploadDynamic;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.Moments;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UploadDynamic extends AppCompatActivity {
    private static final String TAG = "UploadDynamic";
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.recycler)
    RecyclerView recycler;//RecyclerView对象（九宫格）
    private SelectPlotAdapter adapter;
    private ArrayList<String> allSelectList;//所有的图片集合
    private ArrayList<String> categoryLists;//查看图片集合
    private List<LocalMedia> selectList = new ArrayList<>();
    private ImageView ivReturnMoments;
    private OkHttpClient okHttpClient;//定义OKHTTPClient对象属性
    private Handler handler;//定义Handler对象属性
    private Gson gson;//定义Gson对象属性

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_moments_frag01_upload_dynamic);
        ButterKnife.bind(this);
        if (null == allSelectList) {
            allSelectList = new ArrayList();
        }
        if (null == categoryLists) {
            categoryLists = new ArrayList();
        }
        Tools.requestPermissions(UploadDynamic.this);
        initAdapter();

        initOkHttpClient();//初始化OKHTTPClient对象
        initHandler();//初始化Handler对象
        initGson();//初始化Gson
    }
    /**
     * 初始化Gson对象
     */
    private void initGson() {
        gson = new GsonBuilder()//创建GsonBuilder对象
                .setPrettyPrinting()//格式化输出
                .serializeNulls()//允许输出Null值属性
                .setDateFormat("YY:MM:dd")//日期格式化
                .create();//创建Gson对象
    }
    /**
     * 初始化OKHTTPClient对象
     */
    private void initOkHttpClient() {
        okHttpClient = new OkHttpClient();
    }
    /**
     * 初始化Handler对象
     */
    private void initHandler() {
        handler = new Handler(){//handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 1://如果服务端返回的数据是字符串
                    case 2:
                    case 3:
                        break;
                }
            }
        };
    }
    private void initAdapter() {
        //最多9张图片
        adapter = new SelectPlotAdapter(this, 9);//定义adapter对象
        recycler.setLayoutManager(new GridLayoutManager(this, 3));
        adapter.setImageList(allSelectList);//所有图片的集合
        recycler.setAdapter(adapter);
        adapter.setListener(new SelectPlotAdapter.CallbackListener() {
            @Override
            public void add() {
                //可添加的最大张数=9-当前已选的张数
                int size = 9 - allSelectList.size();
                Tools.galleryPictures(UploadDynamic.this, size);
                //Tools.openGallery(UploadDynamic.this,size);
                //Tools.takingPictures(UploadDynamic.this);
            }

            @Override
            public void delete(int position) {
                allSelectList.remove(position);
                categoryLists.remove(position);
                adapter.setImageList(allSelectList);//再set所有集合
            }

            @Override
            public void item(int position, List<String> mList) {
                selectList.clear();
                for (int i = 0; i < allSelectList.size(); i++) {
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setPath(allSelectList.get(i));
                    selectList.add(localMedia);
                }
                //①、图片选择器自带预览
                PictureSelector.create(UploadDynamic.this)
                        .themeStyle(R.style.picture_default_style)
                        .isNotPreviewDownload(true)//是否显示保存弹框
                        .imageEngine(GlideEngine.createGlideEngine()) // 选择器展示不出图片则添加
                        .openExternalPreview(position, selectList);
                //②:自定义布局预览
                Tools.startPhotoViewActivity(UploadDynamic.this, categoryLists, position);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 结果回调
            selectList = PictureSelector.obtainMultipleResult(data);
            showSelectPic(selectList);
        }
    }
    private void showSelectPic(List<LocalMedia> result) {
        for (int i = 0; i < result.size(); i++) {
            String path;
            //判断是否10.0以上
            if (Build.VERSION.SDK_INT >= 29) {
                path = result.get(i).getAndroidQToPath();
            } else {
                path = result.get(i).getPath();
            }
            allSelectList.add(path);
            categoryLists.add(path);
            Log.e(TAG, "图片链接: " + path);
        }
        adapter.setImageList(allSelectList);
    }

    //设置点击事件
    @OnClick({R.id.upload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upload:
                String content = edit.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(this, "请输入上传内容", Toast.LENGTH_LONG).show();
                    return;
                }
                if (allSelectList.size() == 0) {
                    Toast.makeText(this, "请选择图片进行上传", Toast.LENGTH_LONG).show();
                    return;
                }
                Log.e(TAG, "内容: " + content);
                Log.e(TAG, "图片: " + allSelectList.toString());
                sendTimeStringToServer();//发送当前时间字符串
                sendContentToServer(content);//发送文本
                String pictureUrl = allSelectList.toString().substring(1,allSelectList.toString().length()-1);//图片路径集合
                List<String> pictureUrls = Arrays.asList(pictureUrl.split(", "));//将图片路径集合分割开
                for(int i=0;i<pictureUrls.size();i++){
                    sendPictureToServer(pictureUrls.get(i));//循环发送多张图片
                }
                Toast.makeText(UploadDynamic.this,"上传成功",Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(UploadDynamic.this,MainActivity.class);
//                startActivity(intent);
                break;
        }
    }

    //加载个人信息的布局文件
    private String getPersonalPhone(){
        LayoutInflater mInflater = (LayoutInflater)this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        View contentView  = mInflater.inflate(R.layout.fragment_my,null);
        TextView text = (TextView)contentView.findViewById(R.id.tv_mine_phone);
        Log.e("lzz",text.getText().toString());
        return text.getText().toString();
    }

    /**
     * 采用POST请求方式说说信息
     */
    private void sendContentToServer(String content) {
        //2 创建Request对象
        //1) 使用RequestBody封装请求数据
        //获取待传输数据对应的MIME类型
        MediaType type = MediaType.parse("text/plain");
        //创建FormBody对象
        Moments moments = new Moments(content);
        //序列化
        String json = gson.toJson(allSelectList);
        FormBody formBody =
                new FormBody.Builder()
                        .add("content",content)
                        .build();
        //2) 创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS +"MomentsContentServlet")
                .post(formBody)
                .build();
        //3. 创建CALL对象
        Call call = okHttpClient.newCall(request);
        //4. 提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("lww", "请求失败");
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        });
    }


    //向服务端发送图片
    private void sendPictureToServer(String urlPath) {
        final String path=ConfigUtil.SERVICE_ADDRESS+"MomentsPicturesServlet";
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
     * 采用POST请求方式提交当前时间的字符串用于区分动态
     */
    private void sendTimeStringToServer() {
        //2 创建Request对象
        //1) 使用RequestBody封装请求数据
        //获取待传输数据对应的MIME类型
        MediaType type = MediaType.parse("text/plain");
        //创建FormBody对象
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String personalPhone = getPersonalPhone();
        //序列化
        FormBody formBody =
                new FormBody.Builder()
                        .add("time",formatter.format(date))
                        .add("personalPhone",personalPhone)
                        .build();
        //2) 创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS +"TimeStringServlet")
                .post(formBody)
                .build();
        //3. 创建CALL对象
        Call call = okHttpClient.newCall(request);
        //4. 提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("lww", "请求失败");
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        });
    }


}
