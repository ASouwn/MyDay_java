package com.example.myday.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.myday.Manager.DBHelper;
import com.example.myday.DB.DBStruct;
import com.example.myday.R;


/**
 * 首页面
 */
public class MainActivity extends AppCompatActivity {
    ListView view_all;
    Button button,review;

//    DBHelper dbHelper=new DBHelper(MainActivity.this);
    DBHelper dbHelper= new DBHelper(MainActivity.this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        review=findViewById(R.id.main_review);
        view_all=findViewById(R.id.view_all);


//        ArrayAdapter<DBStruct> arrayAdapter=new ArrayAdapter<>(
//                MainActivity.this,
//                android.R.layout.simple_list_item_1,
//                dbHelper.show());
//        view_all.setAdapter(arrayAdapter);

        getlist_view();


        //add new function button
        button.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        review.setOnClickListener(v -> {
            getlist_view();
        });

        //function when attach the position
        view_all.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DBStruct dbStruct=(DBStruct) parent.getItemAtPosition(position);

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setPositiveButton("修改", (dialog, which) -> {
                    Intent intent=new Intent(MainActivity.this, rewrite.class);
                    intent.putExtra("id",dbStruct.getId());
                    intent.putExtra("content",dbStruct.getContent());
                    intent.putExtra("date",dbStruct.getDate());
                    intent.putExtra("title",dbStruct.getTitle());
                    startActivity(intent);
                });

                builder.setNegativeButton("删除", (dialog, which) -> {
                    dbHelper.delete(dbStruct);
                    getlist_view();
                });

                builder.create();
                builder.show();
            }
        });



    }

    private void getlist_view() {
        DBAdapter adapter=new DBAdapter(
                MainActivity.this,
                R.layout.list_view,
                dbHelper.show().toArray(new DBStruct[0])
        );
        view_all.setAdapter(adapter);
    }


}