package com.example.projecttraining;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.projecttraining.util.AppConstantsUtil;
import com.example.projecttraining.util.SpUtils;
import com.github.paolorotolo.appintro.AppIntro;

public class IntroAppActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(new Fragment(R.layout.fragment_appintro1));
        addSlide(new Fragment(R.layout.fragment_appintro3));
        addSlide(new Fragment(R.layout.fragment_appintro2));
        //设置下划线的颜色
        setSeparatorColor(getResources().getColor(R.color.colorPrimary));
        setSkipText("跳过");
        setDoneText("完成");
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        //当执行跳过动作时触发
        Intent intent = new Intent(IntroAppActivity.this, MainActivity.class);
        startActivity(intent);
        SpUtils.putBoolean(IntroAppActivity.this, AppConstantsUtil.FIRST_OPEN, true);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        //当执行完成动作时触发
        Intent intent = new Intent(IntroAppActivity.this, MainActivity.class);
        startActivity(intent);
        SpUtils.putBoolean(IntroAppActivity.this, AppConstantsUtil.FIRST_OPEN, true);
        finish();
    }
}