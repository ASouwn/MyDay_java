package com.example.myday.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myday.Manager.DBHelper;
import com.example.myday.DB.DBStruct;
import com.example.myday.R;

public class rewrite extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewrite);

        Intent intent=getIntent();
        TextView title,date,content;
        Button _return,_finish;

        String title_get=intent.getStringExtra("title");
        String date_get=intent.getStringExtra("date");
        String content_get=intent.getStringExtra("content");
        Integer id=intent.getIntExtra("id",0);

        title=findViewById(R.id.rewrite_title);
        date=findViewById(R.id.rewrite_date);
        content=findViewById(R.id.rewrite_content);
        _return=findViewById(R.id.rewrite_return);
        _finish=findViewById(R.id.rewrite_finish);

        title.setText(title_get);
        date.setText(date_get);
        content.setText(content_get);


        //function return
        _return.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(rewrite.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //after finish add, return
        _finish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DBStruct dbStruct =new DBStruct(
                        id,
                        title.getText().toString(),
                        date.getText().toString(),
                        content.getText().toString()
                );
                DBHelper dbHelper=new DBHelper(rewrite.this);
                dbHelper.rewrite(dbStruct);
                //and return
                Intent intent=new Intent(rewrite.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }
}