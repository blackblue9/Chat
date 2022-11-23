package com.example.chat;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment {


    private String mText;
    private TextView mTextview;

    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance(String text) {
        MainFragment fragment = new MainFragment();
        Bundle agrs = new Bundle();
        agrs.putString("text",text);
        fragment.setArguments(agrs);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mText = getArguments().getString("text");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main,container,false);
        mTextview = (TextView)view.findViewById(R.id.fragment_text);
        mTextview.setText(mText);
        mTextview.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                mTextview.setText("changed_"+mText);
            }
        });
        return view;
        //return inflater.inflate(R.layout.fragment_main, container, false);
    }
}