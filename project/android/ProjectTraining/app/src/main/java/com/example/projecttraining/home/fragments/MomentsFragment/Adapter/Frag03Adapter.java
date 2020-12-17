package com.example.projecttraining.home.fragments.MomentsFragment.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.Comment;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.CommentBean;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.CommentDetailBean;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.Moments;
import com.example.projecttraining.home.fragments.MomentsFragment.DynamicDetails;
import com.example.projecttraining.home.fragments.MomentsFragment.View.CommentExpandableListView;
import com.example.projecttraining.home.fragments.MomentsFragment.View.PictureGridView;
import com.example.projecttraining.util.ConfigUtil;
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


public class Frag03Adapter extends BaseAdapter {
    private Context mContext;//环境上下文
    private List<Moments> moments = new ArrayList<>();//动态列表
    private int itemLayoutRes;//
    private LinearLayout llDynamicDetails;//点击查看详情
    private OkHttpClient okHttpClient;//定义OKHTTPClient对象属性
    private Handler handler;//定义Handler对象属性
    private List<String> likeGiveNames = new ArrayList<>();
    private Gson gson;// 定义Gson对象属性
    String likeGive = null;//点赞人昵称的字符串
    private TextView tvLikeGive;
    private BottomSheetDialog dialog;//点击评论
    private CommentExpandAdapter adapter;//评论的adapter
    private List<CommentDetailBean> commentsList = new ArrayList<>();//评论列表
    private CommentExpandableListView expandableListView;
    private Toolbar toolbar;
    private CommentBean commentBean;

