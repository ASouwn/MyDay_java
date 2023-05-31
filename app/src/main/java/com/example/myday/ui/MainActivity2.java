package com.example.myday.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.services.auto.ListData;
import com.example.myday.Manager.DBHelper;
import com.example.myday.DB.DBStruct;
import com.example.myday.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    TextView textView,textView2,textView3,weather;
    Button button2,button3;
    Date date=new Date();
    String WeatherGet;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    String time=simpleDateFormat.format(date);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent_mainActivity=getIntent();

        button2=findViewById(R.id.rewrite_finish);//finish and add
        button3=findViewById(R.id.rewrite_return);//return
        textView=findViewById(R.id.rewrite_title);//title
        textView2=findViewById(R.id.rewrite_date);//date
        textView3=findViewById(R.id.rewrite_content);//content
        weather=findViewById(R.id.rewrite_weather);//weather

        textView2.setText(time);

        WeatherGet=intent_mainActivity.getStringExtra("weather");
        weather.setText(WeatherGet);

        //function return
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //after finish add, return
        button2.setOnClickListener(v -> {
            DBStruct dbStruct =new DBStruct(
                    -1,
                    textView.getText().toString(),
                    time,
                    textView3.getText().toString(),
                    WeatherGet
            );
            DBHelper dbHelper=new DBHelper(MainActivity2.this);
            dbHelper.add(dbStruct);
            //and return
            Intent intent=new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        });

    }
}