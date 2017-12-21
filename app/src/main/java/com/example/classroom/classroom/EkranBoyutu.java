package com.example.classroom.classroom;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Fatih on 5.12.2017.
 */

public class EkranBoyutu {
    Display ekran;
    Point point;
    private int x=0,y=0;
    public EkranBoyutu(AppCompatActivity ctx){
        //ekran boyutu alma
        ekran=ctx.getWindowManager().getDefaultDisplay();
        point=new Point();
        ekran.getSize(point);
        x=point.x;
        y=point.y;
    }
    public int getGenislik(){
        return x;
    }
    public int getYukseklik(){
        return y;
    }
    public void ekrana_ayarla(View view, int width){
        ViewGroup.LayoutParams params=view.getLayoutParams();
        params.width=width;
        view.setLayoutParams(params);
    }
}
