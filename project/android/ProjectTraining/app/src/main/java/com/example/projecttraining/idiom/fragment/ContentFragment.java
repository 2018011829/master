package com.example.projecttraining.idiom.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;
import com.example.projecttraining.idiom.activitys.IdiomByTypeActivity;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * 2020-11-25
 * 2020-11-26
 * 2020-11-27
 * 2020-11-28
 * @author lrf
 */
public class ContentFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";

    private View containerView;
    private Bitmap bitmap;

    protected LinearLayout mLinearLayout;
    protected List<String> strList = new ArrayList<>();


    public static ContentFragment newInstance(List<String> strings) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Integer.class.getName(), (ArrayList<String>) strings);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        strList = getArguments().getStringArrayList(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.idiom_type_fragment, container, false);

        mLinearLayout = rootView.findViewById(R.id.linear_content);
        // 使用java代码的方式添加按钮
        for(int i = 0; i < strList.size(); ++i){
            final Button btn = new Button(getContext());
            btn.setId(i);
            btn.setText(strList.get(i));
            btn.setTextSize(22);
            btn.setBackgroundResource(R.drawable.idiom_child_type_btn_style);
            // 设置按钮的布局参数
            LinearLayout.LayoutParams buttonParams =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            buttonParams.topMargin = 30;
            mLinearLayout.addView(btn,buttonParams);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String idiomType = btn.getText().toString();
                    Intent intent = new Intent();
                    intent.setClass(getContext(), IdiomByTypeActivity.class);
                    intent.putExtra("idiomType",idiomType);
                    getContext().startActivity(intent);
                    Toast.makeText(getContext(),idiomType,Toast.LENGTH_SHORT).show();
                }
            });
        }

        return rootView;
    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

