package com.example.myday.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myday.DB.DBStruct;
import com.example.myday.R;

public class DBAdapter extends ArrayAdapter<DBStruct> {


    public DBAdapter(@NonNull Context context, int resource, @NonNull DBStruct[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DBStruct list_struct=getItem(position);

        View view= LayoutInflater.from(getContext()).inflate(R.layout.list_view,parent,false);

        TextView dateInList=view.findViewById(R.id.date_inlist);
        TextView titleInList=view.findViewById(R.id.title_inlist);

        dateInList.setText(list_struct.getDate());
        titleInList.setText(list_struct.getTitle());

        return view;

    }
}
