package com.example.projecttraining.home.fragments.MomentsFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MomentsFragment.Adapter.CommentAdapter;
import com.example.projecttraining.home.fragments.MomentsFragment.Beans.Comment;

import java.util.ArrayList;
import java.util.List;

import static com.mob.MobSDK.getContext;

public class DynamicDetails extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout rlComment;//评论布局
    private TextView hideDown;//隐藏输入框按钮
    private EditText commentContent;//评论输入框
    private Button commentSend;//评论发送按钮
    private ImageView ivComment;//评论按钮
    private CommentAdapter adapterComment;//评论内容的adapter
    private ListView commentList;//评论列表
    private List<Comment> data;//评论的数据列表
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_moments_frag01_dynamic_details);

        initView();
    }

    private void initView() {
        // 初始化评论列表
        commentList = findViewById(R.id.comment_list_details);
        // 初始化数据
        data = new ArrayList<>();
        // 初始化适配器
        adapterComment = new CommentAdapter(getApplicationContext(), data);
        // 为评论列表设置适配器
        commentList.setAdapter(adapterComment);
        hideDown = findViewById(R.id.hide_down_details);//加载隐藏输入框id
        commentContent = findViewById(R.id.comment_content_details);//加载输入评论id
        commentSend = findViewById(R.id.comment_send_details);//加载发送评论id
        rlComment = findViewById(R.id.rl_comment_details);//加载评论布局id
        ivComment = findViewById(R.id.iv_comment_details);//加载评论图片id
        setListener();
    }
    /**
     * 设置监听
     */
    public void setListener(){
        ivComment.setOnClickListener(this);
        hideDown.setOnClickListener(this);
        commentSend.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_comment_details:
                // 弹出输入法
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                // 显示评论框
                rlComment.setVisibility(View.VISIBLE);
                break;
            case R.id.hide_down_details:
                // 隐藏评论框
                rlComment.setVisibility(View.GONE);
                // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                InputMethodManager im = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(commentContent.getWindowToken(), 0);
                break;
            case R.id.comment_send_details:
                sendComment();
                break;
            default:
                break;
        }
    }
    /**
     * 发送评论
     */
    public void sendComment(){
        if(commentContent.getText().toString().equals("")){
            Toast.makeText(getContext(), "评论不能为空！", Toast.LENGTH_SHORT).show();
        }else{
            // 生成评论数据
            Comment comment = new Comment();
            comment.setName("评论者"+(data.size()+1)+"：");
            comment.setContent(commentContent.getText().toString());
            adapterComment.addComment(comment);
            // 发送完，清空输入框
            commentContent.setText("");
            Toast.makeText(getContext(), "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }


}
