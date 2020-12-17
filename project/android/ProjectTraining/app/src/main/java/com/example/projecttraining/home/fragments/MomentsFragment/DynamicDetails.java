package com.example.projecttraining.home.fragments.MomentsFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MomentsFragment.Adapter.CommentExpandAdapter;
import com.example.projecttraining.home.fragments.MomentsFragment.Adapter.MyGridViewAdapter;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.Comment;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.CommentBean;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.CommentDetailBean;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.Moments;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.ReplyDetailBean;
import com.example.projecttraining.home.fragments.MomentsFragment.View.CommentExpandableListView;
import com.example.projecttraining.home.fragments.MomentsFragment.View.PictureGridView;
import com.example.projecttraining.util.ConfigUtil;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyphenate.chat.EMClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DynamicDetails extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private Moments moment;//传递过来的一个moment对象
    private Toolbar toolbar;
    private TextView bt_comment;//评论的按钮
    private CommentExpandableListView expandableListView;
    private CommentExpandAdapter adapter;
    private CommentBean commentBean;
    private List<CommentDetailBean> commentsList = new ArrayList<>();//评论的列表
    private List<ReplyDetailBean> replyBeanList = new ArrayList<>();//回复的列表
    private BottomSheetDialog dialog;
    private Intent intent;//跳转页面传递数据的intent对象
    private Gson gson;// 定义Gson对象属性
    private OkHttpClient okHttpClient;//定义OKHTTPClient对象属性
    private Handler handler;//定义Handler对象属性
    private int momentsId = 0;
    private ImageView detail_page_userLogo;//头像
    private TextView detail_page_story;//文案
    private TextView detail_page_userName;//昵称
    private TextView tvLikeNum;//点赞数
    //初始化Handler对象
    private void initHandler() {
        handler = new Handler(){//handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        commentsList.clear();
                        replyBeanList.clear();
                        String json = (String) msg.obj;//接收到的是一个说说对象
                        Log.e("jso",json);
                        Moments moment = gson.fromJson(json, Moments.class);//说说对象反序列化
                        commentsList = generateTestData(moment);
                        replyBeanList = replyData(moment);
                        initExpandableListView(commentsList,replyBeanList);

                        Glide.with(DynamicDetails.this)
                                .load(moment.getHeadPortraitUrl())
                                .circleCrop()
                                .into(detail_page_userLogo);
                        detail_page_story.setText(moment.getContent());
                        detail_page_userName.setText(moment.getName());

                        if(!moment.getLikeGiveName().equals("")){
                            if(!moment.getLikeGiveName().equals("没有人点赞呦")){
                                String[] likeNum =  gson.fromJson(moment.getLikeGiveName(), String[].class);//设置点赞个数
                                tvLikeNum.setText(likeNum.length+"");
                            }else {
                                tvLikeNum.setText("");
                            }
                        }
                        List<String> pictureUrl = new ArrayList<>();//图片列表
                        List<String> list = new ArrayList<>();//临时字符串列表，用来存放带引号的图片路径
                        String img = String.valueOf(moment.getPictureUrl());//说说对象中的图片部分
                        String imgs = img.substring(1, img.length()-2);//将图片对象去掉头部和尾部的中括号
                        list = Arrays.asList(imgs.split(","));//将图片部分按逗号分隔开
                        for(int j=0;j<list.size();j++){
                            String str = list.get(j).substring(4,list.get(j).length()-1);//将带引号的图片路径去掉引号
                            pictureUrl.add(str);
                        }

                        PictureGridView gridview = (PictureGridView)findViewById(R.id.gridView);
                        //根据图片个数创建布局
                        int num = pictureUrl.size();//获取当前的图片数目
                        int col = 1;//默认列数
                        if (num == 1) {
                            gridview.setNumColumns(1);
                            col = 1;
                        } else if (num == 2 || num == 4) {
                            gridview.setNumColumns(2);
                            col = 2;
                        } else {
                            gridview.setNumColumns(3);
                            col = 3;
                        }

                        View view = new View(DynamicDetails.this);
                        gridview.setAdapter(new MyGridViewAdapter(DynamicDetails.this, num, col,pictureUrl,view));
                        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @SuppressLint("WrongConstant")
                            @Override
                            public void onItemClick(AdapterView<?> arg0, View arg1,
                                                    int position, long arg3) {
                                Toast.makeText(DynamicDetails.this, "糟糕眼神躲不掉" + position, 0).show();
                            }
                        });
                        break;
                }
            }
        };
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_moments_frag01_dynamic_details);
        initGson();//初始化gson对象
        initOkHttpClient();//初始化okHttp对象
        initHandler();//初始化handler
        intent = getIntent();
        momentsId = Integer.parseInt(intent.getStringExtra("momentsId"));//获取传递的说说的id
        Log.e("momentsId", String.valueOf(momentsId));
        initView();

        new Thread(){//创建线程发送请求说说数据的命令
            @Override
            public void run() {
                downLoadMomentInfo(momentsId);
            }
        }.start();
    }
    //初始化OKHTTPClient对象
    private void initOkHttpClient() {
        okHttpClient = new OkHttpClient();
    }
    //初始化Gson对象
    private void initGson() {
        gson = new GsonBuilder()// 创建GsonBuilder对象
                .setPrettyPrinting()// 格式化输出
                .serializeNulls()// 允许输出Null值属性
                .setDateFormat("YY:MM:dd")// 日期格式化
                .create();// 创建Gson对象
    }
    private void initView() {
        commentsList.clear();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        expandableListView = (CommentExpandableListView) findViewById(R.id.detail_page_lv_comment);
        bt_comment = (TextView) findViewById(R.id.detail_page_do_comment_details);//评论按钮
        detail_page_userLogo = findViewById(R.id.detail_page_userLogo);//头像
        detail_page_story = findViewById(R.id.detail_page_story);//文案
        detail_page_userName = findViewById(R.id.detail_page_userName);//昵称
        tvLikeNum = findViewById(R.id.tv_like_num);
        bt_comment.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("详情");
    }

    /**
     * by moos on 2018/04/20
     * func:生成评论数据
     * @return
     */
    private List<CommentDetailBean> generateTestData(Moments moment){
        //评论部分的gson串
        String json = moment.getComments();
        //反序列化
        Comment[] comment = gson.fromJson(json, Comment[].class);
        for(int j=0;j<comment.length;j++){
            Log.e("comment[j]", String.valueOf(comment[j].getComment()));
            CommentDetailBean detailBean = new CommentDetailBean(
                    comment[j].getId(),
                    comment[j].getPersonName(),
                    comment[j].getComment(),
                    comment[j].getTime(),
                    comment[j].getPersonHead());
            commentsList.add(detailBean);
        }
        return commentsList;
    }
    /**
     * by moos on 2018/04/20
     * func:生成回复数据
     * @return
     */
    private List<ReplyDetailBean> replyData(Moments moment){
        //评论部分的gson串
        String json = moment.getReplyContent();
        Log.e("json",json);
        //反序列化
        ReplyDetailBean[] replyDetailBeans = gson.fromJson(json, ReplyDetailBean[].class);
        for(int j=0;j<replyDetailBeans.length;j++){
            Log.e("replyDetailBean[j].getContent()", String.valueOf(replyDetailBeans[j].getReplyContent()));
            ReplyDetailBean replyDetailBean = new ReplyDetailBean(
                    replyDetailBeans[j].getPersonName(),
                    replyDetailBeans[j].getReplyContent(),
                    replyDetailBeans[j].getMomentsId(),
                    replyDetailBeans[j].getPosition()
            );
            replyBeanList.add(replyDetailBean);
        }
        return replyBeanList;
    }
    /**
     * 初始化评论和回复列表
     */
    private void initExpandableListView(final List<CommentDetailBean> commentList, final  List<ReplyDetailBean> replyBeanList){
        expandableListView.setGroupIndicator(null);
        //默认展开所有回复
        adapter = new CommentExpandAdapter(this, commentList);
        expandableListView.setAdapter(adapter);
        for(int i = 0; i<commentList.size(); i++){
            expandableListView.expandGroup(i);
        }
        for(int j = 0;j<replyBeanList.size();j++){
            ReplyDetailBean detailBean = new ReplyDetailBean(replyBeanList.get(j).getPersonName(),replyBeanList.get(j).getReplyContent());
            adapter.addTheReplyData(detailBean, replyBeanList.get(j).getPosition());
            Log.e("replyBeanList.get(j).getPosition()", String.valueOf(replyBeanList.get(j).getPosition()));
            Log.e("replyBeanList.get(j).getReplyContent()",replyBeanList.get(j).getReplyContent());
        }

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                boolean isExpanded = expandableListView.isGroupExpanded(groupPosition);
                Log.e(TAG, "onGroupClick: 当前的评论id>>>"+commentList.get(groupPosition).getId());
//                if(isExpanded){
//                    expandableListView.collapseGroup(groupPosition);
//                }else {
//                    expandableListView.expandGroup(groupPosition, true);
//                }
                showReplyDialog(groupPosition);
                return true;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Toast.makeText(DynamicDetails.this,"点击了回复",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.detail_page_do_comment_details){
            showCommentDialog();
        }
    }
    /**
     * by moos on 2018/04/20
     * func:弹出评论框
     */
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.fragment_moments_frag01_item_comment_dialog,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());
        bt_comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){
                    new Thread(){//创建线程发送请求说说数据的命令
                        @Override
                        public void run() {
                            commentsAdd(commentContent,momentsId);//向服务端发送评论内容
                            downLoadMomentInfo(momentsId);
                        }
                    }.start();
                    dialog.dismiss();
                    Toast.makeText(DynamicDetails.this,"评论成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DynamicDetails.this,"评论内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

    /**
     * by moos on 2018/04/20
     * func:弹出回复框
     */
    private void showReplyDialog(final int position){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.fragment_moments_frag01_item_comment_dialog,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        commentText.setHint("回复 " + commentsList.get(position).getNickName() + " 的评论:");
        dialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String replyContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(replyContent)){
                    new Thread(){//创建线程发送请求说说数据的命令
                        @Override
                        public void run() {
                            ReplyContentAddAdd(replyContent,momentsId,position);//向服务端发送回复内容
                            downLoadMomentInfo(momentsId);
                        }
                    }.start();
                    dialog.dismiss();
                    expandableListView.expandGroup(position);
                    Toast.makeText(DynamicDetails.this,"回复成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DynamicDetails.this,"回复内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dialog.show();
    }

    //使用POST方式提交该说说id,评论人手机号和评论内容,向数据库添加数据
    private void commentsAdd(String commentContent,int momentsId) {
        //1. OkClient对象
        //2. 创建Request请求对象（提前准备好Form表单数据封装）
        //创建FormBody对象
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String likegivePerson = getPersonalPhone();//获取点赞人的手机号
        FormBody formBody =
                new FormBody.Builder()
                        .add("momentsId", String.valueOf(momentsId))//被评论说说id
                        .add("likegivePerson", likegivePerson)//评论人的手机号
                        .add("commentContent", commentContent)//评论内容
                        .add("time",formatter.format(date))
                        .build();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "CommentsAdd")
                .post(formBody)
                .build();
        //3. 创建CALL对象
        Call call = okHttpClient.newCall(request);
        //4. 提交请求并获取响应
        try {
            Response response = call.execute();
            //获取响应的字符串信息
            String result = response.body().string();
            Log.e("result",result);
            Message msg = handler.obtainMessage();
            msg.what = 2;
            msg.obj = result;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //使用POST方式提交该说说id,回复人手机号和回复内容,向数据库添加数据
    private void ReplyContentAddAdd(String replyContent,int momentsId,int position) {
        //1. OkClient对象
        //2. 创建Request请求对象（提前准备好Form表单数据封装）
        //创建FormBody对象
        String likegivePerson = getPersonalPhone();//获取点赞人的手机号
        FormBody formBody =
                new FormBody.Builder()
                        .add("momentsId", String.valueOf(momentsId))//被评论说说id
                        .add("position", String.valueOf(position))//回复的评论位置
                        .add("likegivePerson", likegivePerson)//评论人的手机号
                        .add("replyContent", replyContent)//评论内容
                        .build();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "ReplyContentAddAdd")
                .post(formBody)
                .build();
        //3. 创建CALL对象
        Call call = okHttpClient.newCall(request);
        //4. 提交请求并获取响应
        try {
            Response response = call.execute();
            //获取响应的字符串信息
            String result = response.body().string();
            Log.e("result",result);
            Message msg = handler.obtainMessage();
            msg.what = 2;
            msg.obj = result;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //加载个人信息的布局文件
    private String getPersonalPhone(){
        String phone = EMClient.getInstance().getCurrentUser();
        Log.e("lzz",phone);
        return phone;
    }
    /**
     * 从服务端获取指定的说说信息
     */
    private void downLoadMomentInfo(int momentsId) {
        //2 创建Request对象
        //1) 使用RequestBody封装请求数据
        //获取待传输数据对应的MIME类型
        MediaType type = MediaType.parse("text/plain");
        //创建FormBody对象
        FormBody formBody =
                new FormBody.Builder()
                        .add("momentsId", String.valueOf(momentsId))
                        .add("likegivePersonPhone",getPersonalPhone())
                        .build();
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "DownMomentDetails")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String img = response.body().string();
            Message msg = handler.obtainMessage();
            msg.what = 3;
            msg.obj = img;
            handler.sendMessage(msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
