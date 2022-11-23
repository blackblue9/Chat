package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private Button register_btn;//登录按钮
    private String account,password,duplicate_password;//获取的用户名，密码，加密密码
    private EditText user_input,password_input,duplicate_password_input;//编辑框
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        UserDatabaseHelper dbHelper=new UserDatabaseHelper(this, "User.db", 1);
        dbHelper.getWritableDatabase();
        init(dbHelper);
    }
    private void init(UserDatabaseHelper dbHelper){
        register_btn=findViewById(R.id.register_btn);
        user_input=findViewById(R.id.user_input);
        password_input=findViewById(R.id.password_input);
        duplicate_password_input=findViewById(R.id.duplicate_password_input);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account=user_input.getText().toString().trim();
                password=password_input.getText().toString().trim();
                duplicate_password=duplicate_password_input.getText().toString().trim();
                if (account.equals("")){
                    Toast.makeText(Register.this,"账号为空!",Toast.LENGTH_SHORT).show();
                }else if (password.equals("")){
                    Toast.makeText(Register.this,"密码为空!",Toast.LENGTH_SHORT).show();
                }else if (duplicate_password.equals("")){
                    Toast.makeText(Register.this,"确认密码为空!",Toast.LENGTH_SHORT).show();
                }else if (!password.equals(duplicate_password)){
                    Toast.makeText(Register.this,"两次密码不一致，请重新输入！",Toast.LENGTH_SHORT).show();
                }else {
                    Cursor cursor=dbHelper.getReadableDatabase().query("User", null, "account = ?",
                            new String[]{account}, null, null, null);
                    if (cursor.moveToNext()){
                        Toast.makeText(Register.this, "账号已存在，请重新输入", Toast.LENGTH_SHORT).show();
                    }else {
                        ContentValues values = new ContentValues();
                        values.put("account",account);
                        values.put("password",password);
                        dbHelper.getReadableDatabase().insert("User", null, values) ;// 插入一条数据
                        Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}