    //初始化Handler对象
    private void initHandler(View view, int i) {
        handler = new Handler() {//handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        //获取图片资源路径
                        String imgUrl = (String) msg.obj;//接收到的是一个说说对象
                        String json = imgUrl;
                        //反序列化
                        moments = Arrays.asList(gson.fromJson(json, Moments[].class));//说说对象反序列化

                        notifyDataSetChanged();
                        break;
                }
            }
        };
    }

    public Frag03Adapter(Context mContext, List<Moments> moments, int itemLayoutRes) {
        this.mContext = mContext;
        this.moments = moments;
        this.itemLayoutRes = itemLayoutRes;
    }

    @Override
    public int getCount() {
        if (null != moments) {
            return moments.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (null != moments) {
            return moments.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;//图片视图管理
        if (null == view) {
            holder = new ViewHolder();//实例化图片视图
            //加载item的布局文件
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(itemLayoutRes, null);
            holder.gridview = (PictureGridView) view
                    .findViewById(R.id.gridView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ImageView ivHeadPortrait = view.findViewById(R.id.iv_headPortrait);//加载头像id
        TextView tvName = view.findViewById(R.id.tv_name);//加载昵称id
        TextView tvContent = view.findViewById(R.id.tv_content);//加载文案id
        llDynamicDetails = view.findViewById(R.id.ll_dynamic_details);//查看详情id
        ImageView ivGiveLike = view.findViewById(R.id.iv_give_like);//点赞图片
        TextView tvLikeGive = view.findViewById(R.id.tv_likeGive);//点赞昵称的字符串
        TextView edtComment = view.findViewById(R.id.edt_comment);//评论输入框
        ImageView ivConcern = view.findViewById(R.id.iv_concern);//关注按钮
        TextView tvMomentsTime = view.findViewById(R.id.tv_moments_time);//发布时间

        tvName.setText(moments.get(i).getName());//设置昵称的值
        tvContent.setText(moments.get(i).getContent());//设置评论的值
        //设置圆形头像
        Glide.with(mContext)
                .load(moments.get(i).getHeadPortraitUrl())
                .circleCrop()
                .into(ivHeadPortrait);
        //点击文本和图片部分可以查看详情
        llDynamicDetails.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String momentsId = String.valueOf(moments.get(i).getId());
                Intent intent = new Intent(mContext, DynamicDetails.class);
                intent.putExtra("momentsId", momentsId);
                mContext.startActivity(intent);
            }
        });
        tvMomentsTime.setText(moments.get(i).getTime());


        initOkHttpClient();//初始化okHttp对象
        initHandler(view, i);//初始化handler
        initGson();//初始化gson对象

        String img = String.valueOf(moments.get(i).getPictureUrl());//说说对象中的图片部分
        //反序列化
        List<String> pictureUrl = Arrays.asList(gson.fromJson(img, String[].class));//说说对象反序列化


        String likegivename = String.valueOf(moments.get(i).getLikeGiveName());//说说对象中点赞人部分
        List<String> likeGiveNames = Arrays.asList(gson.fromJson(likegivename, String[].class));
        for(int j=0;j<likeGiveNames.size();j++){
            Log.e("likeGiveNames.get(j)",likeGiveNames.get(j));
        }

        if (likeGiveNames.size() == 1) {
            if(likeGiveNames.get(0).equals("没有人点赞呦")){
                likeGive = likeGiveNames.get(0);
            }else {
                likeGive = likeGiveNames.get(0)+"觉得赞";
            }
        } else if (likeGiveNames.size() > 1) {
            likeGive = likeGiveNames.get(0);
            for (int j = 1; j < likeGiveNames.size(); j++) {
                likeGive += "," + likeGiveNames.get(j);
            }
            likeGive += "觉得赞";
        }
        tvLikeGive.setText(likeGive);
        //点赞部分
        int likegiveboolen = moments.get(i).getLikegiveboolen();
        if (likegiveboolen == 0) {
            Glide.with(mContext)
                    .load(R.mipmap.praise1)
                    .into(ivGiveLike);
            ivGiveLike.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Thread() {//创建线程发送请求说说数据的命令
                        @Override
                        public void run() {
                            likegivePersonAndMomentsIdAdd(view, i);
                            mineMoments();
                        }
                    }.start();
                }
            });
        } else {
            Glide.with(mContext)
                    .load(R.mipmap.praise3)
                    .into(ivGiveLike);
            ivGiveLike.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Thread() {//创建线程发送请求说说数据的命令
                        @Override
                        public void run() {
                            likegivePersonAndMomentsIdDelete(view, i);
                            mineMoments();
                        }
                    }.start();
                }
            });
        }

        Glide.with(mContext)
                .load(R.mipmap.delete)
                .into(ivConcern);
        ivConcern.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        deleteMoments(moments.get(i).getId());
                        mineMoments();
                    }
                }.start();
                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
            }
        });

        //评论的点击事件
        edtComment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showCommentDialog(i);//点击评论弹出评论框
            }
        });
        initView(view,i);


        //根据图片个数创建布局
        int num = pictureUrl.size();//获取当前的图片数目
        int col = 1;//默认列数
        if (num == 1) {
            holder.gridview.setNumColumns(1);
            col = 1;
        } else if (num == 2 || num == 4) {
            holder.gridview.setNumColumns(2);
            col = 2;
        } else {
            holder.gridview.setNumColumns(3);
            col = 3;
        }

        holder.gridview.setAdapter(new MyGridViewAdapter(mContext, num, col,pictureUrl,view));
        View finalView1 = view;
        holder.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                Toast.makeText(mContext, "糟糕眼神躲不掉" + position, 0).show();
            }
        });

        return view;
    }
    //初始化Gson对象
    private void initGson() {
        gson = new GsonBuilder()// 创建GsonBuilder对象
                .setPrettyPrinting()// 格式化输出
                .serializeNulls()// 允许输出Null值属性
                .setDateFormat("YY:MM:dd")// 日期格式化
                .create();// 创建Gson对象
    }

    //定义Holder类
    private class ViewHolder {
        PictureGridView gridview;
    }

    //使用POST方式提交点赞人和该说说id,向数据库添加数据
    private void likegivePersonAndMomentsIdAdd(View view,int i) {
        //1. OkClient对象
        //2. 创建Request请求对象（提前准备好Form表单数据封装）
        //创建FormBody对象
        String likegivePerson = getPersonalPhone();//获取点赞人的手机号
        int momentsId = moments.get(i).getId();//获取当前说说id
        FormBody formBody =
                new FormBody.Builder()
                        .add("likegivePerson", likegivePerson)
                        .add("momentsId", String.valueOf(momentsId))
                        .build();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "LikegivePersonAndMomentsId")
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
            msg.what = 1;
            msg.obj = result;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //使用POST方式提交当前用户手机号和说说手机号
    private void deleteMoments(int momentsId) {
        //1. OkClient对象
        //2. 创建Request请求对象（提前准备好Form表单数据封装）
        //创建FormBody对象
        FormBody formBody =
                new FormBody.Builder()
                        .add("momentsId", String.valueOf(momentsId))
                        .build();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "DeleteMoment")
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
            msg.what = 1;
            msg.obj = result;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //使用POST方式提交点赞人和该说说id,从数据库中删除数据
    private void likegivePersonAndMomentsIdDelete(View view,int i) {
        //1. OkClient对象
        //2. 创建Request请求对象（提前准备好Form表单数据封装）
        //创建FormBody对象
        String likegivePerson = getPersonalPhone();//获取点赞人的手机号
        int momentsId = moments.get(i).getId();//获取当前说说id
        FormBody formBody =
                new FormBody.Builder()
                        .add("likegivePerson", likegivePerson)
                        .add("momentsId", String.valueOf(momentsId))
                        .build();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "LikegivePersonAndMomentsIdDelete")
                .post(formBody)
                .build();
        //3. 创建CALL对象
        Call call = okHttpClient.newCall(request);
        //4. 提交请求并获取响应
        try {
            Response response = call.execute();
            //获取响应的字符串信息
            String result = response.body().string();

            Message msg = handler.obtainMessage();
            msg.what = 1;
            msg.obj = result;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //使用POST方式提交该说说id,评论人手机号和评论内容,向数据库添加数据
    private void commentsAdd(String commentContent,int i) {
        //1. OkClient对象
        //2. 创建Request请求对象（提前准备好Form表单数据封装）
        //创建FormBody对象
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String likegivePerson = getPersonalPhone();//获取点赞人的手机号
        int momentsId = moments.get(i).getId();//获取当前说说id
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

    //加载个人信息的布局文件
    private String getPersonalPhone(){
        String phone = EMClient.getInstance().getCurrentUser();
        Log.e("lzz",phone);
        return phone;
    }
    //初始化OKHTTPClient对象
    private void initOkHttpClient() {
        okHttpClient = new OkHttpClient();
    }

    //从服务端获取图片资源的网络路径
    private void mineMoments() {
        //2 创建Request对象
        //1) 使用RequestBody封装请求数据
        //获取待传输数据对应的MIME类型
        MediaType type = MediaType.parse("text/plain");
        //创建FormBody对象
        FormBody formBody =
                new FormBody.Builder()
                        .add("PersonPhone",getPersonalPhone())
                        .build();
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "MineMomentsInfoSerclet")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String img = response.body().string();
            Message msg = handler.obtainMessage();
            msg.what = 1;
            msg.obj = img;
            handler.sendMessage(msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //使用POST方式提交当前用户手机号和说说手机号
    private void personPhoneAndMomentsPnoneAdd(int i) {
        //1. OkClient对象
        //2. 创建Request请求对象（提前准备好Form表单数据封装）
        //创建FormBody对象
        String personPhone = getPersonalPhone();//获取当前用户手机号
        String momentsPhone = moments.get(i).getPhoneNumber();//获取当前说说id
        FormBody formBody =
                new FormBody.Builder()
                        .add("personPhone", personPhone)
                        .add("momentsPhone", momentsPhone)
                        .build();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "AddAttentionServlet")
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
            msg.what = 1;
            msg.obj = result;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //使用POST方式提交当前用户手机号和说说手机号
    private void personPhoneAndMomentsPnoneDelete(int i) {
        //1. OkClient对象
        //2. 创建Request请求对象（提前准备好Form表单数据封装）
        //创建FormBody对象
        String personPhone = getPersonalPhone();//获取当前用户手机号
        String momentsPhone = moments.get(i).getPhoneNumber();//获取当前说说id
        FormBody formBody =
                new FormBody.Builder()
                        .add("personPhone", personPhone)
                        .add("momentsPhone", momentsPhone)
                        .build();
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "DeleteAttention")
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
            msg.what = 1;
            msg.obj = result;
            handler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * by moos on 2018/04/20
     * func:弹出评论框
     */
    private void showCommentDialog(int i){
        dialog = new BottomSheetDialog(mContext);
        View commentView = LayoutInflater.from(mContext).inflate(R.layout.fragment_moments_frag01_item_comment_dialog,null);
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
        bt_comment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)
                ){
                    new Thread(){//创建线程发送请求说说数据的命令
                        @Override
                        public void run() {
                            Log.e( "run: ","123" );
                            commentsAdd(commentContent,i);//向服务端发送评论内容
                            mineMoments();
                        }
                    }.start();
                    //commentOnWork(commentContent);
                    dialog.dismiss();
                    Toast.makeText(mContext,"评论成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext,"评论内容不能为空",Toast.LENGTH_SHORT).show();
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


    private void initView(View view,int i) {
        commentsList.clear();
        expandableListView = (CommentExpandableListView) view.findViewById(R.id.detail_page_lv_comment);
        //评论部分的gson串
        String json = moments.get(i).getComments();
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
        initExpandableListView(commentsList);
    }
    /**
     * 初始化评论和回复列表
     */
    private void initExpandableListView(final List<CommentDetailBean> commentList){
        expandableListView.setGroupIndicator(null);
        //默认展开所有回复
        adapter = new CommentExpandAdapter(mContext, commentList);
        expandableListView.setAdapter(adapter);
        for(int i = 0; i<commentList.size(); i++){
            expandableListView.expandGroup(i);
        }
    }

}
