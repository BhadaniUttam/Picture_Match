package com.example.picturematch;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;



import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class GridViewAdapter extends BaseAdapter {
    Gridview_Activity gridview_activity;
    List<String> arrayList = new ArrayList<>();
    TextView timetext;
    ProgressBar progressBar;
    int pos1,pos2,click=1;
    View mask2;
    Runnable runnable;
    int count=1;


    public GridViewAdapter(Gridview_Activity gridview_activity, List<String> arrayList, TextView timetext, ProgressBar progressBar) {
        this.gridview_activity = gridview_activity;
        this.arrayList = arrayList;
        this.timetext = timetext;
        this.progressBar=progressBar;

    }

    @Override
    public int getCount() {
        return arrayList.size();
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


        view = LayoutInflater.from(gridview_activity).inflate(R.layout.gridview_item,viewGroup,false);
        ImageView imageView = view.findViewById(R.id.girdviewitem);
        View mask1=view.findViewById(R.id.mask);
        RelativeLayout relativeLayout=view.findViewById(R.id.relative);
        InputStream stream = null;
        try {
            stream = gridview_activity.getAssets().open("LevelImages/" + arrayList.get(i));
            Drawable drawable = Drawable.createFromStream(stream, null);
            imageView.setImageDrawable(drawable);
        }
        catch (Exception ignored) {}
        finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (Exception ignored) {
            }
        }
        startTimer(mask1,relativeLayout,i);
        return view;
    }

    private void startTimer(View mask1, RelativeLayout relativeLayout, int i)
    {
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                progressBar.setMax(5);
                int time= (int) (l/1000);
                timetext.setText(""+time+"/0");
                progressBar.setProgress(time);
                Log.d("TTT", "onTick: time="+l);
            }

            @Override
            public void onFinish() {
                playGame(mask1,relativeLayout,i);
                new CountDownTimer(20000, 1000) {
                    @Override
                    public void onTick(long l) {
                        progressBar.setMax(20);
                        int time= (int) (l/1000);
                        timetext.setText(""+(progressBar.getMax()-time)+"/0");
                        progressBar.setProgress(progressBar.getMax()-time);
                        Log.d("TTT", "onTick: time="+l);
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }
        }.start();
    }

    private void playGame(View mask1, RelativeLayout relativeLayout, int position)
    {
        mask1.setVisibility(View.VISIBLE);
        Handler handler=new Handler();
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click==1)
                {
                    mask1.setVisibility(View.INVISIBLE);
                    mask2=mask1;
                    pos1=position;
                    click=3;

                    runnable=new Runnable() {
                        @Override
                        public void run() {
                            click=2;
                        }
                    };
                    handler.postDelayed(runnable,100);
                }
                if(click==2)
                {
                    mask1.setVisibility(View.INVISIBLE);
                    pos2=position;
                    click=3;
                    if(arrayList.get(pos1).equals(arrayList.get(pos2)))
                    {
                        if (count==6)
                        {
                            relativeLayout.setEnabled(false);
                            MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(gridview_activity);
                            builder.setTitle("WINNER ");
                            builder.setMessage("     YOU ARE WIN      ");


                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });builder.show();
                        }
                        count++;
                        runnable =new Runnable() {
                            @Override
                            public void run() {
                                click=1;
                            }
                        };
                        handler.postDelayed(runnable,500);
                    }
                    else {
                        click=3;

                        runnable =new Runnable() {
                            @Override
                            public void run() {
                                mask1.setVisibility(View.VISIBLE);
                                mask2.setVisibility(View.VISIBLE);
                                click=1;
                            }
                        };
                        handler.postDelayed(runnable,500);
                    }

                }

            }
        });
    }
}