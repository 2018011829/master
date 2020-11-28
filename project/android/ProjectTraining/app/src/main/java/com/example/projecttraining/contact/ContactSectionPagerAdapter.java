package com.example.projecttraining.contact;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projecttraining.MainActivity;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

public class ContactSectionPagerAdapter extends FragmentPagerAdapter {
    private final static  String TAG="ContactSectionPagerAdapter";

    public ContactSectionPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0:
               Log.e(TAG, "getItem: 0");
               return new ContactFragment();
           case 1:
               return new ConversationFragment();
           case 2:
               Log.e(TAG, "getItem: 2");
               return new AddFragment();
           default:
               Log.e(TAG, "getItem: default");
               return null;
       }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
