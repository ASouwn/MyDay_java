package com.example.myday.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.example.myday.DB.DBStruct;
import com.example.myday.DB.dateBaseHelper;
import com.example.myday.R;


/**
 * 首页面
 */
public class MainActivity extends AppCompatActivity {
    ListView view_all;
    Button button;

//    DBHelper dbHelper=new DBHelper(MainActivity.this);
    dateBaseHelper dbHelper=new dateBaseHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
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


        //function when attach the position
        view_all.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DBStruct dbStruct=(DBStruct) parent.getItemAtPosition(position);

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                        intent.putExtra("id",dbStruct.getId());
                        intent.putExtra("content",dbStruct.getContent());
                        intent.putExtra("date",dbStruct.getDate());
                        intent.putExtra("title",dbStruct.getTitle());
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*dbHelper.delete(dbStruct);
                        viewall(dbHelper);*/
                    }
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