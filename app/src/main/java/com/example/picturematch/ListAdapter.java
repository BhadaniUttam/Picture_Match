package com.example.picturematch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    No_Time_Limit_Activity notimelimitactivity;
    TextView textView;
    String[] level;


    public ListAdapter(No_Time_Limit_Activity no_time_limit_activity, String[] level) {
        this.notimelimitactivity = no_time_limit_activity;
        this.level = level;
    }

    @Override
    public int getCount() {
        return level.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(notimelimitactivity).inflate(R.layout.activity_listview_item,viewGroup,false);
        textView=view.findViewById(R.id.listview_item);
        textView.setText(""+level[i]);
        return view;
    }
}