 package com.example.revision;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Stack;


 public class MainActivity extends AppCompatActivity {

     ConstraintLayout surfaceDessin;
     SurfaceDessin surf;

     SeekBar seekBar1;
     SeekBar seekbar2;
     Stack<Point> listeTrait = new Stack<>();
     private Path path;

     EcouteurSeekBar es = new EcouteurSeekBar();

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         seekBar1 = findViewById(R.id.seekBar1);
         seekbar2 = findViewById(R.id.seekBar2);
         surfaceDessin = findViewById(R.id.surfaceDessin);

         surf = new SurfaceDessin(this);
         surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1)); // Fill parent
         surf.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200));

         surfaceDessin.addView(surf);

         // Initialisez le chemin avec un point de départ
         path = new Path();
         path.moveTo(100, 100); // Définissez le point de départ à (100, 100)

         // Définissez l'écouteur EcouteurSeekBar sur les SeekBars
         seekBar1.setOnSeekBarChangeListener(es);
         seekbar2.setOnSeekBarChangeListener(es);

         // Ajoutez le point initial à la pile
         listeTrait.add(new Point(0, 0));
     }

     private class EcouteurSeekBar implements SeekBar.OnSeekBarChangeListener {

         @Override
         public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
             // Vous pouvez gérer les changements de progression ici si nécessaire.
         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {
             // Gérez le début du suivi si nécessaire.
         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {
             Point pointTemp = listeTrait.lastElement();
             int tempx = pointTemp.x;
             int tempy = pointTemp.y;

             if (seekBar == seekBar1) {
                 tempx += seekBar1.getProgress();
                 // Réinitialisez la progression à 0
                 seekBar1.setProgress(0);
             } else if (seekBar == seekbar2) {
                 tempy += seekbar2.getProgress();
                 // Réinitialisez la progression à 0
                 seekbar2.setProgress(0);
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
