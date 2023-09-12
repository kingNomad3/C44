package com.example.annex1tp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout zoneDessin;

    SurfaceDessin surf;
    EditText variabley;
    EditText variablex;
    
    Button Envoyez;

    Ecouteur ec = new Ecouteur();

    int x;
    int y;

    Path p;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        zoneDessin = findViewById(R.id.zoneDessin);
        variabley = findViewById(R.id.variabley);
        variablex = findViewById(R.id.variablex);
        Envoyez = findViewById(R.id.button);

        surf = new SurfaceDessin(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1,-1));
        surf.setBackgroundColor(ContextCompat.getColor(this,R.color.teal_200));
        zoneDessin.addView(surf);
        

        
        Envoyez.setOnClickListener(ec);
        p = new Path();

    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View source) {
            x = Integer.parseInt(variablex.getText().toString());
            y = Integer.parseInt(variabley.getText().toString());

            if (p.isEmpty()){
                p.moveTo(x,y);
            }else{
                p.lineTo(x,y);
                surf.invalidate(); // on veut invalited la surface
            }
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

        }

    }


}