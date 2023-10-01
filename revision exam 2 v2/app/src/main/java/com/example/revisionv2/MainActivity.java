package com.example.revisionv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout surfaceDessin;
    SurfaceDessin surf;

    Button horizontalG;
    Button horizontalD;
    Button verticalH;
    Button verticalB;

    Stack<Point> listeTrait = new Stack<>();
    private Path path;

    EcouteurButton eb = new EcouteurButton();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        horizontalG = findViewById(R.id.horizontalG);
        horizontalD = findViewById(R.id.horizontalD);
        verticalH = findViewById(R.id.verticalH);
        verticalB = findViewById(R.id.verticalB);
        surfaceDessin = findViewById(R.id.surfaceDessin);

        surf = new SurfaceDessin(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1)); // Fill parent
        surf.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200));

        surfaceDessin.addView(surf);

        // Initialisez le chemin avec un point de départ
        path = new Path();
        path.moveTo(100, 100); // Définissez le point de départ à (100, 100)

        // Définissez l'écouteur EcouteurSeekBar sur les SeekBars
        horizontalG.setOnClickListener(eb);
        horizontalD.setOnClickListener(eb);
        verticalH.setOnClickListener(eb);
        verticalB.setOnClickListener(eb);


        // Ajoutez le point initial à la pile
        listeTrait.add(new Point(100, 100));
    }

    private class EcouteurButton implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            Point pointTemp = listeTrait.lastElement();
            int tempx = pointTemp.x;
            int tempy = pointTemp.y;


            if (v == horizontalG) {
                tempx -= 10;
            } else if (v == horizontalD) {
                tempx += 10;
            } else if (v == verticalH) {
                tempy -= 10;
            } else if (v == verticalB) {
                tempy += 10;
            }

            listeTrait.add(new Point(tempx, tempy));
            path.lineTo(tempx, tempy); // Utilisez lineTo pour créer un chemin continu
            surf.invalidate();
        }
    }

    private class SurfaceDessin extends View {

        private Paint paint;

        public SurfaceDessin(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10); // Ajustez la largeur de la ligne selon vos besoins
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawPath(path, paint);
        }
    }
}
