package com.example.projecttraining.home.fragments.MomentsFragment.UploadDynamic;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecttraining.MainActivity;
import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.Moments;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.PersonalInfo;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
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

//        //设置点击事件返回到moments
//        ivReturnMoments = findViewById(R.id.iv_return_moments);
//        ivReturnMoments.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent();
//                i.setClass(UploadDynamic.this, MainActivity.class);
//                //一定要指定是第几个pager，因为要跳到ThreeFragment，这里填写2
//                i.putExtra("id",2);
//                startActivity(i);
//            }
//        });
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
                //Tools.startPhotoViewActivity(MainActivity.this, categoryLists, position);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 结果回调
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
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
                //传递内容和图片
                simpStringParamPostRequest(content);
                Intent intent = new Intent(UploadDynamic.this,MainActivity.class);
                String item = intent.getStringExtra("item");
                startActivity(intent);
                break;
        }
    }
    /**
     * 采用POST请求方式提交个人信息
     */
    private void simpStringParamPostRequest(String content) {
        //2 创建Request对象
        //1) 使用RequestBody封装请求数据
        //获取待传输数据对应的MIME类型
        MediaType type = MediaType.parse("text/plain");
        //创建FormBody对象
        Moments moments = new Moments(content,allSelectList);
        //序列化
        String json = gson.toJson(allSelectList);
        FormBody formBody =
                new FormBody.Builder()
                        .add("content",content)
                        .add("pictueUrl",json)
                        .build();
        //2) 创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "MomentsInfoServlet")
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
