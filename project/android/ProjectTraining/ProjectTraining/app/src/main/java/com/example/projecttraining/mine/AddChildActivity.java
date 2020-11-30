package com.example.projecttraining.mine;


import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.example.projecttraining.R;
import java.util.ArrayList;
import java.util.List;

public class AddChildActivity extends AppCompatActivity {
    private List<String> name = new ArrayList<>();
    private List<String> sClasses = new ArrayList<>();
    private TextView tv_relation;
    private TextView tv_class;
    private WheelView wheelView;
    private TextView tv_ok;
    private TextView tv_cancle;
    private int tag;
    private EditText edt_name;
    private String sex = "男";
    private String relation;
    private String sClass;
    private Button btn_child_ok;
    private RadioGroup radioGroup;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        initDateSource();
        initViews();
        setViewListener();
    }


    /*
    * 给界面布局文件设置监听器
    * */
    private void setViewListener() {
        //利用TextWatcher实现对编辑框输入内容的实时监控
        edt_name.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            //通过监控charSequence实现对编辑框内容的实时监控
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(tv_relation.getText().equals("请选择与孩子的关系")
                        ||tv_class.getText().equals("请选择孩子的年级")
                        ||charSequence.toString().trim().equals("")||charSequence.toString().trim().length()==0){
                    btn_child_ok.setBackgroundColor(Color.GRAY);
                    btn_child_ok.setTextColor(Color.WHITE);
                }else {
                    btn_child_ok.setBackgroundColor(Color.GREEN);
                    btn_child_ok.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //点击选择关系文本框时初始化相关参数
        tv_relation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tag = 1;
                showPopupwindow();
                relation = "父亲";
            }
        });
        //点击选择年级文本框时初始化相关参数
        tv_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tag = 2;
                showPopupwindow();
                sClass = "一年级";
            }
        });
        //单选按钮的点击操作
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_man:
                        sex = "男";
                        Toast.makeText(AddChildActivity.this,"男",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_woman:
                        sex = "女";
                        Toast.makeText(AddChildActivity.this,"女",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        //点击确认按钮时进行的判断操作（主要判断各文本内容是否填写,全填写的话按钮会有颜色的变化来提示）
        btn_child_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_relation.getText().equals("请选择与孩子的关系")
                        ||tv_class.getText().equals("请选择孩子的年级")
                        ||edt_name.getText().toString().trim().equals("")||edt_name.getText().toString().trim().length()==0){
                    Toast.makeText(AddChildActivity.this,"请补全孩子信息",Toast.LENGTH_LONG).show();
                }else {
                    finish();
                }
            }
        });
    }

    /*
    * 加载界面布局文件
    * */
    private void initViews() {
        edt_name = findViewById(R.id.edt_child_name);
        btn_child_ok = findViewById(R.id.btn_child_session_ok);
        radioGroup = findViewById(R.id.rg_sex);
        tv_relation = findViewById(R.id.tv_relation_child);
        tv_class = findViewById(R.id.tv_class_child);
    }

    /*
    * 初始化wheelview(滚轮选择器)所需要的集合信息
    * */
    private void initDateSource() {
        name.add("父亲");
        name.add("母亲");
        name.add("其他");
        sClasses.add("一年级");
        sClasses.add("二年级");
        sClasses.add("三年级");
        sClasses.add("四年级");
        sClasses.add("五年级");
        sClasses.add("六年级");
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
        View view = getLayoutInflater().inflate(R.layout.activity_child_popupwindow,null);

        /*
        * 加载popupwindow布局文件
        * 给布局文件设置相应监听器
        * */
        wheelView = view.findViewById(R.id.wheelview);
        wheelView.setCyclic(false); //设置不可循环滚动
        setWheelView(wheelView);//设置wheelview参数

        tv_ok = view.findViewById(R.id.tv_relation_ok);
        tv_cancle = view.findViewById(R.id.tv_relation_cancle);


        //利用tag判断wheelview选中的值（sclass或relation）赋予给哪一个文本框
        //同时赋值完毕后对所有信息是否填全进行判断
        if(tag==1) {
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_relation.setText(relation);
                    if(tv_relation.getText().equals("请选择与孩子的关系")
                            ||tv_class.getText().equals("请选择孩子的年级")
                            ||edt_name.getText().toString().trim().equals("")||edt_name.getText().toString().trim().length()==0){
                        btn_child_ok.setBackgroundColor(Color.GRAY);
                        btn_child_ok.setTextColor(Color.WHITE);
                    }else {
                        btn_child_ok.setBackgroundColor(Color.GREEN);
                        btn_child_ok.setTextColor(Color.BLACK);
                    }
                    popupWindow.dismiss();
                }
            });
            tv_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });
        }else if(tag==2) {
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_class.setText(sClass);
                    if(tv_relation.getText().equals("请选择与孩子的关系")
                            ||tv_class.getText().equals("请选择孩子的年级")
                            ||edt_name.getText().toString().trim().equals("")||edt_name.getText().toString().trim().length()==0){
                        btn_child_ok.setBackgroundColor(Color.GRAY);
                        btn_child_ok.setTextColor(Color.WHITE);
                    }else {
                        btn_child_ok.setBackgroundColor(Color.GREEN);
                        btn_child_ok.setTextColor(Color.BLACK);
                    }
                    popupWindow.dismiss();
                }
            });
            tv_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });
        }
        //加载根布局（popupwindow要显示在其上面）
        LinearLayout root = findViewById(R.id.root);
        //为popupwindow绑定布局
        popupWindow.setContentView(view);
        //设置popupwindow显示的位置
        popupWindow.showAtLocation(root, Gravity.CENTER,0,0);//指定显示的位置
    }

    /*
    * 给wheelview设置相关参数
    * */
    private void setWheelView(WheelView wheelView){

        //tag用于判断加载两个数据源中的哪一个（sClasses和name数据源）
        //设置数据源
        if(tag==1){
            wheelView.setAdapter(new WheelAdapter() {
                @Override
                public int getItemsCount() {
                    return name.size();
                }

                @Override
                public Object getItem(int index) {
                    return name.get(index);
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }
            });
        }else if(tag==2) {
            wheelView.setAdapter(new WheelAdapter() {
                @Override
                public int getItemsCount() {
                    return sClasses.size();
                }

                @Override
                public Object getItem(int index) {
                    return sClasses.get(index);
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }
            });
        }

        //添加数据源的每一个item被选中时的监听器
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if(tag==1){
                    relation = name.get(index);
                }else if(tag==2) {
                    sClass = sClasses.get(index);
                }
            }
        });
    }

}