package com.example.projecttraining.contact.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projecttraining.contact.fragment.ConversationFragment;
import com.example.projecttraining.contact.fragment.ContactFragment;

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
               return new ConversationFragment();
           case 1:
               return new ContactFragment();
           default:
               Log.e(TAG, "getItem: default");
               return null;
       }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
