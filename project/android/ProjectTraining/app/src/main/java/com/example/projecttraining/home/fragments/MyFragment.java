package com.example.projecttraining.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.example.projecttraining.R;
import com.example.projecttraining.mine.AddChildActivity;
import com.example.projecttraining.mine.EditorParentActivity;
import com.example.projecttraining.mine.MyCollectionActivity;
import com.example.projecttraining.mine.SettingActivity;
import com.example.projecttraining.mine.entity.Child;
import com.example.projecttraining.util.ConfigUtil;
import com.example.projecttraining.util.ParentUtil;
import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseParentUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MyFragment extends Fragment {

    public static String phoneNum=EMClient.getInstance().getCurrentUser(); //纪录当前登录的手机号，用来在收藏前进行判断
    public static String childName=""; //纪录当前登录的手机号下的孩子姓名，用来在收藏前进行判断、存储收藏信息
    public static String childSex="" ; //记录当前从孩子数据源中选择的孩子性别
    public static String childGrade=""; //记录当前从孩子数据源中选择的孩子年级
    private String cname;
    private String cgrade;
    private String csex;
    private ImageView iv_headPhoto;
    private RelativeLayout rl_mine_addChild;
    private LinearLayout ll_mine_editorParent;
    private RelativeLayout rl_mine_setting;
    private LinearLayout ll_mine_mychildren;
    private RelativeLayout rl_mine_mycollection;
    private TextView tv_mine_useName;
    private TextView tv_mine_phone;
    private View view;
    private TextView tv_mine_myChildName;
    private ImageView iv_mine_myChildImg;
    private PopupWindow popupWindow;
    private WheelView wheelView;
    private TextView tv_ok;
    private TextView tv_cancle;
    private String childMessage = "";
    private List<String> children = new ArrayList<>();
    Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    init();
                    break;
                case 2:
                    try {
                        JSONObject jChildren = new JSONObject((String) msg.obj);
                        JSONArray jArray = jChildren.getJSONArray("children");
                        for(int i= 0;i<jArray.length();i++){
                            String json = jArray.getJSONObject(i).toString();
                            Log.i("json", json);
                            Child child = new Gson().fromJson(json, Child.class);
                            childMessage = child.getName()+" "+child.getGrade()+" "+child.getSex();
                            children.add(childMessage);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };



    @Override
    public void onResume() {
        super.onResume();
        ParentUtil.storeCurrentParent(EMClient.getInstance().getCurrentUser(),handler);
        children.clear();
        queryChildren();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my,container,false);


        //TODO 获取控件引用
        findViews();
        //TODO 设置控件内容
//        init();
        //TODO 给控件添加监听器
        setClickListener();
        return view;
    }

    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.rl_mine_addChild:
                    Intent addChild = new Intent();
                    addChild.setClass(getContext(), AddChildActivity.class);
                    startActivity(addChild);
                    break;
                case R.id.ll_mine_editorParent:
                    Intent editor = new Intent();
                    editor.setClass(getContext(), EditorParentActivity.class);
                    startActivityForResult(editor,100);
                    break;
                case R.id.rl_mine_setting:
                    Intent intent = new Intent();
                    intent.setClass(getContext(), SettingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.ll_mine_mychildren:
                    if(children.size()==0){
                        Toast.makeText(getActivity(),"当前暂无您孩子的信息，请先添加孩子！",Toast.LENGTH_SHORT).show();
                    }else {
                        String[] message= children.get(0).split(" ");
                        cname = message[0];
                        cgrade = message[1];
                        csex = message[2];
                        showSelectChildPopupwindow();
                    }
                    break;
                case R.id.rl_mine_mycollection:
                    startActivity(new Intent().setClass(getContext(), MyCollectionActivity.class));
                    break;
            }
        }
    }

    private void showSelectChildPopupwindow() {
        //创建popupwindow
        popupWindow = new PopupWindow(this.getActivity());
        //设置popupwindow显示的宽度（默认不占满屏幕）
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //加载popupwindow布局
        View view2 = getLayoutInflater().inflate(R.layout.activity_mine_child_popupwindow,null);

        /*
         * 加载popupwindow布局文件
         * 给布局文件设置相应监听器
         * */
        wheelView = view2.findViewById(R.id.wheelview);
        wheelView.setCyclic(false); //设置不可循环滚动
        setWheelView(wheelView);//设置wheelview参数

        tv_ok = view2.findViewById(R.id.tv_relation_ok);
        tv_cancle = view2.findViewById(R.id.tv_relation_cancle);


        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                childName = cname;
                childGrade = cgrade;
                childSex = csex;
                tv_mine_myChildName.setText(childName);
                if(childSex.equals("男")){
                    iv_mine_myChildImg.setImageDrawable(MyFragment.this.getContext().getDrawable(R.drawable.mine_child_man));
                }else {
                    iv_mine_myChildImg.setImageDrawable(MyFragment.this.getContext().getDrawable(R.drawable.mine_child_woman));
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

        //加载根布局（popupwindow要显示在其上面）
        ScrollView root = view.findViewById(R.id.sv_root);
        //为popupwindow绑定布局
        popupWindow.setContentView(view2);
        //设置popupwindow显示的位置
        popupWindow.showAtLocation(root, Gravity.CENTER,0,0);//指定显示的位置
    }

    private void setWheelView(WheelView wheelView) {
        wheelView.setAdapter(new WheelAdapter() {
            @Override
            public int getItemsCount() {
                return children.size();
            }

            @Override
            public Object getItem(int index) {
                return children.get(index);
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
                String [] childSessions = children.get(index).split(" ");
                cname = childSessions[0];
                cgrade = childSessions[1];
                csex = childSessions[2];
            }
        });
    }

    private void queryChildren() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", EMClient.getInstance().getCurrentUser());
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "QueryChildrenServlet")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("查询孩子信息", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = new Message();
                message.obj = result;
                message.what = 2;
                handler.sendMessage(message);
                Log.i("result", result);
            }
        });
    }

    private void setClickListener() {
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        rl_mine_addChild.setOnClickListener(myOnClickListener);
        ll_mine_editorParent.setOnClickListener(myOnClickListener);
        rl_mine_setting.setOnClickListener(myOnClickListener);
        ll_mine_mychildren.setOnClickListener(myOnClickListener);
        rl_mine_mycollection.setOnClickListener(myOnClickListener);
    }
    private void init(){
        tv_mine_phone.setText(EMClient.getInstance().getCurrentUser());
        tv_mine_useName.setText(EaseParentUtil.currentUserNickname);
        //12-07得到一个设置圆角的requestOptions
        RequestOptions requestOptions=EaseParentUtil.getRoundImageTransform(getContext());
        Glide.with(getContext())
                .load(EaseParentUtil.currentUserAvatar)
                .apply(requestOptions)
                .into(iv_headPhoto);
        if(!childName.equals("")&&childSex.equals("男")){
            tv_mine_myChildName.setText(childName);
            iv_mine_myChildImg.setImageDrawable(MyFragment.this.getContext().getDrawable(R.drawable.mine_child_man));
        }else if(!childName.equals("")&&childSex.equals("女")){
            tv_mine_myChildName.setText(childName);
            iv_mine_myChildImg.setImageDrawable(MyFragment.this.getContext().getDrawable(R.drawable.mine_child_woman));
        }

    }

    private void findViews() {
        iv_headPhoto = view.findViewById(R.id.iv_headPhoto);
        rl_mine_addChild  = view.findViewById(R.id.rl_mine_addChild);
        ll_mine_editorParent = view.findViewById(R.id.ll_mine_editorParent);
        tv_mine_useName = view.findViewById(R.id.tv_mine_userName);
        tv_mine_phone = view.findViewById(R.id.tv_mine_phone);
        rl_mine_setting = view.findViewById(R.id.rl_mine_setting);
        ll_mine_mychildren = view.findViewById(R.id.ll_mine_mychildren);
        tv_mine_myChildName = view.findViewById(R.id.tv_mine_myChildName);
        iv_mine_myChildImg = view.findViewById(R.id.iv_mine_myChildImg);
        rl_mine_mycollection = view.findViewById(R.id.rl_mine_mycollection);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100&&resultCode==200){
            Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();
        }
    }
}
