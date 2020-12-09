package com.example.projecttraining.home.fragments.MomentsFragment.Util;

import android.content.Context;
import android.view.WindowManager;

public class WindowSize {
    public static int getWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        return width;
    }
}
