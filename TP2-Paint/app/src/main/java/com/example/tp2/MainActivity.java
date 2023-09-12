package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;



public class MainActivity extends AppCompatActivity {

    ConstraintLayout zoneDessin;

    SurfaceDessin surf;

    LinearLayout crayon;
    LinearLayout couleur;

    Ecouteursurf ecSurf = new Ecouteursurf();
    EcouteurBouton ecBouton  = new EcouteurBouton();

    Path p;

    Point depart,arrive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zoneDessin = findViewById(R.id.zoneDessin);
        crayon = findViewById(R.id.crayon);
        couleur = findViewById(R.id.couleur);

        surf =  new SurfaceDessin(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1,-1));
        surf.setBackgroundColor(ContextCompat.getColor(this,R.color.teal_200));
        zoneDessin.addView(surf);


        surf.setOnTouchListener(ecSurf);
        crayon.setOnClickListener(ecBouton);
        couleur.setOnClickListener(ecBouton);
        p = new Path();





    }



    private class Ecouteursurf implements View.OnTouchListener {
        @Override
        public boolean onTouch(View source, MotionEvent event) {
            float x = event.getX();
            float y = event.getY();


            if (event.getAction()  == MotionEvent.ACTION_DOWN){
//                depart = new Point((int) event.getX(), (int) event.getY());
                p.moveTo(x,y);
                source.invalidate();

            } else if(event.getAction() == MotionEvent.ACTION_MOVE){
                p.lineTo(x,y);
//                arrive = new Point((int) event.getX(), (int) event.getY());
                source.invalidate();

//            } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                depart = null;
//                arrive = null;
            }


            return true; //tres important
        }
    }

    private class EcouteurBouton implements View.OnClickListener {

        @Override
        public void onClick(View source) {

        }

    }


    private class SurfaceDessin extends View {
        Paint c;
        public SurfaceDessin(Context context) {
            super(context);
            c = new Paint(Paint.ANTI_ALIAS_FLAG);
            c.setStyle(Paint.Style.STROKE);

            c.setColor(Color.RED);
            c.setStrokeWidth(15);

        }


        protected void  onDraw(Canvas canvas){

            super.onDraw(canvas);
            canvas.drawPath(p, c);

//            if (depart != null){
//                canvas.drawRect(depart.x-30,depart.y-30,depart.x+50,depart.y+50,c);
//
//            }
//
//            if (arrive!=null){
//                canvas.drawRect(arrive.x-30,arrive.y-30,arrive.x-30+50,arrive.y-30+50,c);
//                canvas.drawLine(depart.x,depart.y,arrive.x,arrive.y,c);
//            }

        }

    }
}