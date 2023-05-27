package com.example.myday.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myday.DB.AuthorStruct;
import com.example.myday.Manager.AuthorListHelper;
import com.example.myday.R;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Author extends AppCompatActivity {

//    AuthorListHelper authorListHelper=new AuthorListHelper(this);

    ImageView AuthorHead;
    TextView First,Last,Birth,Self;
    Button renew,Return;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);


        AuthorHead=findViewById(R.id.author_head);
        First=findViewById(R.id.author_fistname);
        Last=findViewById(R.id.author_lastname);
        Birth=findViewById(R.id.author_birthday);
        Self=findViewById(R.id.SELF);
        renew=findViewById(R.id.Author_renew);
        Return=findViewById(R.id.author_return);



        //touch the button renew and renew the information of the author
        renew.setOnClickListener(v -> {
//            String firstName=First.getText().toString();
//            String lastName=Last.getText().toString();
//            String birth=Birth.getText().toString();
//            String self=Self.getText().toString();


        });

        Return.setOnClickListener(v -> {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        });

    }
}