package com.example.picturematch;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.picturematch.GridViewAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Gridview_Activity extends AppCompatActivity  {
    GridView gridView;
    TextView timetext;
    ImageView img;
    int level;
    ArrayList<String> imgArr=new ArrayList<>();
    List<String>arrylist = new ArrayList<>();
    ProgressBar progressBar;
    GridViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        gridView = findViewById(R.id.girdview);
        timetext = findViewById(R.id.timetext);
        progressBar=findViewById(R.id.progressBar);

        //assets mathi images put
        String[] images = new String[0];
        try {
            images = getAssets().list("LevelImages");
            imgArr = new ArrayList<String>(Arrays.asList(images));

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        arrylist = imgArr.subList(0,6);

        arrylist.addAll(arrylist);
        Collections.shuffle(arrylist);

        adapter = new GridViewAdapter(Gridview_Activity.this,arrylist,timetext,progressBar);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(3);

    }
}