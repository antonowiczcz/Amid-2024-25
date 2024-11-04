package com.antonowicz.bitmapa;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView drawingView;
    private Bitmap bmp;
    private Canvas canvas;
    private Paint paint;
    private Random random;
    private TextView statusText;
    private boolean isCanvasClear = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawing_view);
        statusText = findViewById(R.id.status_text);

        paint = new Paint();
        random = new Random();


        Button buttonCreate = findViewById(R.id.button_create);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kliknieto_kreuj(v);
            }
        });

        Button buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCanvas();
                statusText.setText("Czyść");
                isCanvasClear = true; //
            }
        });

        Button buttonLines = findViewById(R.id.button_lines);
        buttonLines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCanvasClear) {
                    drawMultipleLines(50);
                    statusText.setText("Linie");
                    isCanvasClear = false;
                } else {
                    Toast.makeText(MainActivity.this, "Najpierw wyczyść obraz!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonEllipses = findViewById(R.id.button_ellipses);
        buttonEllipses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCanvasClear) {
                    drawMultipleEllipses(50);
                    statusText.setText("Elipsy");
                    isCanvasClear = false;
                } else {
                    Toast.makeText(MainActivity.this, "Najpierw wyczyść obraz!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button buttonRectangles = findViewById(R.id.button_rectangles);
        buttonRectangles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCanvasClear) {
                    drawMultipleRectangles(50);
                    statusText.setText("Prostokąty");
                    isCanvasClear = false;
                } else {
                    Toast.makeText(MainActivity.this, "Najpierw wyczyść obraz!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void kliknieto_kreuj(View v) {
        int szer = drawingView.getWidth();
        int wys = drawingView.getHeight();

        bmp = Bitmap.createBitmap(szer, wys, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bmp);

        drawingView.setImageBitmap(bmp);
        isCanvasClear = true;
    }

    private void drawMultipleLines(int count) {
        for (int i = 0; i < count; i++) {
            drawRandomLine();
        }
        drawingView.invalidate();
    }

    private void drawMultipleEllipses(int count) {
        for (int i = 0; i < count; i++) {
            drawRandomEllipse();
        }
        drawingView.invalidate();
    }

    private void drawMultipleRectangles(int count) {
        for (int i = 0; i < count; i++) {
            drawRandomRectangle();
        }
        drawingView.invalidate();
    }

    private void drawRandomLine() {
        int x1 = random.nextInt(bmp.getWidth());
        int y1 = random.nextInt(bmp.getHeight());
        int x2 = random.nextInt(bmp.getWidth());
        int y2 = random.nextInt(bmp.getHeight());

        paint.setColor(randomColor());
        paint.setStrokeWidth(5);
        canvas.drawLine(x1, y1, x2, y2, paint);
    }

    private void drawRandomEllipse() {
        int left = random.nextInt(bmp.getWidth());
        int top = random.nextInt(bmp.getHeight());
        int right = random.nextInt(bmp.getWidth());
        int bottom = random.nextInt(bmp.getHeight());

        paint.setColor(randomColor());
        canvas.drawOval(left, top, right, bottom, paint);
    }

    private void drawRandomRectangle() {
        int left = random.nextInt(bmp.getWidth());
        int top = random.nextInt(bmp.getHeight());
        int right = random.nextInt(bmp.getWidth());
        int bottom = random.nextInt(bmp.getHeight());

        paint.setColor(randomColor());
        canvas.drawRect(left, top, right, bottom, paint);
    }

    private void clearCanvas() {
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            drawingView.invalidate();
        }
    }

    private int randomColor() {
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
