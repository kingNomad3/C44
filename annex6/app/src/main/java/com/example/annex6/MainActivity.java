package com.example.annex6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SurfaceDessin surf;
    ConstraintLayout parent;
    Ecouteur ec = new Ecouteur();

    float positionX ;
    float positionY ;


    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surf = new SurfaceDessin(this);
        parent = findViewById(R.id.parent);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1));
        surf.setBackgroundResource(R.drawable.carte);

        parent.addView(surf);
        surf.setOnTouchListener(ec);
    }

    private class Ecouteur implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction()  == MotionEvent.ACTION_DOWN){
                positionX = event.getX();
                positionY = event.getY();
                v.invalidate();

            } else if(event.getAction() == MotionEvent.ACTION_UP){
                v.invalidate();

            }



            return true;
        }
    }


    private  class SurfaceDessin extends View {

        Paint arrive;
        Paint depart;
        Paint chemin;


        public SurfaceDessin(Context context) {
            super(context);

            arrive = new Paint(Paint.ANTI_ALIAS_FLAG);
            depart = new Paint(Paint.ANTI_ALIAS_FLAG);
            chemin = new Paint(Paint.ANTI_ALIAS_FLAG);


        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            depart.setColor(Color.RED);
            if (depart != null){
                canvas.drawRect(positionX,positionY,positionX+50,positionY+50,depart);

            }

            if (arrive!=null){
                canvas.drawRect(positionX,positionY,positionX+50,positionY+50,depart);
                canvas.drawLine(positionX,positionY,positionX,positionY,depart);
                }

        }


    }


}