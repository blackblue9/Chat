package com.example.chat;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    //在SQLiteOepnHelper的子类当中，继承了父类，必须重写该构造函数，这是java基础知识
    public UserDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        //必须通过super调用父类当中的构造函数
        super(context, name, factory, version);
    }
    //调用三个参数的构造函数
    public UserDatabaseHelper(Context context,String name,int version){
        this(context, name,null,version);
    }

    //该函数是在第一次创建数据库的时候执行,实际上是在第一次得到SQLiteDatabse对象的时候，才会调用这个方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("create a Database");
        //execSQL函数用于执行SQL语句
        String createUser = "create table User (" +
                "id integer primary key autoincrement," +
                "account text," +
                "password text)";
        db.execSQL(createUser);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("update a Database");
        db.execSQL("drop table if exists User");
        onCreate(db);
    }
}

