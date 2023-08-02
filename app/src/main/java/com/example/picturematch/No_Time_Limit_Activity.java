package com.example.picturematch;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class No_Time_Limit_Activity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    ListView listView;
    ListAdapter adapter;


    static String level[]={"LEVEL 1","LEVEL 2","LEVEL 3","LEVEL 4","LEVEL 5","LEVEL 6","LEVEL 7","LEVEL 8","LEVEL 9","LEVEL 10"};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_time_limit);

        listView=findViewById(R.id.listview);
        adapter = new ListAdapter(No_Time_Limit_Activity.this,level);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(No_Time_Limit_Activity.this, Gridview_Activity.class);
                intent.putExtra("i",i);
                startActivity(intent);
            }
        });


        imageView = findViewById(R.id.backimageview);
        imageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==imageView.getId()){
            Intent intent= new Intent(No_Time_Limit_Activity.this,Main_Activity.class);
            startActivity(intent);
        }
    }
}