package com.example.projecttraining.contact;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projecttraining.R;
import com.example.projecttraining.login.LoginByPasswordActivity;
import com.hyphenate.chat.EMClient;


public class AddFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add, container, false);
        Button btnLogout =view.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EMClient.getInstance().logout(true);
                startActivity(new Intent(getContext(), LoginByPasswordActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }
}
