package com.example.myday.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myday.DB.AuthorStruct;
import com.example.myday.DB.DBVersion_and_Name;

import java.util.ArrayList;
import java.util.List;

public class AuthorListHelper extends SQLiteOpenHelper {

    private static String NAME = "author.db";
    private static int VERSION =DBVersion_and_Name.getVersion();


    private static String ID="id";

    private static String FIRST_NAME="firstName";
    private static String LAST_NAME="lastName";
    private static String BIRTH="birth";
    private static String SELF="self";

    private static String TABLE_NAME="authorList";

    public AuthorListHelper(@Nullable Context context) {
        super(context, NAME, null , VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= "CREATE TABLE " +TABLE_NAME+" ("
                +ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                +FIRST_NAME+" TEXT, "
                +LAST_NAME+" TEXT, "
                +BIRTH+" TEXT, "
                +SELF+" TEXT)";
        String insert="INSERT INTO "+TABLE_NAME+" ("+ID+","+FIRST_NAME+","+LAST_NAME+","+BIRTH+","+SELF+") " +
                "VALUES ('0','tang','ASouwn','2002-12-25','A Happy Man')";
        db.execSQL(sql);
        db.execSQL(insert);//inset the information
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    private void add(){
//        ContentValues contentValues=new ContentValues();
//        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
//
//        contentValues.put(FIRST_NAME,"Tang");
//        contentValues.put(LAST_NAME,"ASouwn");
//        contentValues.put(BIRTH,"2002-12-25");
//        contentValues.put(SELF,"A Happy Man");
//
//        sqLiteDatabase.insert(TABLE_NAME,FIRST_NAME,contentValues);
//
//        sqLiteDatabase.close();
//    }

    public AuthorStruct list(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String sql="SELECT * FROM "+TABLE_NAME;
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        AuthorStruct authorStruct;
        List<AuthorStruct> list=new ArrayList<>();

        int IDIndex=cursor.getColumnIndex(ID);
        int FName=cursor.getColumnIndex(FIRST_NAME);
        int LName=cursor.getColumnIndex(LAST_NAME);
        int Birth=cursor.getColumnIndex(BIRTH);
        int Self=cursor.getColumnIndex(SELF);
        while (cursor.moveToNext()){
            authorStruct=new AuthorStruct(
                    cursor.getInt(IDIndex),
                    cursor.getString(FName),
                    cursor.getString(LName),
                    cursor.getString(Birth),
                    cursor.getString(Self));
            list.add(authorStruct);
        }

        sqLiteDatabase.close();
        cursor.close();
        return list.get(0);
    }


    public String renew(AuthorStruct authorStruct){
        ContentValues contentValues=new ContentValues();
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();

        contentValues.put(FIRST_NAME,authorStruct.getFIRST_NAME());
        contentValues.put(LAST_NAME,authorStruct.getLAST_NAME());
        contentValues.put(BIRTH,authorStruct.getBIRTH());
        contentValues.put(SELF,authorStruct.getSELF());

        sqLiteDatabase.update(TABLE_NAME,contentValues,ID+"=?",new String[]{String.valueOf(authorStruct.getID())});

        sqLiteDatabase.close();
        return "success";
    }

}
