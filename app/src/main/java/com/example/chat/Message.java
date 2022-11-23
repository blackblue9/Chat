package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Message extends AppCompatActivity implements View.OnClickListener{

    public static final  String TAG = "MainActivity";

    private MainFragment fg_msg;
    private ContactsFragment fg_contacts;
    private UserinfoFragment fg_news;

    @SuppressLint({"MissingInflatedId", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        // 加载icon字体文件
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        Button contact_btn=findViewById(R.id.contact_btn);
        contact_btn.setTypeface(iconfont);
        Button message_btn=findViewById(R.id.message_btn);
        message_btn.setTypeface(iconfont);
        Button userinfo_btn=findViewById(R.id.userinfo_btn);
        userinfo_btn.setTypeface(iconfont);


        Button bt_msg = (Button) findViewById(R.id.message_btn);
        Button bt_contacts = (Button) findViewById(R.id.contact_btn);
        Button bt_news = (Button) findViewById(R.id.userinfo_btn);
        bt_msg.setOnClickListener(Message.this);
        bt_contacts.setOnClickListener(Message.this);
        bt_news.setOnClickListener(Message.this);
        ShowMsgFragment();
    }

    private void ShowMsgFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(fg_msg == null){
            fg_msg = MainFragment.newInstance("msg");
            transaction.add(R.id.fragment_container,fg_msg);
        }
        hideAllFragement();
        transaction.show(fg_msg);
        transaction.commit();

    }

    private void ShowContactsFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(fg_contacts == null){
            //fg_contacts = MainFragment.newInstance("Contacts");
            fg_contacts = ContactsFragment.newInstance("Contacts","2");
            transaction.add(R.id.fragment_container,fg_contacts);
        }
        hideAllFragement();
        transaction.show(fg_contacts);
        Log.d(TAG,"ShowContactsFragment ,fg_contacts is not null");
        transaction.commit();

    }

    private void ShowUserinfoFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(fg_news == null){
            Intent intent = getIntent();
            String username= intent.getStringExtra("username");
            fg_news = UserinfoFragment.newInstance(username);
            transaction.add(R.id.fragment_container,fg_news);
        }
        hideAllFragement();
        transaction.show(fg_news);
        transaction.commit();

    }

    public void hideAllFragement(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(fg_msg != null){
            transaction.hide(fg_msg);
        }
        if(fg_contacts != null){
            transaction.hide(fg_contacts);
        }
        if(fg_news != null){
            transaction.hide(fg_news);
        }

        transaction.commit();
    }


    //@Override
    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.message_btn:
                Log.d(TAG,"try call ShowMsgFragment");
                ShowMsgFragment();
                break;
            case R.id.contact_btn:
                Log.d(TAG,"try call ShowContactsFragment");
                ShowContactsFragment();
                break;
            case R.id.userinfo_btn:
                Log.d(TAG,"try call ShowUserinfoFragment");
                ShowUserinfoFragment();
                break;
            default:
                break;
        }
    }
}