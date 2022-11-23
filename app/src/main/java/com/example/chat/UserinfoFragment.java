package com.example.chat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class UserinfoFragment extends Fragment {


    private String username;
    private TextView usernameTextview;

    public UserinfoFragment() {

    }
    public static UserinfoFragment newInstance(String param1) {
        UserinfoFragment fragment = new UserinfoFragment();
        Bundle args = new Bundle();
        args.putString("username", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("username");
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_userinfo,container,false);
        usernameTextview = (TextView) view.findViewById(R.id.userinfo_textview);
        usernameTextview.setText("username:"+username);
        Log.v("username",username);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button exit_login_btn=(Button) view.findViewById(R.id.exit_login_btn);
        exit_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }
}