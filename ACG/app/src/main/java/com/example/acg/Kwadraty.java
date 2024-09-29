package com.example.acg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


import androidx.annotation.RequiresApi;

import java.util.Random;

public class Kwadraty extends View {


    Random generator =new Random();

    int r=0 ;

    int kolory_1=0,kolory_2=0,kolory_3=0;

    public Kwadraty(Context context) {
        super(context);
    }

    public Kwadraty(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Kwadraty(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void randowome(){
        r = generator.nextInt(200);
        kolory_1 = generator.nextInt(256);
        kolory_2 = generator.nextInt(256);
        kolory_3 = generator.nextInt(256);

    }

    @Override
    public void onDraw(Canvas canvas) {

        int szer = getWidth(),wys = getHeight();
        randowome();

        @SuppressLint("DrawAllocation") Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setARGB(255,kolory_1,kolory_2,kolory_3);

        for (int i=0;i<10;i++){
            randowome();
            p.setARGB(255,kolory_1,kolory_2,kolory_3);
            int l=generator.nextInt(szer)+r,k=generator.nextInt(wys)+r,l_2=generator.nextInt(szer)+r,k_2=generator.nextInt(wys)+r;
            canvas.drawRect(l,k,l_2,k_2,p);
        }




        super.onDraw(canvas);
    }


}
