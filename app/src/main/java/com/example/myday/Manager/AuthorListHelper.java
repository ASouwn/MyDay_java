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

    private static String NAME = DBVersion_and_Name.getNAME();
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
        String sql= "CREATE TABLE "+TABLE_NAME+" (" +ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +FIRST_NAME+" TEXT, " +LAST_NAME+" TEXT, " +BIRTH+" TEXT, " +SELF+" TEXT)";
        String Insert= "INSERT INTO "+TABLE_NAME+"("+ID+","+ FIRST_NAME+ ","+LAST_NAME+","+BIRTH+","+SELF+") "+"VALUES('Tang','ASouwn','2002-12-25','A_Happy_Man')";
        db.execSQL(sql);
        db.execSQL(Insert);//inset the information
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public AuthorStruct list(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String sql="SELECT * FROM "+TABLE_NAME;
        Cursor cursor= sqLiteDatabase.rawQuery(sql,null);
        AuthorStruct authorStruct;

        int IDIndex=cursor.getColumnIndex(ID);
        int FName=cursor.getColumnIndex(FIRST_NAME);
        int LName=cursor.getColumnIndex(LAST_NAME);
        int Birth=cursor.getColumnIndex(BIRTH);
        int Self=cursor.getColumnIndex(SELF);

        cursor.moveToFirst();
        authorStruct=new AuthorStruct(
                cursor.getInt(IDIndex),
                cursor.getString(FName),
                cursor.getString(LName),
                cursor.getString(Birth),
                cursor.getString(Self));


        sqLiteDatabase.close();
        cursor.close();
        return authorStruct;
    }


    public String renew(AuthorStruct authorStruct){



        return "success";
    }

}
