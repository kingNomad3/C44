package com.example.annex6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SurfaceDessin surf;
    ConstraintLayout parent;
    Ecouteur ec = new Ecouteur();

    Point depart,arrive;


    @SuppressLint({"ResourceType", "MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        parent = findViewById(R.id.parent);
        surf = new SurfaceDessin(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));
        surf.setBackgroundResource(R.drawable.carte);

        parent.addView(surf);
        surf.setOnTouchListener(ec);
    }

    private class Ecouteur implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction()  == MotionEvent.ACTION_DOWN){
                depart = new Point((int) event.getX(), (int) event.getY());

                v.invalidate();

            } else if(event.getAction() == MotionEvent.ACTION_MOVE){

               arrive = new Point((int) event.getX(), (int) event.getY());
                v.invalidate();

            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                depart = null;
                arrive = null;
            }


            return true; //tres important
        }
    }


    private  class SurfaceDessin extends View {

        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        public SurfaceDessin(Context context) {
            super(context);

            p.setColor(Color.RED);
            p.setStrokeWidth(15);


        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            if (depart != null){
                canvas.drawRect(depart.x-30,depart.y-30,depart.x+50,depart.y+50,p);

            }

            if (arrive!=null){
                canvas.drawRect(arrive.x-30,arrive.y-30,arrive.x-30+50,arrive.y-30+50,p);
                canvas.drawLine(depart.x,depart.y,arrive.x,arrive.y,p);
                }

        }


    }


}