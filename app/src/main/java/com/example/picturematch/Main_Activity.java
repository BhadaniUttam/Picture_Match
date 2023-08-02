package com.example.picturematch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView textView1,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.notimelimittextview);
        textView1.setOnClickListener(this);

        textView2 = findViewById(R.id.normaltextview);
        textView2.setOnClickListener(this);

        textView3 = findViewById(R.id.hardtextview);
        textView3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId()==textView1.getId()){
            Intent intent = new Intent(Main_Activity.this, No_Time_Limit_Activity.class);
            startActivity(intent);
        }

    }
}