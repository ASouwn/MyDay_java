package com.example.myday.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myday.DB.DBHelper;
import com.example.myday.DB.DBStruct;
import com.example.myday.R;

public class MainActivity2 extends AppCompatActivity {

    TextView textView,textView2,textView3;
    Button button2,button3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button2=findViewById(R.id.button2);//finish and add
        button3=findViewById(R.id.button3);//return
        textView=findViewById(R.id.editTextText);//title
        textView2=findViewById(R.id.editTextText2);//date
        textView3=findViewById(R.id.editTextText3);//content


        //function return
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //after finish add, return
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DBStruct dbStruct =new DBStruct(
                        -1,
                        textView.getText().toString(),
                        textView2.getText().toString(),
                        textView3.getText().toString()
                );
                DBHelper dbHelper=new DBHelper(MainActivity2.this);
                dbHelper.add(dbStruct);
                //return
                Intent intent=new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}