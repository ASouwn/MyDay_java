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

import java.util.concurrent.atomic.AtomicInteger;

public class Author extends AppCompatActivity {


    ImageView AuthorHead;
    TextView First,Last,Birth,Self;
    Button renew,Return;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        AuthorListHelper authorListHelper=new AuthorListHelper(Author.this);
        AuthorStruct authorStruct=new AuthorStruct(
            authorListHelper.list().getID(),
            authorListHelper.list().getFIRST_NAME(),
            authorListHelper.list().getLAST_NAME(),
            authorListHelper.list().getBIRTH(),
            authorListHelper.list().getSELF()
        );


        AuthorHead=findViewById(R.id.author_head);
        First=findViewById(R.id.author_fistname);
        Last=findViewById(R.id.author_lastname);
        Birth=findViewById(R.id.author_birthday);
        Self=findViewById(R.id.SELF);
        renew=findViewById(R.id.Author_renew);
        Return=findViewById(R.id.author_return);


        First.setText(authorStruct.getFIRST_NAME());
        Last.setText(authorStruct.getLAST_NAME());
        Birth.setText(authorStruct.getBIRTH());
        Self.setText(authorStruct.getSELF());

        Integer id=authorStruct.getID();

        //touch the button renew and renew the information of the author
        renew.setOnClickListener(v -> {


            String firstName=First.getText().toString();
            String lastName=Last.getText().toString();
            String birth=Birth.getText().toString();
            String self=Self.getText().toString();

            AuthorStruct authorStruct1=new AuthorStruct(id,firstName,lastName,birth,self);

            First.setText(firstName);
            Last.setText(lastName);
            Birth.setText(birth);
            Self.setText(self);

            authorListHelper.renew(authorStruct1);

        });

        Return.setOnClickListener(v -> {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        });

    }
}