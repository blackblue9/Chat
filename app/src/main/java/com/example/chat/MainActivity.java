package com.example.chat;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button login_btn;//登录按钮
    private TextView register_textview,forget_password_textview;
    private String account,password;//获取的用户名，密码，加密密码
    private EditText user_input,password_input;//编辑框


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserDatabaseHelper dbHelper=new UserDatabaseHelper(this, "User.db", 1);
        dbHelper.getWritableDatabase();
        init(dbHelper);

    }
    private void init(UserDatabaseHelper dbHelper){
        login_btn=findViewById(R.id.login_btn);
        register_textview=findViewById(R.id.register_textview);
        forget_password_textview=findViewById(R.id.forget_password_textview);
        user_input=findViewById(R.id.user_input);
        password_input=findViewById(R.id.password_input);
        login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                account=user_input.getText().toString().trim();
                password=password_input.getText().toString().trim();
                if (account.equals("")){
                    Toast.makeText(MainActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();
                }else if (password.equals("")){
                    Toast.makeText(MainActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    Cursor cursor=dbHelper.getReadableDatabase().query("User", null, "account = ? and password = ?",
                            new String[]{account,password}, null, null, null);
                    if (!cursor.moveToNext()){
                        user_input.setText("");
                        password_input.setText("");
                        Toast.makeText(MainActivity.this,"账户或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent=new Intent();
                        intent.setClass(MainActivity.this,Message.class);
                        intent.putExtra("username",account);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    }
                }

                }
            }
        );
        register_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Register.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "register", Toast.LENGTH_SHORT).show();
            }

        });
        forget_password_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "forget password", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
