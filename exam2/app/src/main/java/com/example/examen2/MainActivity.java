package com.example.examen2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout surfaceDessin;
    SurfaceDessin surf;
    Button buttonVert;
    Button buttonRouge;
    Button buttoRecommencer;
    SeekBar largeurTraitSeekbar;
    EditText positionX;
    EditText positionY;
    Spinner formeSpinner;
    float strokeSize;
    int currentColour;

    EcouteurSeekbar ecSeekbar = new EcouteurSeekbar();
    EcouteurButton ecButton = new EcouteurButton();
    Hashtable<String, com.eric.ex2formes.Polygone> formePoly;
    Vector<String> listeForme;
    com.eric.ex2formes.Polygone Triangle, Carre, Pentagone;


    Paint paint;
    Path p;

    int  x;
    int  y ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        surfaceDessin = findViewById(R.id.surfaceDessin);
        buttonVert = findViewById(R.id.buttonVert);
        buttonRouge = findViewById(R.id.buttonRouge);
        buttoRecommencer = findViewById(R.id.recommencer);
        largeurTraitSeekbar = findViewById(R.id.largeurTraitSeekbar);
        positionX = findViewById(R.id.positionX);
        positionY = findViewById(R.id.positionY);
        formeSpinner = findViewById(R.id.forme);

        // Initialisez le chemin avec un point de départ

        surf = new SurfaceDessin(this);
        surf.setLayoutParams(new ConstraintLayout.LayoutParams(-1,-1));
        surfaceDessin.addView(surf);

        largeurTraitSeekbar.setOnSeekBarChangeListener(ecSeekbar);
        buttonRouge.setOnClickListener(ecButton);
        buttonVert.setOnClickListener(ecButton);
        buttoRecommencer.setOnClickListener(ecButton);



        formePoly = new Hashtable<>();
//        formePoly.put("Triangle", new Triangle(currentColour,(int) strokeSize,new Point(x,y),3));
//        formePoly.put("Caree", new Carre(currentColour,(int) strokeSize,new Point(x,y), Carre.getNbSommets()));
//        formePoly.put("Pentagone", new Pentagone(currentColour,(int) strokeSize, new Point(x,y),Pentagone.getNbSommets()));

        listeForme = new Vector<String>();
        listeForme.add("Choisir une forme");
        listeForme.add("Triangle");
        listeForme.add("Caree");
        listeForme.add("Pentagone");



        Set<String> ensembleCles = formePoly.keySet();
        listeForme.addAll(ensembleCles);

        ArrayAdapter adapteur = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,listeForme);
        formeSpinner.setAdapter(adapteur);

    }

    private class EcouteurSeekbar implements  SeekBar.OnSeekBarChangeListener{


        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
//            largeurTraitSeekbar.setProgress(12);
//            seekBar.setProgress((int) strokeSize);
//            strokeSize = largeurTraitSeekbar.getProgress();
        }
    }

    private class EcouteurButton implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            x =  Integer.parseInt(positionX.getText().toString());
            y = Integer.parseInt(positionY.getText().toString());


            if (v == buttonRouge || v == buttonVert) {
                Button butonCouleur = (Button) v;
                Drawable couleurId = butonCouleur.getBackground();
                ColorDrawable temp = (ColorDrawable) couleurId;
                currentColour = temp.getColor();
            }else if (v == buttoRecommencer){

            }
        }

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            try{
                ///item choisir pentagone

            }catch (Exception e){

            }finally {

                AlertDialog.Builder builder = new
                        AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Vous n'avez pas appris a faire un pantagone")
                        .setTitle("Erreur Pantagone");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

    }


    class SurfaceDessin extends View {
        Paint crayonVert;
        public SurfaceDessin(Context context) {
            super(context);
//            paint.setStrokeWidth();
            crayonVert = new Paint(Paint.ANTI_ALIAS_FLAG);

        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            crayonVert.setColor(Color.GREEN);
            canvas.drawCircle(125,125,50,crayonVert);



        }




    }
}