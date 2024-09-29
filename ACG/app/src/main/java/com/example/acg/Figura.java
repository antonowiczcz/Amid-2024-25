package com.example.acg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;

public class Figura extends View {
    public Figura(Context context) {
        super(context);
    }

    public Figura(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Figura(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        int szer = getWidth();
        int szer_2 = szer/2;
        int wys = getHeight();
        int rozmiar =(Math.min(szer_2, wys))-10;
        int x,y,dx,dy;
        @SuppressLint("DrawAllocation") Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);
        @SuppressLint("DrawAllocation") Random r = new Random();

        p.setColor(Color.GRAY);
        canvas.drawRect(0,0,szer-1,wys-1,p);

        CharSequence opis = getContentDescription();
        if (opis==null){
            opis="brak";
        }
        for (int i =0;i<10;i++){
            p.setARGB(255,r.nextInt(256),r.nextInt(256),r.nextInt(256));
            if (opis.equals("kolo")){
                dx = r.nextInt(rozmiar);
                x= r.nextInt(szer_2-dx);
                y =r.nextInt(wys-dx);
                canvas.drawCircle(x,y,dx,p);
            } else if (opis.equals("elipsa")) {
                dx = r.nextInt(rozmiar);
                dy = r.nextInt(rozmiar);
                x =r.nextInt(szer_2-dx);
                y = r.nextInt(wys-dy);
                RectF rectF;
                rectF = new RectF(x,y,x+dx,y+dy);
                canvas.drawOval(rectF,p);

            } else if (opis.equals("prostokat")) {
                dx = r.nextInt(rozmiar);
                dy = r.nextInt(rozmiar);
                x=r.nextInt(szer_2-dx);
                y= r.nextInt(wys-dy);
                RectF rectF = new RectF(x,y,x+dx,y+dy);
                canvas.drawRect(rectF,p);

            } else if (opis.equals("luk")) {
                dx = r.nextInt(rozmiar);
                dy = r.nextInt(rozmiar);
                x=r.nextInt(szer_2-dx);
                y= r.nextInt(wys-dy);
                RectF rectF = new RectF(x,y,x+dx,y+dy);
                canvas.drawArc(rectF,r.nextInt(360),r.nextInt(360),false,p);
            } else if (opis.equals("linia")) {
                dx=r.nextInt(szer_2);
                dy=r.nextInt(wys);
                x=r.nextInt(szer_2);
                y=r.nextInt(wys);
                canvas.drawLine(x,y,dx,dy,p);
            }
        }
        p.setTextSize(40);
        p.setTextAlign(Paint.Align.RIGHT);
        p.setColor(Color.BLUE);
        canvas.drawText((String) opis,szer-20, (float) wys /2,p);


        super.onDraw(canvas);
    }
}
