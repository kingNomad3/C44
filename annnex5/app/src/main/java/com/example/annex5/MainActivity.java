package com.example.annex5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    SurfaceDessin surf;
    ConstraintLayout parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent = findViewById(R.id.parent);


        // creation de la surface de dessin
        surf = new SurfaceDessin(this); // context = activity  = this
        // donner un taille a la surface de dessin
//        surf.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)); // ou ecire -1 a la place de mathc parent
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1, -1)); // ou ecire -1 a la place de mathc parent
        surf.setBackgroundColor(Color.MAGENTA);
//        surf.setLayoutParams(new ConstraintLayout.LayoutParams(dpToPx(200), dpToPx(200))); //valeurs en pixels
        //quoi faire si on veut 200dp par 200dp pour qu le carre aie a peu pres la meme dimention physique  peut importe le telephone
        //ajouter la surface de dessin au constraintLayout
        parent.addView(surf);


    }

    //methode pour transformer des dp en pixel equivalent
    public int dpToPx(int dp) {

        float density = this.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }



    private  class SurfaceDessin extends View {

        Paint crayonVert;
        Paint crayonJaune;
        Paint crayonTarte;

        public SurfaceDessin(Context context) {
            super(context);
            crayonVert = new Paint(Paint.ANTI_ALIAS_FLAG); // rend les comptour smooth par exemple lorsqu'on fait un cercle etatnt donne que les pixels sont des carres
            crayonJaune = new Paint(Paint.ANTI_ALIAS_FLAG);
            crayonTarte = new Paint(Paint.ANTI_ALIAS_FLAG);

        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            crayonVert.setColor(Color.GREEN);
            canvas.drawCircle(125,125,50,crayonVert);
//            invalided() est une methode qui efface et rappel le onDraw

            crayonJaune.setColor(Color.YELLOW);
            crayonJaune.setStyle(Paint.Style.STROKE); //vide le cercle
            crayonJaune.setStrokeWidth(10); // permet d;aggrosir les comptour du cercle
            canvas.drawCircle(230,125,50, crayonJaune);

            //trois facon de le faire
            crayonTarte.setColor(Color.GREEN);
            canvas.drawArc(300,60,400,150,0,120,true,crayonTarte);

            crayonTarte.setColor(ContextCompat.getColor(MainActivity.this, R.color.blue)); // leblue provient de colors.xml
            canvas.drawArc(300,30,400,160,120,120,true,crayonTarte);

            crayonTarte.setColor(Color.rgb(223,67,200));
            canvas.drawArc(300,30,400,160,240,120,true,crayonTarte);


        }
    }

}