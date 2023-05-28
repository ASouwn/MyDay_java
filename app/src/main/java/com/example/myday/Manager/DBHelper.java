package com.example.myday.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myday.DB.DBStruct;
import com.example.myday.DB.DBVersion_and_Name;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

public class DBHelper extends SQLiteOpenHelper {

    private static String NAME=DBVersion_and_Name.getNAME();
    private static int VERSION=DBVersion_and_Name.getVersion();

    private static String TABLE_NAME="mydate";
    private static String ID="id";
    private static String TITLE="title";
    private static String DATE="date";
    private static String CONTENT="content";

    public DBHelper(@Nullable Context context ) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        String DBManager_SQL=
                "CREATE TABLE " +TABLE_NAME+" ("
                        +ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        +TITLE+" TEXT, "
                        +DATE+" TEXT, "
                        +CONTENT+" TEXT)";
        db.execSQL(DBManager_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int add(@NonNull DBStruct main_date){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        contentValues.put(CONTENT,main_date.getContent());
        contentValues.put(DATE,main_date.getDate());
        contentValues.put(TITLE,main_date.getTitle());

        long insert = sqLiteDatabase.insert(TABLE_NAME,CONTENT,contentValues);

        sqLiteDatabase.close();
        if (insert==-1){
            return 0;
        }
        return 1;
    }

    public int delete(@NonNull DBStruct main_date){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int delete=sqLiteDatabase.delete(TABLE_NAME,ID+"=?",
                new String[]{String.valueOf(main_date.getId())});
        sqLiteDatabase.close();
        if (delete == 0){
            return 0;
        }
        return 1;
    }

    public int rewrite(@NonNull DBStruct main_date){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        contentValues.put(CONTENT,main_date.getContent());
        contentValues.put(DATE,main_date.getDate());
        contentValues.put(TITLE,main_date.getTitle());

        int update = sqLiteDatabase.update(TABLE_NAME,contentValues,ID+"=?",
                new String[]{String.valueOf(main_date.getId())});
        sqLiteDatabase.close();
        if (update==0){
            return 0;
        }
        return 1;
    }

    public List<DBStruct> show(){
        List<DBStruct> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String sql="SELECT * FROM "+TABLE_NAME;
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        DBStruct dbStruct;

        int idIndex= cursor.getColumnIndex(String.valueOf(ID));
        int titleIndex= cursor.getColumnIndex(TITLE);
        int dateIndex= cursor.getColumnIndex(DATE);
        int contentIndex= cursor.getColumnIndex(CONTENT);

        while (cursor.moveToNext()){
             dbStruct =new DBStruct (
                     cursor.getInt(idIndex),
                     cursor.getString(titleIndex),
                     cursor.getString(dateIndex),
                     cursor.getString(contentIndex));
             list.add(dbStruct);
        }
        sqLiteDatabase.close();
        cursor.close();

        return list;
    }
    public List<String> list() {
        List<String> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        String s;
        int titleIndex = cursor.getColumnIndex(TITLE);
        int dateIndex = cursor.getColumnIndex(DATE);

        while (cursor.moveToNext()) {
            s=cursor.getString(dateIndex)+" "+cursor.getString(titleIndex);
            list.add(s);
        }
        sqLiteDatabase.close();
        return list;
    }
}
