package com.example.projecttraining.home.fragments.MomentsFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MomentsFragment.Adapter.MomentsFragmentAdapter;
import com.example.projecttraining.home.fragments.MomentsFragment.Frag.Frag01;
import com.example.projecttraining.home.fragments.MomentsFragment.Frag.Frag02;
import com.example.projecttraining.home.fragments.MomentsFragment.Frag.Frag03;
import com.example.projecttraining.home.fragments.MomentsFragment.UploadDynamic.UploadDynamic;
import com.example.projecttraining.home.fragments.MyFragment;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.hyphenate.easeui.utils.EaseParentUtil;

import java.util.ArrayList;
import java.util.List;


public class MomentsFragment extends Fragment implements ViewPager.OnPageChangeListener,View.OnClickListener  {
    private List<Fragment> list = new ArrayList<>();
    private View view;
    private ViewPager viewPager;
    private Button button01,button02,button03;
    private FloatingActionButton floatingActionButton;//浮动按钮
    private PopupWindow mPopWindow;//菜单
    private Button btnCancel;//取消按钮
    private TextView titleName;
    //禁止滑动工具对象
    //private DoNotSlideUtil doNotSlideUtil;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //为了防止第二次加载的时候重复调用这个方法的onCreateView(),重新new了一个pagedapter导致子fragment不显示，显示空白
        if (view!=null){
            ViewGroup parent = (ViewGroup) view.getParent();
            if(parent !=null){
                parent.removeView(view);
            }
            return view;
        }
        //加载布局文件
        view=inflater.inflate(R.layout.fragment_moments, container, false);
        titleName = view.findViewById(R.id.top_title_name);
        titleName.setText("圈  子");
        //初始化方法
        initView();
        initBtnListener();
        //获取浮动按钮对象
        floatingActionButton = view.findViewById(R.id.floating_action_button);
        //设置按钮点击事件
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(view);
            }
        });
        return view;
    }
    //显示PopupWindow菜单
    private void showPopupWindow(View view) {
        //设置contentView
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_moments_frag01_popuplayout, null);
        mPopWindow = new PopupWindow(getContext(),null);
        //设置菜单的宽高
        mPopWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        mPopWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        mPopWindow.setContentView(contentView);
        //加载显示菜单的view
        RelativeLayout root = view.findViewById(R.id.root);
        //设置各个控件的点击响应
        ImageView iv1 = contentView.findViewById(R.id.image);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UploadDynamic.class);
                startActivity(intent);//fragment跳转到activity附带值是1的请求码
            }
        });
        //显示PopupWindow
        mPopWindow.showAtLocation(root, Gravity.BOTTOM, 0, 0);
        btnCancel = contentView.findViewById(R.id.btn_cancel);
        RelativeLayout rl_popuplayout = contentView.findViewById(R.id.rl_popuplayout);
        rl_popuplayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
    }

    //初始化
    private void initView() {
        //加载viewPager
        viewPager=view.findViewById(R.id.viewpager01);
        //设置按钮点击监听器
        list=new ArrayList<>();
        button01=view.findViewById(R.id.frag01);
        button02=view.findViewById(R.id.frag02);
        button03=view.findViewById(R.id.frag03);
        button01.setOnClickListener(this);
        button02.setOnClickListener(this);
        button03.setOnClickListener(this);
        //添加界面列表
        list.add(new Frag01());
        list.add(new Frag02());
        list.add(new Frag03());
        //为viewPager添加adapter
        viewPager.setAdapter(new MomentsFragmentAdapter(getFragmentManager(),list));
        viewPager.addOnPageChangeListener(this);
    }
    //滚动事件
    public void onPageScrolled(int i, float v, int i1) {

    }
    //设置滚动事件
    @SuppressLint("ResourceAsColor")
    public void onPageSelected(int i) {
        initBtnListener();//初始化背景颜色
        switch (i){
            case 0:
                button01.setTextColor(Color.parseColor("#2aa515"));//设置滚动时文字颜色
                break;
            case 1:
                button02.setTextColor(Color.parseColor("#2aa515"));//设置滚动时文字颜色
                break;
            case 2:
                button03.setTextColor(Color.parseColor("#2aa515"));//设置滚动时文字颜色
                break;
        }
    }

    public void onPageScrollStateChanged(int i) {
    }
    //设置点击事件
    @SuppressLint("ResourceAsColor")
    public void onClick(View v) {
        initBtnListener();//初始化背景颜色
        switch (v.getId()){
            case R.id.frag01:
                button01.setTextColor(Color.parseColor("#2aa515"));//设置滚动时文字颜色
                button02.setTextColor(Color.parseColor("#000000"));//设置滚动时文字颜色
                button03.setTextColor(Color.parseColor("#000000"));//设置滚动时文字颜色
                viewPager.setCurrentItem(0);
                break;
            case R.id.frag02:
                button02.setTextColor(Color.parseColor("#2aa515"));//设置滚动时文字颜色
                button01.setTextColor(Color.parseColor("#000000"));//设置滚动时文字颜色
                button03.setTextColor(Color.parseColor("#000000"));//设置滚动时文字颜色
                viewPager.setCurrentItem(1);
                break;
            case R.id.frag03:
                button03.setTextColor(Color.parseColor("#2aa515"));//设置滚动时文字颜色
                button01.setTextColor(Color.parseColor("#000000"));//设置滚动时文字颜色
                button02.setTextColor(Color.parseColor("#000000"));//设置滚动时文字颜色
                viewPager.setCurrentItem(2);
                break;
        }
    }
    //初始化背景颜色
    private void initBtnListener(){
        button01.setBackgroundResource(R.color.white0);
        button02.setBackgroundResource(R.color.white0);
        button03.setBackgroundResource(R.color.white0);
        button01.setTextColor(Color.parseColor("#000000"));//设置滚动时文字颜色
        button02.setTextColor(Color.parseColor("#000000"));//设置滚动时文字颜色
        button03.setTextColor(Color.parseColor("#000000"));//设置滚动时文字颜色
    }
}
