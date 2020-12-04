package com.example.projecttraining.mine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentResolver;
import android.content.Intent;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.example.projecttraining.R;
import com.example.projecttraining.util.ConfigUtil;


import org.jetbrains.annotations.NotNull;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EditorParentActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_sex;
    private ImageView iv_head;
    private List<String> sexs = new ArrayList<>();
    private PopupWindow popupWindow;
    private WheelView wheelView;
    private Button btn_commit;
    private TextView tv_ok;
    private TextView tv_cancle;
    private TextView tv_mine_sex;
    private EditText edt_name;
    private String sex;
    private String nickName;
    private String head;
    private String phone;
    private Bitmap bitmap;
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    upLoadParentMessage();
                    break;
                case 2:
                    Toast.makeText(EditorParentActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_editor_parent);

        //给界面添加返回按钮相关代码
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();

        sex = intent.getStringExtra("sex");
        head = intent.getStringExtra("head");
        nickName = intent.getStringExtra("nickName");
        phone = intent.getStringExtra("phone");

        findViews();
        initData();
    }

    //给界面添加返回按钮相关代码
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {

        sexs.add("男");
        sexs.add("女");

        tv_mine_sex.setText(sex);
        edt_name.setText(nickName);

        Glide.with(this)
                .load(ConfigUtil.SERVICE_ADDRESS+"headportraitimgs/"+head)
                .circleCrop()
                .into(iv_head);
    }

    private void findViews() {
        ll_sex = findViewById(R.id.ll_mine_editorParent_sex);
        iv_head = findViewById(R.id.iv_mine_editorParent_head);
        tv_mine_sex = findViewById(R.id.tv_mine_edit_sex);
        edt_name = findViewById(R.id.edt_mine_editorName);
        btn_commit = findViewById(R.id.btn_mine_editorCommit);

        ll_sex.setOnClickListener(this);
        iv_head.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_mine_editorParent_sex:
                showPopupwindow();
                break;
            case R.id.iv_mine_editorParent_head:
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 2);
                break;
            case R.id.btn_mine_editorCommit:
                Intent result = new Intent();
                sex = tv_mine_sex.getText().toString();
                nickName = edt_name.getText().toString();
                if(bitmap!=null){
                    upBitmap(bitmap);
                }else {
                    upLoadParentMessage();
                }
                setResult(200,result);
                break;
        }
    }

    private void upBitmap(final Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        final byte[] bytes = baos.toByteArray();
        new Thread() {
            @Override
            public void run() {
                //获得网络流
                try {
                    URL url = new URL(ConfigUtil.SERVICE_ADDRESS + "DownLoadHeadPortraitServlet");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //设置网络请求的方式为POST
                    conn.setRequestMethod("POST");
                    OutputStream outputStream = conn.getOutputStream();
                    BufferedOutputStream out = new BufferedOutputStream(outputStream);
                    Log.e("bitmap", "run: 上传图片，建立连接");
                    out.write(bytes);
                    out.flush();
                    Log.e("图片touxiang上传", "run: 完成");
                    conn.getInputStream();
                    outputStream.close();
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //输出数据输出流
                //写数据
            }
        }.start();
    }

    private void upLoadParentMessage() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", phone);
        builder.add("sex",sex);
        builder.add("nickName",nickName);
        builder.add("headName",head);
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "DownLoadMessageServlet")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("家长信息更新", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
                Log.i("result", result);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                Log.i("lsq:",uri.toString());

                //使用content的接口
                ContentResolver cr = this.getContentResolver();
                Cursor cursor = cr.query(uri, null, null, null, null);
                cursor.moveToFirst();
                String imgName = cursor.getString(3); //图片文件名
                Log.e("图片的文件名：", "onActivityResult: " + imgName);
                try {
                    //获取图片
                    bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

                    Glide.with(this)
                        .load(bitmap)
                        .circleCrop()
                        .into(iv_head);
                } catch (FileNotFoundException e) {
                    Log.e("Exception", e.getMessage(), e);
                }
            } else {
                //操作错误或没有选择图片
                Log.e("myFragmetn", "operation error");
            }
        }
    }


    /*
     * 利用pupopwindow实现wheelview(滚轮选择器)的点击弹出效果
     * */
    private void showPopupwindow() {
        //创建popupwindow
        popupWindow = new PopupWindow(this);
        //设置popupwindow显示的宽度（默认不占满屏幕）
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //加载popupwindow布局
        View view = getLayoutInflater().inflate(R.layout.activity_mine_child_popupwindow, null);

        /*
         * 加载popupwindow布局文件
         * 给布局文件设置相应监听器
         * */
        wheelView = view.findViewById(R.id.wheelview);
        wheelView.setCyclic(false); //设置不可循环滚动
        setWheelView(wheelView);//设置wheelview参数

        tv_ok = view.findViewById(R.id.tv_relation_ok);
        tv_cancle = view.findViewById(R.id.tv_relation_cancle);

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_mine_sex.setText(sex);
                popupWindow.dismiss();
            }
        });

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        //加载根布局（popupwindow要显示在其上面）
        LinearLayout root = findViewById(R.id.editor_root);
        //为popupwindow绑定布局
        popupWindow.setContentView(view);
        //设置popupwindow显示的位置
        popupWindow.showAtLocation(root, Gravity.CENTER, 0, 0);//指定显示的位置
    }

    /*
     * 给wheelview设置相关参数
     * */
    private void setWheelView(WheelView wheelView){
        //设置数据源
        wheelView.setAdapter(new WheelAdapter() {
                @Override
                public int getItemsCount() {
                    return sexs.size();
                }

                @Override
                public Object getItem(int index) {
                    return sexs.get(index);
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }
        });


        //添加数据源的每一个item被选中时的监听器
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                sex = sexs.get(index);
            }
        });

    }
}