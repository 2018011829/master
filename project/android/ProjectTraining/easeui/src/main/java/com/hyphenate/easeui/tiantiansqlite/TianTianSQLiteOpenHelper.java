package com.hyphenate.easeui.tiantiansqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TianTianSQLiteOpenHelper extends SQLiteOpenHelper {
    //属性
    private static final String NAME="tiantian.db";
    private static final int VERSION=1;
    private static TianTianSQLiteOpenHelper instance;
    //得到实例的方法
    public static TianTianSQLiteOpenHelper getInstance(Context context){
        if(null==instance){
            return new TianTianSQLiteOpenHelper(context,NAME,null,VERSION);
        }else{
            return instance;
        }
    }
    //构造方法
    private TianTianSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //重写的方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table parentInfos(phone char(11) primary key,nickname varchar(50),avatar varchar(50),remark varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
